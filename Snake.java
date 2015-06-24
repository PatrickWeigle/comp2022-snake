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
    private static String snake = "images/head.png";
    private String comida = "images/fries.png";
    private String corpo = "images/body.png";
    private String campo = "images/campo.png";

    private int p = 60;
    private int q = 80;
    private int x = 60 ;
    private int y = 40;
    private int j = 2;
    private int k = 2;
    private Image image;
    private Image imageComida;
    private Image imageCampo;
    private Object w;
    private Snake proximo;
    private Snake anterior;

    public Snake() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
        image = ii.getImage();
        x = 40;
        x = 60;

        this.proximo = null;
    }

    public void setSnake(String mover){
        if (mover == "left"){
            snake = "images/head.png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
            image = ii.getImage();
        }else if (mover == "right"){
            snake = "images/headRight.png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
            image = ii.getImage();
        }else if (mover == "up"){
            snake = "images/headUp.png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
            image = ii.getImage();
        }else if (mover == "down"){
            snake = "images/headDown.png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
            image = ii.getImage();
        }
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
    //     public void moveCorpo(int _j, int _k){
    //          += (_j+10);
    //         k += (_k);
    //     }

    public void geraComida(int _p, int _q){
        p = _p;
        q = _q;

    }

    public void comida() {
        ImageIcon i = new ImageIcon(this.getClass().getResource(comida));
        imageComida = i.getImage();
        p = 1 + (int)(Math.random() * 730);
        q = 1 + (int)(Math.random() * 530);

    }
    
    public void campo() {
        ImageIcon iii = new ImageIcon(this.getClass().getResource(campo));
        imageCampo = iii.getImage();
        j = 2;
        j = 2;

    }
    
    public Image getImageCampo() {
        return imageCampo;
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

    public Snake(int i) {
        //         ImageIcon iii = new ImageIcon(this.getClass().getResource(corpo));
        //         imageCorpo = iii.getImage();
        //         x;
        //         y;
        //         this.proximo = null;

        ImageIcon ii = new ImageIcon(this.getClass().getResource(corpo));
        image = ii.getImage();
        this.proximo = null;

    }

    public Snake novaSnake(){
        return new Snake(1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int _x){
        this.x += _x;
    }

    public void setY(int _y){
        this.y += _y;
    }

    public Image getImage() {
        return image;
    }

    //     public int getJ() {
    //         return j;
    //     }
    // 
    //     public int getK() {
    //         return k;
    //     }

    

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

    public Snake getAnterior(){
        return this.anterior;
    }

    public void setAnterior(Snake _anterior){
        this.anterior = _anterior;
    }

}
