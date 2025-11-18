package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.exceptions.MontoInvalidoException;

public class Deposito extends Transaccion {

    @Override
    public void ejecutar() {
        if (monto <= 0) {
            throw new MontoInvalidoException("El monto del depósito debe ser mayor que 0");
        }
        monedero.setSaldo(monedero.getSaldo() + monto);
        monedero.registrarTransaccion(this);
    }

    @Override
    public int calcularPuntos() {
        return (int) (monto / 100) * 1;
    }
}

