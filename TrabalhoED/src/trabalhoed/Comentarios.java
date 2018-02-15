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
public class Comentarios {
    private long id_utilizador;
    private String mensagem;

    private Date data;
    private Stack<Comentarios> lista;

    public Comentarios(long id_utilizador, String mensagem, Date data, Stack<Comentarios> lista) {
        this.id_utilizador = id_utilizador;
        this.mensagem = mensagem;
        this.data = data;
        this.lista = lista;
    }

    public Comentarios() {
    }
    
    public Comentarios(long id){
        
    }
    
    
    public long getId_utilizador() {
        return id_utilizador;
    }

    public void setId_utilizador(long id_utilizador) {
        this.id_utilizador = id_utilizador;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Stack<Comentarios> getLista() {
        return lista;
    }

    public void setLista(Stack<Comentarios> lista) {
        this.lista = lista;
    }


    
    
    
    
}
