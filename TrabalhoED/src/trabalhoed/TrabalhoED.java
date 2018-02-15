/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmartins
 */
public class TrabalhoED {

    public static LinkedList<Utilizador> listaUtilizadores;
    public static Stack<Mensagem> listaMensagens;
    public static Long maxId = 0L;
    public static Utilizador utilizadorLogado;

    /**
     * @param args the command line arguments
     */

    public static void gravaRedeSocial() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("Utilizadores.txt");
        System.out.println("Save: " + listaUtilizadores.size());
        for (Utilizador util : listaUtilizadores) {
            out.println(util.toString());
            System.out.println(util.toString());
        }

        out.close();
    }

        public static void gravaMensagens() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("Mensagens.txt");
        Mensagem mens = new Mensagem();
        System.out.println("aqui");
       // System.out.println("Mensagem: " + listaMensagens.size());
      // 
       // for (Mensagem mens : listaMensagens) {
           out.println(mens.toString());
           System.out.println(mens.toString());
       // }

        out.close();
    }
        
        
        
        
        sofia
        
    public static void carregarRedeSocial() {
        BufferedReader builder = null;
        try {
            builder = new BufferedReader(new FileReader("utilizadores.txt"));
            String line = builder.readLine();
            listaUtilizadores = new LinkedList<Utilizador>();
            Map<Utilizador, String> amigosAux = new HashMap<Utilizador, String>();
            Map<Utilizador, String> pedidosAux = new HashMap<Utilizador, String>();

            while (line != null) {

                String[] aux = line.split(";");
                Utilizador user = new Utilizador(aux[0].split("-")[1]);
                listaUtilizadores.add(user);
                if (aux.length == 3) {
                    amigosAux.put(user, aux[1].split("-")[1]);
                    pedidosAux.put(user, aux[2].split("-")[1]);
                } else if (aux.length == 2) {
                    if (aux[1].startsWith("amigos")) {
                        amigosAux.put(user, aux[1].split("-")[1]);
                        pedidosAux.put(user, "");
                    } else {
                        pedidosAux.put(user, aux[1].split("-")[1]);
                        amigosAux.put(user, "");
                    }
                }

                if (maxId == null || maxId.longValue() < user.getIdUtilizador()) {
                    maxId = user.getIdUtilizador();
                }

                line = builder.readLine();

            }
            //iterar por todos os pontos do mapa e carregar os amigos
            for (Map.Entry<Utilizador, String> entry : amigosAux.entrySet()) {
                if (entry.getValue() == null || entry.getValue().equals("")) //não faz nada abaixo do continue
                {
                    continue;
                }
                String[] usersId = entry.getValue().split(":");

                for (String s : usersId) {
                    if (s.equals("amigos")) {
                        continue;
                    }

                    Long id = Long.parseLong(s);
                    for (Utilizador u : listaUtilizadores) {
                        if (u.getIdUtilizador() == id.longValue()) {
                            entry.getKey().getAmigos().add(u);
                            break;
                        }

                    }

                }
            }

            //iterar por todos os pontos do mapa e carregar os pedidos de amizade
            for (Map.Entry<Utilizador, String> entry : pedidosAux.entrySet()) {
                if (entry.getValue() == null || entry.getValue().equals("")) //não faz nada abaixo do continue
                {
                    continue;
                }
                String[] usersId = entry.getValue().split(":");

                for (String s : usersId) {
                    Long id = Long.parseLong(s);
                    for (Utilizador u : listaUtilizadores) {
                        if (u.getIdUtilizador() == id.longValue()) {
                            entry.getKey().getPedidosAmizade().add(u);
                            break;
                        }

                    }

                }
            }

        } catch (FileNotFoundException ex) {
            listaUtilizadores = new LinkedList<Utilizador>();
            maxId = 0L;
        } catch (IOException ex) {
            Logger.getLogger(TrabalhoED.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void carregarMensagens() throws FileNotFoundException, IOException {
        BufferedReader builder = null;
       
            builder = new BufferedReader(new FileReader("Mensagens.txt"));
            String line = builder.readLine();
            listaMensagens = new Stack<Mensagem>();
           
            while (line != null) {
                Mensagem mensagem = new Mensagem();
                listaMensagens.add(mensagem);

                if (maxId == null || maxId.longValue() < mensagem.getId_mensagem()) {
                    maxId = mensagem.getId_mensagem();
                }

                line = builder.readLine();

            }
           

            }


    

    
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            carregarRedeSocial();

            Scanner input = new Scanner(System.in);

            while (true) {
                System.out.println("Escolha Opção: \n1 - Sign In \n2 - Log In \n0 - Sair");
                while (!input.hasNextInt()) {
                    input.next();
                    System.out.print("Enter a valid integer: ");
                }
                int choice = input.nextInt();

                if (choice == 1) {
                    criarUtilizador(input);
                } else if (choice == 2) {
                    loginUtilizador(input);
                } else if (choice == 0) {
                    break;
                }
            }

            gravaRedeSocial();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void criarUtilizador(Scanner input) {

        input = new Scanner(System.in);
        System.out.println("Insira o Nome: ");
        String nome = input.nextLine();

        System.out.println("\nInsira o email: ");
        String email = input.nextLine();

        System.out.println("test:" + nome + "--" + email);

        boolean exists = false;
        for (Utilizador u : listaUtilizadores) {
            if (u.getNome().equals(nome) && u.getEmail().equals(email)) {
                exists = true;
            }
        }

        if (!exists) {
            Utilizador newUser = new Utilizador(nome, email);
            System.out.println(maxId);
            newUser.setIdUtilizador(++maxId);
            listaUtilizadores.add(newUser);

        }

    }

    private static void loginUtilizador(Scanner input) throws FileNotFoundException {
        input = new Scanner(System.in);
        System.out.println("Insira o Nome: ");
        String nome = input.nextLine();

        System.out.println("\nInsira o email: ");
        String email = input.nextLine();

        boolean exists = false;
        for (Utilizador u : listaUtilizadores) {
            if (u.getNome().equals(nome) && u.getEmail().equals(email)) {
                utilizadorLogado = u;

            }
        }

        if (utilizadorLogado != null) {
            menuPrincipal(input);
        } else {
            System.out.println("\nUtilizador inexistente!!!");
        }

    }

    private static void menuPrincipal(Scanner input) throws FileNotFoundException {

        while (true) {
            System.out.println("Escolha Opção: \n1 - Lista Amigos \n2 - Adicionar amigos de amigos");
            if (utilizadorLogado.getPedidosAmizade().size() > 0) {
                System.out.println("\n3 - Tratar pedidos");
            }

            while (!input.hasNextInt()) {
                input.next();
                System.out.print("Enter a valid integer: ");
            }
            int choice = input.nextInt();

            if (choice == 1) {
                utilizadorLogado.listaAmigos();
            } else if (choice == 2) {
                adicionarAmigo(input);
            } else if (choice == 3) {
                utilizadorLogado.aceitarAmigos(input);
            } else if (choice == 4) {
                enviarMensagem(input);
                break;
            } else if (choice == 0) {
                break;

            }

        }
    }

    private static void adicionarAmigo(Scanner input) {
        input = new Scanner(System.in);
        System.out.print("Insira o id a adicionar: ");

        while (!input.hasNextLong()) {
            input.next();
            System.out.print("Enter a valid integer: ");
        }
        long id = input.nextLong();

        for (Utilizador u : listaUtilizadores) {
            if (u.getIdUtilizador() == id) {
                if (id != utilizadorLogado.getIdUtilizador()) {
                    u.getPedidosAmizade().add(utilizadorLogado);
                    break;
                }
            }
        }
    }

    private static void enviarMensagem(Scanner input) throws FileNotFoundException {
        Mensagem novaMensagem = new Mensagem(utilizadorLogado.getIdUtilizador());
        novaMensagem.setId_utilizador(utilizadorLogado.getIdUtilizador());
        System.out.println("id: "+ novaMensagem.getId_utilizador());
        input = new Scanner(System.in);
        System.out.println("Introduza a sua mensagem");
        String mensagem = input.nextLine();
       // novaMensagem.setMensagem(mensagem);
        //novaMensagem.setTipo(true);
        gravaMensagens();

    }
}
