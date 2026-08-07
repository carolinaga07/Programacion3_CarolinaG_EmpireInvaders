package Model;

import java.util.ArrayList;
import java.util.List;

public class Niveles {
   private int nivelActual;
   private int oleadaActual;

   public  Niveles(){
      this.nivelActual = 1;
      this.oleadaActual = 1;
   }


   public FormacionEnemigo Formaciones(double xInicial, double yInicial){

       if (nivelActual == 3 && oleadaActual == 2){
        List<Enemigo> jefe = new ArrayList<>();
        jefe.add(new Jefe(xInicial, yInicial));
        return new FormacionEnemigo(jefe);

       }
       switch (nivelActual) {
        case 1:
            return new FormacionEnemigo(3, 6, 50, 40, xInicial, yInicial);
        case 2:
            return new FormacionEnemigo(4, 7, 45, 40, xInicial, yInicial);
        case 3: 
            return new FormacionEnemigo(5, 8, 40, 35, xInicial, yInicial);    
    
        default:
            return new FormacionEnemigo(3, 6, 50, 40, xInicial, yInicial);
       }
   }

   public void siguienteOleada(){
    if(nivelActual == 3 && oleadaActual == 1){
        oleadaActual = 2;
    } else {
        siguienteNivel();
        oleadaActual =1;
    }
   }

   public void siguienteNivel(){
     nivelActual++;
   }

   public boolean esNivelFinal(){
     return nivelActual == 3 || (nivelActual == 3 && oleadaActual < 2);
   }

   public int getNivelActual(){
    return nivelActual;
   }

   public boolean hayMasNiveles(){
      return nivelActual < 3 || (nivelActual == 3 && oleadaActual < 2);
   }
}
