import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String snake = "images/head.png";
    private String comida = "images/fries.png";
    private String corpo = "images/body.png";
    
    

    private int p = 60;
    private int q = 80;
    private int x;
    private int y;
    private int j;
    private int k;
    private Image image;
    private Image imageComida;
    private Image imageCorpo;
    private Object w;
    private Snake proximo;
    
    public Snake() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
        image = ii.getImage();
        x = 40;
        y = 60;
        
    }
    
   
    public void move(int _x, int _y) {
        if(x>=750 || y> 550 || x==0 || y==0){
            Board fim = new Board();
            fim.setIsPlaying(false);
           
        }else{
            x += _x;
            y += _y;
        }
        
        
    }
    
    public void geraComida(int _p, int _q){
        p = _p;
        q = _q;
        
    }
    
    public void comida() {
        ImageIcon i = new ImageIcon(this.getClass().getResource(comida));
        imageComida = i.getImage();
        p = 60;
        q = 200;
    }
    
    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }
    
    public Image getImageComida() {
        return imageComida;
    }
    
    public void corpo() {
        ImageIcon iii = new ImageIcon(this.getClass().getResource(corpo));
        imageComida = iii.getImage();
    }
    
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    
    public int getJ() {
        return j;
    }

    public int getK() {
        return k;
    }

    public Image getImageCorpo() {
        return image;
    }
    
    
    
    public Snake(String args)
    {
        this.w = args;
    }

    public void setW(Object _w){
        this.w = _w;
    }
    
    public Object getW(){
        return this.w;
    }
    
    public void setProximo(Snake _proximo){
        this.proximo = _proximo;
    }
    
    public Snake getProximo(){
        return this.proximo;
    }

}
