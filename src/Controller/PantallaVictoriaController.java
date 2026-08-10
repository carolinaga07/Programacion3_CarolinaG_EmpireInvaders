package Controller;
import Utils.Navegacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PantallaVictoriaController {

    @FXML
    private ImageView IVFondoV;

    @FXML
    private ImageView IVNave;

    @FXML
    private ImageView IVvictoria;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblPuntajeFinal;

    private Stage stageJuego;
    
    @FXML
    private void initialize(){
        Image fondo = new Image(getClass().getResourceAsStream("/Resources/img/fondoVictoria.jpg"));
        IVFondoV.setImage(fondo);

        Image nave = new Image(getClass().getResourceAsStream("/Resources/img/milleniumFalcon.gif"));
        IVNave.setImage(nave);

        Image lblVictoria = new Image(getClass().getResourceAsStream("/Resources/img/lblVictoria.png"));
        IVvictoria.setImage(lblVictoria);
    }

    public void setPuntaje(int puntaje){
        lblPuntajeFinal.setText(String.valueOf(puntaje));
    }

    @FXML
    private void volverAlMenu(){
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        if(stageJuego != null){
            stageJuego.close();
        }
        Navegacion.cambiarEscena("/View/PantallaPrincipal.fxml", "Empire Invaders", stage);
    }

    public void setStageJuego(Stage stageJuego){
        this.stageJuego = stageJuego;
    }
}
