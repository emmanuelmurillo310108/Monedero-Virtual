package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.model.interfaces.ICanjeable;

public class Beneficio implements ICanjeable {
    private int idBeneficio;
    private String descripcion;
    private int costoPuntos;
    private String efecto;

    public Beneficio(int id, String descripcion, int costoPuntos, String efecto) {
        this.idBeneficio = id;
        this.descripcion = descripcion;
        this.costoPuntos = costoPuntos;
        this.efecto = efecto;
    }

    public int getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCostoPuntos() {
        return costoPuntos;
    }

    public void setCostoPuntos(int costoPuntos) {
        this.costoPuntos = costoPuntos;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    @Override
    public void aplicar(Cliente cliente) {
        System.out.println("Beneficio aplicado a " + cliente.getNombre() + ": " + descripcion);
    }
}
