package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.exceptions.CampoVacioException;
import edu.co.uniquindio.poo.monedero.exceptions.EntidadNoEncontradaException;
import edu.co.uniquindio.poo.monedero.exceptions.MontoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Monedero {
    private int idMonedero;
    private String nombre;
    private double saldo;
    private List<Transaccion> historial;
    private Cliente dueño;
    private Banco banco;

    public Monedero(int id, String nombre, Cliente dueño) {
        validarNombre(nombre);
        validarDueño(dueño);

        this.idMonedero = id;
        this.nombre = nombre;
        this.saldo = 0.0;
        this.historial = new ArrayList<>();
        this.dueño = dueño;
    }

    public int getIdMonedero() {
        return idMonedero;
    }

    public void setIdMonedero(int idMonedero) {
        this.idMonedero = idMonedero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new MontoInvalidoException("El saldo no puede ser negativo.");
        }
        this.saldo = saldo;
    }

    public List<Transaccion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Transaccion> historial) {
        this.historial = historial;
    }

    public Cliente getDueño() {
        return dueño;
    }

    public void setDueño(Cliente dueño) {
        this.dueño = dueño;
    }

    public Banco getBanco() {
        return banco;
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new CampoVacioException("El nombre del monedero no puede estar vacío.");
        }
    }

    private void validarDueño(Cliente dueño) {
        if (dueño == null) {
            throw new EntidadNoEncontradaException("El monedero debe tener un dueño.");
        }
    }

    public void registrarTransaccion(Transaccion t) {
        historial.add(t);
    }

    @Override
    public String toString() {
        return nombre;
    }
}

