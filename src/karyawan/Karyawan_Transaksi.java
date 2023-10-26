package karyawan;



/**
 *
 * @author Morgan
 * FiX Bug : DAFFA ADITYA REJASA RUSWANTO
 */
import Setting.Setting_BackUpDb;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.HashMap;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import koneksiDB.koneksi;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class Karyawan_Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form Hitung
     */
    String Tanggal;
    private DefaultTableModel model;
    
    public void totalJumlah(){
        int jumlahBaris = jTable1.getRowCount();
        int hargaBarang;
        int totalJumlah = 0;
        for (int i = 0; i < jumlahBaris; i++) {
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 3).toString()); 
            totalJumlah = totalJumlah + hargaBarang ;
        }
        totalbarang.setText(""+totalJumlah);
        totalbarang.setVisible(false);
        
        
    }
    String ch;
    private void filterAngka1(KeyEvent a){
        ch = String.valueOf(txHarga.getText());
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
           
        } else{
        
        }
    }
    
    private void filterChar1(KeyEvent a){
        ch = String.valueOf(txHarga.getText());
        if(!Character.isLetterOrDigit(a.getKeyChar())){
            a.consume();
           
        } else{
        
        }
    }
    private void filterAngka2(KeyEvent a){
        ch = String.valueOf(txt_bayar.getText());
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
           
        } else{
        
        }
    }
    
  
    
     public void BatasWajarInput(KeyEvent a){
        if(txIDBarang.getText().length()>=12){
            a.consume();
        }
    }
    
    public void setID(){
        String sql = "SELECT login.idkrywn , karyawan.nama_krywn FROM login JOIN karyawan ON login.idkrywn = karyawan.idkrywn WHERE username = '"+userr.getText()+"'";
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet rs = stm.executeQuery();
            while(rs.next()){
                String idKr = rs.getString(1);
                userSetId.setText(idKr);
                userNama.setText(rs.getString(2));
            }
        
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void totalBiaya(){
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txTampil.setText("Rp "+ totalBiaya +",00");
        totalhargabrg.setText(""+totalBiaya);
        totalhargabrg.setVisible(false);
    }
    
    private void autonumber(){
        try {
            String sql = "SELECT * FROM transaksi ORDER BY id_trans DESC";
            java.sql.Connection conn=(Connection)koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet r=stm.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("id_trans").substring(2);
                String TR = "" +(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if(TR.length()==1)
                {Nol = "0";}
                else if(TR.length()==2)
                {Nol = "";}
                txNoTransaksi.setText("TR" + Nol + TR);
            } else {
                txNoTransaksi.setText("TR01");
            }
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void loadData(){
        
        int totalBrgDb = 0;
        int jmlBrg = 0;
        int jmlBrg2 = Integer.parseInt(txJumlah.getText());
        
        String sql = "SELECT * FROM barang WHERE id_barang ='"+txIDBarang.getText()+"'";
        for(int i=0; i<model.getRowCount(); i++){
            if(txIDBarang.getText().equals(String.valueOf(jTable1.getValueAt(i, 1)))){
                jmlBrg += Integer.parseInt(String.valueOf(jTable1.getValueAt(i, 3)));
            }
        }
        int jmlTotal= jmlBrg + jmlBrg2;
        
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet rs = stm.executeQuery();
            while(rs.next()){
                totalBrgDb = Integer.parseInt(rs.getString(5));
            }
        
        }catch(Exception e){
            e.getMessage();
        }
        System.out.println(totalBrgDb);
        System.out.println(jmlTotal);
        if(totalBrgDb ==0){
            JOptionPane.showMessageDialog(this, "BARANG HABIS");
        
        } else if(txJumlah.getText().equals("0")){
            JOptionPane.showMessageDialog(this, "TOLONG ISI JUMLAH BARANG LEBIH DARI 1 , SEGERA ISI !");
        }
        else if(totalBrgDb<jmlTotal){
            JOptionPane.showMessageDialog(this, " STOK BARANG KURANG");
        
        } else {
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            
            String namaBarang= txNamaBarang.getText();
            boolean noMerge = true;
            if( jTable1.getRowCount() >= 1 )
        {
        	for( int i = 0; i < model.getRowCount(); i++ ) {
        	
        		String name = (String)model.getValueAt(i, 2);
        	
        		if( name.equalsIgnoreCase(namaBarang) )
        		{
        			noMerge = false;
        			// Modify the quantity of the last row
        			int oldQuantity = Integer.parseInt((String)model.getValueAt(i, 3));
        
        			int newQuantity = oldQuantity + Integer.parseInt( txJumlah.getText() );
                               // int hargaBaru = harga + Integer.parseInt(hargaBarang);
        			model.setValueAt( Integer.toString(newQuantity), i, 3);
                                String total = Integer.toString(newQuantity * Integer.parseInt(txHarga.getText()));
                                model.setValueAt(total,i,6);
                      }
        	
        	}
        }//batas
        if( noMerge ){
            
            model.addRow(new Object[]{
            txNoTransaksi.getText(),
            txIDBarang.getText(),
            txNamaBarang.getText(),
            txJumlah.getText(),
            txHarga.getText(),
            hrg_beli.getText(),
            txTampil.getText()
        });
            }
        jTable1.setModel(model);
        
        }
        
    }
    
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txNoTransaksi.setText("");
        txIDBarang.setText("");
        txNamaBarang.setText("");
        txHarga.setText("");
        hrg_beli.setText("");
        txJumlah.setText("");
        autonumber();
    }
    
    public void clear(){
        txTampil.setText("0");
        totalhargabrg.setText("");
        totalbarang.setText("");
        txt_bayar.setText("");
        txt_kembali.setText("");
    }
    
    public void clear2(){
        txIDBarang.setText("");
        txNamaBarang.setText("");
        txHarga.setText("");
        hrg_beli.setText("");
        txJumlah.setText("");
    }
    
    public void tambahTransaksi(){
        int jumlah, harga, total;
        
        jumlah = Integer.valueOf(txJumlah.getText());
        harga = Integer.valueOf(txHarga.getText());
        total = jumlah * harga;
        
        txTampil.setText(String.valueOf(total));
        
        loadData();
        totalJumlah();
        totalBiaya();
        clear2();
        txIDBarang.requestFocus();
    }
    

    
    private void tampilIDtrans(){
        try {
            String sql = "SELECT * FROM transaksi ORDER BY id_trans DESC";
            java.sql.Connection conn=(Connection)koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet rs=stm.executeQuery(sql);
            while (rs.next()) {
                idtrans.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
            //digunakan untuk menampilkan pesan jika terjadi error 
        }
    }
  
    public void tampilWaktu(){
        ActionListener taskPerformer = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                    String nol_jam = "", nol_menit = "", nol_detik ="";
                    java.util.Date dateTime = new java.util.Date();
                    int nilai_jam = dateTime.getHours();
                    int nilai_menit = dateTime.getMinutes();
                    int nilai_detik = dateTime.getSeconds();
                    
                    if(nilai_jam <= 9) nol_jam = "";
                    if(nilai_menit <= 9) nol_jam = "";
                    if(nilai_detik <= 9) nol_jam = "";
                    
                    String jam = nol_jam + Integer.toString(nilai_jam);
                    String menit = nol_menit + Integer.toString(nilai_menit);
                    String detik = nol_detik + Integer.toString(nilai_detik);
                    
                    Date date = new Date();
                    SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
                    
                    tgl11.setText(s.format(date)+" "+ jam + ":"+ menit + ":"+detik+"" );
            }
        };
        new Timer(1000,taskPerformer).start();
        
    }
    
    public void Scanbarcode(String key){
        try {
            String sql = "SELECT `id_barang`, `nama_barang`, `harga_jual`, `harga_beli` FROM `barang` where id_barang = '"+key+"'";
            java.sql.Connection conn=(Connection)koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                txIDBarang.setText(res.getString(1));
                txNamaBarang.setText(res.getString(2));
                txHarga.setText(res.getString(3));
                hrg_beli.setText(res.getString(4));
                txJumlah.setText("1");
            }
            tambahTransaksi();
        } catch (Exception e){}
    }
    
    public void test(){
    jTable1.getModel();
    
    }
    
    public Karyawan_Transaksi(String nama) {
        initComponents();
        this.setTitle("TRANSAKSI");
          model = new DefaultTableModel(){
          public boolean isCellEditable(int row, int col) {
                 if (col== 3) { //columnIndex: the column you want to make it editable
                    return true;
                } else {
                    return false;
                }
             }
          };
        
        
        
        
        
        model.addColumn("Nota");
        model.addColumn("Kode Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah");
        model.addColumn("Harga jual");
        model.addColumn("Harga beli");
        model.addColumn("Total Harga");
        jTable1.getTableHeader().setBackground(new java.awt.Color(102,153,255));
        jTable1.getTableHeader().setForeground(java.awt.Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,35));
        jTable1.setModel(model);
        lebar_tabelbarang();
        
        
        
        utama();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
        
        autonumber();
        tampilWaktu();
        tampilIDtrans();
        SetNama(nama);
        setID();
        no_editField();
        tod.setVisible(false);
    }
    private void lebar_tabelbarang(){
   
        TableColumn kolom;
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN); 
        kolom =  jTable1.getColumnModel().getColumn(0); 
        kolom.setPreferredWidth(50);
        kolom =  jTable1.getColumnModel().getColumn(1); 
        kolom.setPreferredWidth(90); 
        kolom =  jTable1.getColumnModel().getColumn(2); 
        kolom.setPreferredWidth(170); 
        kolom =  jTable1.getColumnModel().getColumn(3); 
        kolom.setPreferredWidth(50);
        kolom =  jTable1.getColumnModel().getColumn(4); 
        kolom.setPreferredWidth(90);
        kolom =  jTable1.getColumnModel().getColumn(5); 
        kolom.setPreferredWidth(90);
        kolom =  jTable1.getColumnModel().getColumn(6); 
        kolom.setPreferredWidth(90);
    
       
    }

     public void SetNama(String user){
        userr.setText(user);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Suplier = new javax.swing.JButton();
        btn_trans = new javax.swing.JButton();
        btn_report = new javax.swing.JButton();
        btn_report1 = new javax.swing.JButton();
        btn_home = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txHarga = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        txIDBarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txNoTransaksi = new javax.swing.JTextField();
        txTampil = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txNamaBarang = new javax.swing.JTextField();
        txJumlah = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        tgl11 = new javax.swing.JLabel();
        idtrans = new javax.swing.JComboBox<>();
        userNama = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        txt_kembali = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tod = new javax.swing.JTextField();
        totalbarang = new javax.swing.JLabel();
        totalhargabrg = new javax.swing.JLabel();
        hrg_beli = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        grandtotal = new javax.swing.JLabel();
        userSetId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txHargaKeyTyped(evt);
            }
        });
        jPanel1.add(txHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 140, 30));

        btn_simpan.setBackground(new java.awt.Color(255, 101, 132));
        btn_simpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-save-24.png"))); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 150, 50));

        txIDBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIDBarangActionPerformed(evt);
            }
        });
        txIDBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txIDBarangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txIDBarangKeyTyped(evt);
            }
        });
        jPanel1.add(txIDBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 110, 30));

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
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(255, 101, 132));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 630, 240));

        txNoTransaksi.setEnabled(false);
        jPanel1.add(txNoTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, 30));

        txTampil.setBackground(new java.awt.Color(255, 101, 132));
        txTampil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txTampil.setForeground(new java.awt.Color(255, 255, 255));
        txTampil.setText("Rp.0");
        txTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTampilActionPerformed(evt);
            }
        });
        jPanel1.add(txTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 210, 40));

        btn_tambah.setBackground(new java.awt.Color(255, 101, 132));
        btn_tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tambah.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-plus-24.png"))); // NOI18N
        btn_tambah.setText("TAMBAH");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, 140, 40));

        btn_cari.setBackground(new java.awt.Color(255, 51, 204));
        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        btn_cari.setBorder(null);
        btn_cari.setContentAreaFilled(false);
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 40, 50));

        btnHapus.setBackground(new java.awt.Color(255, 0, 51));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-delete-24.png"))); // NOI18N
        btnHapus.setText("DELETE");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 110, 40));
        jPanel1.add(txNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 230, 30));

        txJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahKeyTyped(evt);
            }
        });
        jPanel1.add(txJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 70, 30));

        jLabel1.setText("ID Transaksi");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel2.setText("Nama Karyawan");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 120, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tanggal");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, 30));

        jLabel4.setText("ID Barang");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel5.setText("Nama Barang");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel6.setText("Harga Jual");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        jLabel7.setText("Jumlah");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, -1, -1));

        jButton2.setBackground(new java.awt.Color(102, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-print-24.png"))); // NOI18N
        jButton2.setText("CETAK ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 120, 40));

        tgl11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(tgl11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 160, 30));

        idtrans.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(idtrans, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 110, 40));
        jPanel1.add(userNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 140, 30));

        txt_bayar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayarKeyTyped(evt);
            }
        });
        jPanel1.add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 210, 40));

        txt_kembali.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kembaliActionPerformed(evt);
            }
        });
        jPanel1.add(txt_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 570, 210, 40));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setText("Kembalian");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 570, 90, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Rp.");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 40, 40));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setText("Grand Total");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 90, 40));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setText("Pembayaran");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, 90, 40));

        tod.setEditable(false);
        jPanel1.add(tod, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 569, 150, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 650, 620));
        getContentPane().add(totalbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));
        getContentPane().add(totalhargabrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        hrg_beli.setText("jLabel2");
        getContentPane().add(hrg_beli, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 101, 132));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/EfashionFrame.png"))); // NOI18N
        jPanel3.add(jLabel12);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 210, 110));

        jPanel2.setBackground(new java.awt.Color(255, 101, 132));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 40));

        grandtotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/krywn.Transaksi.png"))); // NOI18N
        getContentPane().add(grandtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        userr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userr.setForeground(new java.awt.Color(255, 255, 255));
        userr.setText("jLabel1");
        getContentPane().add(userr, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 550, -1, 30));
        getContentPane().add(userSetId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 140, 30));

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

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        new Karyawan_ListBarang().setVisible(true);
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        if(txJumlah.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(this, "HARAP ISI DULU JUMLAH BARANG!!");
            
        } else {
            
            tambahTransaksi();
        }
        
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        totalJumlah();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String noTransaksi = txNoTransaksi.getText();
        String tanggal = tgl11.getText();
        String totalhrg = totalbarang.getText();
        String grandtotal = totalhargabrg.getText();
        int todTotal = Integer.parseInt(grandtotal);
        String checkBayar = txt_bayar.getText();
        
        int bayar =0;
        
        if(checkBayar.isEmpty()){
            
            JOptionPane.showMessageDialog(this, " isi Pembayaran");
        }else{
            bayar = Integer.parseInt(txt_bayar.getText().replace(".","").trim());
        }
     
