package Model;

public class Jugador {
    private double x;
    private double y;
    private int vidas;
    private double velocidad;
    private static final double tamanoS = 130;
   
   
    public Jugador(double x, double y) {
        this.x = x;
        this.y = y;
        this.vidas = 5;
        this.velocidad = 5;
    }

    public void moverIzquierda(){
        x -= velocidad;
        if(x < 0){
            x = 0;
        }
    }

    public void moverDerecha(double limite){
        x += velocidad;

        if(x > limite - tamanoS){
            x = limite - tamanoS;
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
