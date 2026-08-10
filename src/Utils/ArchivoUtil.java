package Utils;

import Model.Enemigo;
import Model.Jugador;
import Model.Rayo;

public class ArchivoUtil {
    
    private static final double tamanoE = 30;
    private static final double tamanoJ = 40;
    private static final double tamanoR = 5;

 //compara si los rayos y el enemigo se superponen en la misma posicion alli se crea la colision
    public static boolean colision(Rayo rayo, Enemigo enemigo){
        return rayo.getX() < enemigo.getX() + tamanoE &&
        rayo.getX() + tamanoR > enemigo.getX() &&
        rayo.getY() < enemigo.getY() + tamanoE &&
        rayo.getY() + tamanoR > enemigo.getY();
    }
 // lo mismo pero con el jugador
    public static boolean colision(Rayo rayo, Jugador jugador){
        return rayo.getX() < jugador.getX() + tamanoJ &&
        rayo.getX() + tamanoR > jugador.getX() &&
        rayo.getY() < jugador.getY() + tamanoJ &&
        rayo.getY() + tamanoR > jugador.getY();
    }

    
    
}
