/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;

import java.awt.Image;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import koneksiDB.koneksi;
/**
 *
 * @author kitan
 */
public class Admin_DaftarKaryawan extends javax.swing.JFrame {

    String getSelectedImage = "";
    public Admin_DaftarKaryawan(String nama) throws SQLException {
        try{
        initComponents();
         this.setTitle("DAFTAR KARYAWAN");
        this.alert_telepon.setVisible(false);
        this.alert_username1.setVisible(false);
        
        txt_idkrywn1.setEnabled(false);
        getCurrentIDKrywn();
        typeKaryawan(); 
        SetNama(nama);
        alertGbr.setVisible(false);
        
       } catch(Exception e){
           JOptionPane.showMessageDialog(null, "ERROR");
       }
      
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

        txt_namakrywn = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_notelp = new javax.swing.JTextField();
        jbox_type = new javax.swing.JComboBox<>();
        txt_idkrywn1 = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        register = new javax.swing.JButton();
        txt_password = new javax.swing.JPasswordField();
        alert_telepon = new javax.swing.JLabel();
        alert_username1 = new javax.swing.JLabel();
        txt_ktp = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        alertGbr = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_namakrywn.setBackground(new java.awt.Color(196, 196, 196));
        txt_namakrywn.setForeground(new java.awt.Color(0, 0, 0));
        txt_namakrywn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namakrywnActionPerformed(evt);
            }
        });
        getContentPane().add(txt_namakrywn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 240, 30));

        txt_alamat.setBackground(new java.awt.Color(196, 196, 196));
        txt_alamat.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 240, 30));

        txt_notelp.setBackground(new java.awt.Color(196, 196, 196));
        txt_notelp.setForeground(new java.awt.Color(0, 0, 0));
        txt_notelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_notelpActionPerformed(evt);
            }
        });
        txt_notelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_notelpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_notelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 240, 30));

        getContentPane().add(jbox_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 90, 30));

        txt_idkrywn1.setBackground(new java.awt.Color(255, 255, 255));
        txt_idkrywn1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        txt_idkrywn1.setForeground(new java.awt.Color(0, 0, 0));
        txt_idkrywn1.setCaretColor(new java.awt.Color(196, 196, 196));
        txt_idkrywn1.setEnabled(false);
        txt_idkrywn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idkrywn1ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_idkrywn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 240, 30));

        txt_username.setBackground(new java.awt.Color(196, 196, 196));
        txt_username.setForeground(new java.awt.Color(0, 0, 0));
        txt_username.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_usernameInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        txt_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_usernameKeyTyped(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 230, 30));

        register.setBackground(new java.awt.Color(255, 101, 132));
        register.setForeground(new java.awt.Color(255, 255, 255));
        register.setText("REGISTER");
        register.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                registerInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        getContentPane().add(register, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 280, 60));

        txt_password.setBackground(new java.awt.Color(196, 196, 196));
        txt_password.setForeground(new java.awt.Color(0, 0, 0));
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 230, 30));

        alert_telepon.setForeground(new java.awt.Color(255, 51, 51));
        alert_telepon.setText("no telpon hanya angka dan tidak melebihi 12 angka");
        getContentPane().add(alert_telepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 340, 20));

        alert_username1.setForeground(new java.awt.Color(255, 51, 51));
        alert_username1.setText("panjang username tidak boleh dari 4");
        getContentPane().add(alert_username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 210, -1));

        txt_ktp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ktpActionPerformed(evt);
            }
        });
        txt_ktp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_ktpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ktpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_ktp, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 100, 30));

        jLabel2.setText("FOTO PROFILE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, -1, -1));

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

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("UPLOAD FOTO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 120, 30));

        jLabel3.setText("KTP");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, -1, -1));

        alertGbr.setForeground(new java.awt.Color(51, 51, 255));
        alertGbr.setText("Gambar Berhasil Ditambahkan");
        getContentPane().add(alertGbr, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 190, -1));

        jLabel4.setText("* Ganti 0 awal nomer dengan 62");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, -1, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/adm_registkaryawan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private String parseIDKrywn(String temp){
        char[] characterText = temp.toCharArray();
        ArrayList<Character> tempText = new ArrayList<>();
        for(int i =0; i < characterText.length;
               i++){
                if(characterText[i] >= 48 && characterText[i] <= 57){
                    tempText.add(characterText[i]);
                } 
        }
        char[] foundID = new char[tempText.size()];
        int index = 0;
       for(Character c : tempText){
           foundID[index] = c;
           index++;
       }  
       return String.valueOf(foundID);
    }
    private void checkIfNumber(){
       String nomerHP = String.valueOf(txt_notelp.getText());
       char[] charArr = nomerHP.toCharArray();
       
       for(char c : charArr){
           System.out.println(c + "->" + (int) c);
       }
       int start = 49;
       int end = 57;
       boolean isNumber = false;
       for(int i = 0; i < charArr.length; i++){
          if(!Character.isDigit(charArr[i])){
              isNumber = false;
          }  else {
              isNumber = true;
          }
       }
       if(isNumber == true ){
           JOptionPane.showMessageDialog(null,"Data adlaah angka");
       } else if(isNumber == false){
          JOptionPane.showMessageDialog(null,"Data mengandung karakter"); 
       }
       
    }
    private void typeKaryawan(){
        String[] typeKaryawan = new String[]{
            "Karyawan"
        };
        for(String text:  typeKaryawan){
             this.jbox_type.addItem(text);
             
        }
       }
    
    public void ResetField(){
    txt_idkrywn1.setText("");
    txt_namakrywn.setText("");
    txt_alamat.setText("");
    txt_notelp.setText("");
    txt_ktp.setText("");
    txt_username.setText("");
    txt_password.setText("");
    alert_username1.setVisible(false);
    alertGbr.setVisible(false);
    getSelectedImage = "";
    }
    public static String escapePath(String path) {
        return path.replace("\\", "\\\\");
    }
    private void registerasiDataKaryawan() throws SQLException{
        String gbr = escapePath(getSelectedImage.toString());
        String password = new String(this.txt_password.getPassword());
        if(!password.isEmpty() && !this.txt_namakrywn.getText().isEmpty()){
            System.out.println("Insert Success");
        } else {    
              System.out.println("Insert Failed");
        }
        if(Integer.parseInt(this.txt_idkrywn1.getText()) >= 0 && Integer.parseInt(this.txt_idkrywn1.getText()) 
                < 10){
        String KEY = "KR0" + this.txt_idkrywn1.getText();
  
        String queryRegist = " INSERT INTO `karyawan` VALUES ('"+ KEY +"','"+this.txt_namakrywn.getText()+"' , '"
                    +this.txt_alamat.getText()+"','"+ this.txt_notelp.getText() +"' , '"+txt_ktp.getText()+"','"+ this.jbox_type.getSelectedItem().toString()+"','"+gbr+"')";
            System.out.println(queryRegist);
        java.sql.Connection conn = (Connection) koneksi.configDB();
        java.sql.PreparedStatement prs = conn.prepareStatement(queryRegist);
        prs.execute(); 
       
        java.sql.Connection connObj = (Connection) koneksi.configDB();
       
        System.out.println("Password : " + this.txt_password.getPassword());
        String queryRegistLogin = "INSERT INTO `login` VALUES('" + this.txt_username.getText() + "','" + password + "','"
                + KEY + "')";
        java.sql.PreparedStatement prs2 = connObj.prepareStatement(queryRegistLogin); 
        prs2.execute();
        
        } else{ 
        String KEY = "KR" + this.txt_idkrywn1.getText();        
             System.out.println(KEY + " HEllo");
         String queryRegist = " INSERT INTO `karyawan` VALUES ('"+ KEY +"','"+this.txt_namakrywn.getText()+"' , '"
                    +this.txt_alamat.getText()+"','"+ this.txt_notelp.getText() +"' , '"+txt_ktp.getText()+"','"+ this.jbox_type.getSelectedItem().toString()+"','"+gbr+"')";
            System.out.println(queryRegist);
       
        java.sql.Connection conn = (Connection) koneksi.configDB();
        java.sql.PreparedStatement prs = conn.prepareStatement(queryRegist);
        prs.execute();          
       
        java.sql.Connection connObj = (Connection) koneksi.configDB();
        System.out.println("Password : " + this.txt_password.getPassword());
        
        String queryRegistLogin2 = "INSERT INTO `login` VALUES('" + this.txt_username.getText() + "','" + password + "','"
                + KEY + "')";
        java.sql.PreparedStatement prs2 = connObj.prepareStatement(queryRegistLogin2); 
        prs2.execute();
        JOptionPane.showMessageDialog(this, "Berhasil tambahkan Karyawan");
        ResetField();

        }   
        
    }
    private void getCurrentIDKrywn() throws SQLException{
      ArrayList<String> temporaryStringIDKrywn = new ArrayList<>();
        String query = "SELECT `karyawan`.`idkrywn` FROM `karyawan`";
        java.sql.Connection conn = (Connection) koneksi.configDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet resultSet = stm.executeQuery(query);
        int n = 0;
        while(resultSet.next()){
             temporaryStringIDKrywn.add(resultSet.getString("idkrywn"));
        }
        String ID = parseIDKrywn(temporaryStringIDKrywn.get(temporaryStringIDKrywn.size() - 1));
        if(ID.charAt(0) == '0'){
            int nID = Integer.parseInt(String.valueOf(ID.charAt(1))) + 1;
            txt_idkrywn1.setText(String.valueOf(nID));
        } else{    
            int nID = Integer.parseInt(String.valueOf(ID)) + 1;
            txt_idkrywn1.setText(String.valueOf(nID));
        }
    }
    private void txt_namakrywnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namakrywnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namakrywnActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_idkrywn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idkrywn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idkrywn1ActionPerformed

    private boolean checkNumber(String strNumber){
        char ch[] = strNumber.toCharArray();
        boolean state = false;
        for(char c: ch){
            if(Character.isDigit(c)){
                state =true;
            } else {
                state = false;
            }
        }
        return state;
    }
    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
    
        try {
            boolean statusOK = checkNumber(this.txt_notelp.getText());
            if(statusOK){
                
            registerasiDataKaryawan();
            JOptionPane.showMessageDialog(this, "Berhasil tambahkan Karyawan");
            ResetField();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_DaftarKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getCurrentIDKrywn();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_DaftarKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           
    }//GEN-LAST:event_registerActionPerformed

    private void txt_notelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_notelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_notelpActionPerformed

    private void txt_usernameInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_usernameInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameInputMethodTextChanged

    private void registerInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_registerInputMethodTextChanged
        // TODO add your handling code here:
     
        
    }//GEN-LAST:event_registerInputMethodTextChanged

    private void txt_usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usernameKeyTyped
        // TODO add your handling code here:   
        
