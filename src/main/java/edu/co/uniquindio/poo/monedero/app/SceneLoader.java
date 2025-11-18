package edu.co.uniquindio.poo.monedero.app;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class SceneLoader {

    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static <T> T cargarVista(String rutaFXML) {
        try {
            URL resource = SceneLoader.class.getResource("/" + rutaFXML);

            if (resource == null) {
                throw new IOException("No se encontró archivo FXML: " + rutaFXML);
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

            fadeIn(root);

            return loader.getController();

        } catch (IOException e) {
            System.err.println("Error cargando FXML: " + rutaFXML);
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T cargarEnNuevaVentana(String rutaFXML, String titulo) {
        try {
            URL resource = SceneLoader.class.getResource("/" + rutaFXML);

            if (resource == null) {
                throw new IOException("No se encontró archivo FXML: " + rutaFXML);
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            Stage ventana = new Stage();
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(root));
            ventana.show();

            fadeIn(root);

            return loader.getController();

        } catch (IOException e) {
            System.err.println("Error cargando FXML en ventana nueva: " + rutaFXML);
            e.printStackTrace();
            return null;
        }
    }

    private static void fadeIn(Parent root) {
        FadeTransition ft = new FadeTransition(Duration.millis(250), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
