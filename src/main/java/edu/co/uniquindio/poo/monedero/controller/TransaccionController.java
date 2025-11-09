package edu.co.uniquindio.poo.monedero.controller;

import edu.co.uniquindio.poo.monedero.model.*;
import java.util.Date;

public class TransaccionController {

    public Transaccion crearTransaccion(String tipo, double monto, Cliente origen, Cliente destino, Monedero monedero) {
        Transaccion transaccion = null;

        switch (tipo.toLowerCase()) {
            case "deposito":
                transaccion = new Deposito();
                break;
            case "retiro":
                transaccion = new Retiro();
                break;
            case "transferencia":
                transaccion = new Transferencia();
                break;
            default:
                System.out.println("Tipo de transaccion no valido");
                return null;
        }

        transaccion.setMonto(monto);
        transaccion.setClienteOrigen(origen);
        transaccion.setClienteDestino(destino);
        transaccion.setMonedero(monedero);
        transaccion.setFechaCreacion(new Date());
        transaccion.setTipo(tipo);

        return transaccion;
    }

    public void programarTransaccion(Transaccion transaccion, Date fecha, String frecuencia) {
        transaccion.programarTransaccion(fecha, frecuencia);
    }

    public void ejecutarTransaccion(Transaccion transaccion) {
        transaccion.ejecutar();
    }

    public int calcularPuntos(Transaccion transaccion) {
        return transaccion.calcularPuntos();
    }
}