//        int pembayaran = Integer.parseInt(txt_bayar.getText());
       
        if(bayar<todTotal){
            
            JOptionPane.showMessageDialog(this, "PEMBAYARAN KURANG");
            
        }else if(checkBayar.isEmpty()){
            
        txt_bayar.setText("");
        JOptionPane.showMessageDialog(this, "Harap isi Pembayaran");
        
        }else{
         
            try {
            String sql = "INSERT INTO `transaksi`(`id_trans`, `tgl_transaksi`, `total_brg`, `total_hrg` , `idkrywn`, `uang_masuk`, `uang_kembalian`) VALUES ('"+noTransaksi+"','"
            +tanggal+"','"+totalhrg+"','"+grandtotal+"','"+userSetId.getText()+"','"+bayar+"','"+tod.getText()+"')";
            java.sql.Connection con=(Connection)koneksi.configDB();
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
                System.out.println(sql);
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
             System.out.println("simpan penjualan error");
        }
        
        try {
            Connection c = (Connection)koneksi.configDB();
            int baris = jTable1.getRowCount();
            
            for (int i = 0; i < baris; i++) {
                String sql = "INSERT INTO `detail_transaksi` (`id_trans`, `jml_brg`, `harga`, `id_barang`) VALUES ('"+jTable1.getValueAt(i, 0)+"','"
                        + jTable1.getValueAt(i, 3) +"','"+jTable1.getValueAt(i, 4) 
                        +"','"+ jTable1.getValueAt(i, 1)+"')";
                System.out.println(sql);
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
            }
        } catch (Exception e) {
            System.out.println("simpan penjualanrinci error");
        }
        
        try {
            Connection c1 = (Connection)koneksi.configDB();
            int baris = jTable1.getRowCount();
            if(userSetId.getText().isEmpty()){
                
                JOptionPane.showMessageDialog(this, "Failed");
                
            } else {
                
                for (int i = 0; i < baris; i++) {
                String sql1 = "INSERT INTO `log_trans`(`nama_barang`, `harga_beli`, `harga_jual`, `jml_barang`, `id_trans`) VALUES ('"
                        + jTable1.getValueAt(i, 2)+"','"+ jTable1.getValueAt(i, 5)+"','"+ jTable1.getValueAt(i, 4)+"','"+ jTable1.getValueAt(i, 3)+"','"+noTransaksi+"')";
                PreparedStatement pst=c1.prepareStatement(sql1);
                pst.executeUpdate();
                pst.close();
                
            }
         }
         
        } catch (Exception e) {
            System.out.println("simpan log_trans error");
        }
        clear();
        utama();
        autonumber();
        kosong();
        idtrans.removeAllItems();
        tampilIDtrans();
        txTampil.setText("Rp. 0");
        txt_kembali.setBackground(java.awt.Color.WHITE);
        
        }
        
    }//GEN-LAST:event_btn_simpanActionPerformed
    public void no_editField(){
    txTampil.setEditable(false);
    txt_kembali.setEditable(false);
    txNamaBarang.setEditable(false);
    txHarga.setEditable(false);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/appbaju","root","");
            String NamaFile = "src\\cetak\\NotaTrans.jrxml";
            
            
            HashMap parameter = new HashMap () ;
            parameter.put ("idtrans", idtrans.getSelectedItem()) ;
            
            JasperReport Jupe = JasperCompileManager.compileReport(NamaFile);
            JasperPrint jp = JasperFillManager.fillReport(Jupe, parameter,con);
            
            JasperViewer.viewReport( jp, false);
         
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak"
            +"\n"+e.getMessage(), "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txIDBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txIDBarangKeyReleased
        // TODO add your handling code here:
        String key=txIDBarang.getText();
        Scanbarcode(key);
        
    }//GEN-LAST:event_txIDBarangKeyReleased
    public void seWarnaBg(){
        
    int bayar = Integer.parseInt(txt_bayar.getText().replace(".","").trim());
    int total = Integer.parseInt(totalhargabrg.getText());
    if(bayar < total){
            txt_kembali.setText("");
           
        }
    
    }
    private void txTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTampilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTampilActionPerformed

    private void txt_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kembaliActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
       // String byr = String.valueOf(txt_bayar.getText()).replaceAll(".", "").trim()
       //Format Rupiah
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        System.out.println(txt_bayar.getText() +"bayar ");
        int bayar = 0;
       
        int total = Integer.parseInt(totalhargabrg.getText());
        System.out.println(total);
        
        if(txt_bayar.getText().isEmpty()){

            txt_bayar.setText("");
            
        } else{
            bayar = Integer.parseInt(txt_bayar.getText().replace(".","").trim());
        }
        
       if(bayar < total){
           
            txt_kembali.setBackground(java.awt.Color.RED);
            txt_kembali.setForeground(java.awt.Color.WHITE);
            txt_kembali.setText("UANG KURANG");
             
        } else if(bayar == total){
           
            txt_kembali.setText("UANG PAS");
            tod.setText(String.valueOf("0"));
            txt_kembali.setBackground(java.awt.Color.BLUE);
            txt_kembali.setForeground(java.awt.Color.WHITE);
           
            
        } else if (bayar >= total){
            
         int kembalian = bayar - total;
         tod.setText(String.valueOf(kembalian));
         String idr = kursIndonesia.format(kembalian);
         txt_kembali.setText(String.valueOf(idr));
         
         txt_kembali.setBackground(java.awt.Color.GREEN);
        }
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarActionPerformed

    private void txIDBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txIDBarangKeyTyped
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if(txIDBarang.getText().length()>12){
            char[] ch = txIDBarang.getText().toCharArray();
            for(int i = 0; i < ch.length; i++){
                if(i >=0 && i < 12){
                    sb.append(ch[i]);
                }
            }
            System.out.println("idbarang" + sb.toString());
            txIDBarang.setText(sb.toString());
            JOptionPane.showMessageDialog(this, "p");
        } 
            BatasWajarInput(evt);
        
      
    }//GEN-LAST:event_txIDBarangKeyTyped

    private void txIDBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIDBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIDBarangActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void txHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txHargaKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txHargaKeyTyped

    private void txJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahKeyTyped
        // TODO add your handling code here:
        filterAngka1(evt);
        filterChar1(evt);
    }//GEN-LAST:event_txJumlahKeyTyped

    private void txt_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyTyped
        // TODO add your handling code here:
        filterAngka2(evt);
    }//GEN-LAST:event_txt_bayarKeyTyped

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
            java.util.logging.Logger.getLogger(Karyawan_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Karyawan_Transaksi(userr.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btn_Suplier;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_home;
    private javax.swing.JButton btn_report;
    private javax.swing.JButton btn_report1;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_trans;
    private javax.swing.JLabel grandtotal;
    public static javax.swing.JLabel hrg_beli;
    private static javax.swing.JComboBox<String> idtrans;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel tgl11;
    private javax.swing.JTextField tod;
    private javax.swing.JLabel totalbarang;
    private javax.swing.JLabel totalhargabrg;
    public static javax.swing.JTextField txHarga;
    public static javax.swing.JTextField txIDBarang;
    private javax.swing.JTextField txJumlah;
    public static javax.swing.JTextField txNamaBarang;
    private javax.swing.JTextField txNoTransaksi;
    private javax.swing.JTextField txTampil;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_kembali;
    private javax.swing.JLabel userNama;
    private javax.swing.JLabel userSetId;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
