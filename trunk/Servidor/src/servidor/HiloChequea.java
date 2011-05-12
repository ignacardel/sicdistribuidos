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
 *clase en la cual se crea un hilo para tener constante verificacion de los
 * estados de los otros servidores
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
    private Ips ips = null;
    private boolean ipnuevo;

    /**
     *
     * @param entrada
     * @param salida
     * @param hiloservidor
     * @param opcion
     * @param numerochequeador
     * @param ips
     */
    public HiloChequea(DataInputStream entrada, DataOutputStream salida, HiloServidor hiloservidor, int opcion, int numerochequeador, Ips ips) {

        this.ips = ips;
        this.entrada = entrada;
        this.salida = salida;
        this.opcion = opcion;
        this.numerochequeador = numerochequeador;
        this.hiloservidor = hiloservidor;


        if (this.numerochequeador == 1) {
            this.ip = ips.getIp1();

        } else {
            this.ip = ips.getIp2();

        }
    }

    public void setIpnuevo(boolean ipnuevo) {
        this.ipnuevo = ipnuevo;
    }

    /**
     * metodo que corre un hilo el cual esta constantemente verificando si los
     * otros sevidores siguen conectados o activos. 
     */
    public void run() {


        while (continuar == true) {
            if (opcion == 0) {
                try {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("fundio en la pausa");
                    }

                    salida.writeUTF(ips.getips());
                    System.out.println("Activo: " + this.ip +"   mi tabla "+ ips.getips());
                    recibe = entrada.readUTF();
                    System.out.println("recibi de "+ recibe + "   tengo" + ips.getips());

                    detectarcambios(recibe);


                } catch (IOException ex) {
                    System.out.println("Error en conexion con: " + this.ip);
                    hiloservidor.quitarservidor(this.numerochequeador);
                    continuar = false;

                }
            } else {
                try {
                    recibe = entrada.readUTF();


                    System.out.println("recibi " + recibe + "   tengo" + ips.getips());


                    detectarcambios(recibe);



                    System.out.println("Activo: " + this.ip +"   mi tabla "+ ips.getips());
                    salida.writeUTF(ips.getips());
                } catch (IOException ex) {
                    System.out.println("Error en conexion con: " + this.ip);
                    hiloservidor.quitarservidor(this.numerochequeador);
                    continuar = false;

                }
            }
        }

    }

/**
 * procedimiento que detecta cambio en la tabla de ip para informar a
 * los demas servidores o intentar conocerlos.
 * @param listarecibida
 */
    public void detectarcambios(String listarecibida) {
        String[] lista = listarecibida.split("/");
        int cont=0;

        if (lista[0].matches(ips.getIp0())==false) cont++;
        if (lista[0].matches(ips.getIp1())==false) cont++;
        if (lista[0].matches(ips.getIp2())==false) cont++;

        if (cont==3) hiloservidor.conocer(lista[0]);


        cont=0;

        if (lista[1].matches(ips.getIp0())==false) cont++;
        if (lista[1].matches(ips.getIp1())==false) cont++;
        if (lista[1].matches(ips.getIp2())==false) cont++;

        if (cont==3) hiloservidor.conocer(lista[1]);

        cont=0;

        if (lista[2].matches(ips.getIp0())==false) cont++;
        if (lista[2].matches(ips.getIp1())==false) cont++;
        if (lista[2].matches(ips.getIp2())==false) cont++;

        if (cont==3) hiloservidor.conocer(lista[2]);
        

    }
}
