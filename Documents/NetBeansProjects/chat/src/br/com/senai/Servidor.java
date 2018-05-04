/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Mayara
 */
public class Servidor extends Thread {

    static JTextArea areaTexto;
    static JTextField txtIp;
    static JTextField txtPorta;

    public Servidor(JTextArea areaTexto, JTextField txtIp, JTextField txtPorta) {
        this.areaTexto = areaTexto;
        this.txtIp = txtIp;
        this.txtPorta = txtPorta;
    }

    public void run() {
        while (true) {
            ServerSocket servidor;	// esperar a conexao e estabelece conexao cliente-servidor
            try {
                servidor = new ServerSocket(1246, 1);	// porta que sera utilizada na conexao
                Socket cliente = servidor.accept();	// Socket: ponto de comunicacao - accept: escuta uma conexao e aceita se encontrar

                DataInputStream entrada = new DataInputStream(cliente.getInputStream());	// leitura dos dados

                String nome = entrada.readUTF();
                int porta = entrada.readInt();
                String mensagemEntrada = entrada.readUTF();
                String ip = cliente.getInetAddress().getHostAddress();	// leitura do ip e host

                areaTexto.setText(areaTexto.getText() + "\n" + nome + " (" + cliente.getInetAddress() + ") - " + mensagemEntrada);

                txtIp.setText(ip);
                txtPorta.setText(Integer.toString(porta));

                entrada.close();
                cliente.close();
                servidor.close();

            } catch (IOException e) {
                System.out.println("Erro de rede!!!");
            }
        }
    }
}
