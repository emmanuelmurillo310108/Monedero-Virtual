package edu.co.uniquindio.poo.monedero.controller;

import edu.co.uniquindio.poo.monedero.model.*;

public class MonederoController {

    public void depositar(Monedero monedero, double monto) {
        Deposito deposito = new Deposito();
        deposito.setMonto(monto);
        deposito.setMonedero(monedero);
        deposito.ejecutar();
    }

    public void retirar(Monedero monedero, double monto) {
        Retiro retiro = new Retiro();
        retiro.setMonto(monto);
        retiro.setMonedero(monedero);
        retiro.ejecutar();
    }

    public void transferir(Cliente origen, Cliente destino, double monto, Monedero monederoOrigen) {
        Transferencia transferencia = new Transferencia();
        transferencia.setMonto(monto);
        transferencia.setClienteOrigen(origen);
        transferencia.setClienteDestino(destino);
        transferencia.setMonedero(monederoOrigen);
        transferencia.ejecutar();
    }

}


