package Dama;

import static Dama.Damiera.arrayPedineBianche;
import static Dama.Damiera.arrayPedineNere;

public class Casella {

    //casella bianca=true, casella nera=false;
    private boolean colorCasella;
    private int x, y;

    public Casella() {}
    
    public Casella(boolean colorCasella, int x, int y) {
        this.colorCasella = colorCasella;
        this.x = x;
        this.y = y;
    }

//Metodi che confrontano le coordinate della casella con quelle della pedina    
    public boolean casellaContienePedinaNera() {
        for (Pedina pedina : arrayPedineNere) {
            if ((pedina.getX() == x) && (pedina.getY() == y) && (pedina instanceof Pedina)) {
                return true;
            }
        }
        return false;
    }

    public boolean casellaContienePedinaBianca() {
        for (Pedina pedina : arrayPedineBianche) {
            if (pedina.getX() == x && pedina.getY() == y && (pedina instanceof Pedina)) {
                return true;
            }
        }
        return false;
    }

    public boolean casellaContieneDamoneNero() {
        for (Pedina pedina : arrayPedineNere) {
            if (pedina.getX() == x && pedina.getY() == y && (pedina instanceof Damone)) {
                return true;
            }
        }
        return false;

    }

    public boolean casellaContieneDamoneBianco() {
        for (Pedina pedina : arrayPedineBianche) {
            if (pedina.getX() == x && pedina.getY() == y && (pedina instanceof Pedina)) {
                return true;
            }
        }
        return false;

    }

    public boolean casellaNeraVuota() {
        if (!colorCasella && !casellaContienePedinaNera()
                && !casellaContienePedinaBianca() && !casellaContieneDamoneBianco()
                && !casellaContieneDamoneNero()) {
            return true;
        } else {
            return false;
        }
    }

    public int getXCasella() {
        return this.x;
    }

    public int getYCasella() {
        return this.y;
    }

    public boolean getColorCasella() {
        return this.colorCasella;
    }

    public void setXCasella(int x) {
        this.x = x;
    }

    public void setYCasella(int y) {
        this.y = y;
    }

    public void setColorCasella(boolean color) {
        this.colorCasella = color;
    }

}
