/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.net.*;

/**
 *
 * @author ignaciocardenas
 */
public class HiloIPs implements Runnable {

    private VentanaCliente ventana;

    public HiloIPs(VentanaCliente ventana) {
        this.ventana = ventana;
    }

    public void run() {
        try {
            ServerSocket socketservidor = new ServerSocket(6003);
            while (true) {
                System.out.println("Esperando actualizacion de Ips");
                Socket socketips = socketservidor.accept();
                DataInputStream entrada = new DataInputStream(socketips.getInputStream());
                String listaips = entrada.readUTF();
                System.out.println("ips nuevos de mierda " + listaips);
                String[] lista = listaips.split("/");
                ventana.setIp0(lista[0]);
                ventana.setIp1(lista[1]);
                ventana.setIp2(lista[2]);
                socketips.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
