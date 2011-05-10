/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Ventana.java
 *
 * Created on May 7, 2011, 4:30:27 PM
 */

package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignaciocardenas
 */
public class Ventana extends javax.swing.JFrame {

    /** Creates new form Ventana */


    private Socket ser;
    private String ip;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private HiloServidor hiloservidorconoce = null;
    private Servidor servidor;

    public Ventana() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText("IP SERVIDOR");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(49, 49, 49)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(14, 14, 14)
                        .add(jButton1))
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(32, 32, 32)
                .add(jButton1)
                .add(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTextField1.setEnabled(false);
        jButton1.setEnabled(false);
        servidor = new Servidor ();
        Thread nuevohiloservidor = new Thread(servidor);
        nuevohiloservidor.start();

        try {
            hiloservidorconoce = new HiloServidor(this);
            Thread nuevohiloservidorconoce = new Thread(hiloservidorconoce);
            nuevohiloservidorconoce.start();
        } catch (UnknownHostException ex) {System.out.println("No consiguio localhost");}

        if (jTextField1.getText().matches("IP SERVIDOR") == false)
        {// si hay un ip en el campo trata de conectarse con el
            hiloservidorconoce.conocer(jTextField1.getText());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
