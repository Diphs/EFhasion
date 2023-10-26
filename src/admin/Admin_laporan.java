/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import koneksiDB.koneksi;

/**
 *
 * @author kitan
 */
public class Admin_laporan extends javax.swing.JFrame {

    /**
     * Creates new form adm_laporan
     */
    public Admin_laporan(String nama) throws SQLException {
        initComponents();
        setComboBoxBulan();
         this.setTitle("KELOLA LAPORAN ADMIN");
     
        txt_totbarang.setEditable(false);
        txt_tothargabarang.setEditable(false);
        SetNama(nama);
        hari1.setVisible(false);
        hari2.setVisible(false);
        combobox_bulang.setVisible(false);
        cariku.setVisible(false);
        userId.setVisible(false);
        System.out.println(userId.getText());

        
       
        
    }
  public void SetNama(String user){
        userr.setText(user);
    }
  
  private void lebar_tabelbarang(){
   
        TableColumn kolom;
        table_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN); 
        kolom = table_laporan.getColumnModel().getColumn(0); 
        kolom.setPreferredWidth(80);
        kolom = table_laporan.getColumnModel().getColumn(1); 
        kolom.setPreferredWidth(266); 
        kolom = table_laporan.getColumnModel().getColumn(2); 
        kolom.setPreferredWidth(128); 
        kolom = table_laporan.getColumnModel().getColumn(3); 
        kolom.setPreferredWidth(158);
        kolom = table_laporan.getColumnModel().getColumn(4); 
        kolom.setPreferredWidth(158);
    
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table_laporan = new javax.swing.JTable();
        combobox_bulang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_totbarang = new javax.swing.JTextField();
        txt_tothargabarang = new javax.swing.JTextField();
        btn_export1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        hari1 = new com.toedter.calendar.JDateChooser();
        hari2 = new com.toedter.calendar.JDateChooser();
        cariku = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt_hari = new javax.swing.JButton();
        txt_bulan = new javax.swing.JButton();
        userId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_laporan.setBackground(new java.awt.Color(255, 255, 255));
        table_laporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_laporan.setGridColor(new java.awt.Color(0, 0, 0));
        table_laporan.setRowHeight(25);
        table_laporan.setSelectionBackground(new java.awt.Color(255, 0, 102));
        table_laporan.setSelectionForeground(new java.awt.Color(255, 255, 255));
        table_laporan.setShowVerticalLines(false);
        table_laporan.getTableHeader().setReorderingAllowed(false);
        table_laporan.setUpdateSelectionOnSort(false);
        table_laporan.setVerifyInputWhenFocusTarget(false);
        table_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_laporanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_laporan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 790, 300));

        combobox_bulang.setBackground(new java.awt.Color(255, 255, 255));
        combobox_bulang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combobox_bulang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combobox_bulangItemStateChanged(evt);
            }
        });
        combobox_bulang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combobox_bulangMouseClicked(evt);
            }
        });
        combobox_bulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox_bulangActionPerformed(evt);
            }
        });
        getContentPane().add(combobox_bulang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 130, 30));

        jLabel2.setText("Total Barang");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 80, -1));

        jLabel3.setText("Total Pendapatan");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, -1, -1));

        this.txt_totbarang.setEditable(false);
        getContentPane().add(txt_totbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 90, 30));

        txt_tothargabarang.setEditable(false);
        getContentPane().add(txt_tothargabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 170, 30));

        btn_export1.setBackground(new java.awt.Color(102, 153, 255));
        btn_export1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_export1.setForeground(new java.awt.Color(255, 255, 255));
        btn_export1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-print-24.png"))); // NOI18N
        btn_export1.setText("EXPORT.XLS");
        btn_export1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_export1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, 170, 50));

        jButton5.setBackground(new java.awt.Color(102, 153, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-back-24.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 50, 40));

        userr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userr.setForeground(new java.awt.Color(255, 255, 255));
        userr.setText("jLabel2");
        getContentPane().add(userr, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 35, 160, 30));

        btn_hapus.setBackground(new java.awt.Color(102, 153, 255));
        btn_hapus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-delete-24.png"))); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 120, 50));
        getContentPane().add(hari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 130, 30));
        getContentPane().add(hari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 130, 30));

        cariku.setBackground(new java.awt.Color(255, 255, 255));
        cariku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        cariku.setBorder(null);
        cariku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carikuActionPerformed(evt);
            }
        });
        getContentPane().add(cariku, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 40, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 80, 290));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 330, 200));

        txt_hari.setBackground(new java.awt.Color(255, 101, 132));
        txt_hari.setForeground(new java.awt.Color(255, 255, 255));
        txt_hari.setText("HARI");
        txt_hari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 120, 50));

        txt_bulan.setBackground(new java.awt.Color(255, 101, 132));
        txt_bulan.setForeground(new java.awt.Color(255, 255, 255));
        txt_bulan.setText("BULAN");
        txt_bulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bulanActionPerformed(evt);
            }
        });
        getContentPane().add(txt_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 120, 50));

        userId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userIdActionPerformed(evt);
            }
        });
        getContentPane().add(userId, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 120, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/adm_laporan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setComboBoxBulan(){
        DefaultComboBoxModel model_combobox = new DefaultComboBoxModel(); 
        model_combobox.addElement("Januari");
        model_combobox.addElement("Febuary");
        model_combobox.addElement("Maret");
        model_combobox.addElement("April");
        model_combobox.addElement("Mei");
        model_combobox.addElement("Juni");
        model_combobox.addElement("Juli");
        model_combobox.addElement("Agustus");
        model_combobox.addElement("September");
        model_combobox.addElement("Oktober");
        model_combobox.addElement("November");
        model_combobox.addElement("Desember");
        this.combobox_bulang.setModel(model_combobox);

        this.combobox_bulang.setModel(model_combobox);
        
    }
     public String  GetkonversiRupiah(String harga){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        String idr = kursIndonesia.format(Integer.parseInt(harga));
        //System.out.println(idr);
        //set text jLabel.setText(idr)
        return idr;
    }
    
    public void Harian(){
       DefaultTableModel tbmBulan = new DefaultTableModel();
       tbmBulan.addColumn("id transaksi");
       tbmBulan.addColumn("tanggal transaksi");
       tbmBulan.addColumn("total barang");
       tbmBulan.addColumn("harga");
       tbmBulan.addColumn("id karyawan");
        table_laporan.getTableHeader().setBackground(new Color(102,153,255));
        table_laporan.getTableHeader().setForeground(Color.WHITE);
        table_laporan.getTableHeader().setPreferredSize(new Dimension(20,25));
       
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl1 = String.valueOf(fm.format(hari1.getDate()));
        String tgl2 = String.valueOf(fm.format(hari2.getDate()));
       try {
               String query= "SELECT * FROM `detail_transaksi` JOIN `transaksi` ON `detail_transaksi`.`id_trans` = `transaksi`.`id_trans` WHERE DATE(`tgl_transaksi`) BETWEEN '"+tgl1+"' AND '"+tgl2+"'";
               java.sql.Connection conn = (Connection)koneksi.configDB();
               java.sql.PreparedStatement prs = conn.prepareCall(query);
               java.sql.ResultSet rs = prs.executeQuery();
               int totalHargaBulan = 0;
               int totalBarangBulan = 0;
               while(rs.next()){
                   tbmBulan.addRow(new Object[]{
                        rs.getString("id_trans"),
               rs.getString("tgl_transaksi"),
               rs.getString("total_brg"),
               rs.getString("total_hrg"),
               rs.getString("idkrywn"),
                   });
                   totalHargaBulan += Integer.parseInt(rs.getString("total_hrg"));
                   totalBarangBulan += Integer.parseInt(rs.getString("total_brg"));
               }
               this.txt_tothargabarang.setText(String.valueOf(totalHargaBulan));
               this.txt_totbarang.setText(String.valueOf(totalBarangBulan));
              
               this.table_laporan.setModel(tbmBulan);
               lebar_tabelbarang();
           } catch (SQLException ex) {
               Logger.getLogger(Admin_laporan.class.getName()).log(Level.SEVERE, null, ex);
           }
       
    }
    private void loadModel(){
       DefaultTableModel tbmDefault = new DefaultTableModel();
       tbmDefault.addColumn("id transaksi");
       tbmDefault.addColumn("tanggal transaksi");
       tbmDefault.addColumn("total barang");
       tbmDefault.addColumn("harga");
       tbmDefault.addColumn("id karyawan");
       table_laporan.getTableHeader().setBackground(new Color(102,153,255));
       table_laporan.getTableHeader().setForeground(Color.WHITE);
       table_laporan.getTableHeader().setPreferredSize(new Dimension(20,25));
  
//       
//     String query= "SELECT * FROM `detail_transaksi` JOIN `transaksi` ON `detail_transaksi`.`id_trans` = `transaksi`.`id_trans`";
//          
//       java.sql.Connection conn = (Connection) koneksi.configDB();
//        java.sql.PreparedStatement prs = conn.prepareStatement(query);
//       java.sql.ResultSet rs = prs.executeQuery();
//       int total_BarangDefault =  0;
//       int total_hargaBarang = 0;
//       while(rs.next()){
//           tbmDefault.addRow(new Object[]{
//               rs.getString("id_trans"),
//               rs.getString("tgl_transaksi"),
//               rs.getString("total_brg"),
//               rs.getString("total_hrg"),
//               rs.getString("idkrywn"),
//           });
//           total_BarangDefault += Integer.parseInt(rs.getString("total_brg"));
//           total_hargaBarang += Integer.parseInt(rs.getString("total_hrg"));
//       }
       table_laporan.setModel(tbmDefault);
       lebar_tabelbarang();
       txt_totbarang.setText("");
       txt_tothargabarang.setText("");
       hari1.setDate(null);
       hari2.setDate(null);
//       System.out.println("total_barang = " + total_BarangDefault);
//       System.out.println("total_hargaBarang = " + total_hargaBarang);
    
       
    }
    private void fetchLaporanHarian(){
        
    }
    private void txt_hariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hariActionPerformed
        // TODO add your handling code here
        combobox_bulang.setVisible(false);
        hari1.setVisible(true);
        hari2.setVisible(true);
        cariku.setVisible(true);
        loadModel();
    }//GEN-LAST:event_txt_hariActionPerformed

    private void combobox_bulangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combobox_bulangItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_combobox_bulangItemStateChanged

    private void combobox_bulangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combobox_bulangMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_combobox_bulangMouseClicked

    private void combobox_bulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox_bulangActionPerformed
        // TODO add your handling code here:
       String getBulan = this.combobox_bulang.getItemAt(this.get_numBulan(this.combobox_bulang.getSelectedItem().toString()) -1);
       DefaultTableModel tbmBulan = new DefaultTableModel();
       tbmBulan.addColumn("id transaksi");
       tbmBulan.addColumn("tanggal transaksi");
       tbmBulan.addColumn("total barang");
       tbmBulan.addColumn("harga");
       tbmBulan.addColumn("id karyawan");
       
      int bulan = get_numBulan(this.combobox_bulang.getSelectedItem().toString());
      if(bulan == 0){
          JOptionPane.showMessageDialog(null,"bulan hanya 1 - 12");
      } else {
           try {
               String query= "SELECT * FROM `detail_transaksi` JOIN `transaksi` ON `detail_transaksi`.`id_trans` = `transaksi`.`id_trans` WHERE MONTH(`tgl_transaksi`) = " + bulan + "";
               java.sql.Connection conn = (Connection)koneksi.configDB();
               java.sql.PreparedStatement prs = conn.prepareCall(query);
               java.sql.ResultSet rs = prs.executeQuery();
               int totalHargaBulan = 0;
               int totalBarangBulan = 0;
               while(rs.next()){
                   tbmBulan.addRow(new Object[]{
                        rs.getString("id_trans"),
               rs.getString("tgl_transaksi"),
               rs.getString("total_brg"),
               rs.getString("total_hrg"),
               rs.getString("idkrywn"),
                   });
                   totalHargaBulan += Integer.parseInt(rs.getString("total_hrg"));
                   totalBarangBulan += Integer.parseInt(rs.getString("total_brg"));
               }
               this.txt_tothargabarang.setText(String.valueOf(totalHargaBulan));
               this.txt_totbarang.setText(String.valueOf(totalBarangBulan));
               this.table_laporan.setModel(tbmBulan);
               lebar_tabelbarang();
           } catch (SQLException ex) {
               Logger.getLogger(Admin_laporan.class.getName()).log(Level.SEVERE, null, ex);
           }
          
      }
    }//GEN-LAST:event_combobox_bulangActionPerformed

    private void btn_export1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export1ActionPerformed
        // TODO add your handling code here:
        openSelection();
    }//GEN-LAST:event_btn_export1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Admin_Fitur(userr.getText()).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        if(userId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "PILIH DATA DULU");
        } else {
        String tableTrans = "DELETE FROM transaksi WHERE id_trans = '"+userId.getText()+"'";
        String tableDetailTrans = "DELETE FROM detail_transaksi WHERE id_trans = '"+userId.getText()+"'";
        String logTrans = "DELETE FROM log_trans WHERE id_trans = '"+userId.getText()+"'";
        System.out.println(tableTrans);
        System.out.println(tableDetailTrans);
        System.out.println(logTrans);
        
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(tableDetailTrans);
            java.sql.PreparedStatement stm1 = conn.prepareStatement(tableTrans);
            java.sql.PreparedStatement stm2 = conn.prepareStatement(logTrans);
            stm.execute();
            stm1.execute();
            stm2.execute();
            JOptionPane.showMessageDialog(this, "BERHASIL DI HAPUS");
            loadModel();
            
        
        }catch(Exception e){
            e.getMessage();
        }
        }
        
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void table_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_laporanMouseClicked
        // TODO add your handling code here:
        int baris = table_laporan.rowAtPoint(evt.getPoint());
        
        if(table_laporan.getValueAt(baris, 0)==null){
            userId.setText("");
        } else {
            userId.setText(table_laporan.getValueAt(baris, 0).toString());
        }
        
    }//GEN-LAST:event_table_laporanMouseClicked

    private void txt_bulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bulanActionPerformed
        // TODO add your handling code here:
        hari1.setVisible(false);
        hari2.setVisible(false);
        cariku.setVisible(false);
        combobox_bulang.setVisible(true);
        
        loadModel();
    }//GEN-LAST:event_txt_bulanActionPerformed

    private void userIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userIdActionPerformed

    private void carikuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carikuActionPerformed
        // TODO add your handling code here:
        Harian();
    }//GEN-LAST:event_carikuActionPerformed
