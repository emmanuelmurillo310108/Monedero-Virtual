package edu.co.uniquindio.poo.monedero.viewController;

import edu.co.uniquindio.poo.monedero.app.SceneLoader;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
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
    @FXML private TableColumn<Monedero, String> colNombreMonedero;
    @FXML private TableColumn<Monedero, Number> colSaldoMonedero;

    private Cliente cliente;

    @FXML
    public void initialize() {
        colNombreMonedero.setCellValueFactory(
                m -> new SimpleStringProperty(m.getValue().getNombre())
        );

        colSaldoMonedero.setCellValueFactory(
                m -> new SimpleDoubleProperty(m.getValue().getSaldo())
        );
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

        if (monedero == null) {
            new Alert(Alert.AlertType.WARNING, "Seleccione un monedero").show();
            return;
        }

        MonederoViewController controller = (MonederoViewController) SceneLoader.cargarVista(
                "edu/co/uniquindio/poo/monedero/monedero.fxml"
        );

        controller.cargarMonedero(monedero, cliente);
    }

    @FXML
    private void volver() {
        SceneLoader.cargarVista("edu/co/uniquindio/poo/monedero/banco.fxml");
    }
}
