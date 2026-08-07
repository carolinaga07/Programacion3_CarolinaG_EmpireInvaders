package Model;

public class Rayo {

    private double x;
    private double y;
    private double velocidad;
    private boolean activo;


    public Rayo(double x, double y, double velocidad, boolean activo) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.activo = true;
    }


    public void mover(){
        y += velocidad;
    }

    public void desactivar(){
        activo = false;
    }

    public boolean estaActivo(){
        return activo;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }


    
    
}
