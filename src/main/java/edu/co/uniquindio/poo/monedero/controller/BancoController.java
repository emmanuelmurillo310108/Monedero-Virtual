package edu.co.uniquindio.poo.monedero.controller;

import edu.co.uniquindio.poo.monedero.model.*;
import java.util.List;

public class BancoController {
    private Banco banco;

    public BancoController(String nombreBanco) {
        this.banco = new Banco(nombreBanco);
    }

    public String registrarCliente(Cliente cliente) {
        return banco.registrarCliente(cliente);
    }

    public Cliente buscarCliente(String email) {
        return banco.buscarCliente(email);
    }

    public String actualizarCliente(String email, String nuevoNombre, String nuevoTelefono) {
        return banco.actualizarCliente(email, nuevoNombre, nuevoTelefono);
    }

    public String eliminarCliente(String email) {
        return banco.eliminarCliente(email);
    }

    public List<Cliente> listarClientes() {
        return banco.getListaClientes();
    }

    public String registrarMonedero(Monedero monedero) {
        return banco.registrarMonedero(monedero);
    }

    public Monedero buscarMonedero(String nombre) {
        return banco.buscarMonedero(nombre);
    }

    public String actualizarMonedero(String nombre, double nuevoSaldo) {
        return banco.actualizarMonedero(nombre, nuevoSaldo);
    }

    public String eliminarMonedero(String nombre) {
        return banco.eliminarMonedero(nombre);
    }

    public List<Monedero> listarMonederos() {
        return banco.getListaMonederos();
    }

    public Banco getBanco() {
        return banco;
    }
}

