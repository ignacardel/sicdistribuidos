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
    private int posicion;
    private long tamano;
    private int accion;// variable para saber si se esta comezando a descargar por primera vez(0)
    private boolean continuar=true;

    HiloDescarga(VentanaCliente ventana, String ip, String nombrearchivo,int posicionvector, int accion) {
        this.ventana = ventana;
        this.ip = ip;
        this.nombrearchivo = nombrearchivo;
        this.posicion = posicionvector;
        this.accion = accion;

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

                    // validar que el archivo ya este descargado completo ?
                    if ((f.exists() == true) && (ventana.getStatus(accion).matches("Cancelado") == false ))
                    {
                        archivo = new FileOutputStream(f, true);
                        salida.writeLong(f.length());
                    } else {
                        archivo = new FileOutputStream(f);
                        salida.writeLong(0);
                    }

                    tamano = entrada.readLong();

                    if (accion == 0)
                      ventana.agregarfila(nombrearchivo, Long.valueOf("0"), tamano, posicion);
                    else
                        if(ventana.getStatus(accion).matches("Cancelado") == false )
                          ventana.actualizarstatus("Descargando",posicion,f.length());
                        else
                          ventana.actualizarstatus("Descargando",posicion,Long.valueOf("0"));


                    fila = ventana.getRowCount();

                    byte[] buffer = new byte[1024];
                    int len;
                    int total = 0;
                    while ((len = entrada.read(buffer)) > 0 && continuar==true) {
                        archivo.write(buffer, 0, len);
                        total = total + len;
                        ventana.actualizarcantidad(f.length(), posicion);
                        //System.out.println("Recibiendo " + String.valueOf(len) + " total " + total);
                    }

                    if (tamano != f.length() && continuar == true)
                    {
                        // aqui es que no se bajo completo y se perdio conexion con el servidor
                        // tumbar este hilo y tratar de conectar con otro servidor.
                        // revisar bien la parte del numero del hilo y el vector en la tabla.
                       // (quitar el # hilo de la tabla)
                        System.out.println("FUNDIO AQUI SE CONECTA CON OTRO");
                    }


                    System.out.println("Recibido!! QUITAR ESTE MENSAJE");
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
    }

    public void borrar() {
        File f = new File(ruta + nombrearchivo);
        f.delete();

    }

}
