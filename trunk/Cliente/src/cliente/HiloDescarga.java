/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int posicion;
    private int reconectar = 1; // numero de veces q intenta reconectar
    private long tamano;
    private int accion;// variable para saber si se esta comezando a descargar por primera vez(0)
    private boolean continuar = true;

    HiloDescarga(VentanaCliente ventana, String ip, String nombrearchivo, int posicionvector, int accion) {
        this.ventana = ventana;
        this.ip = ip;
        this.nombrearchivo = nombrearchivo;
        this.posicion = posicionvector;
        if (accion > 1 ){// si es dos es que se esta tratando de reconectar
            this.reconectar = accion;
            this.accion = 1;
        }
        else
          this.accion = accion;

    }

    public void run() {
        try {
            w = new Socket(ip, 6000);
            entrada = new DataInputStream(w.getInputStream());
            salida = new DataOutputStream(w.getOutputStream());
            salida.writeUTF("GET");
            pedirips();
            descargararchivo();
            w.close();

        } catch (UnknownHostException ex) {

            if (reconectar>1)
            {
            System.out.println("TRATANDOO ARRIBAAAAAAAAAAAAAA");
            this.ventana.reconectar(nombrearchivo, reconectar);
            }
            else
            System.out.println("Error de conexion");

            // JOptionPane.showMessageDialog(rootPane, "Error en la conexion", "", 1);
        } catch (IOException ex) {
            System.out.println("Error de conexion");

            // JOptionPane.showMessageDialog(rootPane, "Error en la conexion", "", 1);
        }
    }

    private void descargararchivo() {
        try {
            
            salida.writeUTF(nombrearchivo.replace("Copy_of_", ""));

            File f = new File(ruta + nombrearchivo);
            System.out.println("iniciando descargar");
            try {

                // validar que el archivo ya este descargado completo ?
                if ((f.exists() == true) && (ventana.getStatus(accion).matches("Cancelado") == false)) {
                    if (accion == 0) {
                        do {
                            nombrearchivo = "Copy_of_" + nombrearchivo;
                            f = new File(ruta + nombrearchivo);
                        } while (f.exists() == true);
                        archivo = new FileOutputStream(f);
                        salida.writeLong(0);
                    } else {
                        archivo = new FileOutputStream(f, true);
                        salida.writeLong(f.length());
                    }
                } else {
                    archivo = new FileOutputStream(f);
                    salida.writeLong(0);
                }

                tamano = entrada.readLong();

                if (accion == 0) {
                    ventana.agregarfila(nombrearchivo, Long.valueOf("0"), tamano, posicion);
                } else if (ventana.getStatus(accion).matches("Cancelado") == false) {
                    ventana.actualizarstatus("Descargando", posicion, f.length());
                } else {
                    ventana.actualizarstatus("Descargando", posicion, Long.valueOf("0"));
                }

                reconectar = 1;
                byte[] buffer = new byte[1024];
                int len;
                int total = 0;
                String solicitud = null;
                 
                    while ((len = entrada.read(buffer)) > 0 && continuar == true)
                    {
                        //try{
                        archivo.write(buffer, 0, len);
                        total = total + len;
                        ventana.actualizarcantidad(f.length(), posicion);

//                        }catch(Exception e)
//                        { // validar que si fue aproposito que se cato no trate de reconectar
//                          System.out.println("TOY AQUIIIIIIIIIIIIIIIIIIIIIII");
//                          if (tamano == f.length())
//                             continuar = false;
//                        }

                    }
 
                if (tamano != f.length() && continuar == true)
                {// tratar de reconectar
                    System.out.println("TRATANDOOOOOOOOOOOOOOOOOOOOOOO");
                    this.ventana.reconectar(nombrearchivo, reconectar);
                }

                System.out.println("Recibido!! QUITAR ESTE MENSAJE");
                archivo.flush();
                archivo.close();
            } catch (FileNotFoundException F) {
                System.out.println("Archivo no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void pedirips() {
        try {

            System.out.println("NUEVA TABLA");
            ventana.setIp0(entrada.readUTF());
            System.out.println(ventana.getIp0());
            ventana.setIp1(entrada.readUTF());
            System.out.println(ventana.getIp1());
            ventana.setIp2(entrada.readUTF());
            System.out.println(ventana.getIp2());
        } catch (IOException ex) {
            Logger.getLogger(HiloDescarga.class.getName()).log(Level.SEVERE, null, ex);
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
