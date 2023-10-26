package admin;


import admin.Admin_Fitur;
import com.onbarcode.barcode.EAN13;
import com.onbarcode.barcode.IBarcode;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksiDB.koneksi;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;



public class Admin_SuplaiBarang extends javax.swing.JFrame {


    
    public Admin_SuplaiBarang(String nama) {
        initComponents();
        this.setTitle("KELOLA DATA BARANG MASUK");
        DataSuplier();
        LoadData();
//        autoIdBrg();
//getImage();
      
        this.txt_idBrg.setText(getrandomString(13));
        autoLogSup();
        
        alert1.setVisible(false);
        alert2.setVisible(false);
        alert3.setVisible(false);
        timeTanggal();
        SetNama(nama);
    }
      public void SetNama(String user){
        userr.setText(user);
    }
    private void lebar_tabelbarang(){
   
        TableColumn kolom;
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN); 
        kolom = jTable1.getColumnModel().getColumn(0); 
        kolom.setPreferredWidth(47);
        kolom = jTable1.getColumnModel().getColumn(1); 
        kolom.setPreferredWidth(87); 
        kolom = jTable1.getColumnModel().getColumn(2); 
        kolom.setPreferredWidth(117); 
        kolom = jTable1.getColumnModel().getColumn(3); 
        kolom.setPreferredWidth(238);
        kolom = jTable1.getColumnModel().getColumn(4); 
        kolom.setPreferredWidth(97);
        kolom = jTable1.getColumnModel().getColumn(5); 
        kolom.setPreferredWidth(97);
        kolom = jTable1.getColumnModel().getColumn(6); 
        kolom.setPreferredWidth(47);
        kolom = jTable1.getColumnModel().getColumn(7); 
        kolom.setPreferredWidth(47);
       
    }
    
   
    public void LoadData(){
        txt_nmBrg.setEditable(true);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga jual");
        model.addColumn("Harga beli");
        model.addColumn("Qty");
        model.addColumn("BARCODE FILES");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));

        
            try{
                String Qontol = "SELECT supplier.id_supplier,supplier.nama, menyuplai.id_barang , "
                        + "barang.nama_barang , barang.harga_jual,barang.harga_beli, "
                        + "barang.jml_barang,menyuplai.tod_beli,barang.Barcode FROM `supplier` "
                        + "JOIN `menyuplai` ON `supplier`.`id_supplier` = `menyuplai`.`id_supplier` "
                        + "JOIN `barang` ON `barang`.`id_barang` = `menyuplai`.`id_barang`; ";
                
                java.sql.Connection conn = (Connection) koneksi.configDB();
                java.sql.PreparedStatement stm = conn.prepareStatement(Qontol);
                java.sql.ResultSet res = stm.executeQuery();
                
               
                
                
                    while(res.next()){
                      
                    model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5),res.getString(6),res.getString(7),res.getString(9)});
                    }
                
                    
                
                jTable1.setModel(model);
                lebar_tabelbarang();
            
            }catch(Exception e){
                e.getMessage();
            }

    }
    
    public void getFieldDataComboBox(){
        
        String sql = "SELECT * FROM supplier";
        
        try{
            
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                if(txt_idSup.getSelectedItem().equals(rs.getString(1))){
                    txt_namaSup.setText(rs.getString(2));
                }
            }
        } catch(Exception e){
            
            JOptionPane.showMessageDialog(this, e.getMessage());
            
        }
    }
    
    public void DataSuplier(){
        
        String sql ="SELECT * FROM supplier";
        txt_namaSup.setEditable(false);
        try{
             java.sql.Connection conn = (Connection) koneksi.configDB();
             java.sql.PreparedStatement stm = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stm.executeQuery(sql);
             
             while(rs.next()){
                 String name = rs.getString(1);
                 txt_idSup.addItem(name);
             }
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
    }
    
    public void tambahData(){
        String brCode = " ";
        String idBarang ="";
        idBarang = "C://drivers//" + this.txt_nmBrg.getText().toString() + ".png";
        
        String sql = "INSERT INTO barang VALUES('"+txt_idBrg.getText()+"','"
                +txt_hrgBli.getText()+"','"+txt_hrgJual.getText()+"','"
                +txt_nmBrg.getText()+"','"+txt_qty.getText()+"','"+idBarang+"');";
        String sql2 ="INSERT INTO  menyuplai VALUES('"+txt_idSup.getSelectedItem()+"','"+txt_idBrg.getText()+"','"+todd+"');";
        String logSup = "INSERT INTO log_sup VALUES('"+idLogSup+"','"+txt_idBrg.getText()+"','"+txt_nmBrg.getText()+"','"+txt_hrgBli.getText()+"','"+txt_qty.getText()+"','"+todd+"',NOW(),'"+txt_idSup.getSelectedItem()+"','"+txt_namaSup.getText()+"')";
        System.out.println(sql);
        System.out.println(sql2);
        System.out.println(logSup);
        
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.PreparedStatement stm1 = conn.prepareStatement(sql2);
            java.sql.PreparedStatement stm2 = conn.prepareStatement(logSup);
            stm.execute();
            stm1.execute();
            stm2.execute();
            
//            if(txt_idBrg.getText())
            JOptionPane.showMessageDialog(this, "DATA BERHASIL DITAMBAHKAN");
            LoadData();
            
        
        }catch(Exception e){
            e.getMessage();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    
    }
    
   public void ResetField(){
    txt_idBrg.setText("");
    txt_nmBrg.setText("");
    txt_hrgBli.setText("");
    txt_hrgJual.setText("");
    txt_qty.setText("");
    txt_idSup.setSelectedItem("==PILIH DATA SUPLIER==");
    txt_namaSup.setText("");
    txt_todHrgSup.setText("");
    searchField.setText("");
    this.txt_idBrg.setText(getrandomString(13));
    }
   
    String ch;
    public void filterAngka1(KeyEvent a){
        ch = String.valueOf(txt_qty.getText());
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
            alert1.setVisible(true);
        } else{
            alert1.setVisible(false);
        }
    }
    
    
    
    public void filterAngka2(KeyEvent a){
        ch = String.valueOf(txt_hrgBli.getText());
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
            alert2.setVisible(true);
        } else{
            alert2.setVisible(false);
        }
    }
    
    public void filterAngka3(KeyEvent a){
        ch = String.valueOf(txt_hrgJual.getText());
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
            alert3.setVisible(true);
        } else{
            alert3.setVisible(false);
        }
    }
   
    int todd = 0;
   
    public void kalikan1(String key1){
       
    int todBeli = 0;
    if(txt_hrgBli.getText().isEmpty()||txt_qty.getText().isEmpty()){
        
        txt_todHrgSup.setText("");
    
    } else {
        
        try{
                String Qontol = "SELECT harga_beli FROM barang WHERE id_barang = '"+txt_idBrg.getText()+"'";
                System.out.println(Qontol);
                
                java.sql.Connection conn = (Connection) koneksi.configDB();
                java.sql.PreparedStatement stm = conn.prepareStatement(Qontol);
                java.sql.ResultSet res = stm.executeQuery();
                int hrgBli = 0;
                while(res.next()){
                   hrgBli = Integer.parseInt(res.getString(1));
                     
                   
                }
              
            
            }catch(Exception e){
                e.getMessage();
            }
        
      
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        todBeli = Integer.parseInt(txt_qty.getText())*Integer.parseInt(txt_hrgBli.getText());
        todd = todBeli;
        String idr = kursIndonesia.format(todBeli);
        
        txt_todHrgSup.setText(String.valueOf(idr));
    }
    
   }
   
    public void kalikan2(String key2){
        
    int todBeli = 0;
     if(txt_hrgBli.getText().isEmpty()||txt_qty.getText().isEmpty()){
        
        txt_todHrgSup.setText("");
    
    } else {
         todBeli = Integer.parseInt(String.valueOf(txt_qty.getText()))*Integer.parseInt(String.valueOf(txt_hrgBli.getText()));
        txt_todHrgSup.setText(String.valueOf(todBeli));
    }   
   }
    
    public void cariDataSearch(String key){
       
       String sql = "SELECT supplier.id_supplier,supplier.nama, menyuplai.id_barang , barang.nama_barang ,  barang.harga_jual,barang.harga_beli,  barang.jml_barang, menyuplai.tod_beli,barang.Barcode FROM `supplier` JOIN `menyuplai` ON `supplier`.`id_supplier` = `menyuplai`.`id_supplier` JOIN `barang` ON `barang`.`id_barang` = `menyuplai`.`id_barang` WHERE barang.nama_barang LIKE '%"+key+"%' OR supplier.nama LIKE '%"+key+"%'; ";
       
       DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga jual");
        model.addColumn("Harga beli");
        model.addColumn("Qty");
        model.addColumn("BARCODE FILES");
        jTable1.getTableHeader().setBackground(new Color(102,153,255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setPreferredSize(new Dimension(20,30));
        
        
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery();
            
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),
                res.getString(5),res.getString(6),res.getString(7),res.getString(9)});
            }
            jTable1.setModel(model);
            lebar_tabelbarang();
        
        }catch(Exception e){
            e.getMessage();
        }
       
   }
   
    private void autoIdBrg(){
       txt_idBrg.setEditable(false);
       String autoNumber = "SELECT * FROM barang ORDER BY id_barang DESC";
   
   try{
       java.sql.Connection conn = (Connection) koneksi.configDB();
       java.sql.PreparedStatement stm = conn.prepareStatement(autoNumber);
       java.sql.ResultSet res = stm.executeQuery(autoNumber);
       
       if(res.next()){
           String id = res.getString(1).substring(2);
           String TR = ""+(Integer.parseInt(id)+1);
           String nol = "";
           
           if(TR.length()==1){
            nol = "0";
            }
            else if(TR.length()==2){
            nol = "";
            }
           txt_idBrg.setText("BR"+nol+TR);
       } else {
           txt_idBrg.setText("BR01");
       }
       
       
   }catch(Exception e){
       e.getMessage();
   }
   
   }
    String idLogSup = "";
    public void autoLogSup(){
       String autoNumber = "SELECT * FROM log_sup ORDER BY id_log DESC";
   
   try{
       java.sql.Connection conn = (Connection) koneksi.configDB();
       java.sql.PreparedStatement stm = conn.prepareStatement(autoNumber);
       java.sql.ResultSet res = stm.executeQuery(autoNumber);
       
       if(res.next()){
           String id = res.getString(1).substring(2);
           String TR = ""+(Integer.parseInt(id)+1);
           String nol = "";
           
           if(TR.length()==1){
            nol = "0";
            }
            else if(TR.length()==2){
            nol = "";
            }
//           txt_idBrg.setText("LP"+nol+TR);
           idLogSup = ("LP"+nol+TR);
           System.out.println(idLogSup);
       } else {
//           txt_idBrg.setText("LP01");
           idLogSup = "LP01";
           System.out.println(idLogSup);
       }
       
       
   }catch(Exception e){
       e.getMessage();
   }
   
   }
   
   static String getrandomString(int panjangKarakter){
       
        List<Character> charTemp = new ArrayList<>();
        for(int i = 0; i < panjangKarakter; i++){
            charTemp.add((char) ThreadLocalRandom.current().nextInt(48,57));
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < charTemp.size(); i++){
            sb.append(charTemp.get(i));
        }
        return  sb.toString();
        
    }
   public void EditStock(){
       int stkDb = 0;
       String stokBarang = "SELECT jml_barang FROM barang WHERE id_barang = '"+txt_idBrg.getText()+"'";
       
       try{
              java.sql.Connection conn = (Connection) koneksi.configDB();
              java.sql.PreparedStatement stm = conn.prepareStatement(stokBarang);
              java.sql.ResultSet rs = stm.executeQuery();
              while(rs.next()){
                  stkDb = Integer.parseInt(rs.getString(1));
              }
       
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       }
       int stok = 0;
       int brgInputan = Integer.parseInt(txt_qty.getText());
       int hrgBli = Integer.parseInt(txt_hrgBli.getText());
       
      
       int totalan = brgInputan * hrgBli;
       int sqlBrg = stkDb + brgInputan;
       System.out.println(sqlBrg);
       System.out. println(totalan);
       
       String sql = "UPDATE barang SET jml_barang = '"+sqlBrg+"',harga_beli = '"+txt_hrgBli.getText()+"',harga_jual = '"+txt_hrgJual.getText()+"' WHERE id_barang = '"+txt_idBrg.getText()+"'; ";
//       String logSup = " UPDATE log_sup SET jml_barang = '"+brgInputan+"',harga_beli = '"+txt_hrgBli.getText()+"',tod_hrgSup = '"+totalan+"', waktu = now() ,idSuplier = '"+txt_idSup.getSelectedItem()+"', NamaSuplier = '"+txt_namaSup.getText()+"' WHERE id_barang = '"+txt_idBrg.getText()+"'";
       String tmbh = "INSERT INTO log_sup VALUES('"+idLogSup+"','"+txt_idBrg.getText()+"','"+txt_nmBrg.getText()+"','"+txt_hrgBli.getText()+"','"+txt_qty.getText()+"','"+todd+"',NOW(),'"+txt_idSup.getSelectedItem()+"','"+txt_namaSup.getText()+"')";
       System.out.println(sql);
       System.out.println(tmbh);
       
        for(int i = 0; i<jTable1.getRowCount(); i++){
            
          if(txt_idBrg.getText().equals(jTable1.getValueAt(i, 2))){
              stok = Integer.parseInt(String.valueOf(jTable1.getValueAt(i, 6)));
          }
      }
       
       System.out.println(brgInputan);
       System.out.println(stok);
       
           try{
           java.sql.Connection conn = (Connection) koneksi.configDB();
           java.sql.PreparedStatement stm = conn.prepareStatement(sql);
           java.sql.PreparedStatement stm2 = conn.prepareStatement(tmbh);
           stm.execute();
           stm2.execute();
           JOptionPane.showMessageDialog(this, "Stok Berhasil Ditambahkan");
           LoadData();
           
       
       }catch(Exception e){
           
           JOptionPane.showMessageDialog(this, e.getMessage());
       }

       
   
   }
   
   public void hapusBrg(){
       String sql = "DELETE FROM menyuplai WHERE id_supplier = '"
               +txt_idSup.getSelectedItem()+"' AND id_barang = '"+txt_idBrg.getText()+"';";
       String sql2 = "DELETE FROM barang WHERE id_barang = '"+txt_idBrg.getText()+"';";
       String sql3 = "DELETE FROM log_sup WHERE id_barang = '"+txt_idBrg.getText()+"';";
       System.out.println(sql);
       System.out.println(sql2);
       try{
           java.sql.Connection conn = (Connection) koneksi.configDB();
           java.sql.PreparedStatement stm = conn.prepareStatement(sql);
           java.sql.PreparedStatement stm1 = conn.prepareStatement(sql2);
           java.sql.PreparedStatement stm2 = conn.prepareStatement(sql3);
           stm.execute();
           stm1.execute();
           stm2.execute();
           JOptionPane.showMessageDialog(this, "DATA BERHASIL DIHAPUS");
           LoadData();
       
       }catch(Exception e){
           e.getMessage();
       }
   }
   
   public void encodeBarcode(){
 
       EAN13 objEan = new EAN13();
       objEan.setData(this.txt_idBrg.getText().toString());
       objEan.setUom(IBarcode.UOM_PIXEL);
       objEan.setX(3f);
       objEan.setY(175f);
 
       objEan.setLeftMargin(0f);
       objEan.setRightMargin(0f);
       objEan.setTopMargin(0f);
       objEan.setBottomMargin(0f);
       objEan.setResolution(72);
       objEan.setShowText(true);
       objEan.setTextMargin(6);
       objEan.setRotate(IBarcode.ROTATE_0);
        try {
            objEan.drawBarcode("C://drivers//" + this.txt_nmBrg.getText().toString() + ".png");
            JOptionPane.showMessageDialog(this, "BARCODE BERHASIL.....");
            
        } catch (Exception ex) {
        }
    }
   
  public void setUkuranBarCode(EAN13 mBarcode,float panjangBarcode , float tinggiBarcode){
            mBarcode.setX(panjangBarcode);
            mBarcode.setY(tinggiBarcode);
            
    }
  
  public void getImage(){
        File fileBarcode = new File("C://drivers");
        File[] listFileBarCode = fileBarcode.listFiles();
        List<File>  listBarcode = new ArrayList();  
       
        for(File fileBarc : listFileBarCode){
           if(fileBarc.getName().contains(".png")){
               listBarcode.add(new File(fileBarc.getAbsolutePath()));
               
           }
            
        }
        
      
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_qty = new javax.swing.JTextField();
        txt_namaSup = new javax.swing.JTextField();
        txt_hrgJual = new javax.swing.JTextField();
        txt_nmBrg = new javax.swing.JTextField();
        txt_idBrg = new javax.swing.JTextField();
        txt_hrgBli = new javax.swing.JTextField();
        txt_idSup = new javax.swing.JComboBox<>();
        btn_risetField = new javax.swing.JButton();
        btn_tambahData = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_history = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        alert1 = new javax.swing.JLabel();
        alert2 = new javax.swing.JLabel();
        alert3 = new javax.swing.JLabel();
        txt_todHrgSup = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        getBarcode = new javax.swing.JLabel();
        BARCODE = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setForeground(new java.awt.Color(0, 0, 0));
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 680, 130));

        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_qtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_qtyKeyTyped(evt);
            }
        });
        getContentPane().add(txt_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 150, -1));

        txt_namaSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaSupActionPerformed(evt);
            }
        });
        getContentPane().add(txt_namaSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 250, -1));

        txt_hrgJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hrgJualKeyTyped(evt);
            }
        });
        getContentPane().add(txt_hrgJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 250, -1));
        getContentPane().add(txt_nmBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 250, -1));
        getContentPane().add(txt_idBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 250, -1));

        txt_hrgBli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hrgBliActionPerformed(evt);
            }
        });
        txt_hrgBli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hrgBliKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hrgBliKeyTyped(evt);
            }
        });
        getContentPane().add(txt_hrgBli, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 250, -1));

        txt_idSup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "==PILIH DATA SUPLIER==" }));
        txt_idSup.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_idSupItemStateChanged(evt);
            }
        });
        txt_idSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idSupActionPerformed(evt);
            }
        });
        getContentPane().add(txt_idSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 250, -1));

        btn_risetField.setBackground(new java.awt.Color(255, 101, 132));
        btn_risetField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_risetField.setForeground(new java.awt.Color(255, 255, 255));
        btn_risetField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-reset-24.png"))); // NOI18N
        btn_risetField.setText("RESET");
        btn_risetField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_risetFieldActionPerformed(evt);
            }
        });
        getContentPane().add(btn_risetField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 120, 30));

        btn_tambahData.setBackground(new java.awt.Color(255, 101, 132));
        btn_tambahData.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_tambahData.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambahData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-plus-24.png"))); // NOI18N
        btn_tambahData.setText("TAMBAH");
        btn_tambahData.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                btn_tambahDataComponentHidden(evt);
            }
        });
        btn_tambahData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahDataActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambahData, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 120, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("DATA BARANG");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, 30));

        btn_history.setBackground(new java.awt.Color(102, 153, 255));
        btn_history.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_history.setForeground(new java.awt.Color(255, 255, 255));
        btn_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-order-history-24.png"))); // NOI18N
        btn_history.setText("HISTORY");
        btn_history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_historyActionPerformed(evt);
            }
        });
        getContentPane().add(btn_history, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 120, 30));

        btn_print.setBackground(new java.awt.Color(102, 153, 255));
        btn_print.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-print-24.png"))); // NOI18N
        btn_print.setText("PRINT");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 360, 120, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 170, 30));

        searchField.setText("Fitur Cari ,Ketikkan nama barang atau nama supplier");
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });
        getContentPane().add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 320, 30));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-delete-24.png"))); // NOI18N
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 490, 100, 40));

        jButton4.setBackground(new java.awt.Color(101, 209, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-edit-24.png"))); // NOI18N
        jButton4.setText("EDIT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, 100, 40));

        jButton5.setBackground(new java.awt.Color(102, 153, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-back-24.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 100, 40));

        alert1.setBackground(new java.awt.Color(255, 0, 0));
        alert1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        alert1.setForeground(new java.awt.Color(255, 51, 51));
        alert1.setText("Masukkan hanya angka");
        getContentPane().add(alert1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, -1));

        alert2.setBackground(new java.awt.Color(255, 0, 0));
        alert2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        alert2.setForeground(new java.awt.Color(255, 51, 51));
        alert2.setText("Masukkan hanya angka");
        getContentPane().add(alert2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, -1, -1));

        alert3.setBackground(new java.awt.Color(255, 0, 0));
        alert3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        alert3.setForeground(new java.awt.Color(255, 51, 51));
        alert3.setText("Masukkan hanya angka");
        getContentPane().add(alert3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, -1, -1));

        txt_todHrgSup.setEditable(false);
        txt_todHrgSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_todHrgSupActionPerformed(evt);
            }
        });
        txt_todHrgSup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_todHrgSupKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_todHrgSupKeyTyped(evt);
            }
        });
        getContentPane().add(txt_todHrgSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 250, -1));

        jLabel3.setText("Total Harga Beli dari Suplier");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));

        getBarcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(getBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 260, 90));

        BARCODE.setText("BARCODE");
        getContentPane().add(BARCODE, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, -1, 20));

        tanggal.setText("jLabel4");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(674, 65, 130, 30));

        userr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userr.setForeground(new java.awt.Color(255, 255, 255));
        userr.setText("jLabel2");
        getContentPane().add(userr, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 35, 160, 30));

        jButton1.setBackground(new java.awt.Color(102, 153, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("CONVERT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 90, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/adm_SuplaiBrg.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idSupActionPerformed
        // TODO add your handling code here:
        getFieldDataComboBox();
    }//GEN-LAST:event_txt_idSupActionPerformed

    private void txt_idSupItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_idSupItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_idSupItemStateChanged

    private void btn_tambahDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahDataActionPerformed
        // TODO add your handling code here:
        
        if(txt_namaSup.getText().isEmpty()&&txt_qty.getText().isEmpty()&&txt_nmBrg.getText().isEmpty()
                &&txt_hrgBli.getText().isEmpty()&&txt_hrgJual.getText().isEmpty()){
        
            JOptionPane.showMessageDialog(this, "HARAP ISI DENGAN LENGKAP!");
            
        } else {
            tambahData();
            encodeBarcode();
            ResetField();
//      autoIdBrg();
            autoLogSup();
            getBarcode.setVisible(true);
        
        }
        
    }//GEN-LAST:event_btn_tambahDataActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        EditStock();
        ResetField();
        
//        autoIdBrg();
        autoLogSup();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        hapusBrg();
        ResetField();
//        autoIdBrg();
        autoLogSup();
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_risetFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_risetFieldActionPerformed
        // TODO add your handling code here:
        ResetField();
        
//        autoIdBrg();
        autoLogSup();
        txt_nmBrg.setEditable(true);
        txt_idSup.setEnabled(true);
        getBarcode.setVisible(false);
    }//GEN-LAST:event_btn_risetFieldActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        txt_idBrg.setEditable(false);
        txt_nmBrg.setEditable(false);
        txt_idSup.setEnabled(false);
        getBarcode.setVisible(true);
        
        int baris = jTable1.rowAtPoint(evt.getPoint());
        
        
        //ComboBox ID Suplier
        if(jTable1.getValueAt(baris, 0)==null){
            txt_idSup.setSelectedItem(this);

        } else{
            txt_idSup.setSelectedItem(jTable1.getValueAt(baris, 0).toString());

        }
        
        //Quantity
        if(jTable1.getValueAt(baris, 6)==null){
            txt_qty.setText("");
        } else {
            txt_qty.setText(jTable1.getValueAt(baris, 6).toString());
        }
        
        //idBarang
        if(jTable1.getValueAt(baris, 2)==null){
            txt_idBrg.setText("");
        } else {
            txt_idBrg.setText(jTable1.getValueAt(baris, 2).toString());
       
        }
        
        //Nama Barang
        if(jTable1.getValueAt(baris, 3)==null){
            txt_nmBrg.setText("");
        } else {
            txt_nmBrg.setText(jTable1.getValueAt(baris, 3).toString());
        }
        
        //Harga Beli
        if(jTable1.getValueAt(baris, 4)==null){
            txt_hrgJual.setText("");
        }else {
            txt_hrgJual.setText(jTable1.getValueAt(baris, 4).toString());
        }
        
        //Harga Jual
        if(jTable1.getValueAt(baris, 5)==null){
            txt_hrgBli.setText("");
        } else {
            txt_hrgBli.setText(jTable1.getValueAt(baris, 5).toString());
        }
        
        //Nama Sup
        if(jTable1.getValueAt(baris, 1)==null){
            txt_namaSup.setText("");

        } else{
            txt_namaSup.setText(jTable1.getValueAt(baris, 1).toString());

        }
        
        if(jTable1.getValueAt(baris, 7)==null){
        
        } else {
        File fps = new File(jTable1.getValueAt(baris, 7).toString());
        try{
        Image mImage = ImageIO.read(fps);       
        ImageIcon mImageIc = new ImageIcon(mImage);
        mImage = mImageIc.getImage().getScaledInstance(getBarcode.getWidth(), getBarcode.getHeight(), Image.SCALE_SMOOTH);
        this.getBarcode.setIcon(new ImageIcon(mImage));
        }
        catch(Exception e){
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
        }
    }//GEN-LAST:event_jTable1MouseClicked
