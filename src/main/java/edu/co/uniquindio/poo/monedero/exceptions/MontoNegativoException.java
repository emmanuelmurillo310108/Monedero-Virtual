package edu.co.uniquindio.poo.monedero.exceptions;

public class MontoNegativoException extends RuntimeException {
    public MontoNegativoException(String mensaje) {
        super(mensaje);
    }
}