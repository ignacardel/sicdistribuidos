/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author ignaciocardenas
 */
public class HiloDescarga implements Runnable {

    private Socket w;
    private String ip;
    private FileOutputStream archivo;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String ruta = "Archivos/";
    private String nombrearchivo;
    private VentanaCliente ventana;
    private int fila;
    private boolean continuar=true;

    HiloDescarga(VentanaCliente ventana, String ip, String nombrearchivo) {
        this.ventana = ventana;
        this.ip = ip;
        this.nombrearchivo = nombrearchivo;

    }

    public void run() {
        try {
            w = new Socket(ip, 6000);
            entrada = new DataInputStream(w.getInputStream());
            salida = new DataOutputStream(w.getOutputStream());
            salida.writeUTF("GET");



            descargararchivo();
            
            w.close();


        } catch (UnknownHostException ex) {
            System.out.println("Error de conexion");

            // JOptionPane.showMessageDialog(rootPane, "Error en la conexion", "", 1);
        } catch (IOException ex) {
            System.out.println("Error de conexion");

            // JOptionPane.showMessageDialog(rootPane, "Error en la conexion", "", 1);
        }
    }

    private void descargararchivo() {
            try {
                salida.writeUTF(nombrearchivo);

                File f = new File(ruta + nombrearchivo);
                System.out.println("iniciando descargar");
                try {

                    if (f.exists()) {

                        archivo = new FileOutputStream(f, true);
                        salida.writeLong(f.length());
                    } else {
                        archivo = new FileOutputStream(f);
                        salida.writeLong(0);

                    }

                    ventana.agregarfila(nombrearchivo, Long.valueOf("0"));
                    fila = ventana.getRowCount();

                    byte[] buffer = new byte[1024];
                    int len;
                    int total = 0;
                    while ((len = entrada.read(buffer)) > 0 && continuar==true) {
                        archivo.write(buffer, 0, len);
                        total = total + len;
                        ventana.actualizarcantidad(f.length(), fila);
                        System.out.println("Recibiendo " + String.valueOf(len) + " total " + total);
                    }



                    System.out.println("Recibido!!");
                    archivo.flush();
                    archivo.close();
                } catch (FileNotFoundException F) {
                    System.out.println("no existe");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void cerrar() throws IOException {

        continuar = false;
        //w.close();


    }
}
