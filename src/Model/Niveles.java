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

   private int maxOleadas(int nivel){ //define cuanta oleadas va a tener cada nivel
    if(nivel == 3){
        return 3;
    }
    return 2;
   }


   public FormacionEnemigo Formaciones(double xInicial, double yInicial){ //decide la formacion a crear es decir cuanto enemigos tendra

       if (nivelActual == 3 && oleadaActual == 2){ //revisa si la oleada actual es la del jefe
        List<Enemigo> jefe = new ArrayList<>();
        jefe.add(new Jefe(xInicial, yInicial));
        return new FormacionEnemigo(jefe);

       }
       switch (nivelActual) {  // determina el tamano de la cuadricula
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

   public void siguienteOleada(){  // contabiliza las oleadas de cada nivel para conocer cuando pasar a la sigte
    if(oleadaActual < maxOleadas(nivelActual)){
        oleadaActual++;
    } else {
        siguienteNivel();
        oleadaActual =1;
    }
   }

   public void siguienteNivel(){
     nivelActual++;
   }

   public boolean esNivelFinal(){
     return nivelActual == 3 && oleadaActual == 2;
   }

   public int getNivelActual(){
    return nivelActual;
   }

   public boolean hayMasNiveles(){ // dispara la pantalla de victoria
      return nivelActual < 3 || (nivelActual == 3 && oleadaActual < 2);
   }
}