//        int totalLen = this.txt_username.getText().length();
//        System.out.printf("%s -> %d%n",this.txt_username.getText() ,totalLen);
//        System.out.print(this.txt_username.getText() +"->" + totalLen);
//        
//        if(totalLen >= 4){
//            this.alert_username1.setVisible(true);
//            
//        } else if(totalLen <=3){
//            this.alert_username1.setVisible(false);
//        }
    BatasWajarInput2(evt);
    }//GEN-LAST:event_txt_usernameKeyTyped

    private void txt_notelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_notelpKeyTyped
        // TODO add your handling code here:
//        char character[] = this.txt_notelp.getText().toCharArray(); 
//        int len = this.txt_notelp.getText().length();
//       
//        System.out.println("len -> " + len);
//        for(char v : character){
//            if(!Character.isDigit(v) || len >= 12 ){
//                this.alert_telepon.setVisible(true);
//                getState(false);
//            } else if(Character.isDigit(v) ){
//               this.alert_telepon.setVisible(false); 
//               getState(true);
//            }
//        }
        BatasWajarInput1(evt);
    }//GEN-LAST:event_txt_notelpKeyTyped
    
    public void BatasWajarInput(KeyEvent a){
        if(txt_ktp.getText().length()>=10){
            a.consume();
        }
    }
    
    public void BatasWajarInput1(KeyEvent a){
        if(txt_notelp.getText().length()>=12){
            this.alert_telepon.setVisible(true);
            a.consume();
        } else {
            this.alert_telepon.setVisible(false);
        }
    }
    
    public void BatasWajarInput2(KeyEvent a){
        if(txt_username.getText().length()>=4){
            
            this.alert_username1.setVisible(true);
            a.consume();
        } else  {
            this.alert_username1.setVisible(false);
        }
    }
    private void txt_ktpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ktpActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_ktpActionPerformed

    private void txt_ktpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ktpKeyReleased
        // TODO add your handling code here:
//        BatasWajarInput(evt);
    }//GEN-LAST:event_txt_ktpKeyReleased

    private void txt_ktpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ktpKeyTyped
        // TODO add your handling code here:
        BatasWajarInput(evt);
    }//GEN-LAST:event_txt_ktpKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Admin_Fitur(userr.getText()).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("4 Extensions Supported","jpg","png","jpeg","gif");
        fileChooser.setFileFilter(filter);
        int selected = fileChooser.showOpenDialog(null);
        if(selected == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            getSelectedImage = file.getAbsolutePath();
            alertGbr.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private boolean getState(boolean state){
        return state;
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
            java.util.logging.Logger.getLogger(Admin_DaftarKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_DaftarKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_DaftarKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_DaftarKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Admin_DaftarKaryawan(userr.getText()).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_DaftarKaryawan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertGbr;
    private javax.swing.JLabel alert_telepon;
    private javax.swing.JLabel alert_username1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> jbox_type;
    private javax.swing.JButton register;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_idkrywn1;
    private javax.swing.JPasswordField txt_ktp;
    private javax.swing.JTextField txt_namakrywn;
    private javax.swing.JTextField txt_notelp;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}