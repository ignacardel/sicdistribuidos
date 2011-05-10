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
    private int opcion;
    private String recibe;
    private int numerochequeador;

    public HiloChequea(DataInputStream entrada, DataOutputStream salida, HiloServidor hiloservidor, int opcion, int numerochequeador){
      
            this.entrada = entrada;
            this.salida = salida;
            this.opcion = opcion;
            this.numerochequeador = numerochequeador;
    }

    public void run() {


        while (true)
        {
            if ( opcion == 0)
            {
                try {
                    try {Thread.sleep(2000); } catch (InterruptedException ex) { System.out.println("pausa");}
                    salida.writeUTF("act");
                    System.out.println("sigue vivo clien");
                    recibe = entrada.readUTF();
                } catch (IOException ex) {
                    hiloservidor.quitarservidor(this.numerochequeador);
                    System.out.println("SE CAYO");}
            }
            else
            {
                try {
                    recibe = entrada.readUTF();
                    System.out.println("sigue activo");
                    salida.writeUTF("act");
                } catch (IOException ex) {
                    hiloservidor.quitarservidor(this.numerochequeador);
                    System.out.println("SE CAYO");}
            }
        }

    }

}
