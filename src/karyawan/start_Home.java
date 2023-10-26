
package karyawan;

import Setting.Setting_BackUpDb;
import admin.Admin_Fitur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import org.jfree.data.category.DefaultCategoryDataset; 
 import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
 import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.general.DefaultPieDataset;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import karyawan.Karyawan_ListDataSuplier;
import karyawan.Karyawan_LaporanBulan;
import koneksiDB.koneksi;
import login.LoginApp;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author Daffa Aditya Rejasa Ruswanto
 */
public class start_Home extends javax.swing.JFrame {

    static String namaUser;
    static String polosan;

    
    public start_Home(String nama) {
        initComponents();
        this.setTitle("DASHBOARD");
        TampilanHarian();
        polosan = nama;
        setName(nama);
        userrNamaAdmin.setText("Halo.. Selamat Datang "+namaUser);
        System.out.println(nama);
        rnd.setVisible(false);
        pagi.setVisible(false);
        Siang.setVisible(false);
        Sore.setVisible(false);
        Malam.setVisible(false);
        Ucapan();
        
    }
  
    public void Ucapan(){
        Calendar c = Calendar.getInstance();
        int timeOfday = c.get(Calendar.HOUR_OF_DAY);
        
        if(timeOfday >=0 && timeOfday <12){
            System.out.println("Good Morning");
            pagi.setVisible(true);
        } else if (timeOfday >=12 && timeOfday <16){
            System.out.println("Good Afternoon");
             Siang.setVisible(true);
        } else if(timeOfday >=16 && timeOfday <21){
            System.out.println("Good evening");
            Sore.setVisible(true);
        } else if (timeOfday >=21 && timeOfday <24){
            System.out.println("Good Night");
            Malam.setVisible(true);
        }
    }
    
    public void setName(String user){
       String namaKrywn = "SELECT `login`.`username`,`login`.`password`,`karyawan`.`nama_krywn`,`karyawan`.`type` FROM `login` JOIN `karyawan` ON `karyawan`.`idkrywn` = `login`.`idkrywn` WHERE username = '"+user+"'";
       try{
           java.sql.Connection conn = (Connection) koneksi.configDB();
           java.sql.PreparedStatement stm = conn.prepareStatement(namaKrywn);
           java.sql.ResultSet rs = stm.executeQuery();
           while(rs.next()){
               
               
               if(rs.getString(4).equals("Karyawan")){
                   btn_dashboard.setVisible(false);
               }
               String nama1 = rs.getString(3);
               namaUser = nama1;
              
               
           }
       
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       }
        
    }

