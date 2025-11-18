package edu.co.uniquindio.poo.monedero.model;

import edu.co.uniquindio.poo.monedero.exceptions.MontoNegativoException;
import edu.co.uniquindio.poo.monedero.exceptions.SaldoInsuficienteException;

public class Transferencia extends Transaccion {

    public Transferencia() {
        this.tipo = "Transferencia";
    }

    @Override
    public void ejecutar() {
        if (monto <= 0) {
            throw new MontoNegativoException("El monto debe ser mayor a 0");
        }
        if (clienteOrigen == null || clienteDestino == null) {
            throw new IllegalArgumentException("Debe especificar clientes");
        }
        if (clienteDestino.getMonederos().isEmpty()) {
            throw new IllegalArgumentException("Cliente destino sin monederos");
        }
        if (monedero.getSaldo() < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        Monedero destino = clienteDestino.getMonederos().get(0);
        monedero.setSaldo(monedero.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);
        monedero.registrarTransaccion(this);
    }

    @Override
    public int calcularPuntos() {
        return (int) (monto / 100) * 3;
    }
}
