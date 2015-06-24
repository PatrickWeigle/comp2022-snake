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
    private Snake campo;
    private static int comidax;
    private static int comiday;
    private static String mover = "right";
    private static boolean valida = false;
    private String orien;

    private int pegaX;
    private int pegaY;

    private static int veloc = 150;

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

        campo = new Snake();
        campo.campo();
        add(campo);

        score = new Score();
        add(score);

        //         corpo = new Snake(1);
        //         //corpo.corpo();
        //         add(corpo);

        timer = new Timer(veloc, this);
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
            g2d.drawImage(campo.getImageCampo(),0,0,this);
            g2d.drawImage(comida.getImageComida(),comida.getP(),comida.getQ(),this);
            g2d.setColor(Color.YELLOW);
            g2d.drawString("Score: " + score.getScore(), 600, 50);
            Snake aux = lista.getCabeca();
            if(aux.getProximo() == null){
                g2d.drawImage(aux.getImage(),aux.getX(), aux.getY(),this);
            }else{
                while(aux.getProximo()!= null){
                    g2d.drawImage(aux.getImage(),aux.getX(), aux.getY(),this);
                    add(aux);
                    aux = aux.getProximo();
                    g2d.drawImage(aux.getImage(),aux.getX(), aux.getY(),this);
                    add(aux);

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
        if(isPlaying == true){
            if(mover == "left"){
                lista.moveCabeca(-28,0,mover);
                lista.giraHead(mover);
            }else if(mover == "right"){
                lista.moveCabeca(28,0,mover);
                lista.giraHead(mover);
            }else if(mover == "up"){
                lista.moveCabeca(0,-28,mover);
                lista.giraHead(mover);
            }else if(mover == "down"){
                lista.moveCabeca(0,28,mover);
                lista.giraHead(mover);
            }
        }else if(isPlaying == false){

        }

        posiçoes();
        if((xsnake >=(pcomidax-25))&&(xsnake <=(pcomidax + 25)) && (ysnake >=(qcomiday-25))&&(ysnake <=(qcomiday + 25))) {
            localComida();
            score.addScore(+1);
            int i = 0;
            veloc = veloc - 10;
            //while(i<30){
            inserirFinal();
            //    i++;
            //}

            //             snake = new Snake();
            //             lista.inserirFinal(snake);
            //             snake.corpo();
            //             add(corpo);
            posiçoes();

        }
        repaint();  
    }

    public void inserirFinal(){
        if (mover == "left"){
            lista.inserirFinal(30,0);
        }else if (mover == "right"){
            lista.inserirFinal(-30,0);
        }else if (mover == "up"){
            lista.inserirFinal(0,30);
        }else if (mover == "down"){
            lista.inserirFinal(0,-30);
        }

    }
    //     
    //     public void newCorpo(){
    //         corpo.novaSnake();
    //         lista.inserirFinal();
    //         //corpo.corpo();
    //         add(corpo);
    //         valida = true;
    //     }

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
                while(isPlaying == false){
                    lista = new Lista();
                    score = new Score();
                    comida = new Snake();
                    comida.comida();
                    add(comida);
                    mover = "right";
                    isPlaying = true;

                }
                break;
                //                 int i = 0;
                //                 while(i<30){
                //                     inserirFinal();
                //                     i++;
                //                 }
                //                 break;

                case KeyEvent.VK_LEFT:
                if (mover == "right")
                    mover = "right";
                else{
                    orien = mover;
                    mover = "left";
                    Snake aux = lista.getCabeca();
                    pegaX = aux.getX();
                    pegaY = aux.getY();
                    lista.giraHead(mover);
                    lista.setPegaXY(pegaX,pegaY,orien);
                }
                break;

                case KeyEvent.VK_RIGHT:
                if (mover == "left")
                    mover = "left";
                else{
                    orien = mover;
                    mover = "right";
                    Snake aux = lista.getCabeca();
                    pegaX = aux.getX();
                    pegaY = aux.getY();
                    lista.giraHead(mover);
                    lista.setPegaXY(pegaX,pegaY,orien);
                }
                break;

                case KeyEvent.VK_UP:
                if (mover == "down")
                    mover = "down";
                else{
                    orien = mover;
                    mover = "up";
                    Snake aux = lista.getCabeca();
                    pegaX = aux.getX();
                    pegaY = aux.getY();
                    lista.giraHead(mover);
                    lista.setPegaXY(pegaX,pegaY,orien);
                }
                break;

                case KeyEvent.VK_DOWN:
                if (mover == "up")
                    mover = "up";
                else{
                    orien = mover;
                    mover = "down";
                    Snake aux = lista.getCabeca();
                    pegaX = aux.getX();
                    pegaY = aux.getY();
                    lista.giraHead(mover);
                    lista.setPegaXY(pegaX,pegaY,orien);
                }
                break;
            }

        }

    }
}