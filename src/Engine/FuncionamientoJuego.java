package Engine;

import java.util.ArrayList;
import java.util.List;

import Controller.PantallaDerrotaController;
import Controller.PantallaJuegoController;
import Controller.PantallaVictoriaController;
import Model.Enemigo;
import Model.FormacionEnemigo;
import Model.Jugador;
import Model.Niveles;
import Model.Rayo;
import Utils.ArchivoUtil;
import View.SpritesJuego;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

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
        sprites.render(gc, jugador, formacion, RayosJugador, RayoEnemigo, puntaje, jugador.getVidas());
        controller.actualizar(niveles.getNivelActual(), puntaje, jugador.getVidas());
        
    }

    public void dispararJugador(){
        RayosJugador.add(new Rayo(jugador.getX() + 65, jugador.getY(), -6, false));
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
        double margenDer = formacion.esFormacionJ() ? 120: 50;
        formacion.actualizar(0, ancho - margenDer);
        moverRayos();
        dispararInvasores(now);
        detectarColisiones();
        limpiarRayos();
        revisarFindeOleada();


        if(!jugador.estarVivo()){
            stop();
            mostrarGameOver();
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
            sprites.limpiarVistaEnemigos();
            formacion = niveles.Formaciones(50, 50);
        }else{
            stop();
            mostrarVictoria();
            
        }

       
    } 

    private void mostrarGameOver(){
        Platform.runLater(() ->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PantallaDerrota.fxml"));
                Parent root = loader.load();

                PantallaDerrotaController controller = loader.getController();
                controller.setPuntaje(puntaje);

                Stage stage = new Stage();
                stage.setTitle("Juego Finalizado");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private void mostrarVictoria(){
        Platform.runLater(() ->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PantallaVictoria.fxml"));
                Parent root = loader.load();

                PantallaVictoriaController controller = loader.getController();
                controller.setPuntaje(puntaje);

                Stage stage = new Stage();
                stage.setTitle("Juego Ganado");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
   
   

}
