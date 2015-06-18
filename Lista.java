
/**
 * Escreva a descrição da classe Lista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Lista
{
    
    private Snake inicio;
    Snake corpo = new Snake();
    
        public void inserirInicio(Snake _node){
        if(isEmpty()){
            inicio = _node;
        }else{  
            _node.setProximo(inicio);
            inicio = _node;
        }
    }
    public boolean isEmpty(){
        if(inicio == null){
            return true;
        }else{  
            return false;
        }
    }
    
    public void inserirFinal(Snake _node){
        if(isEmpty()){
            inicio = _node;
        }else{
            Snake aux = inicio;
            while(aux.getProximo() != null){
                aux = aux.getProximo();
            }
            aux.setProximo(_node);
        }
    }
}
