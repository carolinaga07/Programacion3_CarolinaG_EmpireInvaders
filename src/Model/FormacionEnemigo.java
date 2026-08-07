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


        for(int fila = 0; fila < filas; fila++){
            for(int columna = 0; columna < columnas; columna++){
                double x = xInicial + columna * espacioX;
                double y = yInicial + fila * espacioY;
                enemigos.add(new Enemigo(x, y));
            }
        }


    }

    public FormacionEnemigo(List<Enemigo> enemigoFinal){
        enemigos = enemigoFinal;
        velocidadX = 1.0;
        velocidadDescenso = 20;
        movimientoDerecha = true;
    }

    public void actualizar(double limiteIzq, double limiteDer){
        boolean tocaBorde = false;

        for(Enemigo e : enemigos){
            if(!e.estaVivo()){
                continue;
            }
            if(e.getX() <= limiteIzq || e.getX() >= limiteDer){
                tocaBorde = true;
            }
        }  
          
        double dx;
        if(movimientoDerecha){
            dx = velocidadX;
        }else{
            dx = -velocidadX;
        }

        if (tocaBorde){
            for(Enemigo e : enemigos){
                e.mover(0,velocidadDescenso);
            }
            movimientoDerecha = !movimientoDerecha;
        }
        else{
            for(Enemigo e: enemigos){
                e.mover(dx, 0);
            }
        }
    
    }  

    public List<Enemigo> getEnemigos(){
        return enemigos;
    }

    public boolean quedanVivos(){
        for(Enemigo e : enemigos){
            if(e.estaVivo()){
                return true;
            }
        }
        return false;
    }

}
