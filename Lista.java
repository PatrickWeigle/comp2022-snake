
/**
 * Escreva a descrição da classe Lista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Lista
{

    private Snake inicio;
    private int moveX = 0;
    private int moveY = 0;
    private int guardaX;
    private int guardaY; 
    private int pegaX;
    private int pegaY;
    private boolean mudadir = false;
    private boolean dir = false;
    private String orien;
    public Lista(){
        this.inicio = new Snake();
    }
    
    public void giraHead(String mover){
        inicio.setSnake(mover);
    }
    
    public void subsHead(Snake aux){
        aux.setProximo(inicio.getProximo());
        aux.setY(inicio.getY());
        aux.setX(inicio.getX());
        inicio = aux;
    }

    public boolean isEmpty(){
        if(inicio == null){
            return true;
        }else{  
            return false;
        }
    }

    public void setPegaXY(int _x, int _y,String _orien){
        this.pegaX = _x;
        this.pegaY = _y;
        this.orien = _orien;
        if(inicio.getProximo() != null)
            this.dir = true;
    }

    public void inserirFinal(int _x,int _y){
        if(inicio.getProximo() == null){
            int i = 0;
            Snake corpo = inicio.novaSnake();
            corpo.setX(-corpo.getX());
            corpo.setX(inicio.getX()+_x);
            corpo.setY(-corpo.getY());
            corpo.setY(inicio.getY()+_y);
            inicio.setProximo(corpo);
        }else{
            Snake aux = inicio;
            Snake corpo = aux.novaSnake();
            while(aux.getProximo() != null){
                aux = aux.getProximo();
            }
            corpo.setX(-corpo.getX());
            corpo.setX(inicio.getX()+_x);
            corpo.setY(-corpo.getY());
            corpo.setY(inicio.getY()+_y);
            aux.setProximo(corpo);
        }
    }

    public Snake ultimaSnake(){
        Snake aux = inicio;
        while(aux.getProximo() != null){
            aux = aux.getProximo();
        }
        return aux;
    }

    public Snake validaCorpo(Snake aux){
        Snake aux2 = inicio;
        if(aux == aux2){
            return null;
        }else{
            return aux;
        }
    }

    public Snake getCabeca(){
        return inicio;
    }

    public void moveCabeca(int _x, int _y,String direcao){
        moveX = inicio.getX();
        moveY = inicio.getY();
        if(inicio.getX()>=760 || inicio.getY()> 580 || inicio.getX()<=0 || inicio.getY()<=0){
            Board fim = new Board();
            fim.setIsPlaying(false);

        }else{
            inicio.setX(_x);
            inicio.setY(_y);
            if(inicio.getProximo() != null){
                moveCorp();
                //moveCorpo(direcao);
            }
        }
        System.out.println("X:"+inicio.getX()+" Y:"+inicio.getY());
    }

    public void moveCorp(){
        Snake aux = inicio;
        while(aux.getProximo()!= null){
            aux = aux.getProximo();
            guardaX = aux.getX();
            guardaY = aux.getY();
            aux.setX(-aux.getX());
            aux.setX(moveX);
            aux.setY(-aux.getY());
            aux.setY(moveY);
            moveX = guardaX;
            moveY = guardaY;
        }
        Snake aux2 = inicio;
        while(aux2.getProximo()!=null){
            aux2 = aux2.getProximo();
            if((inicio.getX() >=(aux2.getX()))&&(inicio.getX() <=(aux2.getX())) && (inicio.getY() >= aux2.getY())&&(inicio.getY() <= (aux2.getY()))){
                Board fim = new Board();
                fim.setIsPlaying(false);
            }
        }

    }

    public void moveCorpo(String mover){
        Snake aux = inicio;
        Snake aux2 = aux.getProximo();
        while(aux.getProximo()!= null){
            if(mover == "left"){
                aux = aux.getProximo();
                guardaX = aux.getX();
                guardaY = aux.getY();

                aux.setX(-aux.getX());
                aux.setX(moveX+15);
                aux.setY(-aux.getY());
                aux.setY(moveY);
                moveX = guardaX;
                moveY = guardaY;

            }else if(mover == "right"){
                aux = aux.getProximo();
                guardaX = aux.getX();
                guardaY = aux.getY();
                aux.setX(-aux.getX());
                aux.setX(moveX-15);
                aux.setY(-aux.getY());
                aux.setY(moveY);
                moveX = guardaX;
                moveY = guardaY;

            }else if(mover == "up"){
                aux = aux.getProximo();
                guardaX = aux.getX();
                guardaY = aux.getY();
                aux.setX(-aux.getX());
                aux.setX(moveX);
                aux.setY(-aux.getY());
                aux.setY(moveY+15);
                moveX = guardaX;
                moveY = guardaY;

            }else if(mover == "down"){
                aux = aux.getProximo();
                guardaX = aux.getX();
                guardaY = aux.getY();
                aux.setX(-aux.getX());
                aux.setX(moveX);
                aux.setY(-aux.getY());
                aux.setY(moveY-15);
                moveX = guardaX;
                moveY = guardaY;

            }

        }  
    }
}
