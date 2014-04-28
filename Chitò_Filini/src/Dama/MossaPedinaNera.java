package Dama;

import static Dama.Damiera.arrayPedineBianche;
import static Dama.Damiera.arrayPedineNere;

public class MossaPedinaNera {

    public static boolean seVadoDestraVengoMangiato = false;
    public static boolean seVadoSinistraVengoMangiato = false;
    public static boolean seVadoDestraIndietroVengoMangiato = false;
    public static boolean seVadoSinistraIndietroVengoMangiato = false;

    public boolean controllaSeCasellaLibera(int xTemp, int yTemp) {
        boolean cond1 = false;
        boolean cond2 = false;
        
        for(int i = 0; i < arrayPedineNere.size(); i++) {
            if(xTemp == arrayPedineNere.get(i).getX()
                    && yTemp == arrayPedineNere.get(i).getY()) {
                cond1 = true;
            }
        }
        
        for(int i = 0; i < arrayPedineBianche.size(); i++) {
            if(xTemp == arrayPedineBianche.get(i).getX()
                    && yTemp == arrayPedineBianche.get(i).getY()) {
                cond2 = true;  
            }
        }
        
        if(!cond1 && !cond2)
            return true;    
        else
            return false;   
    }
    
    
//Ritorna la mossa migliore della pedina esaminata
    public void blackMove(Pedina pedina) {
        if (!canBlackMove(pedina)) {
            pedina.setPriority(-6); 
            return;
        } else if (canBlackEat3(pedina)) {
            pedina.setPriority(16);
            return;
        } else if (canBlackEat2(pedina)) {
            pedina.setPriority(14);
            return;
        } else if (canBlackEat(pedina)) {
            pedina.setPriority(12);
            return;
        } else if (canBlackSaveItself(pedina)) {
            pedina.setPriority(11);
            return;
        } else if (canBlackSaveOther(pedina)) {
            pedina.setPriority(8);
            return;
        } else if (canBlackBecomeDamone(pedina)) {
            pedina.setPriority(7);
            return;
        } else if (canBlackMoveRight(pedina) && !seVadoDestraVengoMangiato) {   
            pedina.setPriority(6);
            return;
        } else if (canBlackMoveLeft(pedina) && !seVadoSinistraVengoMangiato) {
            pedina.setPriority(5);
            return;
         } else if (canBlackMoveRight(pedina)) {
            pedina.setPriority(0);
            return;
        } else if (canBlackMoveLeft(pedina)) {
            pedina.setPriority(-1); 
            return;
        }
    }

    private boolean canBlackSaveOther(Pedina pedina) {
        if (canBlackSaveOtherRight(pedina) || canBlackSaveOtherLeft(pedina)) {
            return true;
        }
        return false;
    }

    private boolean canBlackSaveOtherRight(Pedina pedina) {
        boolean trovataPedinaNeraDaProteggere = false;
        boolean trovataPedinaBiancaAttaccante = false;

        //verifico che la casella x+1 e y+1 sia libera
        if(!controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() + 1))
            return false;

        //verifico che nella casella x+2 e y+2 ci sia una pedina nera da proteggere
        for (Pedina pedinaBianca : arrayPedineBianche) {
            if ((pedina.getX() + 2) == pedinaBianca.getX()
                    && (pedina.getY() + 2) == pedinaBianca.getY()) {
                return false;
            }
        }

        for (Pedina pedinaNera : arrayPedineNere) {
            if ((pedina.getX() + 2) == pedinaNera.getX()
                    && (pedina.getY() + 2) == pedinaNera.getY()) {
                trovataPedinaNeraDaProteggere = true;
            }
        }

        //verifico che nella casella x+3 e y+3 ci sia una pedina bianca che possa mangiare la pedina nera compagna
        for (Pedina pedinaNera : arrayPedineNere) {
            if ((pedina.getX() + 3) == pedinaNera.getX()
                    && (pedina.getY() + 3) == pedinaNera.getY()) {
                return false;
            }
        }
        
        for (Pedina pedinaBianca : arrayPedineBianche) {
            if ((pedina.getX() + 3) == pedinaBianca.getX()
                    && (pedina.getY() + 3) == pedinaBianca.getY()) {
                trovataPedinaBiancaAttaccante = true;
            }
        }

