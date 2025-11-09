package edu.co.uniquindio.poo.monedero.model;

import java.util.Date;
import edu.co.uniquindio.poo.monedero.model.interfaces.ITransaccion;

public abstract class Transaccion implements ITransaccion {
    protected int idTransaccion;
    protected double monto;
    protected String tipo;
    protected Date fechaCreacion;
    protected boolean programada;
    protected Date fechaEjecucion;
    protected String frecuencia;
    protected Cliente clienteOrigen;
    protected Cliente clienteDestino;
    protected Monedero monedero;

    public void programarTransaccion(Date fecha, String frecuencia) {
        this.programada = true;
        this.fechaEjecucion = fecha;
        this.frecuencia = frecuencia;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isProgramada() {
        return programada;
    }

    public void setProgramada(boolean programada) {
        this.programada = programada;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Cliente getClienteOrigen() {
        return clienteOrigen;
    }

    public void setClienteOrigen(Cliente clienteOrigen) {
        this.clienteOrigen = clienteOrigen;
    }

    public Cliente getClienteDestino() {
        return clienteDestino;
    }

    public void setClienteDestino(Cliente clienteDestino) {
        this.clienteDestino = clienteDestino;
    }

    public Monedero getMonedero() {
        return monedero;
    }

    public void setMonedero(Monedero monedero) {
        this.monedero = monedero;
    }
}
