/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;


import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import karyawan.start_Home;
import koneksiDB.koneksi;

/**
 *
 * @author kitan
 */
public class Admin_Fitur extends javax.swing.JFrame {

    static String namaUser;
    static String idWong;
    public Admin_Fitur(String nama) {
        idWong = nama;
        System.out.println(idWong);
        setName(nama);
        initComponents();
        this.setTitle("MENU ADMIN");
        pInfo.setText(namaUser );
        System.out.println(namaUser);
        
       
    }
    public void setName(String user){
       String namaKrywn = "SELECT `login`.`username`,`login`.`password`,`karyawan`.`nama_krywn`,`karyawan`.`type` FROM `login` JOIN `karyawan` ON `karyawan`.`idkrywn` = `login`.`idkrywn` WHERE username = '"+user+"'";
       try{
           java.sql.Connection conn = (Connection) koneksi.configDB();
           java.sql.PreparedStatement stm = conn.prepareStatement(namaKrywn);
           java.sql.ResultSet rs = stm.executeQuery();
           while(rs.next()){
               String nama1 = rs.getString(3);
               namaUser = nama1;
               
           }
       
       }catch(Exception e){
       
       }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_reset = new javax.swing.JButton();
        btn_editdatasupplier = new javax.swing.JButton();
        btn_lapora = new javax.swing.JButton();
        btn_daftarkaryawan1 = new javax.swing.JButton();
        btn_editstock = new javax.swing.JButton();
        btn_daftarsupplier1 = new javax.swing.JButton();
        btn_dashboard1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_reset.setBackground(new java.awt.Color(255, 101, 132));
        btn_reset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset.setText("EDIT DATA KARYAWAN");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        getContentPane().add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 230, 60));

        btn_editdatasupplier.setBackground(new java.awt.Color(255, 101, 132));
        btn_editdatasupplier.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_editdatasupplier.setForeground(new java.awt.Color(255, 255, 255));
        btn_editdatasupplier.setText("EDIT DATA SUPPLIER");
        btn_editdatasupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editdatasupplierActionPerformed(evt);
            }
        });
        getContentPane().add(btn_editdatasupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 230, 60));

        btn_lapora.setBackground(new java.awt.Color(255, 101, 132));
        btn_lapora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_lapora.setForeground(new java.awt.Color(255, 255, 255));
        btn_lapora.setText("LAPORAN");
        btn_lapora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laporaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_lapora, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 230, 60));

        btn_daftarkaryawan1.setBackground(new java.awt.Color(255, 101, 132));
        btn_daftarkaryawan1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_daftarkaryawan1.setForeground(new java.awt.Color(255, 255, 255));
        btn_daftarkaryawan1.setText("DAFTAR KARYAWAN");
        btn_daftarkaryawan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarkaryawan1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_daftarkaryawan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 230, 60));

        btn_editstock.setBackground(new java.awt.Color(255, 101, 132));
        btn_editstock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_editstock.setForeground(new java.awt.Color(255, 255, 255));
        btn_editstock.setText("EDIT STOCK BARANG");
        btn_editstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editstockActionPerformed(evt);
            }
        });
        getContentPane().add(btn_editstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 230, 60));

        btn_daftarsupplier1.setBackground(new java.awt.Color(255, 101, 132));
        btn_daftarsupplier1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_daftarsupplier1.setForeground(new java.awt.Color(255, 255, 255));
        btn_daftarsupplier1.setText("DAFTAR SUPPLIER");
        btn_daftarsupplier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarsupplier1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_daftarsupplier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 230, 60));

        btn_dashboard1.setBackground(new java.awt.Color(255, 101, 132));
        btn_dashboard1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_dashboard1.setForeground(new java.awt.Color(255, 255, 255));
        btn_dashboard1.setText("DASHBOARD");
        btn_dashboard1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dashboard1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_dashboard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 230, 60));

        pInfo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pInfo.setForeground(new java.awt.Color(255, 255, 255));
        pInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pInfo.setText("jLabel2");
        getContentPane().add(pInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/formowner.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_laporaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        try {
            new Admin_laporan(idWong).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Fitur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_laporaActionPerformed

    private void btn_editdatasupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editdatasupplierActionPerformed
        try {
            this.setVisible(false);
            new Admin_Suplier(idWong).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Fitur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_editdatasupplierActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        try {
            // TODO add your handling code here:
            new Admin_Karyawan(idWong).setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Fitur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_daftarkaryawan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarkaryawan1ActionPerformed
        // TODO add your handling code here:   
       this.setVisible(false);
        try {
            new Admin_DaftarKaryawan(idWong).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Fitur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_daftarkaryawan1ActionPerformed

    private void btn_editstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editstockActionPerformed
        this.setVisible(false);
        new Admin_SuplaiBarang(idWong).setVisible(true);
      
    }//GEN-LAST:event_btn_editstockActionPerformed

    private void btn_daftarsupplier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarsupplier1ActionPerformed
        // TODO add your handling code here
       this.setVisible(false);
       new Admin_DaftarSuplier(idWong).setVisible(true);
       
    }//GEN-LAST:event_btn_daftarsupplier1ActionPerformed

    private void btn_dashboard1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dashboard1ActionPerformed
        // TODO add your handling code here:
       this.setVisible(false);
       new start_Home(idWong).setVisible(true);
    }//GEN-LAST:event_btn_dashboard1ActionPerformed

    private void delete(String query,String query2) throws SQLException{
        java.sql.Connection con = (Connection) koneksi.configDB();
        java.sql.PreparedStatement prs = con.prepareStatement(query);
        if(prs.execute()){
          System.out.printf("%s%n","Eksekusi query berhasil");   
        } else{
            System.out.printf("%s%n","Ekesekusi query gagal");
        }

    }
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
            java.util.logging.Logger.getLogger(Admin_Fitur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Fitur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Fitur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Fitur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Fitur(pInfo.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_daftarkaryawan1;
    private javax.swing.JButton btn_daftarsupplier1;
    private javax.swing.JButton btn_dashboard1;
    private javax.swing.JButton btn_editdatasupplier;
    private javax.swing.JButton btn_editstock;
    private javax.swing.JButton btn_lapora;
    private javax.swing.JButton btn_reset;
    private javax.swing.JLabel jLabel1;
    public static final javax.swing.JLabel pInfo = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}