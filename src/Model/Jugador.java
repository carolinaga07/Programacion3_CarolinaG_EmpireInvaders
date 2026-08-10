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
        this.vidas = 4;
        this.velocidad = 5;
    }

    public void moverIzquierda(){
        x -= velocidad; // resta la posicion de la nave para moverla a la izquierda
        if(x < 0){
            x = 0;
        }
    }

    public void moverDerecha(double limite){
        x += velocidad; // lo mismo que la anterior

        if(x > limite - tamanoS){ // el limite es el ancho total de la pantalla
            x = limite - tamanoS;// se le resta el tamano del sprite por como JavaFx dibuja izq - derecha
        }
    }


    public void perderVida(){ // es lo que se llama cuando una bala enemiga impacta al jugador
        vidas--;
    }

    public boolean estarVivo(){ // se consulta para saber si ya se acabo el juego
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
