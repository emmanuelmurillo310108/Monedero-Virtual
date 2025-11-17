package edu.co.uniquindio.poo.monedero.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Banco Virtual");

        SceneLoader.setMainStage(stage);

        SceneLoader.cargarVista("fxml/banco.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
