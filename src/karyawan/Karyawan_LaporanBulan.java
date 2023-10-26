/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package karyawan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import koneksiDB.koneksi;


public class Karyawan_LaporanBulan extends javax.swing.JFrame {

    public Karyawan_LaporanBulan(String nama) {
        initComponents();
        tampilanDataBulanan();
        this.setTitle("LAPORAN BULANAN");
        todPeng.setEditable(false);
        SetNama(nama);
        
        
    }
     public void SetNama(String user){
        userr.setText(user);
    }
    private void lebar_tabelbarang(){
   
        TableColumn kolom;
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN); 
        kolom =  jTable1.getColumnModel().getColumn(0); 
        kolom.setPreferredWidth(105);
        kolom =  jTable1.getColumnModel().getColumn(1); 
        kolom.setPreferredWidth(385); 
        kolom =  jTable1.getColumnModel().getColumn(2); 
        kolom.setPreferredWidth(145); 
        kolom =  jTable1.getColumnModel().getColumn(3); 
        kolom.setPreferredWidth(145);

    }
    

    private void tampilanDataBulanan(){
        int tod = 0;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nomer");
        model.addColumn("Penghasilan Perbulan");
        model.addColumn("Bulan");
        model.addColumn("Tahun");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        String sql = "SELECT SUM(total_hrg) , MONTHNAME(tgl_transaksi),YEAR(tgl_transaksi) FROM transaksi WHERE MONTH(tgl_transaksi) >=1 AND YEAR(tgl_transaksi) = '"+jYearChooser1.getYear()+"' GROUP BY MONTH(tgl_transaksi); ";
        System.out.println(sql);
        int nomer = 1 ;
        
        try{
            java.sql.Connection conn =(Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                
             //format kurs
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                
                //format rupiah pada table
                String idr1 = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(1))));
                
              model.addRow(new Object[]{nomer++,idr1,rs.getString(2),rs.getString(3)});
              tod += Integer.parseInt(rs.getString(1));
             String idr = kursIndonesia.format(Integer.parseInt(String.valueOf(tod)));
                System.out.println(tod);
                todPeng.setText(String.valueOf(idr));
              
            }
            jTable1.setModel(model);
            lebar_tabelbarang();
        }catch(Exception e){
            e.getMessage();
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        btn_cariHari = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        todPeng = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TOTAL PENDAPATAN PERBULAN");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 600, -1));

        jYearChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jYearChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jYearChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 192, 70, 30));

        btn_cariHari.setBackground(new java.awt.Color(255, 255, 255));
        btn_cariHari.setForeground(new java.awt.Color(255, 255, 255));
        btn_cariHari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        btn_cariHari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariHariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cariHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 40, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("TOTAL PENGHASILAN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, 130, 30));
        getContentPane().add(todPeng, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 180, 30));

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
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
        jTable1.setSelectionBackground(new java.awt.Color(255, 51, 102));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.setShowVerticalLines(false);
        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 237, 780, 210));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-back-24.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 40, 40));

        userr1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userr1.setForeground(new java.awt.Color(255, 255, 255));
        userr1.setText("ID Admin :");
        getContentPane().add(userr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        userr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userr.setForeground(new java.awt.Color(255, 255, 255));
        userr.setText("jLabel1");
        getContentPane().add(userr, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/laporanBulanan.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariHariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariHariActionPerformed
        // TODO add your handling code here:
        tampilanDataBulanan();
      

    }//GEN-LAST:event_btn_cariHariActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Karyawan_LaporanSwitch(userr.getText()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Karyawan_LaporanBulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan_LaporanBulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan_LaporanBulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan_LaporanBulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Karyawan_LaporanBulan(userr.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cariHari;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTextField todPeng;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    public static final javax.swing.JLabel userr1 = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
