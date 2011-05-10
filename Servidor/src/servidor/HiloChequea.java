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
    private boolean continuar = true;
    private String ip;

    public HiloChequea(DataInputStream entrada, DataOutputStream salida, HiloServidor hiloservidor, int opcion, int numerochequeador){
      
            this.entrada = entrada;
            this.salida = salida;
            this.opcion = opcion;
            this.numerochequeador = numerochequeador;
            this.hiloservidor = hiloservidor;
            
            if (this.numerochequeador == 1)
                this.ip = this.hiloservidor.getIp1();
            else
                this.ip = this.hiloservidor.getIp2();
    }

    public void run() {


        while (continuar == true)
        {
            if ( opcion == 0)
            {
                try {
                    try {Thread.sleep(1000); } catch (InterruptedException ex) { System.out.println("fundio en la pausa");}
                    salida.writeUTF("act");
                    System.out.println("Activo: " + this.ip);
                    recibe = entrada.readUTF();
                } catch (IOException ex) {
                    System.out.println("Error en conexion con: "+this.ip);
                    hiloservidor.quitarservidor(this.numerochequeador);
                    continuar = false;

                }
            }
            else
            {
                try {
                    recibe = entrada.readUTF();
                    System.out.println("Activo: " + this.ip);
                    salida.writeUTF("act");
                } catch (IOException ex) {
                    System.out.println("Error en conexion con: "+this.ip);
                    hiloservidor.quitarservidor(this.numerochequeador);
                    continuar = false;
                   
                }
            }
        }

    }

}
