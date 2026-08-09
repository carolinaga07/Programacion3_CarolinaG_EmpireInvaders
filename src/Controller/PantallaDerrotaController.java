package Controller;

import Utils.Navegacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PantallaDerrotaController {

       @FXML
    private ImageView IVEstrella;

    @FXML
    private ImageView IVfondoDerrota;

    @FXML
    private ImageView IVlblDerrota;

    @FXML
    private Button btnReiniciar;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblPuntajeAlcanzado;


       
    @FXML
    private void initialize(){
        Image fondo = new Image(getClass().getResourceAsStream("/Resources/img/fondoDerrota.jpg"));
        IVfondoDerrota.setImage(fondo);

        Image estrella = new Image(getClass().getResourceAsStream("/Resources/img/estrelladelamuerteCompleta.gif"));
        IVEstrella.setImage(estrella);

        Image lblDerrota = new Image(getClass().getResourceAsStream("/Resources/img/lblDerrota.png"));
        IVlblDerrota.setImage(lblDerrota);
    }

    public void setPuntaje(int puntaje){
        lblPuntajeAlcanzado.setText(String.valueOf(puntaje));
    }

    @FXML
    private void volverAlMenu(){
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        Navegacion.cambiarEscena("/View/PantallaPrincipal.fxml", "Empire Invaders", stage);
    }

    @FXML
    private void reiniciarJuego(){
        Stage stage = (Stage) btnReiniciar.getScene().getWindow();
        Navegacion.cambiarEscena("/View/PantallaJuego.fxml", "Empire Invaders", stage);
    }
    
}
