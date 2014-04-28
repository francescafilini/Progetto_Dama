package Grafica;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Bottone extends JButton {
    private int xBottone;
    private int yBottone;
    private ImageIcon path;
    private String name;
    
    public Bottone(int xBottone, int yBottone, ImageIcon path, String name) {
        super(path);
        this.path = path;
        this.xBottone = xBottone;
        this.yBottone = yBottone;
        this.name = name;
    }
    
    public void setPath(ImageIcon path) {
       this.path = path;
    }
    
    public void setXBottone(int xBottone) {
        this.xBottone = xBottone;
    }
    
    public void setYBottone(int yBottone) {
        this.yBottone = yBottone;
    }
    
    public int getXBottone() {
        return xBottone;
    }
    
    public int getYBottone() {
        return yBottone;
    }
}
