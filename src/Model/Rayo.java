package Model;

public class Rayo {

//*lo denomine Rayo porque en StarWars las naves usan rayos lasers 
// tambien en el codigo se usa una sola clase de Rayo para ambos
//  ya que lo que cambia es la dirreccion*/ 

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

    public void desactivar(){ //cuando choca con algo se desactiva
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
