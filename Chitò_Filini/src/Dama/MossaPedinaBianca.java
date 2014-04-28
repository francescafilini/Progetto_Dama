package Dama;

import static Dama.Damiera.arrayPedineBianche;
import static Dama.Damiera.arrayPedineNere;
import static Grafica.GridLayoutDemo.repeatSecondClick;
import javax.swing.JOptionPane;
import static Grafica.GridLayoutDemo.firstClick;
import static Grafica.GridLayoutDemo.secondClick;
import static Grafica.GridLayoutDemo.x1Button;
import static Grafica.GridLayoutDemo.x2Button;
import static Grafica.GridLayoutDemo.y1Button;
import static Grafica.GridLayoutDemo.y2Button;

public class MossaPedinaBianca {
    public static int idMossaBianca = -1;
     
    public void spostamentoPedinaODamoneBianco() {
        for(int i = 0; i < arrayPedineBianche.size(); i++) {
            if(arrayPedineBianche.get(i).getX() == x1Button &&
                    arrayPedineBianche.get(i).getY() == y1Button) {
                if(arrayPedineBianche.get(i) instanceof Damone) {
                    if(puoDamoneBiancoMuoversi() == 2) {
                        repeatSecondClick = false;
                        return;
                    } else {
                        firstClick = false;
                        secondClick = true;
                        JOptionPane.showMessageDialog(null, "Damone: Secondo "
                                + "click non valido!");
                        repeatSecondClick = true;
                        return;
                    }
                } else {
                    if(puoPedinaBiancaMuoversi() == 2) {
                        repeatSecondClick = false;
                        return;
                    } else {
                        firstClick = false;
                        secondClick = true;
                        JOptionPane.showMessageDialog(null, "Pedina: Secondo "
                        + "click non valido!");
                        repeatSecondClick = true;
                        return;
                    }
                }
            }
        }      
    }
       
    private int puoPedinaBiancaMuoversi() {
        int risultato = 0;
        
        //verifico se ha cliccato una casella indietro rispetto alla pedina selezionata
        if(x2Button > x1Button) {
            idMossaBianca = 0;
            risultato = 1;  //false        
        }
        //verifico se il secondo click sia su una casella avanti di una riga e a destra  
        else if(x2Button == (x1Button - 1) && y2Button == (y1Button + 1)) {
            idMossaBianca = 1;
            risultato = 2;  //true
        }
         //verifico se il secondo click sia su una casella avanti di una riga e a sinistra
        else if(x2Button == (x1Button - 1) && y2Button == (y1Button - 1)) {
            idMossaBianca = 10;
            risultato = 2;
        }
        //verifico se la pedina bianca può mangiare in avanti a destra
        else if(x2Button == (x1Button - 2) && y2Button == (y1Button + 2)) {
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (x1Button - 1) && pedina.getY() == (y1Button + 1)) {
                    idMossaBianca = 2;
                    risultato = 2;
                }
        }
        //verifico se la pedina bianca può mangiare in avanti a sinistra
        else if(x2Button == (x1Button - 2) && y2Button == (y1Button - 2)) {
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (x1Button - 1) && pedina.getY() == (y1Button - 1)) {
                    idMossaBianca = 3;
                    risultato = 2;
                }
        } else {
            risultato = 1;  //false
        }
        return risultato;
    }
    
    private int puoDamoneBiancoMuoversi() {
        int risultato = 0;
        
        //verifico se il secondo click sia su una casella indietro di una riga e a destra  
        if(x2Button == (x1Button + 1) && y2Button == (y1Button + 1)) {
            idMossaBianca = 4;
            risultato = 2;        
        }
        //verifico se il secondo click sia su una casella indietro di una riga e a sinistra  
        else if(x2Button == (x1Button + 1) && y2Button == (y1Button - 1)) {
            idMossaBianca = 11;
            risultato = 2;      
        }
        //verifico se il secondo click sia su una casella avanti di una riga e a destra
        else if(x2Button == (x1Button - 1) && y2Button == (y1Button + 1)) {
            idMossaBianca = 5;
            risultato = 2;
        }
        //verifico se il secondo click sia su una casella avanti di una riga e a sinistra
        else if(x2Button == (x1Button - 1) && y2Button == (y1Button - 1)) {
            idMossaBianca = 12;
            risultato = 2;
        }
        //verifico se la pedina bianca può mangiare in avanti a destra
        else if(x2Button == (x1Button - 2) && y2Button == (y1Button + 2)) {
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (x1Button - 1) && pedina.getY() == (y1Button + 1)) {
                    idMossaBianca = 6;
                    risultato = 2;
                }
        }
        //verifico se la pedina bianca può mangiare in avanti a sinistra
        else if(x2Button == (x1Button - 2) && y2Button == (y1Button - 2)) {
            for(Pedina pedina : arrayPedineNere) 
                if(pedina.getX() == (x1Button - 1) && pedina.getY() == (y1Button - 1)) {
                    idMossaBianca = 7;
                    risultato = 2;
                }
        } 
        //verifico se la pedina bianca può mangiare indietro a destra
        else if(x2Button == (x1Button + 2) && y2Button == (y1Button + 2)) {
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (x1Button + 1) && pedina.getY() == (y1Button + 1)) {
                    idMossaBianca = 8;
                    risultato = 2;
                }
        }
        //verifico se la pedina bianca può mangiare indietro a sinistra
        else if(x2Button == (x1Button + 2) && y2Button == (y1Button - 2)) {
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (x1Button + 1) && pedina.getY() == (y1Button - 1)) {
                    idMossaBianca = 9;
                    risultato = 2;
                }
        } 
        else {
            risultato = 1;
        }
        return risultato;
    }
}
