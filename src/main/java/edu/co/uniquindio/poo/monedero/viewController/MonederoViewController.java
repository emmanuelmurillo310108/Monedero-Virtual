package edu.co.uniquindio.poo.monedero.viewController;

import edu.co.uniquindio.poo.monedero.app.SceneLoader;
import edu.co.uniquindio.poo.monedero.controller.MonederoController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import edu.co.uniquindio.poo.monedero.model.Monedero;
import edu.co.uniquindio.poo.monedero.model.Cliente;

public class MonederoViewController {

    @FXML private Label lblNombreMonedero;
    @FXML private Label lblSaldo;

    @FXML private TextField txtMonto;

    @FXML private TableView tablaHistorial;

    private Cliente dueño;
    private Monedero monedero;
    private MonederoController monederoController;

    @FXML
    public void initialize() {
        monederoController = new MonederoController();
    }

    public void cargarMonedero(Monedero m, Cliente dueño) {
        this.monedero = m;
        this.dueño = dueño;

        lblNombreMonedero.setText(m.getNombre());
        lblSaldo.setText(String.valueOf(m.getSaldo()));
        tablaHistorial.getItems().setAll(m.getHistorial());
    }


    @FXML
    private void depositar() {
        monederoController.depositar(monedero, Double.parseDouble(txtMonto.getText()));
        actualizar();
    }

    @FXML
    private void retirar() {
        monederoController.retirar(monedero, Double.parseDouble(txtMonto.getText()));
        actualizar();
    }

    @FXML
    private void abrirTransaccion() {
        TransaccionViewController controller =
                SceneLoader.cargarVista("fxml/transaccion.fxml");

        controller.cargarDatos(monedero, dueño);
    }

    @FXML
    private void volver() {
        SceneLoader.cargarVista("fxml/cliente.fxml");
    }

    private void actualizar() {
        lblSaldo.setText(String.valueOf(monedero.getSaldo()));
        tablaHistorial.getItems().setAll(monedero.getHistorial());
    }
}
