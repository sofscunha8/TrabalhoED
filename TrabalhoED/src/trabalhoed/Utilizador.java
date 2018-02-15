/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dmartins
 */
public class Utilizador {
    private String nome;
    private String email;
    List<Utilizador> amigos;
    List<Utilizador> pedidosAmizade;

    public List<Utilizador> getPedidosAmizade() {
        return pedidosAmizade;
    }

    public void setPedidosAmizade(List<Utilizador> pedidosAmizade) {
        this.pedidosAmizade = pedidosAmizade;
    }
    private double saldo;
    private long idUtilizador;

    
    public long getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(long idUtilizador) {
        this.idUtilizador = idUtilizador;
    }
    
    
    public Utilizador(String nome, String email){
        this.nome = nome;
        this.email = email;
        this.saldo = 0;
        amigos = new LinkedList<Utilizador>();
        pedidosAmizade = new LinkedList<Utilizador>();
    }
    
    public Utilizador(String toSplit){
        String[] aux = toSplit.split(":");
        this.idUtilizador = Long.parseLong( aux[0]);
        this.nome = aux[1];
        this.email = aux[2];
        this.saldo = Double.parseDouble(aux[3]);
        amigos = new LinkedList<Utilizador>();
        pedidosAmizade = new LinkedList<Utilizador>();

    }
    
    
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Utilizador> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Utilizador> amigos) {
        this.amigos = amigos;
    }
    
    public String toString(){
        String user = "utilizador-"+idUtilizador+":"+nome+":"+email+":"+saldo+";";
        
        if(amigos != null && amigos.size() > 0){
            user += "amigos-";
        }
        //vai percorrer todo o objeto
        for(Utilizador u : amigos){
            user+=u.getIdUtilizador()+":";
        }
        if(pedidosAmizade != null && pedidosAmizade.size() > 0){
            user += "pedidos-";
        }
        //vai percorrer todo o objeto
        for(Utilizador u : pedidosAmizade){
            user+=u.getIdUtilizador()+":";
        }
        return user;
    }
    
    public void listaAmigos(){
        System.out.println("Os meus amigos: ");
        for(Utilizador u: amigos){
            System.out.println(u.getNome());
        }
    }
    
    public void aceitarAmigos(Scanner input){
        input = new Scanner(System.in);
        for(Utilizador u : pedidosAmizade){
            System.out.println("Aceitar "+ u.getNome() + " como amigo? \nS - Sim \nN - Não");
            String s = input.nextLine();
            
            if(s.equalsIgnoreCase("S")){
                amigos.add(u);
                u.getAmigos().add(this);
            }
        }
        pedidosAmizade = new LinkedList<Utilizador>();
    }
    
}
