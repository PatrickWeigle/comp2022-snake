import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Snake snake = new Snake();
    private Snake comida;
    private Snake corpo;
    private static int comidax;
    private static int comiday;
    private static String mover = "rigth";
    private static boolean valida = false;
    
    
    
    //vai receber as posiçoes
    private static int pcomidax;
    private static int qcomiday;
    private static int xsnake;
    private static int ysnake;
    Lista lista = new Lista();
    
    
    
    private static boolean isPlaying = true;

    private Font font;
       
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);       
        
        Snake aux = lista.getCabeca();
        while(aux.getProximo() != null){
            add(aux);
            aux = aux.getProximo();
        }
//         //if(lista.isEmpty() == true){
//             snake = new Snake();
//             lista.inserirFinal(snake);
//             add(snake);
//         //}
        
        comida = new Snake();
        comida.comida();
        add(comida);
        
//         corpo = new Snake(1);
//         //corpo.corpo();
//         add(corpo);
       
        timer = new Timer(5, this);
        timer.start();
    }
    
    public void setIsPlaying(boolean x){
        this.isPlaying = x;
    }


    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;   
        
        if(isPlaying== true){
            
        //g2d.drawImage(snake.getImage(),snake.getX(),snake.getY(),this);
        
        g2d.drawImage(comida.getImageComida(),comida.getP(),comida.getQ(),this);
        Snake aux = lista.getCabeca();
        if(aux.getProximo() == null){
           g2d.drawImage(aux.getImage(),aux.getX(), aux.getY(),this);
        }else{
            while(aux.getProximo()!= null){
                g2d.drawImage(aux.getImage(),aux.getX(), aux.getY(),this);
                aux = aux.getProximo();
            }
        }
//         Snake aux = lista.getCabeca();
//         aux = lista.ultimaSnake();
//         aux = lista.validaCorpo(aux);
//         if(aux != null){
//             g2d.drawImage(aux.getImage(),aux.getX()+50,aux.getY()+50,this);
//         }
    }else{
        fimJogo(g);
    }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }


//     public void paintIntro(Graphics g) {
//         if(isPlaying){
//             isPlaying = false;
//             Graphics2D g2d = (Graphics2D) g;
//             try{
//                 File file = new File("fonts/VT323-Regular.ttf");
//                 font = Font.createFont(Font.TRUETYPE_FONT, file);
//                 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//                 ge.registerFont(font);
//                 font = font.deriveFont(Font.PLAIN,40);
//                 g2d.setFont(font);
//             }catch (Exception e){
//                 System.out.println(e.toString());
//             }   
//             g2d.drawString("S N A K E: " + this.score, 300, 300);
//         }
//     }
    
    
    public void localComida ()
    {
        comidax = 1 + (int)(Math.random() * 750);
        comiday = 1 + (int)(Math.random() * 550);
        
        comida.geraComida(comidax,comiday);

       
    }
    
    public void posiçoes(){
        Snake aux = lista.getCabeca();
        pcomidax = comida.getP();
        qcomiday = comida.getQ();
        xsnake = aux.getX();
        ysnake = aux.getY();
    }
    
    public void actionPerformed(ActionEvent e) {
        if(mover == "left"){
            lista.moveCabeca(-1,0);
        }else if(mover == "right"){
            lista.moveCabeca(1,0);
        }else if(mover == "up"){
            lista.moveCabeca(0,-1);
        }else if(mover == "down"){
            lista.moveCabeca(0,1);
        }
        
        
        
        posiçoes();
        if((xsnake >=(pcomidax)&&(xsnake <=(pcomidax + 25)) && (qcomiday >= ysnake)&&(qcomiday <= ysnake+25))) {
            localComida();
            score.addScore(+1);
            lista.inserirFinal();
            
//             snake = new Snake();
//             lista.inserirFinal(snake);
//             snake.corpo();
//             add(corpo);
            posiçoes();
            
            
        }
        
        repaint();  
    }
    
    public void newCorpo(){
        corpo.novaSnake();
        lista.inserirFinal();
        //corpo.corpo();
        add(corpo);
        valida = true;
    }
    
    public void fimJogo(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawString("GAME OVER", 300, 250);
    }
    
    
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                    score.addScore(1);
                    break;
                    
                case KeyEvent.VK_LEFT:
                    mover = "left";
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    mover = "right";
                    break;
                    
                case KeyEvent.VK_UP:
                    mover = "up";
                    break;
                    
                case KeyEvent.VK_DOWN:
                    mover = "down";
                    break;
            }
            
        }
        
    
    
    }
    
}