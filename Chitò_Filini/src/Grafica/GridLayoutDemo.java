package Grafica;

import static Dama.Damiera.arrayPedineBianche;
import static Dama.Damiera.arrayPedineNere;
import static Dama.Damiera.damieraBottoni;
import Dama.Damone;
import Dama.MossaPedinaBianca;
import static Dama.MossaPedinaBianca.idMossaBianca;
import Dama.MossaPedinaNera;
import Dama.Pedina;
import static Dama.ProvaMain.damiera;
import static Dama.ProvaMain.damieraGrafica;
import static Dama.ProvaMain.turnWhite;
import Dama.TurnoPedinaNera;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GridLayoutDemo extends JFrame implements ActionListener {
    
    private JButton button_PN;
    private JButton button_cn;
    private JButton button_PB;
    private JButton button_cb;
    public static int x1Button;
    public static int y1Button;
    public static int x2Button;
    public static int y2Button;
    public static int mangiataBianca = 0;
    public static boolean firstClick = true;
    public static boolean secondClick = false;
    public static boolean repeatSecondClick = true;
    public static boolean pedinaBiancaDeveMangiare = false;
    public static boolean vittoriaNera = false;
    public static boolean vittoriaBianca = false;
    public static Pedina pedinaBiancaSelezionata = null;
    
    public static MossaPedinaBianca mossaPedinaBianca = new MossaPedinaBianca();

    public GridLayoutDemo(String name) {
        super();
        
        setSize(700, 700);
        setLayout(new GridLayout(8, 8));

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                damieraBottoni[x][y].addActionListener(this);
                add(damieraBottoni[x][y]);
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(turnWhite){ 
        Object actionCommand = evt.getSource();
        Bottone actionCommand2 = (Bottone) actionCommand;
        pedineBiancheCheDevonoMangiare(); //verifica quali pedine è obbligato a spostare perchè devono mangiare
        
        if (firstClick) {
            x1Button = actionCommand2.getXBottone();
            y1Button = actionCommand2.getYBottone();
            verificaFirstClick(x1Button, y1Button);
            return;
        } else if (!firstClick && secondClick && repeatSecondClick) {
            x2Button = actionCommand2.getXBottone();
            y2Button = actionCommand2.getYBottone();
            verificaSecondClick(x2Button, y2Button);
       
            if(!repeatSecondClick) {
                mossaPedinaBianca.spostamentoPedinaODamoneBianco();
            } else
                return;
            if(!repeatSecondClick) {
                 if(pedinaBiancaDeveMangiare && (idMossaBianca != 2 && idMossaBianca != 3 
                         && idMossaBianca != 6 && idMossaBianca != 7 &&
                         idMossaBianca != 8 && idMossaBianca != 9)) {
                    JOptionPane.showMessageDialog(null, "Devi mangiare!");     
                    firstClick = false;
                    secondClick = true;
                    repeatSecondClick = true;
                    return;
                 }
            }
            else 
                return;
            if(!repeatSecondClick) {
                damieraGrafica.ridisegnaGridLayoutBianco();
                damieraGrafica.setVisible(false);
                damieraGrafica = new GridLayoutDemo("Demo2");
                damieraGrafica.setVisible(true);
                
                if(!isFinishedMatch()) {
                if(pedinaBiancaDeveMangiare && mangiataBianca <= 1) { 
                    pedinaBiancaSelezionata.setX(x2Button);
                    pedinaBiancaSelezionata.setY(y2Button);
                    if(canPedinaBiancaSelezionataEatAgain()) {
                        turnWhite = true;
                        pedinaBiancaDeveMangiare = false;
                        mangiataBianca ++;
                        repeatSecondClick = true;
                        firstClick = true;
                        secondClick = false;
                        return;
                    } else {
                        turnWhite = false;
                        repeatSecondClick = true;
                        firstClick = true;
                        secondClick = false;
                        pedinaBiancaSelezionata = null;
                        pedinaBiancaDeveMangiare = false;
                        mangiataBianca = 0;
                    }
                } else {
                    turnWhite = false;
                    repeatSecondClick = true;
                    firstClick = true;
                    secondClick = false;
                    pedinaBiancaSelezionata = null;
                    pedinaBiancaDeveMangiare = false;
                    mangiataBianca = 0;
                }
                } else {
                    if(vittoriaBianca)
                        JOptionPane.showMessageDialog(null, "Partita finita: hai vinto!");
                    else if(vittoriaNera)
                        JOptionPane.showMessageDialog(null, "Partita finita: hai perso!");
                    else
                        JOptionPane.showMessageDialog(null, "Partita finita: parità!");
                    
                    turnWhite = false;
                }
                    
            } else 
                return;  
        }
        
        TurnoPedinaNera turnoPedinaNera = new TurnoPedinaNera();
        turnoPedinaNera.finalPositionPedina();
        
        damiera.damieraDiBottoni();
        damieraGrafica.setVisible(false);
        damieraGrafica = new GridLayoutDemo("Demo2");
        damieraGrafica.setVisible(true);
        
        if(isFinishedMatch()) {
            if(vittoriaBianca)
                JOptionPane.showMessageDialog(null, "Partita finita: hai vinto!");
            else if(vittoriaNera)
                JOptionPane.showMessageDialog(null, "Partita finita: hai perso!");
            else
                JOptionPane.showMessageDialog(null, "Partita finita: parità!");
            
            turnWhite = false;
        } else 
            turnWhite = true;
        
            
        }
    }
    
    private boolean canPedinaBiancaSelezionataEatAgain() {
        if(pedinaBiancaSelezionata instanceof Damone) {
            return puoDamoneBiancoMangiare((Damone)pedinaBiancaSelezionata);
        } else {
            return puoPedinaBiancaMangiare(pedinaBiancaSelezionata);
        }
    }
    
    private void pedineBiancheCheDevonoMangiare() {
        for (int i = 0; i < arrayPedineBianche.size(); i++) {
            if(arrayPedineBianche.get(i) instanceof Damone) {
                if(puoDamoneBiancoMangiare((Damone)arrayPedineBianche.get(i))) {
                    arrayPedineBianche.get(i).setPriorityEat(1);
                    pedinaBiancaDeveMangiare = true;
                } else
                    arrayPedineBianche.get(i).setPriorityEat(0);
            } else {
                if(puoPedinaBiancaMangiare(arrayPedineBianche.get(i))) {
                    arrayPedineBianche.get(i).setPriorityEat(1);
                    pedinaBiancaDeveMangiare = true;
                } else
                    arrayPedineBianche.get(i).setPriorityEat(0);
            }     
         }
    }
    
    private boolean puoDamoneBiancoMangiare(Damone damone) {
        MossaPedinaNera mossaPedinaBianca = new MossaPedinaNera();
        
        //damone bianco può mangiare avanti a destra
        if(mossaPedinaBianca.controllaSeCasellaLibera(damone.getX() - 2, damone.getY() + 2)
                && damone.getX() - 2 >= 0 && damone.getY() + 2 <= 7) {    
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (damone.getX() - 1) && pedina.getY() == (damone.getY() + 1))
                    return true;
        } 
        //damone bianco può mangiare avanti a sinistra 
        if(mossaPedinaBianca.controllaSeCasellaLibera(damone.getX() - 2, damone.getY() - 2)
                && damone.getX() - 2 >= 0 && damone.getY() - 2 >= 0) {
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (damone.getX() - 1) && pedina.getY() == (damone.getY() - 1))
                    return true;
        }
        //damone bianco può mangiare indietro a destra 
        if(mossaPedinaBianca.controllaSeCasellaLibera(damone.getX() + 2, damone.getY() + 2)
                && damone.getX() + 2 <= 7 && damone.getY() + 2 <= 7) {
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (damone.getX() + 1) && pedina.getY() == (damone.getY() + 1))
                    return true;
        }
        //damone bianco può mangiare indietro a sinistra
        if(mossaPedinaBianca.controllaSeCasellaLibera(damone.getX() + 2, damone.getY() - 2)
                && damone.getX() + 2 <= 7 && damone.getY() - 2 >= 0) {
            for(Pedina pedina : arrayPedineNere)
                if(pedina.getX() == (damone.getX() + 1) && pedina.getY() == (damone.getY() - 1))
                    return true;
        }
        return false;
    }
    
    private boolean puoPedinaBiancaMangiare(Pedina pedina) {
        MossaPedinaNera mossaPedinaBianca = new MossaPedinaNera();
        
        //pedina bianca può mangiare avanti a destra
        if(mossaPedinaBianca.controllaSeCasellaLibera(pedina.getX() - 2, pedina.getY() + 2)
                && pedina.getX() - 2 >= 0 && pedina.getY() + 2 <= 7) {    
            for(Pedina pedinaNera : arrayPedineNere)
                if(pedinaNera.getX() == (pedina.getX() - 1) && pedinaNera.getY() == (pedina.getY() + 1))
                    return true;
        } 
        //pedina bianca può mangiare avanti a sinistra 
        
        if(mossaPedinaBianca.controllaSeCasellaLibera(pedina.getX() - 2, pedina.getY() - 2)
                && pedina.getX() - 2 >= 0 && pedina.getY() - 2 >= 0) {
            for(Pedina pedinaNera : arrayPedineNere)
                if(pedinaNera.getX() == (pedina.getX() - 1) && pedinaNera.getY() == (pedina.getY() - 1))
                    return true;
        }
        return false;
    }
    
    
    private void verificaFirstClick(int x1, int y1) {  
        Pedina pedinaClick1 = new Pedina();
        boolean findPedinaClick1 = false;
        
        if ((x1 + y1) % 2 == 0) {
            for (int i = 0; i < arrayPedineBianche.size(); i++) {
                if (arrayPedineBianche.get(i).getX() == x1 && arrayPedineBianche.get(i).getY() == y1) { 
                    pedinaClick1 = arrayPedineBianche.get(i);
                    findPedinaClick1 = true;
                }
            }
            
            if(!findPedinaClick1) {
                firstClick = true;
                secondClick = false;
                JOptionPane.showMessageDialog(null, "Primo click non valido!");
                return;
            } else {
                findPedinaClick1 = false;
                if(!canWhiteMove(pedinaClick1, x1, y1)) {
                    firstClick = true;
                    secondClick = false;   
                    return;
                } else if(mangiataBianca != 0) {
                    if(x1 != pedinaBiancaSelezionata.getX() || y1 != pedinaBiancaSelezionata.getY()) {
                        firstClick = true;
                        secondClick = false;
                        JOptionPane.showMessageDialog(null, "Devi mangiare con la pedina di coordinate: (" 
                            + pedinaBiancaSelezionata.getX() + ", " + pedinaBiancaSelezionata.getY() + ")");  
                        return;
                    } else {
                        pedinaBiancaSelezionata = pedinaClick1;
                        firstClick = false;
                        secondClick = true;
                        return;
                    }
                        
                } else if(pedinaBiancaDeveMangiare && pedinaClick1.getPriorityEat() == 0) {
                    firstClick = true;
                    secondClick = false;
                    JOptionPane.showMessageDialog(null, "Devi mangiare!");
                    return;
                } else {
                    pedinaBiancaSelezionata = pedinaClick1;
                    firstClick = false;
                    secondClick = true;
                }       
            }
            
        } else {
            firstClick = true;
            secondClick = false;
            JOptionPane.showMessageDialog(null, "Primo click non valido!");
        }
    }
    
    private boolean canWhiteMove(Pedina pedinaClick1, int x, int y) {
         if(pedinaClick1 instanceof Damone) {
             if((eCasellaOccupataDaBianco(x + 1, y + 1) || pedinaClick1.getX() + 1 > 7 || pedinaClick1.getY() + 1 > 7) &&
                     (eCasellaOccupataDaBianco(x + 1, y - 1) || pedinaClick1.getX() + 1 > 7 || pedinaClick1.getY() - 1 < 0) &&
                     (eCasellaOccupataDaBianco(x - 1, y + 1) || pedinaClick1.getX() - 1 < 0 || pedinaClick1.getY() + 1 > 7) &&
                     (eCasellaOccupataDaBianco(x - 1, y - 1) || pedinaClick1.getX() - 1 < 0 || pedinaClick1.getY() - 1 < 0)) {
                 
                 return false;
             } else
                 return true;
         } else {
             if((eCasellaOccupataDaBianco(x - 1, y + 1) || pedinaClick1.getX() - 1 < 0 || pedinaClick1.getY() + 1 > 7) &&
                     (eCasellaOccupataDaBianco(x - 1, y - 1) || pedinaClick1.getX() - 1 < 0 || pedinaClick1.getY() - 1 < 0)) {
                 
                 return false;
             } else
                 return true;  
         }          
    }
    
    private boolean eCasellaOccupataDaBianco(int xTemp, int yTemp) {
        for(int i = 0; i < arrayPedineBianche.size(); i++) {
            if(xTemp == arrayPedineBianche.get(i).getX()
                    && yTemp == arrayPedineBianche.get(i).getY()) {
                return true;
            }
        } 
       return false;
    }
    
    private void verificaSecondClick(int x2, int y2) {
        
        if ((x2 + y2) % 2 == 0) {
            for (Pedina pedina : arrayPedineBianche) {
                if (pedina.getX() == x2 && pedina.getY() == y2) {
                    firstClick = false;
                    secondClick = true;
                    JOptionPane.showMessageDialog(null, "Secondo click non valido!");
                    repeatSecondClick = true;
                    return;
                } 
            }
            
            for (Pedina pedina : arrayPedineNere) {
                if (pedina.getX() == x2 && pedina.getY() == y2) {
                    firstClick = false;
                    secondClick = true;
                    JOptionPane.showMessageDialog(null, "Secondo click non valido!");
                    repeatSecondClick = true;
                    return;
                }
            }
            
            repeatSecondClick = false;
            
        } else {
            firstClick = false;
            secondClick = true;
            repeatSecondClick = true;
            JOptionPane.showMessageDialog(null, "Secondo click non valido!");
        }
    }
    
    public void ridisegnaGridLayoutBianco() {
        switch(idMossaBianca) {
            case 1: case 10: //pedina bianca si sposta avanti a destra o a sinistra
                damieraBottoni[x1Button][y1Button] = new Bottone(x1Button, y1Button, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/pedinaBianca2.jpg"), "button_PB");
                break;
            case 2: //pedina bianca mangia in avanti a destra
                damieraBottoni[x1Button][y1Button] = new Bottone(x1Button, y1Button, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x1Button - 1][y1Button + 1] = new Bottone(x1Button - 1, y1Button + 1, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/pedinaBianca2.jpg"), "button_PB");
                for(int i = 0; i < arrayPedineNere.size(); i++) {  
                    if(arrayPedineNere.get(i).getX() == x1Button - 1 && arrayPedineNere.get(i).getY() == y1Button + 1) 
                        arrayPedineNere.remove(arrayPedineNere.get(i));
                }
                break;
            case 3: //pedina bianca mangia in avanti a sinistra
                damieraBottoni[x1Button][y1Button] = new Bottone(x1Button, y1Button, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x1Button - 1][y1Button - 1] = new Bottone(x1Button - 1, y1Button - 1, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/pedinaBianca2.jpg"), "button_PB");
                for(int i = 0; i < arrayPedineNere.size(); i++) {  
                    if(arrayPedineNere.get(i).getX() == x1Button - 1 && arrayPedineNere.get(i).getY() == y1Button - 1) 
                        arrayPedineNere.remove(arrayPedineNere.get(i));
                }
                break;
            case 4: case 5: case 11: case 12: //damone bianco si sposta avanti o indietro a destra o a sinistra
                damieraBottoni[x1Button][y1Button] = new Bottone(x1Button, y1Button, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/DamoneBianco2.jpg"), "button_PB");
                break;
            case 6: //damone bianco mangia in avanti a destra  
                damieraBottoni[x1Button][y1Button] = new Bottone(x1Button, y1Button, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x1Button - 1][y1Button + 1] = new Bottone(x1Button - 1, y1Button + 1, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/DamoneBianco2.jpg"), "button_PB");
                for(int i = 0; i < arrayPedineNere.size(); i++) {  
                    if(arrayPedineNere.get(i).getX() == x1Button - 1 && arrayPedineNere.get(i).getY() == y1Button + 1) 
                        arrayPedineNere.remove(arrayPedineNere.get(i));
                }
                break;
            case 7: //damone bianco mangia in avanti a sinistra
                damieraBottoni[x1Button][y1Button] = new Bottone(x1Button, y1Button, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x1Button - 1][y1Button - 1] = new Bottone(x1Button - 1, y1Button - 1, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/damoneBianco2.jpg"), "button_PB");
                for(int i = 0; i < arrayPedineNere.size(); i++) {  
                    if(arrayPedineNere.get(i).getX() == x1Button - 1 && arrayPedineNere.get(i).getY() == y1Button - 1) 
                        arrayPedineNere.remove(arrayPedineNere.get(i));
                }
                break;
            case 8: //damone bianco mangia indietro a destra  
                damieraBottoni[x1Button][y1Button] = new Bottone(x1Button, y1Button, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x1Button + 1][y1Button + 1] = new Bottone(x1Button + 1, y1Button + 1, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/DamoneBianco2.jpg"), "button_PB");
                for(int i = 0; i < arrayPedineNere.size(); i++) { 
                    if(arrayPedineNere.get(i).getX() == x1Button + 1 && arrayPedineNere.get(i).getY() == y1Button + 1) 
                        arrayPedineNere.remove(arrayPedineNere.get(i));
                }
                break;
            case 9: //damone bianco mangia indietro a sinistra
                damieraBottoni[x1Button][y1Button] = new Bottone(x1Button, y1Button, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x1Button + 1][y1Button - 1] = new Bottone(x1Button + 1, y1Button - 1, 
                        new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/damoneBianco2.jpg"), "button_PB");
                for(int i = 0; i < arrayPedineNere.size(); i++) {  
                    if(arrayPedineNere.get(i).getX() == x1Button + 1 && arrayPedineNere.get(i).getY() == y1Button - 1) 
                        arrayPedineNere.remove(arrayPedineNere.get(i));
                }
                break;       
        }
            
        for(int i = 0; i < arrayPedineBianche.size(); i++) { 
            if(x1Button == arrayPedineBianche.get(i).getX() && y1Button == arrayPedineBianche.get(i).getY()) {
                //Diventa damone
                if(x2Button == 0) {
                    Damone lastDamone = new Damone(false, x2Button, y2Button);
                    arrayPedineBianche.remove(i);
                    arrayPedineBianche.add(lastDamone);
                    damieraBottoni[x2Button][y2Button] = new Bottone(x2Button, y2Button, 
                        new ImageIcon("img/damoneBianco2.jpg"), "button_PB");
                } else if(arrayPedineBianche.get(i) instanceof Damone) {
                    Damone lastDamone = new Damone(false, x2Button, y2Button);
                    arrayPedineBianche.remove(i);
                    arrayPedineBianche.add(lastDamone);
                } else {
                    Pedina lastPedina = new Pedina(false, x2Button, y2Button);
                    arrayPedineBianche.remove(i);
                    arrayPedineBianche.add(lastPedina);
                }
            }
        }
        
        
    }
    
    private boolean isFinishedMatch() {
        if(arrayPedineBianche.isEmpty() || whiteCannotMove())
            vittoriaNera = true;
        if(arrayPedineNere.isEmpty() || blackCannotMove())
            vittoriaBianca = true;
        if(vittoriaBianca || vittoriaNera)
            return true;
        else
            return false;
    }
    
    private boolean whiteCannotMove() {
        for (Pedina pedina : arrayPedineBianche)
            if(canWhiteMove(pedina, pedina.getX(), pedina.getY()))
                return false;
        return true;
    }
    
    private boolean blackCannotMove() {
        for (Pedina pedina : arrayPedineNere)
            if(pedina.getPriority() != -6)
                return false;
        return true;
    }
     
}
