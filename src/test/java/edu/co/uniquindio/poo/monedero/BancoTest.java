package edu.co.uniquindio.poo.monedero;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

import edu.co.uniquindio.poo.monedero.controller.BancoController;
import edu.co.uniquindio.poo.monedero.exceptions.*;
import edu.co.uniquindio.poo.monedero.model.*;

public class BancoTest {

    private static final Logger LOG = Logger.getLogger(BancoTest.class.getName());

    private BancoController banco = BancoController.getInstance();

    @Test
    public void registrarClienteCorrectamente() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(100, "Prueba", "correo@test.com", "1234567");
        String respuesta = banco.registrarCliente(cliente);

        assertTrue(respuesta.contains("exitosamente"));

        System.out.println("Registrado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void registrarMonederoCorrectamente() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(101, "Lina", "lina@test.com", "9876543");
        banco.registrarCliente(cliente);

        Monedero m = new Monedero(200, "Monedero-Lina", cliente);
        String respuesta = banco.registrarMonederoParaCliente(m, cliente);

        assertTrue(respuesta.contains("registrado"));

        System.out.println("Registrado correctamente");
        LOG.info("Fin de prueba registrarMonederoCorrectamente");
    }

    @Test
    public void actualizarClienteCorrectamente() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(102, "Carlos", "carlos@test.com", "1112223");
        banco.registrarCliente(cliente);

        String respuesta = banco.actualizarCliente("carlos@test.com", "Carlos Nuevo", "5555555");

        assertEquals("Cliente actualizado correctamente", respuesta);

        System.out.println("Actualizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void actualizarMonederoCorrectamente() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(103, "Marta", "marta@test.com", "3333333");
        banco.registrarCliente(cliente);

        Monedero m = new Monedero(201, "Monedero-Marta", cliente);
        banco.registrarMonederoParaCliente(m, cliente);

        String respuesta = banco.actualizarMonedero("Monedero-Marta", "Monedero-Marta-New");

        assertEquals("Monedero actualizado correctamente", respuesta);
        System.out.println("Actualizado correctamente");

        LOG.info("Fin de prueba");
    }

    @Test
    public void transaccionDeposito() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(104, "Sofia", "sofia@test.com", "4444444");
        banco.registrarCliente(cliente);

        Monedero monedero = new Monedero(202, "Monedero-Sofia", cliente);
        banco.registrarMonederoParaCliente(monedero, cliente);

        Transaccion deposito = new Deposito(monedero, cliente, 50000);
        deposito.ejecutar();

        assertEquals(50000, monedero.getSaldo());
        System.out.println("Realizado correctamente");

