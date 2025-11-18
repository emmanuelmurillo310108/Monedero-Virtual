package edu.co.uniquindio.poo.monedero.controller;

import edu.co.uniquindio.poo.monedero.model.*;
import java.util.List;

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

    public List<Transaccion> obtenerHistorial(Monedero monedero) {
        return monedero.getHistorial();
    }

}
