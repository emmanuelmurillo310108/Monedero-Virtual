package edu.co.uniquindio.poo.monedero.viewController;

import edu.co.uniquindio.poo.monedero.app.SceneLoader;
import edu.co.uniquindio.poo.monedero.controller.BancoController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import edu.co.uniquindio.poo.monedero.model.Cliente;
import edu.co.uniquindio.poo.monedero.model.Monedero;

public class BancoViewController {

    @FXML private TextField txtNombreBanco;

    @FXML private TextField txtEmailCliente;
    @FXML private TextField txtNombreCliente;
    @FXML private TextField txtTelefonoCliente;

    @FXML private TextField txtNombreMonedero;

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableView<Monedero> tablaMonederos;

    private BancoController bancoController;

    @FXML
    public void initialize() {
        bancoController = new BancoController("Banco Virtual");
        txtNombreBanco.setText(bancoController.getBanco().getNombre());
    }

    @FXML
    private void registrarCliente() {
        Cliente cliente = new Cliente(
                bancoController.listarClientes().size() + 1,
                txtNombreCliente.getText(),
                txtEmailCliente.getText(),
                txtTelefonoCliente.getText()
        );

        mostrarAlerta(bancoController.registrarCliente(cliente));
        tablaClientes.getItems().setAll(bancoController.listarClientes());
    }

    @FXML
    private void eliminarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            mostrarAlerta(bancoController.eliminarCliente(seleccionado.getEmail()));
            tablaClientes.getItems().setAll(bancoController.listarClientes());
        }
    }

    @FXML
    private void registrarMonedero() {
        Monedero monedero = new Monedero(
                bancoController.listarMonederos().size() + 1,
                txtNombreMonedero.getText(),
                clienteSeleccionado
        );

        mostrarAlerta(bancoController.registrarMonedero(monedero));
        tablaMonederos.getItems().setAll(bancoController.listarMonederos());
    }

    @FXML
    private void eliminarMonedero() {
        Monedero seleccionado = tablaMonederos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            mostrarAlerta(bancoController.eliminarMonedero(seleccionado.getNombre()));
            tablaMonederos.getItems().setAll(bancoController.listarMonederos());
        }
    }

    @FXML
    private void abrirCliente() {
        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) return;

        ClienteViewController controller =
                SceneLoader.cargarVista("fxml/cliente.fxml");

        controller.cargarCliente(cliente);
    }

    @FXML
    private void abrirMonedero() {
        Monedero monedero = tablaMonederos.getSelectionModel().getSelectedItem();
        if (monedero == null) return;

        MonederoViewController controller =
                SceneLoader.cargarVista("fxml/monedero.fxml");

        controller.cargarMonedero(monedero);
    }

    private void mostrarAlerta(String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.show();
    }
}