        LOG.info("Fin de prueba");
    }

    @Test
    public void transaccionRetiroSaldoInsuficiente() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(105, "Pablo", "pablo@test.com", "6666666");
        banco.registrarCliente(cliente);

        Monedero monedero = new Monedero(203, "Monedero-Pablo", cliente);
        banco.registrarMonederoParaCliente(monedero, cliente);

        Transaccion retiro = new Retiro(monedero, cliente, 20000);

        assertThrows(SaldoInsuficienteException.class, retiro::ejecutar);

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void sistemaPuntosSubeDeBronzeAPlata() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(106, "Daniela", "daniela@test.com", "7777777");
        banco.registrarCliente(cliente);

        Monedero monedero = new Monedero(204, "Monedero-Daniela", cliente);
        banco.registrarMonederoParaCliente(monedero, cliente);

        Transaccion t = new Deposito(monedero, cliente, 100000);
        cliente.actualizarPuntos(t);

        assertEquals("Plata", cliente.getRango().getNombre());

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void emailInvalidoDebeLanzarExcepcion() {
        LOG.info("Inicio de prueba");

        assertThrows(EmailInvalidoException.class, () -> {
            new Cliente(107, "Nicolas", "correo_malo", "1234567");
        });

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void telefonoInvalidoDebeLanzarExcepcion() {
        LOG.info("Inicio de prueba");

        assertThrows(TelefonoInvalidoException.class, () -> {
            new Cliente(108, "Laura", "laura@test.com", "abcde");
        });

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void montoNegativoDebeLanzarExcepcion() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(109, "Andres", "andres@test.com", "9999999");
        banco.registrarCliente(cliente);

        Monedero mon = new Monedero(205, "Monedero-Andres", cliente);
        banco.registrarMonederoParaCliente(mon, cliente);

        Transaccion deposito = new Deposito(mon, cliente, -50000);

        assertThrows(MontoNegativoException.class, deposito::ejecutar);

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void transferenciaCorrectaEntreClientes() {
        LOG.info("Inicio de prueba");

        Cliente origen = new Cliente(110, "Mario", "mario@test.com", "1111111");
        Cliente destino = new Cliente(111, "Paula", "paula@test.com", "2222222");
        banco.registrarCliente(origen);
        banco.registrarCliente(destino);

        Monedero monOrigen = new Monedero(300, "Monedero-Mario", origen);
        Monedero monDestino = new Monedero(301, "Monedero-Paula", destino);
        banco.registrarMonederoParaCliente(monOrigen, origen);
        banco.registrarMonederoParaCliente(monDestino, destino);

        monOrigen.setSaldo(50000);

        Transaccion t = new Transferencia();
        t.setClienteOrigen(origen);
        t.setClienteDestino(destino);
        t.setMonedero(monOrigen);
        t.setMonto(20000);

        t.ejecutar();

        assertEquals(30000, monOrigen.getSaldo());
        assertEquals(20000, monDestino.getSaldo());

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void transferenciaDebeFallarSiDestinoNoTieneMonederos() {
        LOG.info("Inicio de prueba");

        Cliente origen = new Cliente(112, "Juan", "juan@test.com", "7777777");
        Cliente destino = new Cliente(113, "Sara", "sara@test.com", "8888888");
        banco.registrarCliente(origen);
        banco.registrarCliente(destino);

        Monedero monOrigen = new Monedero(302, "Monedero-Juan", origen);
        banco.registrarMonederoParaCliente(monOrigen, origen);
        monOrigen.setSaldo(40000);

        Transaccion t = new Transferencia();
        t.setClienteOrigen(origen);
        t.setClienteDestino(destino);
        t.setMonedero(monOrigen);
        t.setMonto(10000);

        assertThrows(IllegalArgumentException.class, t::ejecutar);

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void retiroExactamenteIgualAlSaldo() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(114, "Brenda", "brenda@test.com", "5555555");
        banco.registrarCliente(cliente);

        Monedero mon = new Monedero(303, "Mon-Brenda", cliente);
        banco.registrarMonederoParaCliente(mon, cliente);
        mon.setSaldo(30000);

        Transaccion r = new Retiro(mon, cliente, 30000);
        r.ejecutar();

        assertEquals(0, mon.getSaldo());

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void depositoDebeRegistrarTransaccionEnHistorial() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(115, "Oscar", "oscar@test.com", "4441111");
        banco.registrarCliente(cliente);

        Monedero mon = new Monedero(304, "Mon-Oscar", cliente);
        banco.registrarMonederoParaCliente(mon, cliente);

        Transaccion dep = new Deposito(mon, cliente, 15000);
        dep.ejecutar();

        assertEquals(1, mon.getHistorial().size());

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void clienteDebeIniciarConRangoBronze() {
        LOG.info("Inicio de prueba");

        Cliente cliente = new Cliente(116, "Valeria", "val@test.com", "1231231");

        assertEquals("Bronce", cliente.getRango().getNombre());

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void actualizarClienteDebeCambiarTelefono() {
        LOG.info("Inicio de prueba");

        Cliente c = new Cliente(117, "Marco", "marco@test.com", "8880000");
        banco.registrarCliente(c);

        banco.actualizarCliente("marco@test.com", "Marco Nuevo", "1112222");

        assertEquals("1112222", c.getTelefono());

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void monederoDebeIniciarConSaldoCero() {
        LOG.info("Inicio de prueba");

        Cliente c = new Cliente(118, "Luis", "luis@test.com", "7771111");
        banco.registrarCliente(c);

        Monedero m = new Monedero(305, "Mon-Luis", c);

        assertEquals(0, m.getSaldo());

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void clienteDebeTenerListaDeMonederos() {
        LOG.info("Inicio de prueba");

        Cliente c = new Cliente(120, "Esteban", "esteban@test.com", "9090909");
        banco.registrarCliente(c);

        Monedero m1 = new Monedero(307, "Mon1", c);
        Monedero m2 = new Monedero(308, "Mon2", c);

        banco.registrarMonederoParaCliente(m1, c);
        banco.registrarMonederoParaCliente(m2, c);

        assertEquals(2, c.getMonederos().size());

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }

    @Test
    public void transferenciaFallaSiSaldoEsInsuficiente() {
        LOG.info("Inicio de prueba");

        Cliente o = new Cliente(121, "Rafa", "rafa@test.com", "3333333");
        Cliente d = new Cliente(122, "Clara", "clara@test.com", "4444444");
        banco.registrarCliente(o);
        banco.registrarCliente(d);

        Monedero mo = new Monedero(309, "Mon-Rafa", o);
        Monedero md = new Monedero(310, "Mon-Clara", d);

        banco.registrarMonederoParaCliente(mo, o);
        banco.registrarMonederoParaCliente(md, d);

        mo.setSaldo(5000);

        Transaccion t = new Transferencia();
        t.setClienteOrigen(o);
        t.setClienteDestino(d);
        t.setMonedero(mo);
        t.setMonto(10000);

        assertThrows(SaldoInsuficienteException.class, t::ejecutar);

        System.out.println("Realizado correctamente");
        LOG.info("Fin de prueba");
    }
}

