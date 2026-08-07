package Model;

public class Enemigo {

    private double x;
    private double y;
    private boolean vivo;

    public Enemigo(double x, double y) {
        this.x = x;
        this.y = y;
        this.vivo = true;
    }

    public  void mover(double X, double Y){
        x += X;
        y += Y;

    }

    public void eliminar(){
        vivo = false;
    }

    public boolean estaVivo(){
        return vivo;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

}
