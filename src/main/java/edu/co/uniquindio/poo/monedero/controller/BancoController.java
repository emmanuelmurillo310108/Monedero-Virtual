package edu.co.uniquindio.poo.monedero.controller;

import edu.co.uniquindio.poo.monedero.exceptions.CampoVacioException;
import edu.co.uniquindio.poo.monedero.exceptions.ClienteYaExisteException;
import edu.co.uniquindio.poo.monedero.exceptions.MonederoYaExisteException;
import edu.co.uniquindio.poo.monedero.exceptions.NombreDuplicadoException;
import edu.co.uniquindio.poo.monedero.model.*;
import java.util.List;

public class BancoController {

    private static final BancoController instance = new BancoController();

    private final Banco banco;

    private BancoController() {
        this.banco = new Banco("Banco Virtual");
        cargarDatosIniciales();  // ← Datos quemados aquí
    }

    public static BancoController getInstance() {
        return instance;
    }

    private void cargarDatosIniciales() {

        Cliente c1 = new Cliente(1, "Alice", "alice@mail.com", "1111111");
        Cliente c2 = new Cliente(2, "Bob", "bob@mail.com", "2222222");
        Cliente c3 = new Cliente(3, "Charlie", "charlie@mail.com", "3333333");

        banco.registrarCliente(c1);
        banco.registrarCliente(c2);
        banco.registrarCliente(c3);

        Monedero m1 = new Monedero(1, "Monedero-Alice", c1);
        Monedero m2 = new Monedero(2, "Monedero-Bob", c2);
        Monedero m3 = new Monedero(3, "Monedero-Charlie", c3);

        c1.agregarMonedero(m1);
        c2.agregarMonedero(m2);
        c3.agregarMonedero(m3);

        banco.registrarMonedero(m1);
        banco.registrarMonedero(m2);
        banco.registrarMonedero(m3);

        m1.setSaldo(150000);
        m2.setSaldo(87000);
        m3.setSaldo(50000);
    }

    public String registrarCliente(Cliente cliente) {
        try {
            if (banco.buscarCliente(cliente.getEmail()) != null) {
                throw new ClienteYaExisteException("Ya existe un cliente registrado con este correo");
            }
            for (Cliente c : banco.getListaClientes()) {
                if (c.getNombre().equalsIgnoreCase(cliente.getNombre())) {
                    throw new NombreDuplicadoException("Ya existe un cliente con el nombre: " + cliente.getNombre());
                }
            }
            banco.getListaClientes().add(cliente);
            return "Cliente registrado exitosamente";
        } catch (RuntimeException ex) {
            return "Error: " + ex.getMessage();
        }
    }

    public Cliente buscarCliente(String email) {
        return banco.buscarCliente(email);
    }

    public String actualizarCliente(String email, String nuevoNombre, String nuevoTelefono) {
        if (nuevoNombre == null || nuevoNombre.isBlank())
            throw new CampoVacioException("El nombre no puede estar vacío");
        if (nuevoTelefono == null || nuevoTelefono.isBlank())
            throw new CampoVacioException("El teléfono no puede estar vacío");
        Cliente actual = banco.buscarCliente(email);
        if (actual != null && !actual.getNombre().equalsIgnoreCase(nuevoNombre)) {
            for (Cliente c : banco.getListaClientes()) {
                if (c.getNombre().equalsIgnoreCase(nuevoNombre)) {
                    throw new NombreDuplicadoException("Ya existe un cliente con el nombre: " + nuevoNombre);
                }
            }
        }
        return banco.actualizarCliente(email, nuevoNombre, nuevoTelefono);
    }


    public String actualizarMonedero(String nombreAnterior, String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank())
            throw new CampoVacioException("El nuevo nombre no puede estar vacío");
        Monedero existente = banco.buscarMonedero(nuevoNombre);
        if (existente != null && !nombreAnterior.equalsIgnoreCase(nuevoNombre)) {
            throw new MonederoYaExisteException("Ya existe un monedero con el nombre: " + nuevoNombre);
        }
        return banco.actualizarMonedero(nombreAnterior, nuevoNombre);
    }



    public String eliminarCliente(String email) {
        return banco.eliminarCliente(email);
    }

    public List<Cliente> listarClientes() {
        return banco.getListaClientes();
    }

    public String registrarMonederoParaCliente(Monedero monedero, Cliente cliente) {
        if (banco.buscarMonedero(monedero.getNombre()) != null) {
            throw new MonederoYaExisteException("Ya existe un monedero con el nombre: " + monedero.getNombre());
        }
        for (Monedero m : cliente.getMonederos()) {
            if (m.getNombre().equalsIgnoreCase(monedero.getNombre())) {
                throw new NombreDuplicadoException("Este cliente ya tiene un monedero llamado: " + monedero.getNombre());
            }
        }
        cliente.agregarMonedero(monedero);
        return banco.registrarMonedero(monedero);
    }

    public String eliminarMonedero(String nombre) {
        Monedero m = banco.buscarMonedero(nombre);
        if (m != null && m.getDueño() != null) {
            m.getDueño().getMonederos().remove(m);
        }
        return banco.eliminarMonedero(nombre);
    }

    public List<Monedero> listarMonederos() {
        return banco.getListaMonederos();
    }

    public String getNombreBanco() {
        return banco.getNombre();
    }

    public Banco getBanco() {
        return banco;
    }
}
