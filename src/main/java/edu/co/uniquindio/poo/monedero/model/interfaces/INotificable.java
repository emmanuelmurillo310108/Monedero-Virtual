package edu.co.uniquindio.poo.monedero.model.interfaces;

import edu.co.uniquindio.poo.monedero.model.Cliente;

public interface INotificable {
    void enviar(Cliente cliente, String mensaje);
}

