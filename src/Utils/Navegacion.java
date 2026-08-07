package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navegacion {


       
    public static <T> T cambiarEscena(String rutaFXML, String titulo, Stage stage){

        try {
            FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource(rutaFXML));

            Scene scene = new Scene(loader.load());
           
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

            return loader.getController();

        } catch (Exception e) {

            throw new RuntimeException("Error al abrir la ventana:" + rutaFXML, e);
        }
    }

       
    
}
