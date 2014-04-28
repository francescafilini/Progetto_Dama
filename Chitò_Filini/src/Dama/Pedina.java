package Dama;


public class Pedina {
    private int x, y;
    private boolean colorPedina;
    private int xTemp;
    private int yTemp;
    private int priority;
    private int priorityEat;
    private int xPedinaMangiata1 = -1;
    private int yPedinaMangiata1 = -1;
    private int xPedinaMangiata2 = -1;
    private int yPedinaMangiata2 = -1;
    private int xPedinaMangiata3 = -1;
    private int yPedinaMangiata3 = -1;
    
    
    public Pedina() {}
    
    public Pedina(boolean color, int x, int y) {
        this.colorPedina = color;
        this.x = x;
        this.y = y;
    }
    
    public void setColor(boolean color) {
        this.colorPedina = colorPedina;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public void setXTemp(int xTemp) {
        this.xTemp = xTemp;
    }
    
    public void setYTemp(int yTemp) {
        this.yTemp = yTemp;
    }
    
    public void setxPedinaMangiata1(int xPedinaMangiata1) {
        this.xPedinaMangiata1 = xPedinaMangiata1;
    }
    
    public void setyPedinaMangiata1(int yPedinaMangiata1) {
        this.yPedinaMangiata1 = yPedinaMangiata1;
    }
    
    public void setxPedinaMangiata2(int xPedinaMangiata2) {
        this.xPedinaMangiata2 = xPedinaMangiata2;
    }
    
    public void setyPedinaMangiata2(int yPedinaMangiata2) {
        this.yPedinaMangiata2 = yPedinaMangiata2;
    }
    
    public void setxPedinaMangiata3(int xPedinaMangiata3) {
        this.xPedinaMangiata3 = xPedinaMangiata3;
    }
    
    public void setyPedinaMangiata3(int yPedinaMangiata3) {
        this.yPedinaMangiata3 = yPedinaMangiata3;
    }
    
    
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setPriorityEat(int priorityEat) {
        this.priorityEat = priorityEat;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getxPedinaMangiata1(){
        return xPedinaMangiata1;
    }
    
    public int getyPedinaMangiata1(){
        return yPedinaMangiata1;
    }
    
    public int getxPedinaMangiata2(){
        return xPedinaMangiata2;
    }
    
    public int getyPedinaMangiata2(){
        return yPedinaMangiata2;
    }
    
    public int getxPedinaMangiata3(){
        return xPedinaMangiata3;
    }
    
    public int getyPedinaMangiata3(){
        return yPedinaMangiata3;
    }
    
    public boolean getColor() {
        return colorPedina;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public int getPriorityEat() {
        return priorityEat;
    }
    
     public int getXTemp() {
        return xTemp;
    }
     
    public int getYTemp() {
        return yTemp;
    }
    
}
