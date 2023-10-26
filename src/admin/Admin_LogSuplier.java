
package admin;


import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import koneksiDB.koneksi;
import karyawan.*;


public class Admin_LogSuplier extends javax.swing.JFrame {

    String tgl= "";
    public Admin_LogSuplier() {
        initComponents();
        this.setTitle("HISTORY SUPPLIER SUPLAI BARANG MASUK");
        suplayThisDay();
        hariIni();
        this.setResizable(false);
    }
    
    public void suplayThisDay(){
        int todBrg = 0;
        int todHrg = 0;
        DefaultTableModel model =new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("HARGA BELI");
        model.addColumn("JUMLAH BARANG");
        model.addColumn("TOTAL HARGA BELI");
        model.addColumn("WAKTU");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        String sql = "SELECT id_barang, nama_barang, harga_beli, SUM(jml_barang) "
                + "AS Total ,SUM(tod_hrgSup) ,waktu FROM log_sup WHERE DATE(waktu)= "
                + "CURDATE() GROUP BY id_barang;";
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm  = conn.prepareStatement(sql);
            java.sql.ResultSet rs =stm.executeQuery();
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),"+"+rs.getString(4),rs.getString(5),rs.getString(6)});
                todHrg += Integer.parseInt(String.valueOf(rs.getString(5)));
                todBrg += Integer.parseInt(String.valueOf(rs.getString(4)));
                
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                
                String idr = kursIndonesia.format(Integer.parseInt(String.valueOf(todHrg)));
                System.out.println(idr);
                
                setBrg.setText(String.valueOf(todBrg+ " pcs"));
                setHrg.setText(String.valueOf(idr));
               
            }
            jTable1.setModel(model);
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void CariData(){
        int todBrg = 0;
        int todHrg = 0;
        DefaultTableModel model =new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("HARGA BELI");
        model.addColumn("JUMLAH BARANG");
        model.addColumn("TOTAL HARGA BELI");
        model.addColumn("WAKTU");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        String sql = "SELECT id_barang, nama_barang, harga_beli, SUM(jml_barang) "
                + "AS Total ,tod_hrgSup ,waktu FROM log_sup WHERE waktu BETWEEN '"+tgl+" "+tgl1.getText()+"' AND '"+tgl+" "+ tgl2.getText()+"' GROUP BY id_barang;";
        System.out.println(sql);
        try{
            java.sql.Connection conn= (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                todHrg += Integer.parseInt(String.valueOf(rs.getString(5)));
                todBrg += Integer.parseInt(String.valueOf(rs.getString(4)));
                
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                
                String idr = kursIndonesia.format(Integer.parseInt(String.valueOf(todHrg)));
                System.out.println(idr);
                setBrg.setText(String.valueOf(todBrg+" pcs"));
                setHrg.setText(String.valueOf(idr));
            }
            jTable1.setModel(model);
            
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }
    
    public void hariIni(){
        Date hari_ini = new Date();
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        tgl = fm.format(hari_ini);
        System.out.println(tgl);
        tglHariIni.setText(tgl);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tglHariIni = new javax.swing.JLabel();
        tgl2 = new javax.swing.JTextField();
        tgl1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        setHrg = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        setBrg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 640, 180));

        tglHariIni.setText("jLabel1");
        getContentPane().add(tglHariIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        tgl2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(tgl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 70, 40));

        tgl1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(tgl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 70, 40));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 40, 40));

        setHrg.setEditable(false);
        getContentPane().add(setHrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 140, -1));

        jLabel1.setText("TOTAL HARGA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, -1, -1));

        jLabel2.setText("JUMLAH BARANG");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        setBrg.setEditable(false);
        getContentPane().add(setBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 140, -1));

        jLabel3.setText("TANGGAL : ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 106, 20, 20));

        jLabel5.setText("PUKUL");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel6.setText("PUKUL");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-reset-24.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 40, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        CariData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        suplayThisDay();
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
            java.util.logging.Logger.getLogger(Admin_LogSuplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_LogSuplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_LogSuplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_LogSuplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_LogSuplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField setBrg;
    private javax.swing.JTextField setHrg;
    private javax.swing.JTextField tgl1;
    private javax.swing.JTextField tgl2;
    private javax.swing.JLabel tglHariIni;
    // End of variables declaration//GEN-END:variables
}