public void openSelection(){

    JFileChooser path = new JFileChooser();
    path.showOpenDialog(this);
    String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
    try {
        File f = path.getSelectedFile();
        String location = f.getAbsolutePath();
        String filename = location + "_" + date + ".xls";
           JOptionPane.showMessageDialog(null, filename);
           File fp = new File(filename);
           
        RubahKeExcel(this.table_laporan, fp);
        
        JOptionPane.showMessageDialog(null, "DATA BERHASIL DI EXPORT");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
public void RubahKeExcel(javax.swing.JTable table, File file) {
        try {
            DefaultTableModel Model_Data = (DefaultTableModel) table.getModel();
            FileWriter ObjWriter = new FileWriter(file);
            for (int i = 0; i < Model_Data.getColumnCount(); i++) {
                ObjWriter.write(Model_Data.getColumnName(i) + "\t");
            }

            ObjWriter.write("\n");

            for (int i = 0; i < Model_Data.getRowCount(); i++) {
                for (int j = 0; j < Model_Data.getColumnCount(); j++) {
                    ObjWriter.write(Model_Data.getValueAt(i, j).toString() + "\t");
                }
                ObjWriter.write("\n");
            }

            ObjWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private int get_numBulan(String month){
        if(month.equals("Januari")){
            return 1;
        } else if(month.equals("Febuary")){
            return 2;
        } else if(month.equals("Maret")){
            return 3;
        } else if(month.equals("April")){
            return 4;
        } else if(month.equals("Mei")){
            return 5; 
        } else if(month.equals("Juni")){
            return 6;
        } else if(month.equals("Juli")){
            return 7;
        } else if(month.equals("Agustus")){
            return 8;
        } else if(month.equals("September")){
            return 9;
        } else if(month.equals("Oktober")){
            return 10;
        } else if(month.equals("November")){
            return 11;
        } else if(month.equals("Desember")){
            return 12;
        }
        return 0;
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
            java.util.logging.Logger.getLogger(Admin_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Admin_laporan(userr.getText()).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_laporan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_export1;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton cariku;
    private javax.swing.JComboBox<String> combobox_bulang;
    private com.toedter.calendar.JDateChooser hari1;
    private com.toedter.calendar.JDateChooser hari2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_laporan;
    private javax.swing.JButton txt_bulan;
    private javax.swing.JButton txt_hari;
    private javax.swing.JTextField txt_totbarang;
    private javax.swing.JTextField txt_tothargabarang;
    private javax.swing.JTextField userId;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
