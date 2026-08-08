package Controller;




import java.net.URL;
import java.util.ResourceBundle;

import Main.App;
import Utils.Navegacion;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class PantallaPrincipalController implements Initializable {

    @FXML
    private ImageView ivEnemigo1;

    @FXML
    private Label lblBoton;

    @FXML
    private Label lblBoton2;

    @FXML
    private Label lblEslogan;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        TranslateTransition animacion = new TranslateTransition(Duration.seconds(1.2), ivEnemigo1);
        animacion.setByY(-25);
        animacion.setAutoReverse(true);
        animacion.setCycleCount(TranslateTransition.INDEFINITE);
        animacion.play();

        lblBoton.setCursor(Cursor.HAND);

        lblBoton.setOnMouseClicked(event ->{
            PantallaJuegoController controller = Navegacion.cambiarEscena("/View/PantallaJuego.fxml", "Empire Invaders", App.stagePrincipal);


        });

        lblBoton2.setOnMouseClicked(event ->{
            Platform.exit();
        });
    }

    

    
}
