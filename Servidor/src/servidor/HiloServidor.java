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
    private String ip0 = null;
    private String ip1 = null;
    private String ip2 = null;
    private HiloChequea chequea1 = null;
    private HiloChequea chequea2 = null;
    private Ips ips = null;
    private boolean conocer = true;

    public HiloServidor(Ventana ventana, Ips ips ) throws UnknownHostException {
        this.ventana = ventana;
        this.ips = ips;
        ips.setIp0(InetAddress.getLocalHost().getHostAddress()); // asigan a ip0 la variable del localhost
        //XMLServidor xml = new XMLServidor();
        //xml.escribirIPs(ip0, ip1, ip2);
        //xml.cargaIPs();
        
    }

    public void setConocer(boolean conocer) {
        this.conocer = conocer;
    }

    public void run() {
        // espera conexiones de otros servidores y se intercambian ips
        // se busca en alguna de las otras variables de ip cual esta vacia y ahi se pone el nuevo ip
        // se crea un objeto chequea para estar enviando mensaje por si los servidores se caen
        //
        try {
            ServerSocket socketservidor = new ServerSocket(6001);
            while (true) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void conocer(String ip) {
        try {
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
            System.out.println("");

            this.guardarip(nuevoip, ser, 1);
            // metodo para guardar la ip nueva
            // tambien crea un objeto chequea
            //ser.close();
        } catch (UnknownHostException ex) {
            System.out.println("Error de conexion");
        } catch (IOException ex) {
            System.out.println("Error de conexion");
        }


    }

    private void guardarip(String nuevoip, Socket s, int opcion) {// cheque que variable esta libre y ahi guarda el ip y crea el objeto para estar revisando la conex


        if (ips.getIp1().matches("null") == true) {
            ips.setIp1( nuevoip );
            System.out.println("Servidor 0: " + ips.getIp0());
            System.out.println("SERVIDOR 1: " + ips.getIp1());
            System.out.println("Servidor 2: " + ips.getIp2());
            chequea1 = null;
            chequea1 = new HiloChequea(entrada, salida, this, opcion, 1, ips);
            Thread chequeador1 = new Thread(chequea1);
            chequeador1.start();
            // empezar el hilo

        } else {
            if (ips.getIp2().matches("null") == true) {
                ips.setIp2(nuevoip);
                System.out.println("Servidor 0: " + ips.getIp0());
                System.out.println("Servidor 1: " + ips.getIp1());
                System.out.println("SERVIDOR 2: " + ips.getIp2());
                chequea2 = null;
                chequea2 = new HiloChequea(entrada, salida, this, opcion, 2,ips);
                Thread chequeador2 = new Thread(chequea2);
                chequeador2.start();

                //mandar el ip al servidor que ta conectado con chequea 1
                // empezar el hilo

            } else {
                System.out.println("Ya estan conectados los tres servidores.");
            }
        }
        //XMLServidor xml = new XMLServidor();
        //xml.escribirIPs(ip0, ip1, ip2);

    }

    public void quitarservidor(int num) {
        if (num == 1) {
            System.out.println("Se ha caido al servidor # " + num + " con ip: " + ips.getIp1());
            ips.setIp1("null");
            this.chequea1 = null;
        }
        if (num == 2) {
            System.out.println("Se ha caido al servidor # " + num + " con ip: " + ips.getIp2());
            ips.setIp2("null");
            this.chequea2 = null;
        }
        
        //XMLServidor xml = new XMLServidor();
        //xml.escribirIPs(ip0, ip1, ip2);
    }

}
