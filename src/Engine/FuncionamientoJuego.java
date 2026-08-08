package Engine;

import java.util.ArrayList;
import java.util.List;

import Controller.PantallaJuegoController;
import Model.Enemigo;
import Model.FormacionEnemigo;
import Model.Jugador;
import Model.Niveles;
import Model.Rayo;
import Utils.ArchivoUtil;
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
    private PantallaJuegoController controller;


    


    public FuncionamientoJuego(Jugador jugador, Niveles niveles, SpritesJuego sprites,
            GraphicsContext gc, PantallaJuegoController controller,  double ancho, double alto) {
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
        this.controller = controller;
    }


    @Override
    public void handle(long now) {
        moverJugador();
        actualizar(now);
        sprites.render(gc, jugador, formacion, puntaje, jugador.getVidas());
        controller.actualizar(niveles.getNivelActual(), puntaje, jugador.getVidas());
        
    }

    public void dispararJugador(){
        RayosJugador.add(new Rayo(jugador.getX() + 15, jugador.getY(), -6, false));
    }

    private void moverJugador(){
        if(controller.esTeclaIzq()){
            jugador.moverIzquierda();
        }
        if(controller.esTeclaDer()){
            jugador.moverDerecha(ancho);
        }
    }

    public void actualizar(long now){
        formacion.actualizar(0, ancho);
        moverRayos();
        dispararInvasores(now);
        detectarColisiones();
        limpiarRayos();
        revisarFindeOleada();


        if(!jugador.estarVivo()){
            stop();
            ///pantalla de game over
        }
    }

    public void moverRayos(){
        for( Rayo r: RayosJugador){
            r.mover();

        }
        for(Rayo r : RayoEnemigo){
            r.mover();
        }
    }

    private void dispararInvasores(long now){
        if(now - ultimoDisparoE < 1_000_000_000){
            return;
        }
        for (Enemigo e : formacion.getEnemigos()){
            if(e.estaVivo() && Math.random() < 0.02){
                RayoEnemigo.add(new Rayo(e.getX() + 15, e.getY() + 30, 4, false));
            }
        }
        ultimoDisparoE = now;
    }

    private void detectarColisiones(){
        for(Rayo rayo : RayosJugador){
            for(Enemigo e : formacion.getEnemigos()){
                if(e.estaVivo() && ArchivoUtil.colision(rayo, e)){
                    e.RecibirDanio();
                    rayo.desactivar();
                    if(!e.estaVivo()){
                        puntaje += e.esJefe() ? 100 : 10;
                    }
                }
            }
        }

        for(Rayo rayo: RayoEnemigo){
            if(ArchivoUtil.colision(rayo, jugador)){
                jugador.perderVida();
                rayo.desactivar();
            }
        }

       
    }


    private void limpiarRayos(){
            List<Rayo> rayosJugadorA = new ArrayList<>();
            for(Rayo r : RayosJugador){
               if(r.estaActivo() && r.getY() >= 0){
                    rayosJugadorA.add(r);
                } 
            }
            RayosJugador = rayosJugadorA;

            List<Rayo> rayosEnemigoA = new ArrayList<>();
            for(Rayo r : RayoEnemigo){
                if(r.estaActivo() && r.getY() <= alto){
                    rayosEnemigoA.add(r);
                }
            }
            RayoEnemigo = rayosEnemigoA;
    }

    private void revisarFindeOleada(){
        if(formacion.quedanVivos()){
            return;
        }
        if(niveles.hayMasNiveles()){
            niveles.siguienteOleada();
            formacion = niveles.Formaciones(50, 50);
        }else{
            stop();
            //pantalla victoria
        }
    }

}
