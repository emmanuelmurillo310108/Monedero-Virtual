package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.exceptions.MontoNegativoException;

public class Deposito extends Transaccion {

    public Deposito() {
        this.tipo = "Deposito";
    }

    public Deposito(Monedero monedero, Cliente clienteOrigen, double monto) {
        super(monedero, clienteOrigen, null, monto);
        this.tipo = "Depósito";
    }

    @Override
    public void ejecutar() {
        if (monto <= 0) {
            throw new MontoNegativoException("El monto debe ser mayor a 0");
        }
        monedero.setSaldo(monedero.getSaldo() + monto);
        monedero.registrarTransaccion(this);
    }

    @Override
    public int calcularPuntos() {
        return (int) (monto / 100) * 1;
    }
}