
/**
 * Escreva a descrição da classe Lista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Lista
{
    
    private Snake inicio;
    
    
    public Lista(){
        this.inicio = new Snake();
    }
    
    
    public boolean isEmpty(){
        if(inicio == null){
            return true;
        }else{  
            return false;
        }
    }
    
    public void inserirFinal(){
        if(inicio.getProximo() == null){
            
            Snake corpo = inicio.novaSnake();
            corpo.setX(inicio.getX()+50);
            corpo.setY(inicio.getY());
            inicio.setProximo(corpo);
        }else{
            Snake aux = inicio;
            Snake novo = aux.novaSnake();
            while(aux.getProximo() != null){
                aux = aux.getProximo();
            }
            novo.setX(aux.getX()+50);
            novo.setY(aux.getY());
            aux.setProximo(novo);
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
    
    public void moveCabeca(int _x, int _y){
        if(inicio.getX()>=750 || inicio.getY()> 550 || inicio.getX()==0 || inicio.getY()==0){
            Board fim = new Board();
            fim.setIsPlaying(false);
           
        }else{
            inicio.setX(_x);
            inicio.setY(_y);
        }
        System.out.println("X:"+inicio.getX()+" Y:"+inicio.getY());
    }
}
