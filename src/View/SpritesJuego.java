package View;

import Model.Enemigo;
import Model.FormacionEnemigo;
import Model.Jugador;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SpritesJuego {
    private Image fondo;
    private double fondoOffsetY;
    private final double velocidadF = 1.5;

    public SpritesJuego(Image fondo){
        this.fondo = fondo;
        this.fondoOffsetY = 0;
    }

    public void render(GraphicsContext gc, Jugador jugador, FormacionEnemigo formacion, int puntaje, int vidas){
        double ancho = gc.getCanvas().getWidth();
        double alto = gc.getCanvas().getHeight();

        dibujarFondo(gc, ancho, alto);
        dibujarJugador(gc, jugador);
        dibujarEnemigos(gc, formacion);
      


    }

    private void dibujarTablaP(GraphicsContext gc, int puntaje, int vidas) {

        gc.setFill(Color.WHITE);
        gc.setFont(new Font(16));
        gc.fillText("Puntos: " + puntaje, 10, 20);
        gc.fillText("Vidas: " + vidas, 10, 40);
        
    }

    private void dibujarEnemigos(GraphicsContext gc, FormacionEnemigo formacion) {
        for(Enemigo e : formacion.getEnemigos()){
            if(!e.estaVivo()){
                continue;
            }

            if(e.esJefe()){
                gc.setFill(Color.RED);
                gc.fillOval(e.getX(), e.getY(), 80, 80);
            }else{
                gc.setFill(Color.WHITE);
                gc.fillRect(e.getX(), e.getY(), 30, 30);
            }
        }
    }

    private void dibujarJugador(GraphicsContext gc, Jugador jugador) {
        gc.setFill(Color.LIME);
        gc.fillRect(jugador.getX(), jugador.getY(), 40, 40);
    }

    private void dibujarFondo(GraphicsContext gc, double ancho, double alto) {
        fondoOffsetY += velocidadF;
        if(fondoOffsetY >= alto){
            fondoOffsetY = 0;
        }

        gc.drawImage(fondo, 0, fondoOffsetY - alto, ancho, alto);
        gc.drawImage(fondo, 0, fondoOffsetY, ancho, alto);
    }
    
}
