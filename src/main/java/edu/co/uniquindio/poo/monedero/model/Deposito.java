package edu.co.uniquindio.poo.monedero.model;

public class Deposito extends Transaccion {

    @Override
    public void ejecutar() {
        monedero.setSaldo(monedero.getSaldo() + monto);
        monedero.registrarTransaccion(this);
    }

    @Override
    public int calcularPuntos() {
        return (int) (monto / 100) * 1;
    }
}

