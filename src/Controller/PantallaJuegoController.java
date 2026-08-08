package Controller;

import Engine.FuncionamientoJuego;
import Model.Jugador;
import Model.Niveles;
import View.SpritesJuego;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.io.File;

public class PantallaJuegoController {

       @FXML
    private Canvas canvaJuego;

    @FXML
    private Label lblNivel;

    @FXML
    private Label lblPuntaje;

    @FXML
    private Label lblVida;

    @FXML
    private AnchorPane rootPane;

    private Jugador jugador;
    private FuncionamientoJuego funcionamientoJuego;
    private boolean teclaIzq;
    private boolean teclaDer;


    @FXML void initialize(){
        jugador = new Jugador(347, 445);

        Image fondo = new Image( getClass().getResourceAsStream ("/Resources/img/fondojuego.jpg"));
        SpritesJuego sprites = new SpritesJuego(fondo);
        Niveles niveles = new Niveles();

        funcionamientoJuego = new FuncionamientoJuego(jugador, niveles, sprites, canvaJuego.getGraphicsContext2D(), this, canvaJuego.getWidth(), canvaJuego.getHeight());

        rootPane.setOnKeyPressed(this::manejarTeclaPresionada);
        rootPane.setOnKeyReleased(this::manejarTeclaSoltada);
        rootPane.setFocusTraversable(true);

        Platform.runLater(() -> rootPane.requestFocus());
        funcionamientoJuego.start();
        
    }

    private void manejarTeclaPresionada(javafx.scene.input.KeyEvent evento){
        if (evento.getCode() == KeyCode.LEFT){
            teclaIzq = true;
        }

        if(evento.getCode() == KeyCode.RIGHT){
            teclaDer = true;
        }

        if(evento.getCode() == KeyCode.SPACE){
            funcionamientoJuego.dispararJugador();
        }
    }

    private void manejarTeclaSoltada(javafx.scene.input.KeyEvent evento){
        if(evento.getCode() == KeyCode.LEFT){
            teclaIzq = false;
        }
        if(evento.getCode() == KeyCode.RIGHT){
            teclaDer = false;
        }
    }

    public boolean esTeclaIzq(){
        return teclaIzq;

    }

    public boolean esTeclaDer(){
        return teclaDer;
    }

    public void actualizar(int nivel, int puntaje, int vidas){
        lblNivel.setText(String.valueOf(nivel));
        lblPuntaje.setText(String.valueOf(puntaje));
        lblVida.setText(String.valueOf(vidas));
    }



    
}