public void print(){
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/appbaju","root","");
                String report = ("C:\\Users\\Windows 10\\Documents\\NetBeansProjects\\Efashion_Final\\src\\cetak\\NotaBeli.jrxml");
                
                HashMap hash = new HashMap();
                hash.put("supId", txt_idSup.getSelectedItem());
                hash.put("tanggal", tanggal.getText());
                JasperReport JRpt = JasperCompileManager.compileReport(report);
                JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, con);
                JasperViewer.viewReport(JPrint, false);
                
         }catch(Exception e){
             
                javax.swing.JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak"
                +"\n"+e.getMessage(), "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
         }
    }
public void timeTanggal(){
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
Date date = new Date();  
tanggal.setText(formatter.format(date));

}
    private void txt_hrgBliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hrgBliKeyTyped
        // TODO add your handling code here:
        filterAngka2(evt);
        
    }//GEN-LAST:event_txt_hrgBliKeyTyped

    private void txt_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyTyped
        // TODO add your handling code here:
        filterAngka1(evt);
       
    }//GEN-LAST:event_txt_qtyKeyTyped

    private void txt_hrgJualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hrgJualKeyTyped
        // TODO add your handling code here:
        filterAngka3(evt);
    }//GEN-LAST:event_txt_hrgJualKeyTyped

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        // TODO add your handling code here:
        String key=searchField.getText(); 
        if(key!=""){
            cariDataSearch(key);
        }else{
            LoadData();
        }
    }//GEN-LAST:event_searchFieldKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Admin_Fitur(userr.getText()).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_namaSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaSupActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void txt_todHrgSupKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_todHrgSupKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_todHrgSupKeyTyped

    private void txt_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyReleased
        // TODO add your handling code here:
         String key2 = txt_qty.getText();
         kalikan1(key2);
       
       
    }//GEN-LAST:event_txt_qtyKeyReleased

    private void txt_hrgBliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hrgBliKeyReleased
        // TODO add your handling code here:
        String key2 = txt_hrgBli.getText();
         kalikan1(key2);
       
    }//GEN-LAST:event_txt_hrgBliKeyReleased

    private void txt_todHrgSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_todHrgSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_todHrgSupActionPerformed

    private void txt_todHrgSupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_todHrgSupKeyReleased
        // TODO add your handling code here:
     
         
    }//GEN-LAST:event_txt_todHrgSupKeyReleased

    private void btn_historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_historyActionPerformed
        // TODO add your handling code here:
        new Admin_LogSuplier().setVisible(true);
    }//GEN-LAST:event_btn_historyActionPerformed

    private void btn_tambahDataComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_btn_tambahDataComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambahDataComponentHidden

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        print();
    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_hrgBliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hrgBliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hrgBliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new convertSatuan().setVisible(true);
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
            java.util.logging.Logger.getLogger(Admin_SuplaiBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_SuplaiBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_SuplaiBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_SuplaiBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_SuplaiBarang(userr.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BARCODE;
    private javax.swing.JLabel alert1;
    private javax.swing.JLabel alert2;
    private javax.swing.JLabel alert3;
    private javax.swing.JButton btn_history;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_risetField;
    private javax.swing.JButton btn_tambahData;
    private javax.swing.JLabel getBarcode;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField txt_hrgBli;
    private javax.swing.JTextField txt_hrgJual;
    private javax.swing.JTextField txt_idBrg;
    private javax.swing.JComboBox<String> txt_idSup;
    private javax.swing.JTextField txt_namaSup;
    private javax.swing.JTextField txt_nmBrg;
    public static javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_todHrgSup;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}