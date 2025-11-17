package edu.co.uniquindio.poo.monedero.viewController;

import edu.co.uniquindio.poo.monedero.app.SceneLoader;
import edu.co.uniquindio.poo.monedero.controller.TransaccionController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import edu.co.uniquindio.poo.monedero.model.*;

public class TransaccionViewController {

    @FXML private TextField txtMonto;
    @FXML private ComboBox<String> cbTipoTransaccion;
    @FXML private ComboBox<Cliente> cbDestino;

    private Monedero monederoActual;
    private Cliente clienteOrigen;

    private TransaccionController transaccionController;

    @FXML
    public void initialize() {
        transaccionController = new TransaccionController();
        cbTipoTransaccion.getItems().addAll("Deposito", "Retiro", "Transferencia");
    }

    public void cargarDatos(Monedero monedero, Cliente origen) {
        this.monederoActual = monedero;
        this.clienteOrigen = origen;

        cbDestino.getItems().setAll(
                origen.getBanco().listarClientes().stream()
                        .filter(c -> c != origen)
                        .toList()
        );
    }

    @FXML
    private void ejecutarTransaccion() {
        double monto = Double.parseDouble(txtMonto.getText());
        String tipo = cbTipoTransaccion.getValue();

        Cliente destino = cbDestino.getValue();

        Transaccion t = transaccionController.crearTransaccion(
                tipo, monto, clienteOrigen, destino, monederoActual
        );

        transaccionController.ejecutarTransaccion(t);

        new Alert(Alert.AlertType.INFORMATION, "Transacción completada").show();
    }

    @FXML
    private void volver() {
        MonederoViewController controller =
                SceneLoader.cargarVista("fxml/monedero.fxml");

        controller.cargarMonedero(monederoActual, clienteOrigen);
    }
}
