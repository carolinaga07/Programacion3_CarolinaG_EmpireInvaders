package Model;

public class Jugador {
    private double x;
    private double y;
    private int vidas;
    private double velocidad;
   
   
    public Jugador(double x, double y) {
        this.x = x;
        this.y = y;
        this.vidas = 3;
        this.velocidad = 5;
    }

    public void moverIzquierda(){
        x -= velocidad;
    }

    public void moverDerecha(double limite){
        x += velocidad;

        if(x > limite){
            x = limite;
        }
    }


    public void perderVida(){
        vidas--;
    }

    public boolean estarVivo(){
        return vidas > 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getVidas() {
        return vidas;
    }

    



    

    


}