        if (trovataPedinaNeraDaProteggere && trovataPedinaBiancaAttaccante) {
            if(pedina.getX() + 1 > 7 || pedina.getY() + 1 > 7)
                return false;
            else {
                    pedina.setXTemp(pedina.getX() + 1);
                    pedina.setYTemp(pedina.getY() + 1);
                    return true;
            }
        } else 
            return false;
    }

    private boolean canBlackSaveOtherLeft(Pedina pedina) {
        boolean trovataPedinaNeraDaProteggere = false;
        boolean trovataPedinaBiancaAttaccante = false;

        //verifico che la casella x+1 e y-1 sia libera
       if(!controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() - 1))
            return false;

        //verifico che nella casella x+2 e y-2 ci sia una pedina nera da proteggere
        for (Pedina pedinaBianca : arrayPedineBianche) {
            if ((pedina.getX() + 2) == pedinaBianca.getX()
                    && (pedina.getY() - 2) == pedinaBianca.getY()) {
                return false;
            }
        }

        for (Pedina pedinaNera : arrayPedineNere) {
            if ((pedina.getX() + 2) == pedinaNera.getX()
                    && (pedina.getY() - 2) == pedinaNera.getY()) {
                trovataPedinaNeraDaProteggere = true;
            }
        }

        //verifico che nella casella x+3 e y-3 ci sia una pedina bianca che possa mangiare la pedina nera compagna
        for (Pedina pedinaNera : arrayPedineNere) {
            if ((pedina.getX() + 3) == pedinaNera.getX()
                    && (pedina.getY() - 3) == pedinaNera.getY()) {
                return false;
            }
        }
        
        for (Pedina pedinaBianca : arrayPedineBianche) {
            if ((pedina.getX() + 3) == pedinaBianca.getX()
                    && (pedina.getY() - 3) == pedinaBianca.getY()) {
                trovataPedinaBiancaAttaccante = true;
            }
        }

        if (trovataPedinaNeraDaProteggere && trovataPedinaBiancaAttaccante) {
            if(pedina.getX() + 1 > 7 || pedina.getY() - 1 < 0)
                return false;
            else {
                    pedina.setXTemp(pedina.getX() + 1);
                    pedina.setYTemp(pedina.getY() - 1);
                    return true;
            }
        } else 
            return false;
    }

    private boolean canBlackSaveItself(Pedina pedina) {
        if (canBlackSaveItselfRightBehind(pedina) || canBlackSaveItselfLeftAhead(pedina)
                || canBlackSaveItselfLeftBehind(pedina) || canBlackSaveItselfRightAhead(pedina)) {
            return true;
        }
        return false;
    }

    private boolean canBlackSaveItselfRightBehind(Pedina pedina) {    //attacco da destra indietro
        boolean condizione1 = false;
        boolean condizione2 = false;
        boolean fine1 = false;
        boolean fine2 = false;
        boolean fine3 = false;

        for (Pedina pedinaBianca : arrayPedineBianche) {
            if (pedina.getX() - 1 == pedinaBianca.getX()
                    && pedina.getY() + 1 == pedinaBianca.getY()
                    && (pedinaBianca instanceof Damone))
                condizione1 = true;
        }
        
        if(pedina.getX() + 1 <= 7 && pedina.getY() - 1 >= 0 && 
                controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() - 1))
            condizione2 = true;
        
        
        if (condizione1 && condizione2) {
            
            if(pedina.getX() + 1 > 7 || pedina.getY() + 1 > 7)
                    fine1 = true;
            else {  
                    for (int i = 0; i < arrayPedineBianche.size(); i++) {
                        if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                            && pedina.getY() + 2 == arrayPedineBianche.get(i).getY()) {
                            fine1 = true;
                        }
                    }
            }
            
            if(!fine1) {
                if(controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() + 1)) {
                    pedina.setXTemp(pedina.getX() + 1);
                    pedina.setYTemp(pedina.getY() + 1);
                    return true;
                } else
                    fine1 = true;
            }                  
           
            if(pedina.getX() + 1 > 7 || pedina.getY() - 1 < 0)
                fine2 = true;
            else {
                for (int i = 0; i < arrayPedineBianche.size(); i++) {
                    if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                        && pedina.getY() - 2 == arrayPedineBianche.get(i).getY()) {
                        fine2 = true;
                    }
                }
            }
                
            if(!fine2) {
                if(controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() - 1)) {
                        pedina.setXTemp(pedina.getX() + 1);
                        pedina.setYTemp(pedina.getY() - 1);
                        return true;
                } else
                        fine2 = true;
            }
            
            
            if(pedina instanceof Damone){
                if(pedina.getX() - 1 < 0 || pedina.getY() - 1 < 0)
                    fine3 = true;
                else {
                    for (int i = 0; i < arrayPedineBianche.size(); i++) {
                        if (pedina.getX() - 2 == arrayPedineBianche.get(i).getX()
                            && pedina.getY() - 2 == arrayPedineBianche.get(i).getY()) {
                            fine3 = true;
                        }
                    }
                }
                    
                if(!fine3) {
                    if(controllaSeCasellaLibera(pedina.getX() - 1, pedina.getY() - 1)) {
                            pedina.setXTemp(pedina.getX() - 1);
                            pedina.setYTemp(pedina.getY() - 1);
                            return true;
                    } else
                            return false;
                } else
                    return false;
                
            } else
                return false;
            
        } else 
            return false;

    }

    private boolean canBlackSaveItselfLeftAhead(Pedina pedina) {
        boolean condizione1 = false;
        boolean condizione2 = false;
        boolean fine1 = false;
        boolean fine2 = false;
        boolean fine3 = false;
        
        for (int i = 0; i < arrayPedineBianche.size(); i++) {
            if (pedina.getX() + 1 == arrayPedineBianche.get(i).getX()
                    && pedina.getY() - 1 == arrayPedineBianche.get(i).getY()) {
                condizione1 = true;
            }
        }
        
        if(controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() - 1))
            condizione2 = true;
        
        
        if (condizione1 && condizione2) {
            if(pedina.getX() + 1 > 7 || pedina.getY() - 1 < 0)
                return false;
            else {
                if(pedina.getX() + 1 > 7 || pedina.getY() + 1 > 7)
                        fine1 = true;
                else {
                    for (int i = 0; i < arrayPedineBianche.size(); i++) {
                        if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                            && pedina.getY() + 2 == arrayPedineBianche.get(i).getY()) {
                            fine1 = true;
                        }
                    }
                }
                    
                if(!fine1){
                    if(controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() + 1)) {
                            pedina.setXTemp(pedina.getX() + 1);
                            pedina.setYTemp(pedina.getY() + 1);
                            return true;
                    } else
                            fine1 = true;
                }
                
            
                if(pedina instanceof Damone) {
                    if(pedina.getX() - 1 < 0 || pedina.getY() - 1 < 0)
                        fine2 = true;
                    else {
                        for (int i = 0; i < arrayPedineBianche.size(); i++) {
                            if (pedina.getX() - 2 == arrayPedineBianche.get(i).getX()
                                && pedina.getY() - 2 == arrayPedineBianche.get(i).getY()) {
                                fine2 = true;
                            }
                        }
                    }
                        
                    if(!fine2) {
                        if(controllaSeCasellaLibera(pedina.getX() - 1, pedina.getY() - 1)) {
                                pedina.setXTemp(pedina.getX() - 1);
                                pedina.setYTemp(pedina.getY() - 1);
                                return true;
                        } else
                                fine2 = true;
                    }
                    
                
                    for (int i = 0; i < arrayPedineBianche.size(); i++) {
                        if (pedina.getX() - 2 == arrayPedineBianche.get(i).getX()
                            && pedina.getY() + 2 == arrayPedineBianche.get(i).getY()) {
                            fine3 = true;
                        }
                    }
                
                    if(!fine3) {
                        pedina.setXTemp(pedina.getX() - 1);
                        pedina.setYTemp(pedina.getY() + 1);
                        return true;
                    } else
                        return false;
                
                } else
                    return false; 
            } 
        } else
            return false;    
    }

    private boolean canBlackSaveItselfLeftBehind(Pedina pedina) {
        boolean condizione1 = false;
        boolean condizione2 = false;
        boolean fine1 = false;
        boolean fine2 = false;
        boolean fine3 = false;

        for (Pedina pedinaBianca : arrayPedineBianche) {
            if (pedina.getX() - 1 == pedinaBianca.getX()
                    && pedina.getY() - 1 == pedinaBianca.getY()
                    && (pedinaBianca instanceof Damone))
                condizione1 = true;
        }
        
        if(pedina.getX() - 1 >= 0 && pedina.getY() - 1 >= 0 && 
                controllaSeCasellaLibera(pedina.getX() - 1, pedina.getY() - 1))
            condizione2 = true;
        
        
        if (condizione1 && condizione2) {
            
            if(pedina.getX() + 1 > 7 || pedina.getY() + 1 > 7)
                    fine1 = true;
            else {  
                    for (int i = 0; i < arrayPedineBianche.size(); i++) {
                        if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                            && pedina.getY() + 2 == arrayPedineBianche.get(i).getY()) {
                            fine1 = true;
                        }
                    }
            }
            
            if(!fine1) {
                if(controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() + 1)) {
                    pedina.setXTemp(pedina.getX() + 1);
                    pedina.setYTemp(pedina.getY() + 1);
                    return true;
                } else
                    fine1 = true;
            }                  
           
            if(pedina.getX() + 1 > 7 || pedina.getY() - 1 < 0)
                fine2 = true;
            else {
                for (int i = 0; i < arrayPedineBianche.size(); i++) {
                    if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                        && pedina.getY() - 2 == arrayPedineBianche.get(i).getY()) {
                        fine2 = true;
                    }
                }
            }
                
            if(!fine2) {
                if(controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() - 1)) {
                        pedina.setXTemp(pedina.getX() + 1);
                        pedina.setYTemp(pedina.getY() - 1);
                        return true;
                } else
                        fine2 = true;
            }
            
            
            if(pedina instanceof Damone){
                if(pedina.getX() - 1 < 0 || pedina.getY() + 1 > 7)
                    fine3 = true;
                else {
                    for (int i = 0; i < arrayPedineBianche.size(); i++) {
                        if (pedina.getX() - 2 == arrayPedineBianche.get(i).getX()
                            && pedina.getY() + 2 == arrayPedineBianche.get(i).getY()) {
                            fine3 = true;
                        }
                    }
                }
                    
                if(!fine3) {
                    if(controllaSeCasellaLibera(pedina.getX() - 1, pedina.getY() + 1)) {
                            pedina.setXTemp(pedina.getX() - 1);
                            pedina.setYTemp(pedina.getY() + 1);
                            return true;
                    } else
                            return false;
                } else
                    return false;
                
            } else
                return false;
            
        } else 
            return false;

    }
    
    private boolean canBlackSaveItselfRightAhead(Pedina pedina) {    //attacco da destra avanti    
        boolean condizione1 = false;
        boolean condizione2 = false;
        boolean fine1 = false;
        boolean fine2 = false;
        boolean fine3 = false;
        
        for (int i = 0; i < arrayPedineBianche.size(); i++) {
            if (pedina.getX() + 1 == arrayPedineBianche.get(i).getX()
                    && pedina.getY() + 1 == arrayPedineBianche.get(i).getY()) {
                condizione1 = true;
            }
        }
        
        if(controllaSeCasellaLibera(pedina.getX() - 1, pedina.getY() - 1))
            condizione2 = true;
        
        
        if (condizione1 && condizione2) {
            if(pedina.getX() - 1 < 0 || pedina.getY() - 1 < 0)
                return false;
            else {
                if(pedina.getX() + 1 > 7 || pedina.getY() - 1 < 0)
                        fine1 = true;
                else {
                    for (int i = 0; i < arrayPedineBianche.size(); i++) {
                        if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                            && pedina.getY() - 2 == arrayPedineBianche.get(i).getY()) {
                            fine1 = true;
                        }
                    }
                }
                
                if(!fine1){
                    if(controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() - 1)) {
                            pedina.setXTemp(pedina.getX() + 1);
                            pedina.setYTemp(pedina.getY() - 1);
                            return true;
                    } else
                            fine1 = true;
                }
            
                if(pedina instanceof Damone) {
                    if(pedina.getX() - 1 < 0 || pedina.getY() + 1 > 7)
                        fine2 = true;
                    else {
                        for (int i = 0; i < arrayPedineBianche.size(); i++) {
                            if (pedina.getX() - 2 == arrayPedineBianche.get(i).getX()
                                && pedina.getY() + 2 == arrayPedineBianche.get(i).getY()) {
                                fine2 = true;
                            }
                        }
                    }
                        
                    if(!fine2) {
                        if(controllaSeCasellaLibera(pedina.getX() - 1, pedina.getY() + 1)) {
                                pedina.setXTemp(pedina.getX() - 1);
                                pedina.setYTemp(pedina.getY() + 1);
                                return true;
                        } else
                                fine2 = true;
                    }
                    
                
                    for (int i = 0; i < arrayPedineBianche.size(); i++) {
                        if (pedina.getX() - 2 == arrayPedineBianche.get(i).getX()
                            && pedina.getY() - 2 == arrayPedineBianche.get(i).getY()) {
                            fine3 = true;
                        }
                    }
                
                    if(!fine3) {
                        pedina.setXTemp(pedina.getX() - 1);
                        pedina.setYTemp(pedina.getY() - 1);
                        return true;
                    } else
                        return false;
                
                } else
                    return false; 
            } 
        } else
            return false;    
    }

    private boolean canBlackMove(Pedina pedina) {
        boolean casellaDestraOccupata = false;
        boolean casellaSinistraOccupata = false;
        
        if(pedina.getX() + 1 > 7)
            return false;
        else {
                for(int i = 0; i < arrayPedineNere.size(); i++) {
                    if (pedina.getX() + 1 == arrayPedineNere.get(i).getX()
                        && pedina.getY() + 1 == arrayPedineNere.get(i).getY())
                        casellaDestraOccupata = true;
                }
            
                for(int i = 0; i < arrayPedineNere.size(); i++) {
                    if (pedina.getX() + 1 == arrayPedineNere.get(i).getX()
                        && pedina.getY() - 1 == arrayPedineNere.get(i).getY())
                        casellaSinistraOccupata = true;
                }
                
                
                for(int i = 0; i < arrayPedineBianche.size(); i++) {
                    if (pedina.getX() + 1 == arrayPedineBianche.get(i).getX()
                        && pedina.getY() + 1 == arrayPedineBianche.get(i).getY()) {
                                if(pedina.getX() + 2 <= 7 && pedina.getY() + 2 <= 7 &&
                                        !controllaSeCasellaLibera(pedina.getX() + 2, pedina.getY() + 2))
                                    casellaDestraOccupata = true;  
                    }
                }
                    
                for(int i = 0; i < arrayPedineBianche.size(); i++) {
                    if (pedina.getX() + 1 == arrayPedineBianche.get(i).getX()
                        && pedina.getY() - 1 == arrayPedineBianche.get(i).getY()) {
                            if(pedina.getX() + 2 <= 7 && pedina.getY() - 2 >= 0 &&
                                    !controllaSeCasellaLibera(pedina.getX() + 2, pedina.getY() - 2))    
                            casellaSinistraOccupata = true;
                        }     
                }
                
                if(pedina.getY() + 1 > 7)
                    casellaDestraOccupata = true;
                if(pedina.getY() - 1 < 0)
                    casellaSinistraOccupata = true;
        }
        
        if(casellaDestraOccupata && casellaSinistraOccupata)
            return false;
        else
            return true;
    }
    
    private boolean canBlackMoveRight(Pedina pedina) {
        //verifico se la casella x+1, y+1 sia libera e
        //la casella x+2, y+2 sia occupata da una pedina bianca
        if(!controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() + 1))
            return false;
        else {
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                        && pedina.getY() + 2 == arrayPedineBianche.get(i).getY()) 
                    seVadoDestraVengoMangiato = true;
            }
            
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                        && pedina.getY() == arrayPedineBianche.get(i).getY()
                        && pedina.getY() + 2 <= 7
                        && controllaSeCasellaLibera(pedina.getX(), pedina.getY() + 2)) 
                    seVadoDestraVengoMangiato = true;
            }
        
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (pedina.getX() == arrayPedineBianche.get(i).getX()
                        && pedina.getY() + 2 == arrayPedineBianche.get(i).getY()
                        && pedina.getX() + 2 <= 7
                        && arrayPedineBianche.get(i) instanceof Damone
                        && controllaSeCasellaLibera(pedina.getX() + 2, pedina.getY()))
                    seVadoDestraVengoMangiato = true;
            }
            
            if(pedina.getX() + 1 > 7 || pedina.getY() + 1 > 7)
                return false;
            else {
                pedina.setXTemp(pedina.getX() + 1);
                pedina.setYTemp(pedina.getY() + 1);
                return true;
            }
        }
    }

    
    private boolean canBlackMoveLeft(Pedina pedina) {
        //verifico se la casella x+1, y-1 sia libera e
        //la casella x+2, y-2 sia occupata da una pedina bianca
        if(!controllaSeCasellaLibera(pedina.getX() + 1, pedina.getY() - 1))
            return false;
        else {
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                        && pedina.getY() - 2 == arrayPedineBianche.get(i).getY())
                    seVadoSinistraVengoMangiato = true;
            }
            
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (pedina.getX() + 2 == arrayPedineBianche.get(i).getX()
                        && pedina.getY() == arrayPedineBianche.get(i).getY()
                        && pedina.getY() - 2 >= 0
                        && controllaSeCasellaLibera(pedina.getX(), pedina.getY() - 2))
                    seVadoSinistraVengoMangiato = true;
            }
        
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (pedina.getX() == arrayPedineBianche.get(i).getX()
                        && pedina.getY() - 2 == arrayPedineBianche.get(i).getY()
                        && pedina.getX() + 2 <= 7
                        && arrayPedineBianche.get(i) instanceof Damone
                        && controllaSeCasellaLibera(pedina.getX() + 2, pedina.getY())) 
                    seVadoSinistraVengoMangiato = true;
            }
            
            if(pedina.getX() + 1 > 7 || pedina.getY() - 1 < 0)
                return false;
            else {
                    pedina.setXTemp(pedina.getX() + 1);
                    pedina.setYTemp(pedina.getY() - 1);
                    return true;
            }
        }
    }

    private boolean canBlackEat(Pedina pedina) {
        if (canBlackEatRight(pedina) || canBlackEatLeft(pedina)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canBlackEatRight(Pedina pedina) {
        boolean trovataPedinaBiancaDaMangiare = false;
        boolean casellaFinaleLibera = false;

        for (int i = 0; i < arrayPedineBianche.size(); i++) {
            if ((pedina.getX() + 1) == arrayPedineBianche.get(i).getX()
                    && (pedina.getY() + 1) == arrayPedineBianche.get(i).getY()) {
                trovataPedinaBiancaDaMangiare = true;
                
            }
        }
        
        if(trovataPedinaBiancaDaMangiare) {
            if(controllaSeCasellaLibera(pedina.getX() + 2, pedina.getY() + 2))
                casellaFinaleLibera = true;
            
            if(casellaFinaleLibera) {
                if(!(pedina.getX() + 2 > 7 || pedina.getY() + 2 > 7)) {
                        pedina.setxPedinaMangiata1(pedina.getX() + 1);
                        pedina.setyPedinaMangiata1(pedina.getY() + 1);
                        pedina.setXTemp(pedina.getX() + 2);
                        pedina.setYTemp(pedina.getY() + 2);
                        return true;
                } else
                    return false;
            } else
                return false;
        }else 
            return false;
    }

    private boolean canBlackEatLeft(Pedina pedina) {
        
        boolean trovataPedinaBiancaDaMangiare = false;
        boolean casellaFinaleLibera = false;

        for (int i = 0; i < arrayPedineBianche.size(); i++) {
            if ((pedina.getX() + 1) == arrayPedineBianche.get(i).getX()
                    && (pedina.getY() - 1) == arrayPedineBianche.get(i).getY()) {
                trovataPedinaBiancaDaMangiare = true;  
            }
        }
        
        if(trovataPedinaBiancaDaMangiare) {
            if(controllaSeCasellaLibera(pedina.getX() + 2, pedina.getY() - 2))
                casellaFinaleLibera = true;
            
            if(casellaFinaleLibera) {
                if(!(pedina.getX() + 2 > 7 || pedina.getY() - 2 < 0)){
                        pedina.setxPedinaMangiata1(pedina.getX() + 1);
                        pedina.setyPedinaMangiata1(pedina.getY() - 1);
                        pedina.setXTemp(pedina.getX() + 2);
                        pedina.setYTemp(pedina.getY() - 2);
                        return true;
                } else
                    return false;
            } else
                return false;
        }else 
            return false;  
    }

    private boolean canBlackEat2(Pedina pedina) {
        int xOriginal = pedina.getX();
        int yOriginal = pedina.getY();

        if (canBlackEatRight(pedina)) {
            pedina.setxPedinaMangiata2(pedina.getxPedinaMangiata1());
            pedina.setyPedinaMangiata2(pedina.getyPedinaMangiata1());
            pedina.setX(pedina.getX() + 2); 
            pedina.setY(pedina.getY() + 2);
            if (canBlackEatRight(pedina)) {
                pedina.setX(xOriginal); 
                pedina.setY(yOriginal); 
                return true;
            } else if (canBlackEatLeft(pedina)) {
                pedina.setX(xOriginal);
                pedina.setY(yOriginal);

                return true;
            }

        } else if (canBlackEatLeft(pedina)) {
            pedina.setxPedinaMangiata2(pedina.getxPedinaMangiata1());
            pedina.setyPedinaMangiata2(pedina.getyPedinaMangiata1());
            pedina.setX(pedina.getX() + 2);
            pedina.setY(pedina.getY() - 2);
            if (canBlackEatRight(pedina)) {
                pedina.setX(xOriginal);
                pedina.setY(yOriginal);
                return true;
            } else if (canBlackEatLeft(pedina)) {
                pedina.setX(xOriginal);
                pedina.setY(yOriginal);
                return true;
            }
        }
        
        pedina.setX(xOriginal);
        pedina.setY(yOriginal);
        return false;
    }

    
    private boolean canBlackEat3(Pedina pedina) {
        int xOriginal = pedina.getX();
        int yOriginal = pedina.getY();

        if (canBlackEatRight(pedina)) {
            pedina.setxPedinaMangiata3(pedina.getxPedinaMangiata1());
            pedina.setyPedinaMangiata3(pedina.getyPedinaMangiata1());
            pedina.setX(pedina.getX() + 2);
            pedina.setY(pedina.getY() + 2);
            if (canBlackEatRight(pedina)) {
                pedina.setxPedinaMangiata2(pedina.getxPedinaMangiata1());
                pedina.setyPedinaMangiata2(pedina.getyPedinaMangiata1());
                pedina.setX(pedina.getX() + 2);
                pedina.setY(pedina.getY() + 2);
                if (canBlackEatRight(pedina)) {
                    pedina.setX(xOriginal);
                    pedina.setY(yOriginal);
                    return true;
                } else if (canBlackEatLeft(pedina)) {
                    pedina.setX(xOriginal);
                    pedina.setY(yOriginal);
                    return true;
                }
            } else if (canBlackEatLeft(pedina)) {
                pedina.setxPedinaMangiata2(pedina.getxPedinaMangiata1());
                pedina.setyPedinaMangiata2(pedina.getyPedinaMangiata1());
                pedina.setX(pedina.getX() + 2);
                pedina.setY(pedina.getY() - 2);
                if (canBlackEatRight(pedina)) {
                    pedina.setX(xOriginal);
                    pedina.setY(yOriginal);
                    return true;
                } else if (canBlackEatLeft(pedina)) {
                    pedina.setX(xOriginal);
                    pedina.setY(yOriginal);
                    return true;
                }
            }
        } else if (canBlackEatLeft(pedina)) {
            pedina.setxPedinaMangiata3(pedina.getxPedinaMangiata1());
            pedina.setyPedinaMangiata3(pedina.getyPedinaMangiata1());
            pedina.setX(pedina.getX() + 2);
            pedina.setY(pedina.getY() - 2);
            if (canBlackEatRight(pedina)) {
                pedina.setxPedinaMangiata2(pedina.getxPedinaMangiata1());
                pedina.setyPedinaMangiata2(pedina.getyPedinaMangiata1());
                pedina.setX(pedina.getX() + 2);
                pedina.setY(pedina.getY() + 2);
                if (canBlackEatRight(pedina)) {
                    pedina.setX(xOriginal);
                    pedina.setY(yOriginal);
                    return true;
                } else if (canBlackEatLeft(pedina)) {
                    pedina.setX(xOriginal);
                    pedina.setY(yOriginal);
                    return true;
                }
            } else if (canBlackEatLeft(pedina)) {
                pedina.setxPedinaMangiata2(pedina.getxPedinaMangiata1());
                pedina.setyPedinaMangiata2(pedina.getyPedinaMangiata1());
                pedina.setX(pedina.getX() + 2);
                pedina.setY(pedina.getY() - 2);
                if (canBlackEatRight(pedina)) {
                    pedina.setX(xOriginal);
                    pedina.setY(yOriginal);
                    return true;
                } else if (canBlackEatLeft(pedina)) {
                    pedina.setX(xOriginal);
                    pedina.setY(yOriginal);
                    return true;
                }
            }
        }
        
        pedina.setX(xOriginal);
        pedina.setY(yOriginal);
        return false;
    }
    
    
    private boolean canBlackBecomeDamone(Pedina pedina) {
        if (canBlackBecomeDamoneRight(pedina) || canBlackBecomeDamoneLeft(pedina)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canBlackBecomeDamoneRight(Pedina pedina) {
        if (pedina.getX() == 6 && pedina.getY() + 1 <= 7) {
            if(!controllaSeCasellaLibera(7, pedina.getY() + 1))
                return false;
            else {
                pedina.setXTemp(7);
                pedina.setYTemp(pedina.getY() + 1);
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean canBlackBecomeDamoneLeft(Pedina pedina) {
        if (pedina.getX() == 6 && pedina.getY() - 1 >= 0) {
            if(!controllaSeCasellaLibera(7, pedina.getY() - 1))
                return false;
            else {
                pedina.setXTemp(7);
                pedina.setYTemp(pedina.getY() - 1);
                return true;
            }
        } else {
            return false;
        }
    }

    /* Metodi damone nero*/
    public void damoneBlackMove(Damone damone) {
        if (!canDamoneBlackMove(damone)) {
            damone.setPriority(-6);
            return;
        } else if (canDamoneBlackEat3(damone)) {
            damone.setPriority(17);
            return;
        } else if (canDamoneBlackEat2(damone)) {
            damone.setPriority(15);
            return;
        } else if (canDamoneBlackEat(damone)) {
            damone.setPriority(13);
            return;
        } else if (canDamoneBlackSaveItself(damone)) {
            damone.setPriority(10);
            return;
        } else if (canDamoneBlackSaveOther(damone)) {
            damone.setPriority(9);
            return;
        } else if (canDamoneBlackMoveRightBehind(damone) && !seVadoDestraIndietroVengoMangiato) {
            damone.setPriority(4);
            return;
        } else if (canDamoneBlackMoveLeftBehind(damone) && !seVadoSinistraIndietroVengoMangiato) {
            damone.setPriority(3);
            return;
        } else if (canDamoneBlackMoveRightAhead(damone) && !seVadoDestraVengoMangiato) {
            damone.setPriority(2);
            return;
        } else if (canDamoneBlackMoveLeftAhead(damone) && !seVadoSinistraVengoMangiato) {
            damone.setPriority(1);
            return;
        } else if (canDamoneBlackMoveRightAhead(damone)) {
            damone.setPriority(-2);
            return;
        } else if (canDamoneBlackMoveRightBehind(damone)) {
            damone.setPriority(-3);
            return;
        } else if (canDamoneBlackMoveLeftAhead(damone)) {
            damone.setPriority(-4);
            return;
        } else if (canDamoneBlackMoveLeftBehind(damone)) {
            damone.setPriority(-5);
            return;
        }
    }

    private boolean canDamoneBlackSaveOther(Damone damone) {
        if (canDamoneBlackSaveOtherRightBehind(damone) || canDamoneBlackSaveOtherLeftBehind(damone)
                || canDamoneBlackSaveOtherRightAhead(damone) || canDamoneBlackSaveOtherLeftAhead(damone)) {
            return true;
        }
        return false;
    }
    
    private boolean canDamoneBlackSaveOtherRightAhead(Damone damone) { //si sposta a destra avanti
        return canBlackSaveOtherRight(damone);
    }

    private boolean canDamoneBlackSaveOtherLeftAhead(Damone damone) {  //si sposta a sinistra avanti
        return canBlackSaveOtherLeft(damone);
    }

    private boolean canDamoneBlackSaveOtherLeftBehind(Damone damone) {  //si sposta a sinistra indietro
        boolean trovataPedinaNeraDaProteggere = false;
        boolean trovataPedinaBiancaAttaccante = false;

        //verifico che la casella x+1 e y+1 sia libera
        if(!controllaSeCasellaLibera(damone.getX() - 1, damone.getY() - 1))
            return false;

        //verifico che nella casella x+2 e y+2 ci sia una pedina nera da proteggere
        //e che non vengo mangiato
        for (Pedina pedinaBianca : arrayPedineBianche) {
            if ((damone.getX() - 2) == pedinaBianca.getX()
                    && (damone.getY() - 2) == pedinaBianca.getY()) 
                return false;
            else if(damone.getX() == pedinaBianca.getX()
                    && damone.getY() - 2 == pedinaBianca.getY()
                    && damone.getX() - 2 >= 0
                    && controllaSeCasellaLibera(damone.getX() - 2, damone.getY()))
                return false;
            else if(pedinaBianca instanceof Damone
                    && damone.getX() - 2 == pedinaBianca.getX()
                    && damone.getY() == pedinaBianca.getY()
                    && damone.getY() - 2 >= 0
                    && controllaSeCasellaLibera(damone.getX(), damone.getY() - 2))
                return false;
        }

        for (Pedina pedinaNera : arrayPedineNere) {
            if ((damone.getX() - 2) == pedinaNera.getX()
                    && (damone.getY() - 2) == pedinaNera.getY()) {
                trovataPedinaNeraDaProteggere = true;
            }
        }

        //verifico che nella casella x+3 e y+3 ci sia una pedina bianca che possa mangiare la pedina nera compagna
        for (Pedina pedinaNera : arrayPedineNere) {
            if ((damone.getX() - 3) == pedinaNera.getX()
                    && (damone.getY() - 3) == pedinaNera.getY()) {
                return false;
            }
        }
        
        for (Pedina pedinaBianca : arrayPedineBianche) {
            if ((damone.getX() - 3) == pedinaBianca.getX()
                    && (damone.getY() - 3) == pedinaBianca.getY()) {
                trovataPedinaBiancaAttaccante = true;
            }
        }

        if (trovataPedinaNeraDaProteggere && trovataPedinaBiancaAttaccante) {
            if(damone.getX() - 1 < 0 || damone.getY() - 1 < 0)
                return false;
            else {
                    damone.setXTemp(damone.getX() - 1);
                    damone.setYTemp(damone.getY() - 1);
                    return true;
            }
        } else 
            return false;
    }

    private boolean canDamoneBlackSaveOtherRightBehind(Damone damone) {  //si sposta a destra indietro
        boolean trovataPedinaNeraDaProteggere = false;
        boolean trovataPedinaBiancaAttaccante = false;

        //verifico che la casella x+1 e y+1 sia libera
        if(!controllaSeCasellaLibera(damone.getX() - 1, damone.getY() + 1))
            return false;

        //verifico che nella casella x-2 e y+2 ci sia una pedina nera da proteggere
        //e che non vengo mangiato
        for (Pedina pedinaBianca : arrayPedineBianche) {
            if ((damone.getX() - 2) == pedinaBianca.getX()
                    && (damone.getY() + 2) == pedinaBianca.getY())
                return false;
            else if(damone.getX() == pedinaBianca.getX()
                    && damone.getY() + 2 == pedinaBianca.getY()
                    && damone.getX() - 2 >= 0
                    && controllaSeCasellaLibera(damone.getX() - 2, damone.getY()))
                return false;
            else if(pedinaBianca instanceof Damone
                    && damone.getX() - 2 == pedinaBianca.getX()
                    && damone.getY() == pedinaBianca.getY()
                    && damone.getY() + 2 <= 7
                    && controllaSeCasellaLibera(damone.getX(), damone.getY() + 2))
                return false;  
        }

        for (Pedina pedinaNera : arrayPedineNere) {
            if ((damone.getX() - 2) == pedinaNera.getX()
                    && (damone.getY() + 2) == pedinaNera.getY()) {
                trovataPedinaNeraDaProteggere = true;
            }
        }

        //verifico che nella casella x+3 e y+3 ci sia una pedina bianca che possa mangiare la pedina nera compagna
        for (Pedina pedinaNera : arrayPedineNere) {
            if ((damone.getX() - 3) == pedinaNera.getX()
                    && (damone.getY() + 3) == pedinaNera.getY()) {
                return false;
            }
        }
        
        for (Pedina pedinaBianca : arrayPedineBianche) {
            if ((damone.getX() - 3) == pedinaBianca.getX()
                    && (damone.getY() + 3) == pedinaBianca.getY()) {
                trovataPedinaBiancaAttaccante = true;
            }
        }

        if (trovataPedinaNeraDaProteggere && trovataPedinaBiancaAttaccante) {
            if(damone.getX() - 1 < 0 || damone.getY() + 1 > 7)
                return false;
            else {
                    damone.setXTemp(damone.getX() - 1);
                    damone.setYTemp(damone.getY() + 1);
                    return true;
            }
        } else 
            return false;
    }

    private boolean canDamoneBlackSaveItself(Damone damone) {
        if (canDamoneBlackSaveItselfLeftBehind(damone) || canDamoneBlackSaveItselfRightBehind(damone)
                || canDamoneBlackSaveItselfLeftAhead(damone) || canDamoneBlackSaveItselfRightAhead(damone)) {
            return true;
        }
        return false;
    }

    private boolean canDamoneBlackSaveItselfLeftAhead(Damone damone) { //si sposta avanti a sinistra
        return canBlackSaveItselfLeftAhead(damone);
    }

    private boolean canDamoneBlackSaveItselfRightBehind(Damone damone) { //si sposta indietro a destra
        return canBlackSaveItselfRightBehind(damone);
    }

    private boolean canDamoneBlackSaveItselfRightAhead(Damone damone) { //si sposta avanti a destra
        return canBlackSaveItselfRightAhead(damone);
    }

    private boolean canDamoneBlackSaveItselfLeftBehind(Damone damone) { //si sposta indietro a sinistra
        return canBlackSaveItselfLeftBehind(damone);
    }

    

    private boolean canDamoneBlackEat(Damone damone) {
        if (canDamoneBlackEatRightAhead(damone) || canDamoneBlackEatLeftAhead(damone)
                || canDamoneBlackEatRightBehind(damone) || canDamoneBlackEatLeftBehind(damone)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canDamoneBlackEatRightAhead(Damone damone) {
        return canBlackEatRight(damone);
    }
    
    
    private boolean canDamoneBlackEatRightBehind(Damone damone) {
        boolean trovataPedinaBiancaDaMangiare = false;
        boolean casellaFinaleLibera = false;

        for (int i = 0; i < arrayPedineBianche.size(); i++) {
            if ((damone.getX() - 1) == arrayPedineBianche.get(i).getX()
                    && (damone.getY() + 1) == arrayPedineBianche.get(i).getY()) {
                trovataPedinaBiancaDaMangiare = true;
                
            }
        }
        
        if(trovataPedinaBiancaDaMangiare) {
            if(controllaSeCasellaLibera(damone.getX() - 2, damone.getY() + 2))
                casellaFinaleLibera = true;
            
            if(casellaFinaleLibera) {
                if(!(damone.getX() - 2 < 0 || damone.getY() + 2 > 7)) {
                        damone.setxPedinaMangiata1(damone.getX() - 1);
                        damone.setyPedinaMangiata1(damone.getY() + 1);
                        damone.setXTemp(damone.getX() - 2);
                        damone.setYTemp(damone.getY() + 2);
                        return true;
                } else
                    return false;
            } else
                return false;
        }else 
            return false;
    }
    
    private boolean canDamoneBlackEatLeftAhead(Damone damone) {
        return canBlackEatLeft(damone);
    }

    
    private boolean canDamoneBlackEatLeftBehind(Damone damone) {
        boolean trovataPedinaBiancaDaMangiare = false;
        boolean casellaFinaleLibera = false;

        for (int i = 0; i < arrayPedineBianche.size(); i++) {
            if ((damone.getX() - 1) == arrayPedineBianche.get(i).getX()
                    && (damone.getY() - 1) == arrayPedineBianche.get(i).getY()) {
                trovataPedinaBiancaDaMangiare = true;
                
            }
        }
        
        if(trovataPedinaBiancaDaMangiare) {
            if(controllaSeCasellaLibera(damone.getX() - 2, damone.getY() - 2))
                casellaFinaleLibera = true;
            
            if(casellaFinaleLibera) {
                if(!(damone.getX() - 2 < 0 || damone.getY() - 2 < 0)) {
                        damone.setxPedinaMangiata1(damone.getX() - 1);
                        damone.setyPedinaMangiata1(damone.getY() - 1);
                        damone.setXTemp(damone.getX() - 2);
                        damone.setYTemp(damone.getY() - 2);
                        return true;
                } else
                    return false;
            } else
                return false;
        }else 
            return false;
    }

    private boolean canEatAgain(Damone damone, int xOriginal, int yOriginal) {
        if (canDamoneBlackEatRightAhead(damone) && damone.getPriorityEat() != 4) {
            damone.setX(xOriginal);
            damone.setY(yOriginal);
            return true;
        } else if (canDamoneBlackEatLeftAhead(damone) && damone.getPriorityEat() != 3) {
            damone.setX(xOriginal);
            damone.setY(yOriginal);
            return true;
        } else if (canDamoneBlackEatRightBehind(damone) && damone.getPriorityEat() != 2) {
            damone.setX(xOriginal);
            damone.setY(yOriginal);
            return true;
        } else if (canDamoneBlackEatLeftBehind(damone) && damone.getPriorityEat() != 1) {
            damone.setX(xOriginal);
            damone.setY(yOriginal);
            return true;
        }
        damone.setX(xOriginal);
        damone.setY(yOriginal);
        return false;
    }
    
    private boolean canDamoneBlackEat2(Damone damone) {
        int xOriginal = damone.getX();
        int yOriginal = damone.getY();
        boolean risultato = false;

        if (canDamoneBlackEatRightAhead(damone) && damone.getPriorityEat() != 4) {
            damone.setPriorityEat(1);
            damone.setxPedinaMangiata2(damone.getxPedinaMangiata1());
            damone.setyPedinaMangiata2(damone.getyPedinaMangiata1());
            damone.setX(damone.getX() + 2);
            damone.setY(damone.getY() + 2);
            risultato = canEatAgain(damone, xOriginal, yOriginal);
        } else if (canDamoneBlackEatLeftAhead(damone) && !risultato
                && damone.getPriorityEat() != 3) {
            damone.setPriorityEat(2);
            damone.setxPedinaMangiata2(damone.getxPedinaMangiata1());
            damone.setyPedinaMangiata2(damone.getyPedinaMangiata1());
            damone.setX(damone.getX() + 2);
            damone.setY(damone.getY() - 2);
            risultato = canEatAgain(damone, xOriginal, yOriginal);
        } else if (canDamoneBlackEatRightBehind(damone) && !risultato
                && damone.getPriorityEat() != 2) {
            damone.setPriorityEat(3);
            damone.setxPedinaMangiata2(damone.getxPedinaMangiata1());
            damone.setyPedinaMangiata2(damone.getyPedinaMangiata1());
            damone.setX(damone.getX() - 2);
            damone.setY(damone.getY() + 2);
            risultato = canEatAgain(damone, xOriginal, yOriginal);
        } else if (canDamoneBlackEatLeftBehind(damone) && !risultato
                && damone.getPriorityEat() != 1) {
            damone.setPriorityEat(4);
            damone.setxPedinaMangiata2(damone.getxPedinaMangiata1());
            damone.setyPedinaMangiata2(damone.getyPedinaMangiata1());
            damone.setX(damone.getX() - 2);
            damone.setY(damone.getY() - 2);
            risultato = canEatAgain(damone, xOriginal, yOriginal);
        }
        
        return risultato;
    }
         
    private boolean canDamoneBlackEat3(Damone damone) {
        int xOriginal = damone.getX();
        int yOriginal = damone.getY();
   
        if (canDamoneBlackEatLeftBehind(damone)) {
            damone.setxPedinaMangiata3(damone.getxPedinaMangiata1());
            damone.setyPedinaMangiata3(damone.getyPedinaMangiata1());
            damone.setX(damone.getX() - 2);
            damone.setY(damone.getY() - 2);
            damone.setPriorityEat(4);
            if (canDamoneBlackEat2(damone)) {
                return true;
            }
        } else if (canDamoneBlackEatRightBehind(damone)) {
            damone.setxPedinaMangiata3(damone.getxPedinaMangiata1());
            damone.setyPedinaMangiata3(damone.getyPedinaMangiata1());
            damone.setX(damone.getX() - 2);
            damone.setY(damone.getY() + 2);
            damone.setPriorityEat(3);
            if (canDamoneBlackEat2(damone)) {
                return true;
            }
        } else if (canDamoneBlackEatRightAhead(damone)) {
            damone.setxPedinaMangiata3(damone.getxPedinaMangiata1());
            damone.setyPedinaMangiata3(damone.getyPedinaMangiata1());
            damone.setX(damone.getX() + 2);
            damone.setY(damone.getY() + 2);
            damone.setPriorityEat(1);
            if (canDamoneBlackEat2(damone)) {
                return true;
            }
        } else if (canDamoneBlackEatLeftAhead(damone)) {
            damone.setxPedinaMangiata3(damone.getxPedinaMangiata1());
            damone.setyPedinaMangiata3(damone.getyPedinaMangiata1());
            damone.setX(damone.getX() + 2);
            damone.setY(damone.getY() - 2);
            damone.setPriorityEat(2);
            if (canDamoneBlackEat2(damone)) {
                return true;
            }
        }
        damone.setX(xOriginal);
        damone.setY(yOriginal);
        return false;
    }
    
    private boolean canDamoneBlackMove(Damone damone) {
        boolean casellaDestraOccupata = false;
        boolean casellaSinistraOccupata = false;
        boolean fine = false;
        
        if(damone.getX() - 1 < 0)
            fine = true;
        else {
            
                for(int i = 0; i < arrayPedineNere.size(); i++) {
                    if (damone.getX() - 1 == arrayPedineNere.get(i).getX()
                        && damone.getY() + 1 == arrayPedineNere.get(i).getY())
                        casellaDestraOccupata = true;
                }
            
                for(int i = 0; i < arrayPedineNere.size(); i++) {
                    if (damone.getX() - 1 == arrayPedineNere.get(i).getX()
                        && damone.getY() - 1 == arrayPedineNere.get(i).getY())
                        casellaSinistraOccupata = true;
                }
                
                
                for(int i = 0; i < arrayPedineBianche.size(); i++) {
                    if (damone.getX() - 1 == arrayPedineBianche.get(i).getX()
                        && damone.getY() + 1 == arrayPedineBianche.get(i).getY()) {
                                if(!controllaSeCasellaLibera(damone.getX() - 2, damone.getY() + 2))
                                    casellaDestraOccupata = true;  
                    }
                }
                    
                for(int i = 0; i < arrayPedineBianche.size(); i++) {
                    if (damone.getX() - 1 == arrayPedineBianche.get(i).getX()
                        && damone.getY() - 1 == arrayPedineBianche.get(i).getY()) {
                            if(!controllaSeCasellaLibera(damone.getX() - 2, damone.getY() - 2))    
                            casellaSinistraOccupata = true;
                        }     
                }
                
                if(damone.getY() + 1 > 7)
                    casellaDestraOccupata = true;
                if(damone.getY() - 1 < 0)
                    casellaSinistraOccupata = true;
        }
        
        if(casellaDestraOccupata && casellaSinistraOccupata)
            fine = true;
        else
            return true;
        if(fine)
            return canBlackMove(damone);
        else
            return true;
    }

    private boolean canDamoneBlackMoveRightAhead(Damone damone) {
        return canBlackMoveRight(damone);
    }

    private boolean canDamoneBlackMoveRightBehind(Damone damone) {
        if(!controllaSeCasellaLibera(damone.getX() - 1, damone.getY() + 1))
            return false;
        else {
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (damone.getX() - 2 == arrayPedineBianche.get(i).getX()
                        && damone.getY() + 2 == arrayPedineBianche.get(i).getY()) 
                    seVadoDestraIndietroVengoMangiato = true;
            }
            
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (damone.getX() - 2 == arrayPedineBianche.get(i).getX()
                        && damone.getY() == arrayPedineBianche.get(i).getY()
                        && damone.getY() + 2 <= 7
                        && controllaSeCasellaLibera(damone.getX(), damone.getY() + 2)) 
                    seVadoDestraIndietroVengoMangiato = true;
                else if(damone.getX() == arrayPedineBianche.get(i).getX()
                        && damone.getY() + 2 == arrayPedineBianche.get(i).getY()
                        && damone.getX() - 2 >= 0
                        && controllaSeCasellaLibera(damone.getX() - 2, damone.getY()))
                    seVadoDestraIndietroVengoMangiato = true;
            }
        
            if(damone.getX() - 1 < 0 || damone.getY() + 1 > 7)
                return false;
            else {
                damone.setXTemp(damone.getX() - 1);
                damone.setYTemp(damone.getY() + 1);
                return true;
            }
        }
    }

    private boolean canDamoneBlackMoveLeftAhead(Damone damone) {
        return canBlackMoveLeft(damone);
    }

    private boolean canDamoneBlackMoveLeftBehind(Damone damone) {
        if(!controllaSeCasellaLibera(damone.getX() - 1, damone.getY() - 1))
            return false;
        else {
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (damone.getX() - 2 == arrayPedineBianche.get(i).getX()
                        && damone.getY() - 2 == arrayPedineBianche.get(i).getY())
                    seVadoSinistraIndietroVengoMangiato = true;
            }
            
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (damone.getX() - 2 == arrayPedineBianche.get(i).getX()
                        && damone.getY() == arrayPedineBianche.get(i).getY()
                        && damone.getY() - 2 >= 0
                        && controllaSeCasellaLibera(damone.getX(), damone.getY() - 2))
                    seVadoSinistraIndietroVengoMangiato = true;
                else if(damone.getX() == arrayPedineBianche.get(i).getX()
                        && damone.getY() - 2 == arrayPedineBianche.get(i).getY()
                        && damone.getX() - 2 >= 0
                        && controllaSeCasellaLibera(damone.getX() - 2, damone.getY()))
                    seVadoSinistraIndietroVengoMangiato = true;
            }
        
            if(damone.getX() - 1 < 0 || damone.getY() - 1 < 0)
                return false;
            else {
                    damone.setXTemp(damone.getX() - 1);
                    damone.setYTemp(damone.getY() - 1);
                    return true;
            }
        }
    }
}
