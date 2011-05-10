/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cotu
 */
public class HiloChequea implements Runnable {
 
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Socket s;
    private HiloServidor hiloservidor;

    public HiloChequea(Socket nuevosocketcliente, HiloServidor hiloservidor){
      try {
            s = nuevosocketcliente;
            entrada = new DataInputStream(s.getInputStream());
            salida = new DataOutputStream(s.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(HiloEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run() {


    }

}
