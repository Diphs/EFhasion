/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package login;

import admin.Admin_Fitur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import interface_pkgefashion.Login;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import karyawan.start_Home;
import koneksiDB.koneksi;

/**
 *
 * @author kitan
 */
public class LoginApp extends javax.swing.JFrame implements Login {

   
    public LoginApp() {

        initComponents();
        gantiIcon();
        this.ic_hidden.setVisible(false);
        ic_show.setVisible(true);
        alert.setVisible(false);
        this.setTitle("LOGIN APP");
        
 
    }
    public void gantiIcon(){
//        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("EfashionStart.png"));
//        ImageIcon icon1 = new ImageIcon();
//        setIconImage(icon1.getImage());

    try {
        this.setIconImage(ImageIO.read(new File("C:\\Users\\Windows 10\\Documents\\NetBeansProjects\\Efashion_Final\\src\\image\\EfashionStart.png")));
    }
    catch (IOException exc) {
        
        exc.printStackTrace();
    }
        
    
    }
    public void peringatan(){
        if(!txt_username1.getText().isEmpty() || !txt_password.getText().isEmpty()){
            alert.setVisible(false);
        }  
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_masuk = new javax.swing.JButton();
        txt_username1 = new javax.swing.JTextField();
        ic_show = new javax.swing.JLabel();
        ic_hidden = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        alert = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_masuk.setBackground(new java.awt.Color(255, 101, 132));
        btn_masuk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_masuk.setForeground(new java.awt.Color(255, 255, 255));
        btn_masuk.setBorder(null);
        btn_masuk.setText("MASUK");
        btn_masuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_masukActionPerformed(evt);
            }
        });
        getContentPane().add(btn_masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 200, 50));

        txt_username1.setBackground(new java.awt.Color(234, 234, 234));
        txt_username1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_username1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_username1ActionPerformed(evt);
            }
        });
        txt_username1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_username1KeyTyped(evt);
            }
        });
        getContentPane().add(txt_username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 260, 30));

        ic_show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/show_1.png"))); // NOI18N
        ic_show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_showMouseClicked(evt);
            }
        });
        getContentPane().add(ic_show, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, 50, 50));

        ic_hidden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_hidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hidden_1.png"))); // NOI18N
        ic_hidden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_hiddenMouseClicked(evt);
            }
        });
        getContentPane().add(ic_hidden, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, 50, 50));

        txt_password.setBackground(new java.awt.Color(255, 255, 255));
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_passwordKeyTyped(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 260, 30));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("apabila sudah terdaftar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 270, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Atau Anda dapat menggunakan Login dengan kartu ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 310, 30));

        jButton1.setBackground(new java.awt.Color(101, 107, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SCAN KARTU");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, 190, 40));

        alert.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        alert.setForeground(new java.awt.Color(255, 0, 0));
        alert.setText("Gagal Login! Pastikan Username dan Sandi Benar!");
        getContentPane().add(alert, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 101, 132));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/EfashionStart.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 280, 240));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Tech_Otakus.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 180, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/01login.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 584));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    private void txt_username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_username1ActionPerformed

    private void btn_masukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_masukActionPerformed
       loginApp();
    }//GEN-LAST:event_btn_masukActionPerformed

    private void ic_showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_showMouseClicked
        // TODO add your handling code here:
        this.showPassword();

    }//GEN-LAST:event_ic_showMouseClicked

    private void ic_hiddenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_hiddenMouseClicked
        // TODO add your handling code here:
      this.hiddenPassword();

    }//GEN-LAST:event_ic_hiddenMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login_RFID_KTP().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_username1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_username1KeyTyped
        // TODO add your handling code here:
        peringatan();
    }//GEN-LAST:event_txt_username1KeyTyped

    private void txt_passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyTyped
        // TODO add your handling code here:
        peringatan();
    }//GEN-LAST:event_txt_passwordKeyTyped

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
            java.util.logging.Logger.getLogger(LoginApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert;
    private javax.swing.JButton btn_masuk;
    private javax.swing.JLabel ic_hidden;
    private javax.swing.JLabel ic_show;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void loginApp() {
        try {
            String query = "SELECT `login`.`username`,`login`.`password`,`karyawan`.`nama_krywn`,`karyawan`.`type` FROM `login` JOIN `karyawan` ON `karyawan`.`idkrywn` = `login`.`idkrywn`\n" +
"WHERE login.username = '"+txt_username1.getText()+"' AND login.password = '"+txt_password.getText()+"'";
            java.sql.Connection conn = (Connection)koneksi.configDB();
            java.sql.Statement statementObj = conn.createStatement();
            java.sql.ResultSet rs = statementObj.executeQuery(query);
            int t = 0; 
            if(rs.next()){
                String psw = new String(txt_password.getPassword());
                
                if(rs.getString("username").equals(txt_username1.getText()) &&
                        
                        rs.getString("password").equals(psw)){
                    
                    if(rs.getString("type").equals("Bos")){
                        
                        System.out.printf("%s%n","Switch to owner frame");
                   
                        this.setVisible(false);
                        new Admin_Fitur(rs.getString(1)).setVisible(true);
                        
                    } else if(rs.getString("type").equals("karyawan")||rs.getString("type").equals("Karyawan")){
                        new start_Home(rs.getString(1)).setVisible(true);
                        this.setVisible(false);
                        System.out.printf("%s%n","Switch to member frame ");
                 
                    } 
                } 
            } else {
                txt_username1.setText("");
                txt_password.setText("");
                alert.setVisible(true);
            }
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    


    @Override
    public void showPassword() {
          this.ic_show.setVisible(false);
        this.ic_show.setEnabled(false);
        this.ic_hidden.setVisible(true);
        this.ic_hidden.setEnabled(true);
        this.txt_password.setEchoChar(((char)0));

    }

    @Override
    public void hiddenPassword(){
          this.ic_hidden.setVisible(false);
       this.ic_hidden.setEnabled(false);
       this.ic_show.setVisible(true);
       this.ic_show.setEnabled(true);
       this.txt_password.setEchoChar('*');
    }
}
