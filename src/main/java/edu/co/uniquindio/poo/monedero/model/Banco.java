package edu.co.uniquindio.poo.monedero.model;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nombre;
    private List<Cliente> listaClientes;
    private List<Monedero> listaMonederos;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.listaClientes = new ArrayList<>();
        this.listaMonederos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void setListaMonederos(List<Monedero> listaMonederos) {
        this.listaMonederos = listaMonederos;
    }

    public String registrarCliente(Cliente cliente) {
        Cliente existente = buscarCliente(cliente.getEmail());
        if (existente == null) {
            listaClientes.add(cliente);
            return "Cliente registrado exitosamente:\n" + cliente.toString();
        } else {
            return "El cliente con el correo " + cliente.getEmail() + " ya está registrado.";
        }
    }

    public Cliente buscarCliente(String email) {
        for (Cliente c : listaClientes) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    public String actualizarCliente(String email, String nuevoNombre, String nuevoTelefono) {
        Cliente c = buscarCliente(email);
        if (c != null) {
            c.setNombre(nuevoNombre);
            c.setTelefono(nuevoTelefono);
            return "Cliente actualizado:\n" + c.toString();
        }
        return "Cliente no encontrado para actualizar.";
    }

    public String eliminarCliente(String email) {
        Cliente c = buscarCliente(email);
        if (c != null) {
            listaClientes.remove(c);
            return "Cliente eliminado correctamente.";
        }
        return "Cliente no encontrado para eliminar.";
    }


    public String registrarMonedero(Monedero monedero) {
        Monedero existente = buscarMonedero(monedero.getNombre());
        if (existente == null) {
            listaMonederos.add(monedero);
            return "Monedero registrado:\n" + monedero.toString();
        } else {
            return "El monedero '" + monedero.getNombre() + "' ya existe.";
        }
    }

    public Monedero buscarMonedero(String nombre) {
        for (Monedero m : listaMonederos) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null;
    }

    public String actualizarMonedero(String nombre, double nuevoSaldo) {
        Monedero m = buscarMonedero(nombre);
        if (m != null) {
            m.setSaldo(nuevoSaldo);
            return "Monedero actualizado:\n" + m.toString();
        }
        return "Monedero no encontrado para actualizar.";
    }

    public String eliminarMonedero(String nombre) {
        Monedero m = buscarMonedero(nombre);
        if (m != null) {
            listaMonederos.remove(m);
            return "Monedero eliminado correctamente.";
        }
        return "Monedero no encontrado para eliminar.";
    }

    public List<Monedero> getListaMonederos() {
        return listaMonederos;
    }

    @Override
    public String toString() {
        return "Clientes registrados=" + listaClientes.size() +
                ", monederos activos=" + listaMonederos.size() + "]";
    }

}

