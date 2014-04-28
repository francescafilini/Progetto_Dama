package Dama;

import static Dama.Damiera.arrayPedineBianche;
import static Dama.Damiera.arrayPedineNere;
import static Dama.MossaPedinaNera.seVadoDestraIndietroVengoMangiato;
import static Dama.MossaPedinaNera.seVadoDestraVengoMangiato;
import static Dama.MossaPedinaNera.seVadoSinistraIndietroVengoMangiato;
import static Dama.MossaPedinaNera.seVadoSinistraVengoMangiato;

public class TurnoPedinaNera {
    
    //Imposta la posizione finale della pedina spostata
    public void finalPositionPedina() {
        MossaPedinaNera mossaPedinaNera = new MossaPedinaNera();
        
        for(int i = 0; i < arrayPedineNere.size(); i++) {   
            if(arrayPedineNere.get(i) instanceof Damone) {
                mossaPedinaNera.damoneBlackMove((Damone)arrayPedineNere.get(i));
                seVadoDestraVengoMangiato = false;
                seVadoSinistraVengoMangiato = false;
                seVadoDestraIndietroVengoMangiato = false;
                seVadoSinistraIndietroVengoMangiato = false;
            } else {
                mossaPedinaNera.blackMove(arrayPedineNere.get(i));
                seVadoDestraVengoMangiato = false;
                seVadoSinistraVengoMangiato = false;
    
            }
        }
        
        Pedina pedina = findPedina();
        
        for(int i = 0; i < arrayPedineBianche.size(); i++) { 
                boolean removeWhite = false;
                        
                if(arrayPedineBianche.get(i).getX() == pedina.getxPedinaMangiata1() 
                        && arrayPedineBianche.get(i).getY() == pedina.getyPedinaMangiata1()) {
                    removeWhite = true;
                }   
                if(arrayPedineBianche.get(i).getX() == pedina.getxPedinaMangiata2() 
                        && arrayPedineBianche.get(i).getY() == pedina.getyPedinaMangiata2()) {
                    removeWhite = true;
                }
                if(arrayPedineBianche.get(i).getX() == pedina.getxPedinaMangiata3() 
                        && arrayPedineBianche.get(i).getY() == pedina.getyPedinaMangiata3()) {
                    removeWhite = true;
                }
                
                if(removeWhite) {
                    arrayPedineBianche.remove(arrayPedineBianche.get(i));
                    removeWhite = false;
                    i--;
                }
                    
        }
        
        for(int i = 0; i < arrayPedineNere.size(); i++) { 
            if(pedina.getX() ==  arrayPedineNere.get(i).getX() && pedina.getY() ==  arrayPedineNere.get(i).getY()) {
                //Diventa damone
                if(arrayPedineNere.get(i).getXTemp() == 7) {
                    Damone lastDamone = new Damone(false, arrayPedineNere.get(i).getXTemp(), arrayPedineNere.get(i).getYTemp());
                    arrayPedineNere.remove(i);
                    arrayPedineNere.add(lastDamone);
                } else if(arrayPedineNere.get(i) instanceof Damone) {
                    Damone lastDamone = new Damone(false, arrayPedineNere.get(i).getXTemp(), arrayPedineNere.get(i).getYTemp());
                    arrayPedineNere.remove(i);
                    arrayPedineNere.add(lastDamone);
                } else {
                    Pedina lastPedina = new Pedina(false, arrayPedineNere.get(i).getXTemp(), arrayPedineNere.get(i).getYTemp());
                    arrayPedineNere.remove(i);
                    arrayPedineNere.add(lastPedina);
                }
            }
        }
        
    }
    
    
    //Troviamo la pedina/il damone con priorità maggiore
    private Pedina findPedina() {
        Pedina maxPedina = arrayPedineNere.get(arrayPedineNere.size() - 1);
        
        for(int i = arrayPedineNere.size() - 1; i >= 0; i--) {
            
            if(maxPedina.getPriority() < arrayPedineNere.get(i).getPriority()) {
                maxPedina = arrayPedineNere.get(i);
            }
        }
        return maxPedina;
    }
    
    
}
