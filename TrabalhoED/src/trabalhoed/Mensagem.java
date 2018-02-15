/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed;

import java.util.Date;
import java.util.Stack;

/**
 *
 * @author dmartins
 */
public class Mensagem extends Comentarios{
   
   private boolean tipo;
   private long id_mensagem;
    

    public Mensagem(boolean tipo, long id_utilizador, String mensagem, Date data, Stack<Comentarios> lista) {
        super(id_utilizador, mensagem, data, lista);
        this.tipo = tipo;
    }

    /**
     *
     */
    public Mensagem() {
      
    }

    public Mensagem(long id){
        
        setId_mensagem(id);
        
    }
    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public long getId_mensagem() {
        return id_mensagem;
    }

    public void setId_mensagem(long id_mensagem) {
        this.id_mensagem = id_mensagem;
    }

      public String toString(){
          System.out.println("");
        String mensagem = "Mensagem: " + super.getId_utilizador();
        
        
        return mensagem;
    }
}

  
