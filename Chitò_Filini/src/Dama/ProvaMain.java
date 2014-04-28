package Dama;

import Grafica.GridLayoutDemo;

public class ProvaMain {
    public static GridLayoutDemo damieraGrafica;
    public static boolean turnWhite = true;
    public static Damiera damiera;
    
    public static void main(String[] args){
        damiera= new Damiera();
        
        damiera.inizializzaArrayPedineBianche();
        damiera.inizializzaArrayPedineNere();
        damiera.damieraDiBottoni();
        
        damieraGrafica = new GridLayoutDemo("Demo");
        damieraGrafica.setVisible(true);
        
        
        
    }
    
}
