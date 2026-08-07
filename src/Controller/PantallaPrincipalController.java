package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class PantallaPrincipalController {

    @FXML
    private ImageView ivEnemigo1;

    @FXML
    private Label lblBoton;

    @FXML
    private Label lblBoton2;

    @FXML
    public void initialize(){
         Font miFuente = Font.loadFont((getClass().getResourceAsStream("Resources/font/pixelFont.ttf")), 18);

         if(miFuente != null){
            lblBoton.setFont(miFuente);
            lblBoton2.setFont(miFuente);
         }
         else{
            System.out.println("Error: no se pudo cargar el archivo de la fuente.");
         }
    }
    
}
