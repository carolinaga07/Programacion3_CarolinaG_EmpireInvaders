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


public class SpritesJuego {
    private Image fondo;
    private double fondoOffsetY;
    private final double velocidadF = 1.5;

    private AnchorPane pane;
    private ImageView jugadorView;
    private Image enemigo1Gif;
    private Image enemigo2Gif;
    private Image jefeGif;
    private Image jefeDestruidoGif;
    private Map<Enemigo, ImageView> vistaEnemigos; // asocia cada objeto a su imageView para saber cual mover o eliminar

    public SpritesJuego(Image fondo, Image jugadorGif, Image enemigo1Gif, Image enemigo2Gif, Image jefeGif, Image jefeDestruidoGif, AnchorPane pane){
        this.fondo = fondo;
        this.pane = pane;
        this.enemigo1Gif = enemigo1Gif;
        this.enemigo2Gif = enemigo2Gif;
        this.jefeGif = jefeGif;
        this.jefeDestruidoGif = jefeDestruidoGif;
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

    private void dibujarFondo(GraphicsContext gc, double ancho, double alto) { //dibuja las imagenes juntas una arriba de la otra y las mueve juntas hacia abajo 
        fondoOffsetY += velocidadF;
        if(fondoOffsetY >= alto){
            fondoOffsetY = 0;
        }

        gc.drawImage(fondo, 0, fondoOffsetY - alto, ancho, alto);
        gc.drawImage(fondo, 0, fondoOffsetY, ancho, alto);
    }

    public void limpiarVistaEnemigos(){ //solucion a que queden enemigos en pantalla al dispararles
        for(ImageView vista : vistaEnemigos.values()){
            pane.getChildren().remove(vista);
        }
        vistaEnemigos.clear();
    }

    private void actualizarVistaEnemigos(FormacionEnemigo formacion){ //recorre los invasores de la formacion , si esta vivo y no tiene una Image view crea una y se la asigna.
        int indice = 0;
        for(Enemigo e: formacion.getEnemigos()){
            if(!e.estaVivo()){
                ImageView vista = vistaEnemigos.get(e);
                if(vista != null){
                    if(e.esJefe()){
                        mostrarExplosionJefe(e, vista);
                    }else{
                    pane.getChildren().remove(vista);
                    vistaEnemigos.remove(e);
                    }
                }
                indice++;
                continue;
            }

            ImageView vista = vistaEnemigos.get(e);
            if(vista == null){
                Image gif;
                double tamano;
                if(e.esJefe()){ //configura el gif y el tamano de los enemigos
                    gif = jefeGif;
                    tamano = 185;
                }else if(indice % 2 == 0){
                    gif = enemigo1Gif;
                    tamano = 45;
                }else{
                    gif = enemigo2Gif;
                    tamano = 45;
                }
                vista = new ImageView(gif); // si no tiene imageview la crea y guarda su posicion
                vista.setFitWidth(tamano);
                vista.setFitHeight(tamano);
                vistaEnemigos.put(e, vista);
                pane.getChildren().add(vista);
            }
            vista.setLayoutX(e.getX()); //actualiza su posicion 
            vista.setLayoutY(e.getY());
            indice++;
        }
    }

    private void mostrarExplosionJefe(Enemigo e, ImageView vista){
        vista.setImage(jefeDestruidoGif);
        vistaEnemigos.remove(e);
    }
    
}
