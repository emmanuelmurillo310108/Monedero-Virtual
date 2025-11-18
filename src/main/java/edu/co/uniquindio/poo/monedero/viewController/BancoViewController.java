package edu.co.uniquindio.poo.monedero.viewController;

import edu.co.uniquindio.poo.monedero.app.SceneLoader;
import edu.co.uniquindio.poo.monedero.controller.BancoController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    @FXML private TableColumn<Cliente, String> colNombreCliente;
    @FXML private TableColumn<Cliente, String> colEmailCliente;
    @FXML private TableColumn<Cliente, String> colTelefonoCliente;
    @FXML private TableColumn<Cliente, Number> colPuntosCliente;

    @FXML private TableView<Monedero> tablaMonederos;
    @FXML private TableColumn<Monedero, String> colNombreMonedero;
    @FXML private TableColumn<Monedero, Number> colSaldoMonedero;
    @FXML private TableColumn<Monedero, String> colDuenoMonedero;

    private BancoController banco = BancoController.getInstance();

    @FXML
    public void initialize() {

        txtNombreBanco.setText(banco.getNombreBanco());

        colNombreCliente.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colEmailCliente.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmail()));
        colTelefonoCliente.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
        colPuntosCliente.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getPuntosAcumulados()));

        colNombreMonedero.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colSaldoMonedero.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSaldo()));
        colDuenoMonedero.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getDueño() != null ? c.getValue().getDueño().getNombre() : "Sin dueño"
        ));

        actualizarTablas();
    }

    private void actualizarTablas() {
        tablaClientes.getItems().setAll(banco.listarClientes());
        tablaMonederos.getItems().setAll(banco.listarMonederos());
    }

    @FXML
    private void registrarCliente() {
        Cliente cliente = new Cliente(
                banco.listarClientes().size() + 1,
                txtNombreCliente.getText(),
                txtEmailCliente.getText(),
                txtTelefonoCliente.getText()
        );

        mostrarAlerta(banco.registrarCliente(cliente));
        actualizarTablas();
    }

    @FXML
    private void actualizarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Seleccione un cliente para actualizar");
            return;
        }
        String nuevoNombre = txtNombreCliente.getText();
        String nuevoTelefono = txtTelefonoCliente.getText();

        String respuesta = banco.actualizarCliente(
                seleccionado.getEmail(),
                nuevoNombre,
                nuevoTelefono
        );
        mostrarAlerta(respuesta);
        actualizarTablas();
    }

    @FXML
    private void eliminarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            mostrarAlerta(banco.eliminarCliente(seleccionado.getEmail()));
            actualizarTablas();
        }
    }

    @FXML
    private void registrarMonedero() {
        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            mostrarAlerta("Seleccione un cliente.");
            return;
        }

        Monedero monedero = new Monedero(
                banco.listarMonederos().size() + 1,
                txtNombreMonedero.getText(),
                cliente
        );

        mostrarAlerta(banco.registrarMonederoParaCliente(monedero, cliente));
        actualizarTablas();
    }

    @FXML
    private void actualizarMonedero() {
        Monedero seleccionado = tablaMonederos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Seleccione un monedero para actualizar");
            return;
        }
        String nuevoNombre = txtNombreMonedero.getText();

        String respuesta = banco.actualizarMonedero(
                seleccionado.getNombre(),
                nuevoNombre
        );
        mostrarAlerta(respuesta);
        actualizarTablas();
    }

    @FXML
    private void eliminarMonedero() {
        Monedero seleccionado = tablaMonederos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            mostrarAlerta(banco.eliminarMonedero(seleccionado.getNombre()));
            actualizarTablas();
        }
    }

    @FXML
    private void abrirCliente() {
        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) return;

        ClienteViewController controller =
                (ClienteViewController) SceneLoader.cargarVista(
                        "edu/co/uniquindio/poo/monedero/cliente.fxml"
                );

        controller.cargarCliente(cliente);
    }

    @FXML
    private void abrirMonedero() {
        Monedero monedero = tablaMonederos.getSelectionModel().getSelectedItem();
        if (monedero == null) return;

        MonederoViewController controller =
                (MonederoViewController) SceneLoader.cargarVista(
                        "edu/co/uniquindio/poo/monedero/monedero.fxml"
                );

        controller.cargarMonedero(monedero, monedero.getDueño());
    }

    private void mostrarAlerta(String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.show();
    }
}
