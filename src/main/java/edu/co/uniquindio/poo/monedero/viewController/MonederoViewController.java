package edu.co.uniquindio.poo.monedero.viewController;

import edu.co.uniquindio.poo.monedero.app.SceneLoader;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import edu.co.uniquindio.poo.monedero.model.Cliente;
import edu.co.uniquindio.poo.monedero.model.Monedero;
import edu.co.uniquindio.poo.monedero.model.Transaccion;

public class MonederoViewController {

    @FXML private Label lblNombreMonedero;
    @FXML private Label lblSaldo;

    @FXML private TableView<Transaccion> tablaHistorial;
    @FXML private TableColumn<Transaccion, String> colTipoTrans;
    @FXML private TableColumn<Transaccion, Number> colMontoTrans;
    @FXML private TableColumn<Transaccion, String> colFechaTrans;

    private Monedero monedero;
    private Cliente clienteDueño;

    @FXML
    public void initialize() {
        colTipoTrans.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getTipo()));
        colMontoTrans.setCellValueFactory(t -> new SimpleDoubleProperty(t.getValue().getMonto()));
        colFechaTrans.setCellValueFactory(
                t -> new SimpleStringProperty(t.getValue().getFechaCreacion().toString())
        );
    }

    public void cargarMonedero(Monedero m, Cliente dueño) {
        this.monedero = m;
        this.clienteDueño = dueño;

        lblNombreMonedero.setText(m.getNombre());
        lblSaldo.setText(String.valueOf(m.getSaldo()));

        tablaHistorial.getItems().setAll(m.getHistorial());
    }

    @FXML
    private void abrirTransaccion() {
        TransaccionViewController controller =
                (TransaccionViewController) SceneLoader.cargarVista(
                        "edu/co/uniquindio/poo/monedero/transaccion.fxml"
                );

        controller.cargarDatos(monedero, clienteDueño);
    }

    @FXML
    private void volver() {
        ClienteViewController controller =
                (ClienteViewController) SceneLoader.cargarVista(
                        "edu/co/uniquindio/poo/monedero/cliente.fxml"
                );

        controller.cargarCliente(clienteDueño);
    }
}
