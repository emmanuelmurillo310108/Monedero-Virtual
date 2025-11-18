package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.exceptions.MontoInvalidoException;
import edu.co.uniquindio.poo.monedero.exceptions.SaldoInsuficienteException;

public class Retiro extends Transaccion {

    @Override
    public void ejecutar() {
        if (monto <= 0) {
            throw new MontoInvalidoException("El monto del retiro debe ser mayor que 0");
        }
        if (monedero.getSaldo() < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro");
        }
        monedero.setSaldo(monedero.getSaldo() - monto);
        monedero.registrarTransaccion(this);
    }

    @Override
    public int calcularPuntos() {
        return (int) (monto / 100) * 2;
    }
}
