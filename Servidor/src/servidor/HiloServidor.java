/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author cotu
 */
public class HiloServidor implements Runnable {

        private DataInputStream entrada;
        private DataOutputStream salida;

        public HiloServidor() {
    }

    public void run() {
        try {
            ServerSocket socketservidor = new ServerSocket(6001);
            while (true) {
                System.out.println("Esperando Servidor..");
                Socket nuevosocketservidor = socketservidor.accept();
                entrada = new DataInputStream(nuevosocketservidor.getInputStream());
                salida = new DataOutputStream(nuevosocketservidor.getOutputStream());
                System.out.println("Se conecto el servidor " + nuevosocketservidor.getInetAddress());

                salida.writeUTF("Ip del viejo: "+socketservidor.getInetAddress());
                salida.writeUTF("Ip del nuevo: "+nuevosocketservidor.getInetAddress());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
