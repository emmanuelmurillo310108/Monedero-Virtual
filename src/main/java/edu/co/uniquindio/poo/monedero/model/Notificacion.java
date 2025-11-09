package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.model.interfaces.INotificable;

public class Notificacion implements INotificable {
    private String tipo;
    private String mensaje;

    public Notificacion(String tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void enviar(Cliente cliente, String mensaje) {
        System.out.println("Notificacion " + tipo + " enviada a " + cliente.getNombre() + ": " + mensaje);
    }
}

