package edu.co.uniquindio.poo.monedero.model;

public class Transferencia extends Transaccion {

    @Override
    public void ejecutar() {
        if (clienteOrigen != null && clienteDestino != null && monedero.getSaldo() >= monto) {
            monedero.setSaldo(monedero.getSaldo() - monto);
            clienteDestino.getMonederos().get(0).setSaldo(
                    clienteDestino.getMonederos().get(0).getSaldo() + monto
            );
            monedero.registrarTransaccion(this);
        } else {
            System.out.println("Error en la transferencia");
        }
    }

    @Override
    public int calcularPuntos() {
        return (int) (monto / 100) * 3;
    }
}
