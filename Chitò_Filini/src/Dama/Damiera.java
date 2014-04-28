package Dama;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import Grafica.Bottone;

public class Damiera {
    
    public static List<Pedina> arrayPedineBianche = new ArrayList<Pedina>();
    public static List<Pedina> arrayPedineNere = new ArrayList<Pedina>();
    public static Casella[][] caselle = new Casella[8][8];
    public static Bottone[][] damieraBottoni = new Bottone[8][8];
    
    public Damiera() {
        for(int x = 0; x < 8; x++)
            for(int y = 0; y < 8; y++)
                //Casella: pari = nera, dispari == bianca
                //Pedina: 0 = nera, 1 = bianca
                if((x + y) % 2 == 0) 
                    caselle[x][y] = new Casella(false, x, y);
                else
                    caselle[x][y] = new Casella(true, x, y);
    }
    
    public void damieraDiBottoni() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((i + j) % 2 != 0)
                    damieraBottoni[i][j] = new Bottone(i, j, 
                            new ImageIcon("img/casellaBianca2.jpg"), "button_cb");
                else {
                    damieraBottoni[i][j] = new Bottone(i, j, 
                            new ImageIcon("img/casellaNera2.jpg"), "button_cn");
                    for(Pedina pedina : arrayPedineBianche) {
                        if(pedina.getX() == i && pedina.getY() == j) {
                            if(pedina instanceof Damone)
                                damieraBottoni[i][j] = new Bottone(i, j, 
                                        new ImageIcon("img/damoneBianco2.jpg"), "button_DB");
                             else
                                damieraBottoni[i][j] = new Bottone(i, j, 
                                        new ImageIcon("img/pedinaBianca2.jpg"), "button_PB");
                        }
                    }
                    for(Pedina pedina : arrayPedineNere) {
                        if(pedina.getX() == i && pedina.getY() == j) {
                            if(pedina instanceof Damone)
                                damieraBottoni[i][j] = new Bottone(i, j, 
                                        new ImageIcon("img/damoneNero2.jpg"), "button_DN");
                             else
                                damieraBottoni[i][j] = new Bottone(i, j, 
                                        new ImageIcon("img/pedinaNera2.jpg"), "button_PN");
                        }
                    }                 
                }
            }
        }
    }
    
    
    public void inizializzaArrayPedineNere() {
            for(int x = 0; x < 3; x++)
                for(int y = 0; y < 8; y++)
                    if((x + y) % 2 == 0)
                        arrayPedineNere.add(new Pedina(false, x, y));
    }
    
    public void inizializzaArrayPedineBianche() {
            for(int x = 5; x < 8; x++)
                for(int y = 0; y < 8; y++)
                    if((x + y) % 2 == 0)
                        arrayPedineBianche.add(new Pedina(true, x, y));
    }
    
    }
            

