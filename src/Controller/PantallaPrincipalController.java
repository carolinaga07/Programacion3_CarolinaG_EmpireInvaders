package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

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
    public void initialize(URL location, ResourceBundle resources) {
        Font miFuente = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 36);
        lblBoton.setFont(miFuente);
        lblBoton2.setFont(miFuente);
        lblEslogan.setFont(miFuente);
    }
    
    
}
