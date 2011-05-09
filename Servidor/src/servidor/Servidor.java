/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ignaciocardenas
 */
public class Servidor implements Runnable {

    public Servidor() {
    }

    public void run() {
        try {
            ServerSocket socketservidor = new ServerSocket(6000);
            while (true) {
                System.out.println("Esperando usuario..");
                Socket nuevosocketcliente = socketservidor.accept();
                System.out.println("Se conecto " + nuevosocketcliente.getInetAddress());
                HiloEnvio nuevocliente = new HiloEnvio(nuevosocketcliente);
                Thread nuevohilocliente = new Thread(nuevocliente);
                nuevohilocliente.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
