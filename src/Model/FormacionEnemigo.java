package Model;

import java.util.ArrayList;
import java.util.List;

public class FormacionEnemigo {

    private List<Enemigo> enemigos;
    private double velocidadX;
    private double velocidadDescenso;
    private boolean  movimientoDerecha;

    public FormacionEnemigo( int filas, int columnas, double espacioX, double espacioY, double xInicial, double yInicial) {
        this.enemigos = new ArrayList<>();
        this.velocidadX = 1.0;
        this.velocidadDescenso = 20;
        this.movimientoDerecha = true;

        // Parte que arma la cuadricula de la formacion para poder recorrer los enemigos como si fuesen uno solo
        for(int fila = 0; fila < filas; fila++){
            for(int columna = 0; columna < columnas; columna++){
                double x = xInicial + columna * espacioX;
                double y = yInicial + fila * espacioY;
                enemigos.add(new Enemigo(x, y)); 
            }
        }


    }

    public FormacionEnemigo(List<Enemigo> enemigoFinal){ // se usa solo para el jefe final ya que es uno
        enemigos = enemigoFinal;
        velocidadX = 1.0;
        velocidadDescenso = 20;
        movimientoDerecha = true;
    }

    public void actualizar(double limiteIzq, double limiteDer){ // movimiento de los invasores
        boolean tocaBorde = false;

        for(Enemigo e : enemigos){
            if(!e.estaVivo()){
                continue;
            }
            if(movimientoDerecha && e.getX() >= limiteDer){ 
                tocaBorde = true;
            }
            if(!movimientoDerecha && e.getX() <= limiteIzq){
                tocaBorde = true;
            }
        }  
          
        double dx;
        if(movimientoDerecha){
            dx = velocidadX; // el signo determina la direccion
        }else{
            dx = -velocidadX;
        }

        if (tocaBorde){ // todos bajan un escalon e invierten su direccion de movimiento
            for(Enemigo e : enemigos){
                e.mover(0,velocidadDescenso);
            }
            movimientoDerecha = !movimientoDerecha;
        }
        else{  // todos se mueven lateralmente
            for(Enemigo e: enemigos){
                e.mover(dx, 0);
            }
        }
    
    }  

    public boolean esFormacionJ(){
        if(enemigos.isEmpty()){
            return false;
        }
        return enemigos.get(0).esJefe();
    }

    public List<Enemigo> getEnemigos(){
        return enemigos;
    }

    public boolean quedanVivos(){ // lo que determina si se puede cambiar a la siguiente oleada de enemigos
        for(Enemigo e : enemigos){
            if(e.estaVivo()){
                return true;
            }
        }
        return false;
    }

}
