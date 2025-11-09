package edu.co.uniquindio.poo.monedero.model;

public class Retiro extends Transaccion {

    @Override
    public void ejecutar() {
        if (monedero.getSaldo() >= monto) {
            monedero.setSaldo(monedero.getSaldo() - monto);
            monedero.registrarTransaccion(this);
        } else {
            System.out.println("Saldo insuficiente para realizar el retiro");
        }
    }

    @Override
    public int calcularPuntos() {
        return (int) (monto / 100) * 2;
    }
}
