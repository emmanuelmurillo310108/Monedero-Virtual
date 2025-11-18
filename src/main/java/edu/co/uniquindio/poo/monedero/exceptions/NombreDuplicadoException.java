package edu.co.uniquindio.poo.monedero.exceptions;

public class NombreDuplicadoException extends RuntimeException {
    public NombreDuplicadoException(String mensaje) {
        super(mensaje);
    }
}