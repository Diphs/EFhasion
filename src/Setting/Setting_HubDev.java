/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Setting;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import javax.swing.UIManager;
import karyawan.start_Home;
import koneksiDB.koneksi;

/**
 *
 * @author Windows 10
 */
public class Setting_HubDev extends javax.swing.JFrame {

    /**
     * Creates new form krywn_set_WA
     */
    public Setting_HubDev(String nama) {
        initComponents();
         this.setTitle("HUBUNGI DEVELOPPER");
        SetNama(nama);
        textID();
    }
     public void SetNama(String user){
        userr.setText(user);
    }
    public void textID(){
        try{
            String query = "SELECT `login`.`username`,`login`.`password`,"
                    + "`karyawan`.`nama_krywn`,`karyawan`.`type` FROM "
                    + "`login` JOIN `karyawan` ON `karyawan`.`idkrywn` = `login`.`idkrywn` WHERE username = '"+userr.getText()+"'";
            java.sql.Connection conn = (Connection)koneksi.configDB();
            java.sql.Statement statementObj = conn.createStatement();
            java.sql.ResultSet rs = statementObj.executeQuery(query);
            
            while(rs.next()){
                
                if(rs.getString(4).equals("Karyawan")||rs.getString(4).equals("karyawan")){
                    adminId.setVisible(false);
                    karyawanId.setVisible(true);
                    
                } else  if(rs.getString(4).equals("Bos")){
                    adminId.setVisible(true);
                    karyawanId.setVisible(false);
                }
            
            }
        }catch(Exception e){
            e.getMessage();
        }
        
    }

    public void wa(){
    String urlfb = "https://api.whatsapp.com/send/?phone=6285851065295&text&app_absent=0";
        
        if (Desktop.isDesktopSupported()){
        Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(urlfb));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
        Runtime runtime = Runtime.getRuntime();
        try {
                runtime.exec("xdg-open " + urlfb);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        adminId = new javax.swing.JLabel();
        karyawanId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 410, 60));

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 102, 140, 30));

        jButton3.setBackground(new java.awt.Color(102, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-back-24.png"))); // NOI18N
        jButton3.setText("BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 100, 40));

        adminId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        adminId.setForeground(new java.awt.Color(255, 255, 255));
        adminId.setText("ID Admin :");
        getContentPane().add(adminId, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 90, 30));

        userr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userr.setForeground(new java.awt.Color(255, 255, 255));
        userr.setText("jLabel1");
        getContentPane().add(userr, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 500, -1, 30));

        karyawanId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        karyawanId.setForeground(new java.awt.Color(255, 255, 255));
        karyawanId.setText("ID Karyawan :");
        getContentPane().add(karyawanId, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 110, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Tech_Otakus.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 550, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/setWa.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Setting_BackUpDb(userr.getText()).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        wa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new start_Home(userr.getText()).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
            java.util.logging.Logger.getLogger(Setting_HubDev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Setting_HubDev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Setting_HubDev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Setting_HubDev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Setting_HubDev(userr.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminId;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel karyawanId;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
