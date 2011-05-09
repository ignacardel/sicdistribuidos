/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Ventana.java
 *
 * Created on May 7, 2011, 5:14:56 PM
 */
package cliente;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ignaciocardenas
 */
public class VentanaCliente extends javax.swing.JFrame {

    Vector <HiloDescarga> VectorHilos;
    int selected = -1;
    /** Creates new form Ventana */
    public VentanaCliente() {
        VectorHilos=new Vector <HiloDescarga> ();
        initComponents();
        pausar.setVisible(false);
        cancelar.setVisible(false);
        eliminar.setVisible(false);
        this.llenarcombobox();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jArchivo = new javax.swing.JTextField();
        jIP = new javax.swing.JTextField();
        jDescargar = new javax.swing.JButton();
        jListarArchivos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();
        cancelar = new javax.swing.JButton();
        pausar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 2, 13));
        jLabel2.setText("Conectar a:");

        jArchivo.setText("Nombre Archivo");
        jArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jArchivoActionPerformed(evt);
            }
        });

        jIP.setText("localhost");
        jIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIPActionPerformed(evt);
            }
        });

        jDescargar.setText("Descargar");
        jDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDescargarActionPerformed(evt);
            }
        });

        jListarArchivos.setText("Listar Archivos");

        jLabel1.setFont(new java.awt.Font("Meiryo", 1, 13));
        jLabel1.setText("Proyecto 1");

        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Archivo", "Cantidad Descargada", "Total", "Estado", "hilo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaMouseClicked(evt);
            }
        });
        jTabla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTablaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTablaFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTabla);
        jTabla.getColumnModel().getColumn(4).setResizable(false);
        jTabla.getColumnModel().getColumn(4).setPreferredWidth(1);

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        pausar.setText("Pausar");
        pausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausarActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(31, 31, 31)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jIP)
                                    .add(jArchivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 182, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jDescargar)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jListarArchivos)))
                            .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(cancelar)
                        .add(27, 27, 27)
                        .add(pausar)
                        .add(33, 33, 33)
                        .add(eliminar))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jIP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jListarArchivos))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jArchivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jDescargar)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelar)
                    .add(pausar)
                    .add(eliminar))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jArchivoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jArchivoActionPerformed

    private void jIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIPActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jIPActionPerformed

    private void jDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDescargarActionPerformed
        // TODO add your handling code here:
     
        HiloDescarga nuevadescarga = new HiloDescarga(this, jIP.getText(), jComboBox1.getSelectedItem().toString(),VectorHilos.size(),0);
        VectorHilos.add(nuevadescarga);
        Thread nuevohilodescarga = new Thread(nuevadescarga);
        nuevohilodescarga.start();
        
    }//GEN-LAST:event_jDescargarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        try {

            String status = (String)jTabla.getValueAt(selected, 3);

            if ( status.matches("Descargando") == true)
            {
                int hilo = (Integer)jTabla.getValueAt(selected, 4);
                VectorHilos.get(hilo).cerrar();
                VectorHilos.setElementAt(null, hilo);
                jTabla.setValueAt("Cancelado", selected, 3);
                jTabla.setValueAt(null, selected, 4);
            }
            else
            {
                jTabla.setValueAt("Cancelado", selected, 3);
                jTabla.setValueAt(null, selected, 4);
            }

            this.eliminar.setVisible(true);
            this.pausar.setVisible(true);
            this.pausar.setText("Recomenzar");
            this.cancelar.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(VentanaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cancelarActionPerformed

    private void jTablaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTablaFocusGained


        // TODO add your handling code here:
    }//GEN-LAST:event_jTablaFocusGained

    private void jTablaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTablaFocusLost
       //     cancelar.setVisible(false);
       //     pausar.setVisible(false);
       //     pausar.setText("Pausar");
       //     eliminar.setVisible(false);   // TODO add your handling code here:
    }//GEN-LAST:event_jTablaFocusLost

    private void pausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausarActionPerformed
        try {

            String accion = (String)jTabla.getValueAt(selected, 3);

            if(accion.matches("Descargando")== true)
            {

                int hilo = (Integer)jTabla.getValueAt(selected, 4);
                VectorHilos.get(hilo).cerrar();
                VectorHilos.setElementAt(null, hilo);
                jTabla.setValueAt("Pausado", selected, 3);
                jTabla.setValueAt(null, selected, 4);
                this.pausar.setText("Continuar");
            }
            if(accion.matches("Pausado")== true)
            {
                
                HiloDescarga nuevadescarga = new HiloDescarga(this, jIP.getText(), (String)jTabla.getValueAt(selected, 0),VectorHilos.size(),1);
                VectorHilos.add(nuevadescarga);
                Thread nuevohilodescarga = new Thread(nuevadescarga);
                nuevohilodescarga.start();
                this.pausar.setText("Pausar");

            }
            if(accion.matches("Cancelado")== true)
            {

                HiloDescarga nuevadescarga = new HiloDescarga(this, jIP.getText(), (String)jTabla.getValueAt(selected, 0),VectorHilos.size(),1);
                VectorHilos.add(nuevadescarga);
                Thread nuevohilodescarga = new Thread(nuevadescarga);
                nuevohilodescarga.start();
                this.pausar.setText("Pausar");
                this.cancelar.setVisible(true);
            }

        } catch (IOException ex) {
            Logger.getLogger(VentanaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_pausarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        try {

            String status = (String)jTabla.getValueAt(selected, 3);

            if ( status.matches("Descargando") == true)
            {   int hilo = (Integer)jTabla.getValueAt(selected, 4);
                VectorHilos.get(hilo).cerrar();
                VectorHilos.get(hilo).borrar();
                VectorHilos.setElementAt(null, hilo);// en vez de remove poner en null
                ((DefaultTableModel) this.jTabla.getModel()).removeRow(selected);
            }
            else
            {
                String nombre = (String)jTabla.getValueAt(selected, 0);
                HiloDescarga borrador = new HiloDescarga(this, null,nombre ,-1,-1);
                borrador.borrar();
                ((DefaultTableModel) this.jTabla.getModel()).removeRow(selected);
            }

        } catch (IOException ex) {
            Logger.getLogger(VentanaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_eliminarActionPerformed

    private void jTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaMouseClicked

        this.selected = jTabla.getSelectedRow();
        if (this.selected >= 0 )
        {
            String status =(String)jTabla.getValueAt(selected, 3);

            if (status.matches("Descargando") == true)
            {
                cancelar.setVisible(true);
                pausar.setVisible(true);
                pausar.setText("Pausar");
                eliminar.setVisible(true);
            }

            if (status.matches("Pausado") == true)
            {
                cancelar.setVisible(true);
                pausar.setText("Continuar");
                pausar.setVisible(true);
                eliminar.setVisible(true);
            }
            if (status.matches("Cancelado") == true)
            {
                eliminar.setVisible(true);
                pausar.setVisible(true);
                pausar.setText("Recomenzar");
            }

             if (status.matches("Descarga Completada") == true)
            {
                cancelar.setVisible(false);
                pausar.setVisible(false);
                pausar.setText("Pausar");
                eliminar.setVisible(false);

            }   // TODO add your handling code here:
        }
    }//GEN-LAST:event_jTablaMouseClicked

    public void agregarfila(String nombre, Long cantidad, Long tamano, int hilo) {
        ((DefaultTableModel) this.jTabla.getModel()).addRow(new Object[]{nombre, cantidad, tamano, "Descargando",hilo});
    }

    public void actualizarstatus(String status ,int hilo, Long cantidad) {

     jTabla.setValueAt("Descargando", selected, 3);
     jTabla.setValueAt(hilo, selected, 4);
     jTabla.setValueAt(cantidad, selected, 1);
     pausar.setText("Pausar");

    }

    public void actualizarcantidad(Long cantidad,int hilo) {
        int count = jTabla.getRowCount();
        for (int i= 0;i<count;i++)
        {
           if (jTabla.getValueAt(i, 4) != null  && (Integer)jTabla.getValueAt(i, 4) == hilo)
           {
             jTabla.setValueAt(cantidad, i, 1);
             count = i;
           }
        
        }

        long total = (Long)jTabla.getValueAt(count, 2);
        long lleva = (Long)jTabla.getValueAt(count, 1);
        if ( lleva == total)
        {
            VectorHilos.setElementAt(null, hilo);
            jTabla.setValueAt("Descarga Completada", count, 3);
            jTabla.setValueAt(null, count, 4);
            cancelar.setVisible(false);
            pausar.setVisible(false);
            pausar.setText("Pausar");
            eliminar.setVisible(false);
        }
    }

    public String getStatus(int acc)  {
        if (acc == 1)
         return (String)jTabla.getValueAt(selected, 3);
        else
         return "null";
    }

    public int getRowCount()  {
        return jTabla.getRowCount();
    }


    private void llenarcombobox(){
      this.jComboBox1.addItem("1.mp4");
      this.jComboBox1.addItem("2.mp4");
      this.jComboBox1.addItem("3.avi");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new VentanaCliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField jArchivo;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JButton jDescargar;
    private javax.swing.JTextField jIP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jListarArchivos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabla;
    private javax.swing.JButton pausar;
    // End of variables declaration//GEN-END:variables
}
