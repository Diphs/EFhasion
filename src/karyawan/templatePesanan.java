/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karyawan;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Windows 10
 */
public class templatePesanan extends javax.swing.JFrame {

    static String namaan = "";
    public templatePesanan(String nama) {
        initComponents();
       
        NamaS(nama);
        
    }

    public void NamaS(String user){
        namaan = user;
        
    }

    public void ngopy(){
    StringSelection stringSelection = new StringSelection (jTextArea1.getText());
    Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
    clpbrd.setContents (stringSelection, null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 600, 120));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "==PILIH TEMPLATE==", "Barang Habis", "Barang Cacat", "Barang Kurang" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 30));

        jButton1.setText("COPY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 110, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 300));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String Template1 = "Assalamualaikum wr. wb. \n"
                + "Saya Karyawan dari Toko Baju Mahkota Bandung ingin mengonfirmasi bahwa barang dari "+namaan+"\n"
                + "Stok nya sudah menipis , dengan begitu diharapkan untuk segera datang ke Toko Baju Mahkota Bandung\n"
                + "Terima Kasih";
        
        String Template2 = "Assalamualaikum wr. wb. \n"
                + "Saya Karyawan dari Toko Baju Mahkota Bandung ingin mengonfirmasi bahwa barang dari "+namaan+"\n"
                + "Barangnya ada yang cacat , dengan begitu diharapkan untuk segera mengganti\n"
                + "Terima Kasih";
        
        String Template3 = "Assalamualaikum wr. wb. \n"
                + "Saya Karyawan dari Toko Baju Mahkota Bandung ingin mengonfirmasi bahwa barang dari "+namaan+"\n"
                + "Barang yang stok ternyata ada kekurangan , dengan begitu diharapkan untuk segera datang ke\nToko Baju Mahkota Bandung\n"
                + "Terima Kasih";
        
        if(jComboBox1.getSelectedItem().equals("Barang Habis")){
            jTextArea1.setText(Template1);
            
        } else if(jComboBox1.getSelectedItem().equals("Barang Cacat")){
            jTextArea1.setText(Template2);
            
        }else if(jComboBox1.getSelectedItem().equals("Barang Kurang")){
            jTextArea1.setText(Template3);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ngopy();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
              try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
         } catch (Exception e) {
            e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(templatePesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(templatePesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(templatePesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(templatePesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new templatePesanan(namaan).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
