/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package karyawan;


import Setting.Setting_BackUpDb;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksiDB.koneksi;

/**
 *
 * @author Windows 10
 */
public class Karyawan_ListDataSuplier extends javax.swing.JFrame {
    
    public Karyawan_ListDataSuplier(String nama) {
        initComponents();
         this.setTitle("LIST SUPPLIER");
        DataSuplier();
        SetNama(nama);
        textID();
    }
    public void SetNama(String user){
        userr.setText(user);
    }
    
    private void lebar_tabelbarang(){
   
        TableColumn kolom;
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN); 
        kolom =  jTable1.getColumnModel().getColumn(0); 
        kolom.setPreferredWidth(100);
        kolom =  jTable1.getColumnModel().getColumn(1); 
        kolom.setPreferredWidth(200); 
        kolom =  jTable1.getColumnModel().getColumn(2); 
        kolom.setPreferredWidth(150); 
        kolom =  jTable1.getColumnModel().getColumn(3); 
        kolom.setPreferredWidth(160); 

       
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
    private void CariData(String key){
         DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID SUPLIER");
        model.addColumn("NAMA");
        model.addColumn("ALAMAT");
        model.addColumn("NO TELEPHONE");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        String sql ="SELECT * FROM supplier WHERE nama LIKE '%"+key+"%'";
        
        
        
        try{
             java.sql.Connection conn = (Connection) koneksi.configDB();
             java.sql.PreparedStatement stm = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stm.executeQuery(sql);
             
             while(rs.next()){
                 model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                 
             }
             jTable1.setModel(model);
             lebar_tabelbarang();
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
    }
    private void DataSuplier(){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID SUPLIER");
        model.addColumn("NAMA");
        model.addColumn("ALAMAT");
        model.addColumn("NO TELEPHONE");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        String sql ="SELECT * FROM supplier";
         
        try{
             java.sql.Connection conn = (Connection) koneksi.configDB();
             java.sql.PreparedStatement stm = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stm.executeQuery(sql);
             
             while(rs.next()){
                 model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                 
             }
             jTable1.setModel(model);
             lebar_tabelbarang();
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
    }

    String nomerHp = "";
    String nomerSup = "";
    
 
    public void wa(){
    String urlfb = "https://api.whatsapp.com/send/?phone="+nomerHp+"&text&app_absent=0";
        
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

        btn_home = new javax.swing.JButton();
        btn_Suplier = new javax.swing.JButton();
        btn_trans = new javax.swing.JButton();
        btn_report = new javax.swing.JButton();
        btn_report1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_cari = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        karyawanId = new javax.swing.JLabel();
        adminId = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_home.setBackground(new java.awt.Color(51, 51, 255));
        btn_home.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_home.setForeground(new java.awt.Color(255, 255, 255));
        btn_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-home-24.png"))); // NOI18N
        btn_home.setText("HOME");
        btn_home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        btn_home.setContentAreaFilled(false);
        btn_home.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_homeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 170, 40));

        btn_Suplier.setBackground(new java.awt.Color(51, 51, 255));
        btn_Suplier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Suplier.setForeground(new java.awt.Color(255, 255, 255));
        btn_Suplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-supplier-24.png"))); // NOI18N
        btn_Suplier.setText("SUPLIER");
        btn_Suplier.setBorder(null);
        btn_Suplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuplierActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Suplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 170, 40));

        btn_trans.setBackground(new java.awt.Color(51, 51, 255));
        btn_trans.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_trans.setForeground(new java.awt.Color(255, 255, 255));
        btn_trans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-cashier-24.png"))); // NOI18N
        btn_trans.setText("TRANSAKSI");
        btn_trans.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        btn_trans.setContentAreaFilled(false);
        btn_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transActionPerformed(evt);
            }
        });
        getContentPane().add(btn_trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 170, 40));

        btn_report.setBackground(new java.awt.Color(51, 51, 255));
        btn_report.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_report.setForeground(new java.awt.Color(255, 255, 255));
        btn_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-report-24.png"))); // NOI18N
        btn_report.setText("REPORT");
        btn_report.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        btn_report.setContentAreaFilled(false);
        btn_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reportActionPerformed(evt);
            }
        });
        getContentPane().add(btn_report, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 170, 40));

        btn_report1.setBackground(new java.awt.Color(51, 51, 255));
        btn_report1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_report1.setForeground(new java.awt.Color(255, 255, 255));
        btn_report1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-setting-24.png"))); // NOI18N
        btn_report1.setText("SETTING");
        btn_report1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        btn_report1.setContentAreaFilled(false);
        btn_report1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_report1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_report1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 170, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(30);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 610, 310));

        btn_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btn_cariKeyReleased(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 300, 30));

        jPanel3.setBackground(new java.awt.Color(255, 101, 132));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/EfashionFrame.png"))); // NOI18N
        jPanel3.add(jLabel12);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 210, 110));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Cari Nama Suplier");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, -1, -1));

        userr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userr.setForeground(new java.awt.Color(255, 255, 255));
        userr.setText("jLabel1");
        getContentPane().add(userr, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, -1, 30));

        karyawanId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        karyawanId.setForeground(new java.awt.Color(255, 255, 255));
        karyawanId.setText("ID Karyawan :");
        getContentPane().add(karyawanId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 110, 30));

        adminId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        adminId.setForeground(new java.awt.Color(255, 255, 255));
        adminId.setText("ID Admin :");
        getContentPane().add(adminId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 90, 30));

        jButton1.setBackground(new java.awt.Color(0, 204, 51));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("WHATSAPP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 570, 110, 40));

        jButton2.setText("TEMPLATE PESAN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 140, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data Suplier.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_homeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new start_Home(userr.getText()).setVisible(true);
    }//GEN-LAST:event_btn_homeActionPerformed

    private void btn_SuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuplierActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Karyawan_ListDataSuplier(userr.getText()).setVisible(true);
    }//GEN-LAST:event_btn_SuplierActionPerformed

    private void btn_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Karyawan_Transaksi(userr.getText()).setVisible(true);
    }//GEN-LAST:event_btn_transActionPerformed

    private void btn_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Karyawan_LaporanSwitch(userr.getText()).setVisible(true);
    }//GEN-LAST:event_btn_reportActionPerformed

    private void btn_report1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_report1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Setting_BackUpDb(userr.getText()).setVisible(true);
    }//GEN-LAST:event_btn_report1ActionPerformed

    private void btn_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_cariKeyReleased
        // TODO add your handling code here:
        String key=btn_cari.getText(); 
        if(key!=""){
            CariData( key);
        }else{
          DataSuplier();
        }
    }//GEN-LAST:event_btn_cariKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int baris = jTable1.rowAtPoint(evt.getPoint());
        
        if(jTable1.getValueAt(baris, 3)==null){
            //null
        } else {
            System.out.println(jTable1.getValueAt(baris, 3).toString());
            nomerHp = jTable1.getValueAt(baris, 3).toString();
            
        } 
        
        if(jTable1.getValueAt(baris, 1)==null){
            //null
        } else {
            System.out.println(jTable1.getValueAt(baris, 1).toString());
            nomerSup = jTable1.getValueAt(baris, 1).toString();
            
        } 
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        wa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       String passing = nomerSup;
       new templatePesanan(passing).setVisible(true);

      
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Karyawan_ListDataSuplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan_ListDataSuplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan_ListDataSuplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan_ListDataSuplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Karyawan_ListDataSuplier(userr.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminId;
    private javax.swing.JButton btn_Suplier;
    private javax.swing.JTextField btn_cari;
    private javax.swing.JButton btn_home;
    private javax.swing.JButton btn_report;
    private javax.swing.JButton btn_report1;
    private javax.swing.JButton btn_trans;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel karyawanId;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
