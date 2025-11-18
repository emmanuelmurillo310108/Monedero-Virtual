package edu.co.uniquindio.poo.monedero.app;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Banco Virtual");
        stage.setWidth(900);
        stage.setHeight(600);
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        SceneLoader.setMainStage(stage);

        Object controller = SceneLoader.cargarVista("edu/co/uniquindio/poo/monedero/banco.fxml");

        if (controller == null) {
            System.err.println("No se pudo cargar la vista principal (banco.fxml); revisar rutas y module-info.java");
            new Alert(Alert.AlertType.ERROR,
                    "Error al cargar la interfaz principal; revisar la consola para más detalles")
                    .showAndWait();
            stage.close();
            return;
        }

        if (!stage.isShowing()) {
            stage.show();
        }
        stage.centerOnScreen();

        stage.setOnCloseRequest(ev -> {
            System.out.println("cerrando app");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
