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
    private Snake snake;
    private Snake comida;
    private Snake corpo;
    private static int comidax;
    private static int comiday;
    private static String mover = " ";
    
    
    
    //vai receber as posiçoes
    private static int pcomidax;
    private static int qcomiday;
    private static int xsnake;
    private static int ysnake;
    
    
    
    private static boolean isPlaying = true;

    private Font font;
       
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);       
        
        snake = new Snake();
        add(snake);
        
        comida = new Snake();
        comida.comida();
        add(comida);
        
        corpo = new Snake();
        corpo.corpo();
        add(corpo);
       
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
        g2d.drawImage(snake.getImage(),snake.getX(),snake.getY(),this);
        g2d.drawImage(comida.getImageComida(),comida.getP(),comida.getQ(),this);
        
    }else{
        fimJogo(g);
    }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }


    public void paintIntro(Graphics g) {
        if(isPlaying){
            isPlaying = false;
            Graphics2D g2d = (Graphics2D) g;
            try{
                File file = new File("fonts/VT323-Regular.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, file);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                font = font.deriveFont(Font.PLAIN,40);
                g2d.setFont(font);
            }catch (Exception e){
                System.out.println(e.toString());
            }   
            g2d.drawString("S N A K E: " + this.score, 300, 300);
        }
    }
    
    
    public void localComida ()
    {
        comidax = 1 + (int)(Math.random() * 750);
        comiday = 1 + (int)(Math.random() * 550);
        
        comida.geraComida(comidax,comiday);

       
    }
    
    public void posiçoes(){
        pcomidax = comida.getP();
        qcomiday = comida.getQ();
        xsnake = snake.getX();
        ysnake = snake.getY();
    }
    
    public void actionPerformed(ActionEvent e) {
        if(mover == "left"){
            snake.move(-1,0);
        }else if(mover == "right"){
            snake.move(1,0);
        }else if(mover == "up"){
            snake.move(0,-1);
        }else if(mover == "down"){
            snake.move(0,1);
        }
        
        posiçoes();
        if((xsnake ==(pcomidax)) && (qcomiday == ysnake)){
            localComida();
            score.addScore(+1);
            posiçoes();
        }
        
        repaint();  
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