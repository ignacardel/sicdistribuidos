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
 *
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
    private long tamano;
    private long cantidaddescargada;

    HiloEnvio(Socket nuevosocketcliente) {
        try {
            s = nuevosocketcliente;
            entrada = new DataInputStream(s.getInputStream());
            salida = new DataOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(HiloEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            solicitud = entrada.readUTF();
            if (solicitud.equals("GET")) {
                this.enviarips();
                this.enviararchivo();
            }
            if (solicitud.equals("RDIR")) {
                this.enviarips();
                this.listararhivos();
            }


            s.close();
            System.out.println("Fin de conexion de " + s.getInetAddress());
        } catch (IOException ex) {
            Logger.getLogger(HiloEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
                salida.write(buffer, 0, len);
                total = total + len;
                System.out.println("Enviando " + String.valueOf(len) + " total " + total);
            }
            System.out.println("Fin de envio");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listararhivos() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void enviarips() {
        try {
            XMLServidor xml = new XMLServidor();
            ArrayList<String> listaips = xml.cargaIPs();
            salida.writeUTF(listaips.get(0));
            salida.writeUTF(listaips.get(1));
            salida.writeUTF(listaips.get(2));
        } catch (IOException ex) {
            Logger.getLogger(HiloEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
