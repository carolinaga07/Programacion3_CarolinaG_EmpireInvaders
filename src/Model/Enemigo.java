package Model;

public class Enemigo {

    private double x;
    private double y;
    private boolean vivo;
    private int vida;

    public Enemigo(double x, double y) {
        this.x = x;
        this.y = y;
        this.vida = 1;
        this.vivo = true;
    }

    public  void mover(double X, double Y){
        x += X;
        y += Y;

    }

    public void RecibirDanio(){
        vida--;
        if(vida <= 0){
            vivo = false;
        }
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

    protected void setVida(int vida){
        this.vida = vida;
    }

}