    private void TampilanHarian(){
        int hariIni = 0;
        int totalBaju = 0;
        int totalPembeli = 0;
        String sql = "Select * From transaksi where date(tgl_transaksi) = CURDATE()";
        System.out.println(sql);
        try{
            Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery();
            
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);
                
            while(res.next()){
                hariIni += Integer.parseInt(res.getString(4));
                String idr = kursIndonesia.format(hariIni);
                totalBaju += Integer.parseInt(res.getString(3));
                totalPembeli++;
                setHari.setText(idr);
                totalBrg.setText(String.valueOf(totalBaju)+" Pcs");
                totalPmbl.setText(String.valueOf(totalPembeli)+" Org");
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    public static ChartPanel frameBarchart;
    public static ChartPanel frameLinechart;
    public static ChartPanel frameAreaChart;
    public static ChartFrame frameBar;
    public static ChartFrame frameLine;
    public static ChartFrame frameArea;
    
    private void Barchart12(){
        try{
             String sql = "SELECT MONTH(tgl_transaksi), DAY(tgl_transaksi), SUM(total_hrg) from transaksi WHERE YEAR(CURDATE()) GROUP BY MONTH(tgl_transaksi);";
             JDBCCategoryDataset dataset = new JDBCCategoryDataset (koneksi.configDB(),sql);
             
             DefaultCategoryDataset dastaset = dataset;
             java.sql.Connection conn = (Connection) koneksi.configDB();
             java.sql.PreparedStatement stm = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stm.executeQuery(sql);
        
            
        JFreeChart chart1 = ChartFactory.createBarChart("Data", "Bulan", "Omset", dataset,PlotOrientation.VERTICAL,false,true,true);
        frameBarchart = new ChartPanel(chart1);
        CategoryPlot p = chart1.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        frameBar = new ChartFrame("bar Chart",chart1);
        frameBar.setVisible(true);
        frameBar.setSize(450,350);
        frameBar.setLocationRelativeTo(null);
           
        }catch(Exception e){
            e.printStackTrace();
        
        }
       
    }
    
    private void LineChart(){
        try{
            String sql = "SELECT MONTH(tgl_transaksi), SUM(total_hrg) from transaksi WHERE YEAR(CURDATE()) GROUP BY MONTH(tgl_transaksi);";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset (koneksi.configDB(),sql);
            
             DefaultCategoryDataset dastaset = dataset;
             java.sql.Connection conn = (Connection) koneksi.configDB();
             java.sql.PreparedStatement stm = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stm.executeQuery(sql);
             
             JFreeChart chart1 = ChartFactory.createLineChart("Data", "Bulan", "Omset", dataset,PlotOrientation.VERTICAL,false,true,true);
             frameLinechart = new ChartPanel(chart1);
             CategoryPlot p = chart1.getCategoryPlot();
             p.setRangeGridlinePaint(Color.black);
             frameLine = new ChartFrame("Line Chart",chart1);
             frameLine.setVisible(true);
             frameLine.setSize(450,350);
             frameLine.setLocationRelativeTo(null);
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void AreaChart(){
    try{
        String sql = "SELECT MONTH(tgl_transaksi), DAY(tgl_transaksi), SUM(total_hrg) from  transaksi WHERE YEAR(CURDATE()) GROUP BY MONTH(tgl_transaksi);";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset (koneksi.configDB(),sql);
            
             DefaultCategoryDataset dastaset = dataset;
             java.sql.Connection conn = (Connection) koneksi.configDB();
             java.sql.PreparedStatement stm = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stm.executeQuery(sql);
             
             JFreeChart chart1 = ChartFactory.createAreaChart("Data", "Bulan", "Omset", dataset,PlotOrientation.VERTICAL,false,true,true);
             frameAreaChart= new ChartPanel(chart1);
             CategoryPlot p = chart1.getCategoryPlot();
             p.setRangeGridlinePaint(Color.black);
             frameArea = new ChartFrame("Area Chart",chart1);
             frameArea.setVisible(true);
             frameArea.setSize(450,350);
             frameArea.setLocationRelativeTo(null);
             
    }catch(Exception e){
        e.printStackTrace();
    }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_home = new javax.swing.JButton();
        btn_report = new javax.swing.JButton();
        btn_Suplier = new javax.swing.JButton();
        btn_trans = new javax.swing.JButton();
        setHari = new javax.swing.JLabel();
        totalPmbl = new javax.swing.JLabel();
        totalBrg = new javax.swing.JLabel();
        btn_report1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        btn_dashboard = new javax.swing.JButton();
        rnd = new javax.swing.JPanel();
        Malam = new javax.swing.JLabel();
        Sore = new javax.swing.JLabel();
        Siang = new javax.swing.JLabel();
        pagi = new javax.swing.JLabel();
        logOut = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        HomeBaru = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_home.setBackground(new java.awt.Color(51, 51, 255));
        btn_home.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_home.setForeground(new java.awt.Color(255, 255, 255));
        btn_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-home-24.png"))); // NOI18N
        btn_home.setText("HOME");
        btn_home.setBorder(null);
        btn_home.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_homeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 170, 40));

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

        btn_Suplier.setBackground(new java.awt.Color(51, 51, 255));
        btn_Suplier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Suplier.setForeground(new java.awt.Color(255, 255, 255));
        btn_Suplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-supplier-24.png"))); // NOI18N
        btn_Suplier.setText("SUPLIER");
        btn_Suplier.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        btn_Suplier.setContentAreaFilled(false);
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

        setHari.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        setHari.setForeground(new java.awt.Color(255, 255, 255));
        setHari.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setHari.setText("TES");
        setHari.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        setHari.setName(""); // NOI18N
        getContentPane().add(setHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 360, 50));

        totalPmbl.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        totalPmbl.setForeground(new java.awt.Color(255, 255, 255));
        totalPmbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(totalPmbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 140, 90));

        totalBrg.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        totalBrg.setForeground(new java.awt.Color(255, 255, 255));
        totalBrg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(totalBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, 140, 90));

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

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "== Pilih Model Chart ==", "Barchart", "Linechart", "Areachart" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 462, 480, 30));

        jPanel1.setBackground(new java.awt.Color(255, 101, 132));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 150, 30));

        btn_dashboard.setBackground(new java.awt.Color(255, 101, 132));
        btn_dashboard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        btn_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-dashboard-24.png"))); // NOI18N
        btn_dashboard.setText("MENU ADMIN");
        btn_dashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        btn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btn_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 170, 40));

        userrNamaAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userrNamaAdmin.setForeground(new java.awt.Color(0, 0, 0));
        userrNamaAdmin.setText("jLabel1");
        getContentPane().add(userrNamaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 480, 30));

        rnd.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(rnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 250, 60));

        Malam.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Malam.setForeground(new java.awt.Color(0, 0, 0));
        Malam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-night-50.png"))); // NOI18N
        Malam.setText("SELAMAT MALAM");
        getContentPane().add(Malam, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, -1, -1));

        Sore.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Sore.setForeground(new java.awt.Color(0, 0, 0));
        Sore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-evening-50.png"))); // NOI18N
        Sore.setText("SELAMAT SORE");
        getContentPane().add(Sore, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        Siang.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Siang.setForeground(new java.awt.Color(0, 0, 0));
        Siang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-afternoon-50.png"))); // NOI18N
        Siang.setText("SELAMAT SIANG");
        getContentPane().add(Siang, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        pagi.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        pagi.setForeground(new java.awt.Color(0, 0, 0));
        pagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-morning-50.png"))); // NOI18N
        pagi.setText("SELAMAT PAGI");
        getContentPane().add(pagi, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        logOut.setBackground(new java.awt.Color(255, 101, 132));
        logOut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logOut.setForeground(new java.awt.Color(255, 255, 255));
        logOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-log-out-32.png"))); // NOI18N
        logOut.setText("LOG OUT");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });
        getContentPane().add(logOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, 40));

        jPanel3.setBackground(new java.awt.Color(255, 101, 132));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/EfashionFrame.png"))); // NOI18N
        jPanel3.add(jLabel12);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 210, 110));

        jButton1.setBackground(new java.awt.Color(255, 101, 132));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-user-menu-female-32.png"))); // NOI18N
        jButton1.setText("PROFILE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 130, 40));

        HomeBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/HomeBaru.png"))); // NOI18N
        getContentPane().add(HomeBaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_homeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_homeActionPerformed

    private void btn_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Karyawan_LaporanSwitch(polosan).setVisible(true);
    }//GEN-LAST:event_btn_reportActionPerformed

    private void btn_SuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuplierActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Karyawan_ListDataSuplier(polosan).setVisible(true);
    }//GEN-LAST:event_btn_SuplierActionPerformed

    private void btn_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Karyawan_Transaksi(polosan).setVisible(true);
    }//GEN-LAST:event_btn_transActionPerformed

    private void btn_report1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_report1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
       new Setting_BackUpDb(polosan).setVisible(true);
    }//GEN-LAST:event_btn_report1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        try{
            
            if(evt.getStateChange()== ItemEvent.SELECTED){
                String listChart = jComboBox1.getSelectedItem().toString();
                switch(listChart){
                    
                    case "Barchart":
                        Barchart12();
                       frameLine.setVisible(false);
                       frameArea.setVisible(false);
                        break;
                        
                    case "Linechart":
                        LineChart();
                       frameBar.setVisible(false);
                       frameArea.setVisible(false);
                        break;
                        
                    case "Areachart":
                        AreaChart();
                        frameBar.setVisible(false);
                        frameLine.setVisible(false);
                        break;
                }
            
            }
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void btn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dashboardActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Admin_Fitur(polosan).setVisible(true);
    }//GEN-LAST:event_btn_dashboardActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new LoginApp().setVisible(true);
        
      
    }//GEN-LAST:event_logOutActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Karyawan_Profile(polosan).setVisible(true);
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
            java.util.logging.Logger.getLogger(start_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(start_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(start_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(start_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new start_Home(userrNamaAdmin.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HomeBaru;
    private javax.swing.JLabel Malam;
    private javax.swing.JLabel Siang;
    private javax.swing.JLabel Sore;
    private javax.swing.JButton btn_Suplier;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_home;
    private javax.swing.JButton btn_report;
    private javax.swing.JButton btn_report1;
    private javax.swing.JButton btn_trans;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logOut;
    private javax.swing.JLabel pagi;
    private javax.swing.JPanel rnd;
    private javax.swing.JLabel setHari;
    private javax.swing.JLabel totalBrg;
    private javax.swing.JLabel totalPmbl;
    public static final javax.swing.JLabel userrNamaAdmin = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
