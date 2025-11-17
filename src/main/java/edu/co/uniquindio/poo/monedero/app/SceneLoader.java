package edu.co.uniquindio.poo.monedero.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {

    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static <T> T cargarVista(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    SceneLoader.class.getClassLoader().getResource(rutaFXML)
            );

            Parent root = loader.load();
            Scene scene = new Scene(root);

            mainStage.setScene(scene);
            mainStage.show();

            return loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar FXML: " + rutaFXML);
            return null;
        }
    }
}
