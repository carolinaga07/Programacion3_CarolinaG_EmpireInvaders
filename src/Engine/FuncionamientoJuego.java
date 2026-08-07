package Engine;

import java.util.ArrayList;
import java.util.List;

import Model.FormacionEnemigo;
import Model.Jugador;
import Model.Niveles;
import Model.Rayo;
import View.SpritesJuego;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class FuncionamientoJuego extends AnimationTimer {

    private Jugador jugador;
    private FormacionEnemigo formacion;
    private Niveles niveles;
    private SpritesJuego sprites;
    private GraphicsContext gc;
    private int puntaje;
    private double ancho;
    private double alto;
    private List<Rayo> RayosJugador;
    private List<Rayo> RayoEnemigo;
    private long ultimoDisparoE;


    


    public FuncionamientoJuego(Jugador jugador, Niveles niveles, SpritesJuego sprites,
            GraphicsContext gc,  double ancho, double alto) {
        this.jugador = jugador;
        this.formacion = niveles.Formaciones(50, 50);
        this.niveles = niveles;
        this.sprites = sprites;
        this.gc = gc;
        this.puntaje = 0;
        this.ancho = ancho;
        this.alto = alto;
        this.RayosJugador = new ArrayList<>();
        this.RayoEnemigo = new ArrayList<>();
        this.ultimoDisparoE = 0;
    }


    @Override
    public void handle(long now) {
        actualizar();
        sprites.render(gc, jugador, formacion, puntaje, jugador.getVidas());
        
    }

    public void actualizar(){
        formacion.actualizar(0, ancho);

        if(!formacion.quedanVivos()){
            if(niveles.hayMasNiveles()){
                niveles.siguienteOleada();
                formacion = niveles.Formaciones(50, 50);
            }else{
                stop();
                ///pantalla victoria
            }
        }

        if(!jugador.estarVivo()){
            stop();
            ///pantalla de game over
        }
    }
    
}
