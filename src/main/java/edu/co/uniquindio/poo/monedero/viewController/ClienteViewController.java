package edu.co.uniquindio.poo.monedero.viewController;

import edu.co.uniquindio.poo.monedero.app.SceneLoader;
import edu.co.uniquindio.poo.monedero.controller.ClienteController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import edu.co.uniquindio.poo.monedero.model.Cliente;
import edu.co.uniquindio.poo.monedero.model.Monedero;

public class ClienteViewController {

    @FXML private Label lblNombre;
    @FXML private Label lblEmail;
    @FXML private Label lblTelefono;
    @FXML private Label lblPuntos;
    @FXML private Label lblRango;

    @FXML private TableView<Monedero> tablaMonederos;

    private Cliente cliente;
    private ClienteController clienteController;

    @FXML
    public void initialize() {
        clienteController = new ClienteController();
    }

    public void cargarCliente(Cliente cliente) {
        this.cliente = cliente;

        lblNombre.setText(cliente.getNombre());
        lblEmail.setText(cliente.getEmail());
        lblTelefono.setText(cliente.getTelefono());
        lblPuntos.setText(String.valueOf(cliente.getPuntosAcumulados()));
        lblRango.setText(cliente.getRango().getNombre());

        tablaMonederos.getItems().setAll(cliente.getMonederos());
    }

    @FXML
    private void abrirMonedero() {
        Monedero monedero = tablaMonederos.getSelectionModel().getSelectedItem();
        if (monedero == null) return;

        MonederoViewController controller =
                SceneLoader.cargarVista("fxml/monedero.fxml");

        controller.cargarMonedero(monedero, cliente);
    }

    @FXML
    private void volver() {
        SceneLoader.cargarVista("fxml/banco.fxml");
    }
}
