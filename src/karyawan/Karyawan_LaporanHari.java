
package karyawan;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksiDB.koneksi;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class Karyawan_LaporanHari extends javax.swing.JFrame {

    public Karyawan_LaporanHari(String nama) {
        initComponents();
        this.setTitle("LAPORAN HARI");
        tampilanData();
        pembatasan();
        SetNama(nama);
        
    }
     public void SetNama(String user){
        userr.setText(user);
    }
    
    private void pembatasan(){
        txt_plg.setEditable(false);
        txt_brg.setEditable(false);
        txt_pndptn.setEditable(false);
    }
    
    public void konversiRupiah(String harga){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

    
        String idr = kursIndonesia.format(Integer.parseInt(harga));
        //System.out.println(idr);
        //set text jLabel.setText(idr)
    }
    private void lebar_tabelbarang(){
   
        TableColumn kolom;
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN); 
        kolom =  jTable1.getColumnModel().getColumn(0); 
        kolom.setPreferredWidth(50);
        kolom =  jTable1.getColumnModel().getColumn(1); 
        kolom.setPreferredWidth(210); 
        kolom =  jTable1.getColumnModel().getColumn(2); 
        kolom.setPreferredWidth(70); 
        kolom =  jTable1.getColumnModel().getColumn(3); 
        kolom.setPreferredWidth(80);
        kolom =  jTable1.getColumnModel().getColumn(4); 
        kolom.setPreferredWidth(190); 
        kolom =  jTable1.getColumnModel().getColumn(5); 
        kolom.setPreferredWidth(180); 

       
    }
    private void tampilanDataHariAntara(){
        int totalPembeli = 0;
        int totalBrg = 0;
        int totalPendapatan = 0;
        String idr = "";
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Hari");
        model.addColumn("Total Barang");
        model.addColumn("Total Harga");
        model.addColumn(" ID Karyawan");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl1 = String.valueOf(fm.format(jDateChooser3.getDate()));
        String tgl2 = String.valueOf(fm.format(jDateChooser2.getDate()));
        
        String sql= "SELECT transaksi.id_trans,transaksi.tgl_transaksi,DAYNAME(transaksi.tgl_transaksi),transaksi.total_brg,transaksi.total_hrg,karyawan.nama_krywn FROM transaksi JOIN karyawan ON karyawan.idkrywn = transaksi.idkrywn WHERE DATE(tgl_transaksi) BETWEEN '"+tgl2+"' AND '"+tgl1+"'";
        System.out.println(sql);
        
        
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
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
                String idr1 = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(5))));
                
                //Masukkan data pada table
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),idr1,rs.getString(6)});
                totalPembeli++;
                totalBrg += Integer.parseInt(rs.getString(4));
                totalPendapatan += Integer.parseInt(rs.getString(5));
                
                //set penghasilan ke kurs rupiah
                idr = kursIndonesia.format(Integer.parseInt(String.valueOf(totalPendapatan)));
                
                    
                    txt_plg.setText(totalPembeli+ " Orang");
                    txt_brg.setText(String.valueOf(totalBrg) +  " Pcs");
                    txt_pndptn.setText( String.valueOf(idr));

            }
            jTable1.setModel(model);
            lebar_tabelbarang();
        
        }catch(Exception e){
            e.getMessage();
            JOptionPane.showMessageDialog(this, e.getMessage());
        
        }
    
    }
    
    private void tampilanDataHari(){
        int totalPembeli = 0;
        int totalBrg = 0;
        int totalPendapatan = 0;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Hari");
        model.addColumn("Total Barang");
        model.addColumn("Total Harga");
        model.addColumn(" ID Karyawan");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(jDateChooser1.getDate()));
        
        String sql= "SELECT transaksi.id_trans,transaksi.tgl_transaksi,DAYNAME(transaksi.tgl_transaksi),transaksi.total_brg,transaksi.total_hrg,karyawan.nama_krywn FROM transaksi JOIN karyawan ON karyawan.idkrywn = transaksi.idkrywn WHERE DATE(tgl_transaksi) = '"+tgl+"'; ";
        System.out.println(sql);
        
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                
                //fitur format
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                
                //format rupiah pada table
                String idr1 = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(5))));
                
                //menambahkan data pada table
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),idr1,rs.getString(6)});
                totalPembeli++;
                totalBrg += Integer.parseInt(rs.getString(4));
                totalPendapatan += Integer.parseInt(rs.getString(5));
                
                 //set penghasilan ke kurs rupiah
                String idr = kursIndonesia.format(Integer.parseInt(String.valueOf(totalPendapatan)));
                
                txt_plg.setText(totalPembeli+ " Orang");
                txt_brg.setText(String.valueOf(totalBrg) +  " Pcs");
                txt_pndptn.setText(String.valueOf(idr));
                
               
                    txt_plg.setText(totalPembeli+ " Orang");
                    txt_brg.setText(String.valueOf(totalBrg) +  " Pcs");
                    txt_pndptn.setText( String.valueOf(idr));
                
            
            }
            jTable1.setModel(model);
            lebar_tabelbarang();
            
        }catch(Exception e){
            e.getMessage();
          
        
        }
    
    }
    
    private void tampilanData(){
        int totalPembeli = 0;
        int totalBrg = 0;
        int totalPendapatan = 0;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Hari");
        model.addColumn("Total Barang");
        model.addColumn("Total Harga");
        model.addColumn(" ID Karyawan");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        
        String sql= "SELECT transaksi.id_trans,transaksi.tgl_transaksi,DAYNAME(transaksi.tgl_transaksi),transaksi.total_brg,transaksi.total_hrg,karyawan.nama_krywn FROM transaksi JOIN karyawan ON karyawan.idkrywn = transaksi.idkrywn; ";
        System.out.println(sql);
        
        
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                
                //fitur format
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                
                //format rupiah pada table
                String idr1 = kursIndonesia.format(Integer.parseInt(String.valueOf(rs.getString(5))));
                
                //menambahkan data pada table
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),idr1,rs.getString(6)});
                totalPembeli++;
                totalBrg += Integer.parseInt(rs.getString(4));
                totalPendapatan += Integer.parseInt(rs.getString(5));
                
                //set penghasilan ke kurs rupiah
                String idr = kursIndonesia.format(Integer.parseInt(String.valueOf(totalPendapatan)));
                
                
                txt_plg.setText(totalPembeli+ " Orang");
                txt_brg.setText(String.valueOf(totalBrg) +  " Pcs");
                txt_pndptn.setText(String.valueOf(idr));
               
                
            
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_brg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_plg = new javax.swing.JTextField();
        txt_pndptn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_cariHariAntara = new javax.swing.JButton();
        btn_cariHari = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        btn_print = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TOTAL PENDAPATAN PERHARI");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 600, -1));

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
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 780, 260));
        getContentPane().add(txt_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, 110, 30));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("TOTAL BARANG");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, -1, 30));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("TOTAL PELANGGAN");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, -1, 30));
        getContentPane().add(txt_plg, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 110, 30));

        txt_pndptn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pndptnActionPerformed(evt);
            }
        });
        getContentPane().add(txt_pndptn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 510, 200, 30));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("TOTAL PENDAPATAN");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 510, -1, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-back-24.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 40, 40));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("TANGGAL");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, -1, -1));

        btn_cariHariAntara.setBackground(new java.awt.Color(255, 255, 255));
        btn_cariHariAntara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        btn_cariHariAntara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariHariAntaraActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cariHariAntara, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, -1, 30));

        btn_cariHari.setBackground(new java.awt.Color(255, 255, 255));
        btn_cariHari.setForeground(new java.awt.Color(255, 255, 255));
        btn_cariHari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        btn_cariHari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariHariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cariHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, 30));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SAMPAI");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 196, 70, 20));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("DATA PERHARI");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("TANGGAL");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 101, 132));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-reset-24.png"))); // NOI18N
        jButton2.setText(" SEMUA DATA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 140, 40));

        jButton3.setBackground(new java.awt.Color(255, 101, 132));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-order-history-24.png"))); // NOI18N
        jButton3.setText("HISTORY");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 120, 40));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 110, 30));

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 120, 30));

        jDateChooser3.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 189, 120, 30));

        userr1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userr1.setForeground(new java.awt.Color(255, 255, 255));
        userr1.setText("ID Admin :");
        getContentPane().add(userr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        userr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userr.setForeground(new java.awt.Color(255, 255, 255));
        userr.setText("jLabel1");
        getContentPane().add(userr, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        btn_print.setBackground(new java.awt.Color(255, 255, 255));
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-print-24.png"))); // NOI18N
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 40, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/laporanHarian.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariHariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariHariActionPerformed
        // TODO add your handling code here:
       
            tampilanDataHari();
            jDateChooser1.setDate(null);
        
        
    }//GEN-LAST:event_btn_cariHariActionPerformed

    private void btn_cariHariAntaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariHariAntaraActionPerformed
        // TODO add your handling code here:
            tampilanDataHariAntara();
            
            jDateChooser2.setDate(null);
            jDateChooser3.setDate(null);
            
    }//GEN-LAST:event_btn_cariHariAntaraActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tampilanData();
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jDateChooser3.setDate(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Karyawan_LaporanSwitch(userr.getText()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_pndptnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pndptnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pndptnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new History_Transaksi().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
 
    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        try {
            String tampilan = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggalan12 =String.valueOf(fm.format(jDateChooser2.getDate()));
            String tanggalan13 =String.valueOf(fm.format(jDateChooser3.getDate()));
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/appbaju","root","");
            String NamaFile = "C:\\Users\\Windows 10\\Documents\\NetBeansProjects\\Efashion_Final\\src\\cetak\\Laporan.jrxml";

            HashMap parameter = new HashMap () ;
            parameter.put ("hari1", tanggalan12);
            System.out.println(tanggalan12);
            
//            System.out.println("TANGGAL = "+jDateChooser2.getDate());
            
            parameter.put ("hari2", tanggalan13) ;
            System.out.println(tanggalan13);
            
            JasperReport Jupe = JasperCompileManager.compileReport(NamaFile);
            JasperPrint jp = JasperFillManager.fillReport(Jupe, parameter,con);
          
            JasperViewer.viewReport( jp, false);
         
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak"
            +"\n"+e.getMessage(), "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        
    }//GEN-LAST:event_btn_printActionPerformed

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
            java.util.logging.Logger.getLogger(Karyawan_LaporanHari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan_LaporanHari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan_LaporanHari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan_LaporanHari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Karyawan_LaporanHari(userr.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cariHari;
    private javax.swing.JButton btn_cariHariAntara;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_brg;
    private javax.swing.JTextField txt_plg;
    private javax.swing.JTextField txt_pndptn;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    public static final javax.swing.JLabel userr1 = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
