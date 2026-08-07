package Controller;



import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class PantallaPrincipalController  {

    @FXML
    private ImageView ivEnemigo1;

    @FXML
    private Label lblBoton;

    @FXML
    private Label lblBoton2;

    @FXML
    private Label lblEslogan;

    @FXML
    public void initialize(){
        TranslateTransition animacion = new TranslateTransition(Duration.seconds(1.2), ivEnemigo1);
        animacion.setByY(-25);
        animacion.setAutoReverse(true);
        animacion.setCycleCount(TranslateTransition.INDEFINITE);
        animacion.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        lblBoton.setCursor(Cursor.HAND);

        lblBoton.setOnMouseClicked(event ->{
            

        });
    }

    

    
}
