package View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Enemigo;
import Model.FormacionEnemigo;
import Model.Jugador;
import Model.Rayo;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SpritesJuego {
    private Image fondo;
    private double fondoOffsetY;
    private final double velocidadF = 1.5;

    private AnchorPane pane;
    private ImageView jugadorView;
    private Image enemigo1Gif;
    private Image enemigo2Gif;
    private Image jefeGif;
    private Map<Enemigo, ImageView> vistaEnemigos;

    public SpritesJuego(Image fondo, Image jugadorGif, Image enemigo1Gif, Image enemigo2Gif, Image jefeGif, AnchorPane pane){
        this.fondo = fondo;
        this.pane = pane;
        this.enemigo1Gif = enemigo1Gif;
        this.enemigo2Gif = enemigo2Gif;
        this.jefeGif = jefeGif;
        this.vistaEnemigos = new HashMap<>();

        jugadorView = new ImageView(jugadorGif);
        jugadorView.setFitWidth(135);
        jugadorView.setFitHeight(135);
        pane.getChildren().add(jugadorView);
    }

    public void render(GraphicsContext gc, Jugador jugador, FormacionEnemigo formacion, List<Rayo> rayoJugador, List<Rayo> rayoEnemigo, int puntaje, int vidas){
        double ancho = gc.getCanvas().getWidth();
        double alto = gc.getCanvas().getHeight();

        dibujarFondo(gc, ancho, alto);
        dibujarRayos(gc, rayoJugador, rayoEnemigo);
        jugadorView.setLayoutX(jugador.getX());
        jugadorView.setLayoutY(jugador.getY());

        actualizarVistaEnemigos(formacion);

    }

    private void dibujarRayos(GraphicsContext gc, List<Rayo> rayoJugador, List<Rayo> rayoEnemigo){
        gc.setFill(Color.BLUE);
        for(Rayo r: rayoJugador){
            gc.fillRect(r.getX(), r.getY(), 5, 10);
        }
        gc.setFill(Color.RED);
        for(Rayo r: rayoEnemigo){
            gc.fillRect(r.getX(), r.getY(), 5, 10);
        }
    }

    private void dibujarFondo(GraphicsContext gc, double ancho, double alto) {
        fondoOffsetY += velocidadF;
        if(fondoOffsetY >= alto){
            fondoOffsetY = 0;
        }

        gc.drawImage(fondo, 0, fondoOffsetY - alto, ancho, alto);
        gc.drawImage(fondo, 0, fondoOffsetY, ancho, alto);
    }

    private void actualizarVistaEnemigos(FormacionEnemigo formacion){
        int indice = 0;
        for(Enemigo e: formacion.getEnemigos()){
            if(!e.estaVivo()){
                ImageView vista = vistaEnemigos.remove(e);
                if(vista != null){
                    pane.getChildren().remove(vista);
                }
                indice++;
                continue;
            }

            ImageView vista = vistaEnemigos.get(e);
            if(vista == null){
                Image gif;
                double tamano;
                if(e.esJefe()){
                    gif = jefeGif;
                    tamano = 200;
                }else if(indice % 2 == 0){
                    gif = enemigo1Gif;
                    tamano = 55;
                }else{
                    gif = enemigo2Gif;
                    tamano = 55;
                }
                vista = new ImageView(gif);
                vista.setFitWidth(tamano);
                vista.setFitHeight(tamano);
                vistaEnemigos.put(e, vista);
                pane.getChildren().add(vista);
            }
            vista.setLayoutX(e.getX());
            vista.setLayoutY(e.getY());
            indice++;
        }
    }
    
}
