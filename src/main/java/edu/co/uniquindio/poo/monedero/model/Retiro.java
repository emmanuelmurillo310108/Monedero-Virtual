package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.exceptions.MontoNegativoException;
import edu.co.uniquindio.poo.monedero.exceptions.SaldoInsuficienteException;

public class Retiro extends Transaccion {

    public Retiro() {
        this.tipo = "Retiro";
    }

    public Retiro(Monedero monedero, Cliente clienteOrigen, double monto) {
        super(monedero, clienteOrigen, null, monto);
        this.tipo = "Retiro";
    }

    @Override
    public void ejecutar() {
        if (monto <= 0) {
            throw new MontoNegativoException("El monto debe ser mayor a 0");
        }
        if (monedero.getSaldo() < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        monedero.setSaldo(monedero.getSaldo() - monto);
        monedero.registrarTransaccion(this);
    }

    @Override
    public int calcularPuntos() {
        return (int) (monto / 100) * 2;
    }
}
