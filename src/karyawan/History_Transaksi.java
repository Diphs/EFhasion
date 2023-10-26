/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package karyawan;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import koneksiDB.koneksi;

/**
 *
 * @author Windows 10
 */
public class History_Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form krywn_HistoryTrans
     */
    public History_Transaksi() {
        initComponents();
        tampilData();
        this.setTitle("HISTORY TRANSAKSI");
//        this.setDefaultCloseOperation(0);
    }
    
         
    private void tampilData(){
    int totlBrg = 0;
    int Keunt =0;
    int todHrg = 0;
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Transaksi");
    model.addColumn("Nama barang");
    model.addColumn("Harga Beli");
    model.addColumn("harga Jual");
    model.addColumn("Jumlah Barang");
    model.addColumn("Labah Kotor");
    model.addColumn("Labah Bersih");
    model.addColumn("Untung");
    jTable1.getTableHeader().setBackground(new Color(102,153,255));
    jTable1.getTableHeader().setForeground(Color.WHITE);
    jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
    
    String sql = " SELECT id_trans, nama_barang ,harga_beli, harga_jual , "
            + "jml_barang , (harga_jual*jml_barang) AS Labah_Kotor ,"
            + "(harga_beli*jml_barang) AS Labah_Bersih , "
            + "((harga_jual*jml_barang)-(harga_beli*jml_barang)) AS Untung FROM log_trans; ";
    try{
        java.sql.Connection conn = (Connection) koneksi.configDB();
        java.sql.PreparedStatement stm = conn.prepareStatement(sql);
        java.sql.ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            
          //Format Rupiah
          DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
          DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
          formatRp.setCurrencySymbol("Rp. ");
          formatRp.setMonetaryDecimalSeparator(',');
          formatRp.setGroupingSeparator('.');
          kursIndonesia.setDecimalFormatSymbols(formatRp);
          
          //Format Table
          String Hrgbeli = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(3))));
          String HrgJual = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(4))));
          String LbhKtr = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(6))));
          String LbhBrs = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(7))));
          String UntungTable = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(8))));
          
          
          
          model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),
          rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
          
          Keunt += Integer.parseInt(String.valueOf(rs.getString(8)));
          totlBrg  += Integer.parseInt(String.valueOf(rs.getString(5)));
          todHrg  += Integer.parseInt(String.valueOf(rs.getString(6)));
          
          String TodUnt = kursIndonesia.format(Integer.parseInt(String.valueOf(Keunt)));
          String TodHarga1 = kursIndonesia.format(Integer.parseInt(String.valueOf(todHrg)));
          
          untung12.setText(String.valueOf(TodUnt));
          Brg.setText(String.valueOf(totlBrg));
          todHrg1.setText(String.valueOf(TodHarga1));
          
        }
        jTable1.setModel(model);
    }catch(Exception e){
        JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void tampilDataTentu(String key){
    int totlBrg = 0;
    int Keunt =0;
    int todHrg = 0;
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Transaksi");
    model.addColumn("Nama barang");
    model.addColumn("Harga Beli");
    model.addColumn("harga Jual");
    model.addColumn("Jumlah Barang");
    model.addColumn("Labah Kotor");
    model.addColumn("Labah Bersih");
    model.addColumn("Untung");
    jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
    
    String sql = "SELECT id_trans, nama_barang ,harga_beli, harga_jual , "
            + "jml_barang , (harga_jual*jml_barang) AS Labah_Kotor ,"
            + "(harga_beli*jml_barang) AS Labah_Bersih , "
            + "((harga_jual*jml_barang)-(harga_beli*jml_barang)) AS Untung FROM log_trans WHERE id_trans LIKE '%"+key+"%';";
    try{
        java.sql.Connection conn = (Connection) koneksi.configDB();
        java.sql.PreparedStatement stm = conn.prepareStatement(sql);
        java.sql.ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            
          //Format Rupiah
          DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
          DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
          formatRp.setCurrencySymbol("Rp. ");
          formatRp.setMonetaryDecimalSeparator(',');
          formatRp.setGroupingSeparator('.');
          kursIndonesia.setDecimalFormatSymbols(formatRp);
          
          //Format Table
          String Hrgbeli = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(3))));
          String HrgJual = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(4))));
          String LbhKtr = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(6))));
          String LbhBrs = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(7))));
          String UntungTable = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(8))));
          
          
          
          model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),
          rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
          
          Keunt += Integer.parseInt(String.valueOf(rs.getString(8)));
          totlBrg  += Integer.parseInt(String.valueOf(rs.getString(5)));
          todHrg  += Integer.parseInt(String.valueOf(rs.getString(6)));
          
          String TodUnt = kursIndonesia.format(Integer.parseInt(String.valueOf(Keunt)));
          String TodHarga1 = kursIndonesia.format(Integer.parseInt(String.valueOf(todHrg)));
          
          untung12.setText(String.valueOf(TodUnt));
          Brg.setText(String.valueOf(totlBrg));
          todHrg1.setText(String.valueOf(TodHarga1));
          
        }
        jTable1.setModel(model);
    }catch(Exception e){
        JOptionPane.showMessageDialog(this, e.getMessage());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        Brg = new javax.swing.JLabel();
        todHrg1 = new javax.swing.JLabel();
        untung12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jTable1.setSelectionBackground(new java.awt.Color(255, 102, 102));
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 760, 190));

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 260, 40));

        Brg.setForeground(new java.awt.Color(255, 255, 255));
        Brg.setText("jLabel1");
        getContentPane().add(Brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 50, 40));

        todHrg1.setForeground(new java.awt.Color(255, 255, 255));
        todHrg1.setText("jLabel1");
        getContentPane().add(todHrg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 140, 40));

        untung12.setForeground(new java.awt.Color(255, 255, 255));
        untung12.setText("jLabel1");
        getContentPane().add(untung12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, 140, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/HistoryTransaksi.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        String key=jTextField1.getText(); 
        if(key!=""){
            tampilDataTentu(key);
        }else{
            tampilData();
        }
    }//GEN-LAST:event_jTextField1KeyReleased

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
            java.util.logging.Logger.getLogger(History_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(History_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(History_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(History_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new History_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Brg;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel todHrg1;
    private javax.swing.JLabel untung12;
    // End of variables declaration//GEN-END:variables
}
