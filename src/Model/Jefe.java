package Model;

public class Jefe extends Enemigo {

    public Jefe(double x, double y) {
        super(x, y);
       setVida(15);
    }

    @Override
    public boolean esJefe(){
        return true;
    }
    
}
