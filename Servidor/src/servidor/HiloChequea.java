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
    private Ips ips = null;
    private boolean ipnuevo;

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
        

//        if (ips.getIp1().matches(lista[0]) == true || ips.getIp1().matches(lista[1]) == true || ips.getIp1().matches(lista[2]) == true) {
//            System.out.println("ya conocia la ip1 que me llego desde ");
//
//        } else {
//
//            System.out.println("voy a conocer una ip1");
//
//            if (ips.getIp1().matches(lista[0]) == false ) {
//                hiloservidor.conocer(lista[0]);
//            }
//            if (ips.getIp1().matches(lista[1]) == false && ips.getIp0().matches(lista[1]) == false) {
//                hiloservidor.conocer(lista[1]);
//            }
//            if (ips.getIp1().matches(lista[2]) == false && ips.getIp0().matches(lista[2]) == false) {
//                hiloservidor.conocer(lista[2]);
//            }
//        }
//
//
//        if (ips.getIp2().matches(lista[0]) == true || ips.getIp2().matches(lista[1]) == true || ips.getIp2().matches(lista[2]) == true) {
//            System.out.println("ya conocia la ip2 que me llego");
//
//        } else {
//            System.out.println("voy a conocer una ip2");
//            if (ips.getIp2().matches(lista[0]) == false) {
//                hiloservidor.conocer(lista[0]);
//            }
//            if (ips.getIp2().matches(lista[1]) == false && ips.getIp0().matches(lista[1]) == false) {
//                hiloservidor.conocer(lista[1]);
//            }
//            if (ips.getIp2().matches(lista[2]) == false && ips.getIp0().matches(lista[2]) == false) {
//                hiloservidor.conocer(lista[2]);
//            }
//        }

    }
}
