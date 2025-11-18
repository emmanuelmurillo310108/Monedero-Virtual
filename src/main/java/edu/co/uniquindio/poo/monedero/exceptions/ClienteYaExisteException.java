package edu.co.uniquindio.poo.monedero.exceptions;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String mensaje) {
        super(mensaje);
    }
}