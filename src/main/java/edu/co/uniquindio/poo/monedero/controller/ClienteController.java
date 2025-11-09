package edu.co.uniquindio.poo.monedero.controller;

import edu.co.uniquindio.poo.monedero.model.*;

public class ClienteController {

    public void realizarTransaccion(Cliente cliente, Transaccion transaccion) {
        cliente.realizarTransaccion(transaccion);
    }

    public void canjearBeneficio(Cliente cliente, Beneficio beneficio) {
        cliente.canjearPuntos(beneficio);
    }

    public double consultarSaldo(Cliente cliente, Monedero monedero) {
        return cliente.consultarSaldo(monedero);
    }

    public void verHistorial(Cliente cliente, Monedero monedero) {
        for (Transaccion t : monedero.getHistorial()) {
            System.out.println("Transaccion: " + t.getTipo() + " - Monto: " + t.getMonto());
        }
    }
}
