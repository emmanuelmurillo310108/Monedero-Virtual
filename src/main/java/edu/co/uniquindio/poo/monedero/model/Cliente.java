package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.controller.BancoController;
import edu.co.uniquindio.poo.monedero.exceptions.CampoVacioException;
import edu.co.uniquindio.poo.monedero.exceptions.EmailInvalidoException;
import edu.co.uniquindio.poo.monedero.exceptions.TelefonoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;
    private String telefono;
    private List<Monedero> monederos;
    private int puntosAcumulados;
    private Rango rango;
    private List<Beneficio> beneficiosCanjeados;
    private Banco banco;

    public Cliente(int id, String nombre, String email, String telefono) {
        validarNombre(nombre);
        validarEmail(email);
        validarTelefono(telefono);

        this.idCliente = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;

        this.monederos = new ArrayList<>();
        this.beneficiosCanjeados = new ArrayList<>();
        this.puntosAcumulados = 0;
        this.rango = new Rango("Bronce", 0, 500, 1);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        validarTelefono(telefono);
        this.telefono = telefono;
    }

    public List<Monedero> getMonederos() {
        return monederos;
    }

    public void setMonederos(List<Monedero> monederos) {
        this.monederos = monederos;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public Rango getRango() {
        return rango;
    }

    public void setRango(Rango rango) {
        this.rango = rango;
    }

    public List<Beneficio> getBeneficiosCanjeados() {
        return beneficiosCanjeados;
    }

    public void setBeneficiosCanjeados(List<Beneficio> beneficiosCanjeados) {
        this.beneficiosCanjeados = beneficiosCanjeados;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new CampoVacioException("El nombre no puede estar vacío");
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new CampoVacioException("El email no puede estar vacío");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new EmailInvalidoException("El formato del email es inválido");
        }
    }

    private void validarTelefono(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            throw new CampoVacioException("El teléfono no puede estar vacío");
        }
        if (!telefono.matches("\\d{7,12}")) {
            throw new TelefonoInvalidoException("El teléfono debe ser numérico y de 7 a 12 dígitos");
        }
    }

    public void realizarTransaccion(Transaccion t){
        t.ejecutar();
        puntosAcumulados += t.calcularPuntos();
        actualizarRango();
    }

    public void actualizarPuntos(Transaccion t) {
        int puntosBase = t.calcularPuntos();
        double multiplicador = rango.getMultiplicadorPuntos();
        int puntosFinal = (int) Math.round(puntosBase * multiplicador);

        this.puntosAcumulados += puntosFinal;
        actualizarRango();
    }

    public void canjearPuntos(Beneficio b) {
        if (puntosAcumulados >= b.getCostoPuntos()) {
            puntosAcumulados -= b.getCostoPuntos();
            b.aplicar(this);
            beneficiosCanjeados.add(b);
        }
    }

    public double consultarSaldo(Monedero monedero) {
        if (monederos.contains(monedero)) {
            return monedero.getSaldo();
        }
        System.out.println("El monedero no pertenece al cliente");
        return 0.0;
    }

    public void consultarHistorial(Monedero monedero) {
        if (monederos.contains(monedero)) {
            System.out.println("Historial de transacciones para " + monedero.getNombre() + ":");
            for (Transaccion t : monedero.getHistorial()) {
                System.out.println("- " + t.getTipo() + " | Monto: " + t.getMonto());
            }
        } else {
            System.out.println("El monedero no pertenece al cliente");
        }
    }

    private void actualizarRango() {
        if (puntosAcumulados <= 500) {
            rango = new Rango("Bronce", 0, 500, 1.0);
        }
        else if (puntosAcumulados <= 1000) {
            rango = new Rango("Plata", 501, 1000, 1.1);
        }
        else if (puntosAcumulados <= 5000) {
            rango = new Rango("Oro", 1001, 5000, 1.3);
        }
        else {
            rango = new Rango("Platino", 5001, Integer.MAX_VALUE, 1.5);
        }
    }

    public void agregarMonedero(Monedero monedero) {
        monederos.add(monedero);
    }

    @Override
    public String toString() {
        return nombre;
    }

}

