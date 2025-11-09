package edu.co.uniquindio.poo.monedero.model;

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

    public Cliente(int id, String nombre, String email, String telefono) {
        this.idCliente = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.monederos = new ArrayList<>();
        this.beneficiosCanjeados = new ArrayList<>();
        this.puntosAcumulados = 0;
        this.rango = new Rango("Bronce", 0, 500);
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
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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

    public void realizarTransaccion(Transaccion t) {
        t.ejecutar();
        actualizarPuntos(t);
    }

    public void actualizarPuntos(Transaccion t) {
        this.puntosAcumulados += t.calcularPuntos();
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
        if (puntosAcumulados <= 500)
            rango = new Rango("Bronce", 0, 500);
        else if (puntosAcumulados <= 1000)
            rango = new Rango("Plata", 501, 1000);
        else if (puntosAcumulados <= 5000)
            rango = new Rango("Oro", 1001, 5000);
        else
            rango = new Rango("Platino", 5001, Integer.MAX_VALUE);
    }
}

