package edu.co.uniquindio.poo.monedero.exceptions;

public class MonederoYaExisteException extends RuntimeException {
    public MonederoYaExisteException(String mensaje) {
        super(mensaje);
    }
}