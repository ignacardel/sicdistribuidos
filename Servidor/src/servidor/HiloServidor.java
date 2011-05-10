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
import java.net.UnknownHostException;

/**
 *
 * @author cotu
 */
public class HiloServidor implements Runnable {

        private DataInputStream entrada;
        private DataOutputStream salida;
        private Socket ser;
        private Ventana ventana;

        private String ip0=null;
        private String ip1=null;
        private String ip2=null;
        private HiloChequea chequea1 = null;
        private HiloChequea chequea2 = null;

        public HiloServidor(Ventana ventana) throws UnknownHostException {
            this.ventana = ventana;
            ip0 = InetAddress.getLocalHost().getHostAddress(); // asigan a ip0 la variable del localhost
        }

    public void run() {
        // espera conexiones de otros servidores y se intercambian ips
        // se busca en alguna de las otras variables de ip cual esta vacia y ahi se pone el nuevo ip
        // se crea un objeto chequea para estar enviando mensaje por si los servidores se caen
        //
        try {
            ServerSocket socketservidor = new ServerSocket(6001);
            while (true)
            {
                System.out.println("Esperando Servidor..");
                Socket nuevosocketservidor = socketservidor.accept();
                entrada = new DataInputStream(nuevosocketservidor.getInputStream());
                salida = new DataOutputStream(nuevosocketservidor.getOutputStream());
                System.out.println("Se conecto el servidor :" + nuevosocketservidor.getInetAddress());

                salida.writeUTF(InetAddress.getLocalHost().getHostAddress());
                                          //este es el ip que mando al otro servidor para que sepa a quien se conecto
                String nuevoip = nuevosocketservidor.getInetAddress().getHostAddress();
                salida.writeUTF(nuevoip);// nuevo ip es el ip de la maquina q se conecto,
                                         // este es el que se guarda en ip1 o ip2

                this.guardarip(nuevoip, nuevosocketservidor, 0); // metodo para guardar la ip nueva
                                                                 // tambien crea un objeto chequea
            }

        } catch (Exception e) {e.printStackTrace();}
    }



        public void conocer(String ip)
        {
            try{
                // metodo que se conecta con alguno de los otros servidores para conocerlos
                // recibe los ips y los guarda en variables el ip 0 es el localhost
                // luego inicia el objeto chequea peor como clinte para saber quien manda mensaje primero.
                ser = new Socket(ip, 6001);
                entrada = new DataInputStream(ser.getInputStream());
                salida = new DataOutputStream(ser.getOutputStream());

                String nuevoip = ser.getInetAddress().getHostAddress(); // ip a quien se conecto este va en ip1 o 1p2
                System.out.println(" ");
                System.out.println("Servidores");
                System.out.println("IP A DONDE ME CONECTE: " + entrada.readUTF());
                System.out.println("MI IP                : " + entrada.readUTF());

                this.guardarip(nuevoip, ser, 1);// metodo para guardar la ip nueva
                                                // tambien crea un objeto chequea
                ser.close();
            } catch (UnknownHostException ex) {
                System.out.println("Error de conexion");
            } catch (IOException ex) {
                System.out.println("Error de conexion");
            }


         }


        private void guardarip(String nuevoip, Socket s, int Opcion)
        {// cheque que variable esta libre y ahi guarda el ip y crea el objeto para estar revisando la conex


                 if (ip1 != null)
                 {
                    ip1=nuevoip;
                    System.out.println("Servidor 0: "+ ip0);
                    System.out.println("SERVIDOR 1: "+ ip1);
                    System.out.println("Servidor 2: "+ ip2);
                    chequea1 = null;
                    chequea1 = new HiloChequea(s, this);
                 }else
                 if(ip2 != null)
                 {
                    ip2=nuevoip;
                    System.out.println("Servidor 0: "+ ip0);
                    System.out.println("Servidor 1: "+ ip1);
                    System.out.println("SERVIDOR 2: "+ ip2);
                    chequea2 = null;
                    chequea2 = new HiloChequea(s, this);
                 }
                 else
                 {
                    System.out.println("Ya estan conectados los tres servidores.");
                 }

        }

    public String getIp0() {
        return ip0;
    }

    public void setIp0(String ip0) {
        this.ip0 = ip0;
    }

    public String getIp1() {
        return ip1;
    }

    public void setIp1(String ip1) {
        this.ip1 = ip1;
    }

    public String getIp2() {
        return ip2;
    }

    public void setIp2(String ip2) {
        this.ip2 = ip2;
    }



}
