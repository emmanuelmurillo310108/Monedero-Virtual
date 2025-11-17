package edu.co.uniquindio.poo.monedero.model;

import java.util.ArrayList;
import java.util.List;

public class Monedero {
    private int idMonedero;
    private String nombre;
    private double saldo;
    private List<Transaccion> historial;
    private Cliente cliente;

    public Monedero(int id, String nombre, Cliente cliente) {
        this.idMonedero = id;
        this.nombre = nombre;
        this.saldo = 0.0;
        this.historial = new ArrayList<>();
        this.cliente = cliente;
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
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Transaccion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Transaccion> historial) {
        this.historial = historial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void registrarTransaccion(Transaccion t) {
        historial.add(t);
    }
}

