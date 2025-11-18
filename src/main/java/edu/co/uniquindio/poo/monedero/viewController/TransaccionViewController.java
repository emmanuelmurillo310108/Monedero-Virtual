package edu.co.uniquindio.poo.monedero.viewController;

import edu.co.uniquindio.poo.monedero.app.SceneLoader;
import edu.co.uniquindio.poo.monedero.controller.TransaccionController;
import edu.co.uniquindio.poo.monedero.controller.BancoController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import edu.co.uniquindio.poo.monedero.model.*;
import edu.co.uniquindio.poo.monedero.exceptions.*;

public class TransaccionViewController {

    @FXML private TextField txtMonto;
    @FXML private ComboBox<String> cbTipoTransaccion;
    @FXML private ComboBox<Cliente> cbDestino;

    private Monedero monederoActual;
    private Cliente clienteOrigen;

    private final TransaccionController transaccionController = new TransaccionController();

    @FXML
    public void initialize() {
        cbTipoTransaccion.getItems().addAll("Deposito", "Retiro", "Transferencia");
    }

    public void cargarDatos(Monedero monedero, Cliente origen) {
        this.monederoActual = monedero;
        this.clienteOrigen = origen;

        cbDestino.getItems().setAll(
                BancoController.getInstance().listarClientes()
                        .stream()
                        .filter(c -> c != origen)
                        .toList()
        );
    }

    @FXML
    private void ejecutarTransaccion() {
        try {
            if (cbTipoTransaccion.getValue() == null) {
                throw new CampoVacioException("Seleccione un tipo de transacción.");
            }

            if (txtMonto.getText().isBlank()) {
                throw new CampoVacioException("Ingrese un monto.");
            }

            double monto = Double.parseDouble(txtMonto.getText());
            String tipo = cbTipoTransaccion.getValue();
            Cliente destino = cbDestino.getValue();

            if (tipo.equals("Transferencia") && destino == null) {
                throw new CampoVacioException("Seleccione un cliente destino.");
            }

            Transaccion t = transaccionController.crearTransaccion(
                    tipo, monto, clienteOrigen, destino, monederoActual
            );

            transaccionController.ejecutarTransaccion(t);

            new Alert(Alert.AlertType.INFORMATION, "Transacción realizada exitosamente").show();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "El monto debe ser numérico.").show();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void volver() {
        MonederoViewController controller =
                (MonederoViewController) SceneLoader.cargarVista(
                        "edu/co/uniquindio/poo/monedero/monedero.fxml"
                );

        controller.cargarMonedero(monederoActual, clienteOrigen);
    }
}
