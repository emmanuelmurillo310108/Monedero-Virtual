package edu.co.uniquindio.poo.monedero.model;

public class Rango {
    private String nombre;
    private int puntosMin;
    private int puntosMax;

    public Rango(String nombre, int puntosMin, int puntosMax) {
        this.nombre = nombre;
        this.puntosMin = puntosMin;
        this.puntosMax = puntosMax;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosMin() {
        return puntosMin;
    }

    public void setPuntosMin(int puntosMin) {
        this.puntosMin = puntosMin;
    }

    public int getPuntosMax() {
        return puntosMax;
    }

    public void setPuntosMax(int puntosMax) {
        this.puntosMax = puntosMax;
    }
}

