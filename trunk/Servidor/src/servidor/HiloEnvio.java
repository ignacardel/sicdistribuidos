/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clase que crea un hilo cuando se conecta un cliente para hacer una peticion
 * @author ignaciocardenas
 */
public class HiloEnvio implements Runnable {

    private FileInputStream archivo;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String ruta = "Archivos/";
    private String nombrearchivo;
    private String solicitud;
    private Socket s;
    private String ip0 = null;
    private String ip1 = null;
    private String ip2 = null;
    private long tamano;
    private long cantidaddescargada;
    private Ips ips = null;

    HiloEnvio(Socket nuevosocketcliente, Ips ips) {
        try {
            this.ips = ips;
            s = nuevosocketcliente;
            entrada = new DataInputStream(s.getInputStream());
            salida = new DataOutputStream(s.getOutputStream());
            this.ip0 = ips.getIp0();
            this.ip1 = ips.getIp1();
            this.ip2 = ips.getIp2();
        } catch (IOException ex) {
            Logger.getLogger(HiloEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * metodo en donde se verica que tipo de peticion solicito el cliente.
     * bien sea, descarga de archivo o peticion de lista
     */
    public void run() {
        try {
            solicitud = entrada.readUTF();
            if (solicitud.equals("GET")) {
               
                this.enviararchivo();
            }
            if (solicitud.equals("RDIR")) {
                 this.enviarips();
                this.enviararhivos();
            }


            s.close();
            System.out.println("Fin de conexion de " + s.getInetAddress());
        } catch (IOException ex) {
            Logger.getLogger(HiloEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * metodo por el cual se manda al cliente el archivo cuanto este solicite
     * la descarga.
     */
    private void enviararchivo() {
        try {
            nombrearchivo = entrada.readUTF();

            File f = new File(ruta + nombrearchivo);
            tamano = f.length();
            archivo = new FileInputStream(ruta + nombrearchivo);
            cantidaddescargada = entrada.readLong();

            if (cantidaddescargada != 0) {
                archivo.skip(cantidaddescargada);
            }

            salida.writeLong(tamano);

            System.out.println("Iniciando transmision con" + String.valueOf(archivo.getChannel().size()) + " bytes");
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = archivo.read(buffer)) > 0) {

                this.compararips();
                salida.write(buffer, 0, len);
                total = total + len;
                //System.out.println("Enviando " + String.valueOf(len) + " total " + total);               
            }
            System.out.println("Fin de envio");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * en este metodo se envia la lista de archivo del servidor al cliente como
     * un string para que el cliente sepa cuales archivos estan disponibles
     */
    private void enviararhivos() {
        try {
            XMLServidor xml = new XMLServidor();
            System.out.println(xml.cargaArchivos());
            salida.writeUTF(xml.cargaArchivos());
            
        } catch (IOException ex) {
            Logger.getLogger(HiloEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * metodo para verificar si hay algun cambio en la tabla de ips global
     * si se consigue algun ip diferente entonces es notificado al cliente
     * para que este actualize su tabla.
     */
    private void compararips()
    {
        boolean cambio = false;

        if(this.ip0.matches(ips.getIp0()) == false)
        {
            System.out.println("IP0 DIFERENTE "+ this.ip0+ " NUEVO "+ ips.getIp0());
            cambio = true;
            this.ip0 = ips.getIp0();
        }
        if(this.ip1.matches(ips.getIp1()) == false)
        {
            System.out.println("IP0 DIFERENTE "+ this.ip1+ " NUEVO "+ ips.getIp1());
            cambio = true;
            this.ip1 = ips.getIp1();
        }
        if(this.ip2.matches(ips.getIp2()) == false)
        {
            System.out.println("IP0 DIFERENTE "+ this.ip2+ " NUEVO "+ ips.getIp2());
            cambio = true;
            this.ip2 = ips.getIp2();
        }

        if(cambio == true)
        {
            try {
                Socket sockectip = new Socket(s.getInetAddress().getHostAddress(), 6003);
                DataInputStream entradaips = new DataInputStream(sockectip.getInputStream());
                DataOutputStream salidaips = new DataOutputStream(sockectip.getOutputStream());
                salidaips.writeUTF(ips.getips());
                sockectip.close();
            } catch (IOException ex) {
                System.out.println("No mando bien los ips a: "+this.s.getInetAddress().getHostAddress());
            }

        }
    }

    /**
     * metodo que se llama para enviar la tabla de ips al cliente
     */
    private void enviarips() {
        try {

            //XMLServidor xml = new XMLServidor();
            //ArrayList<String> listaips = xml.cargaIPs();
            //this.ip0 =listaips.get(0);

            if(ips.getIp0() == null)
              salida.writeUTF("null");
            else
              salida.writeUTF(ips.getIp0());
            System.out.println(ips.getIp0());

            if(ips.getIp1() == null)
              salida.writeUTF("null");
            else
              salida.writeUTF(ips.getIp1());
            System.out.println(ips.getIp1());
            
            if(ips.getIp2() == null)
              salida.writeUTF("null");
            else
              salida.writeUTF(ips.getIp2());
            System.out.println(ips.getIp2());


        } catch (IOException ex) {
            Logger.getLogger(HiloEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
