package Utils;

import Model.Enemigo;
import Model.Jugador;
import Model.Rayo;

public class ArchivoUtil {
    
    private static final double tamanoE = 30;
    private static final double tamanoJ = 40;
    private static final double tamanoR = 5;


    public static boolean colision(Rayo rayo, Enemigo enemigo){
        return rayo.getX() < enemigo.getX() + tamanoE &&
        rayo.getX() + tamanoR > enemigo.getX() &&
        rayo.getY() < enemigo.getY() + tamanoE &&
        rayo.getY() + tamanoR > enemigo.getY();
    }

    public static boolean colision(Rayo rayo, Jugador jugador){
        return rayo.getX() < Jugador.getX() + tamanoE &&
        rayo.getX() + tamanoR > enemigo.getX() &&
        rayo.getY() < enemigo.getY() + tamanoE &&
        rayo.getY() + tamanoR > enemigo.getY();
    }

    
    
}
