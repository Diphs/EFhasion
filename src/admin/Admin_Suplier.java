/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;

/**
 *
 * @author kitan
 */
import java.awt.Color;
import java.awt.Dimension;
import java.sql.*;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksiDB.koneksi;
public class Admin_Suplier extends javax.swing.JFrame {

    /**
     * Creates new form editdelete_supplier
     */
    public Admin_Suplier(String nama) throws SQLException {
        initComponents();
        this.setTitle("KELOLA DATA SUPPLIER");
        getDataTable(
        );
        this.alert_username1.setVisible(false);
        SetNama(nama);
        txt_idsupplier1.setEditable(false);
  
    }
      public void SetNama(String user){
        userr.setText(user);
    }
private void lebar_tabelbarang(){
   
        TableColumn kolom;
        table_supplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN); 
        kolom = table_supplier.getColumnModel().getColumn(0); 
        kolom.setPreferredWidth(80);
        kolom = table_supplier.getColumnModel().getColumn(1); 
        kolom.setPreferredWidth(240); 
        kolom = table_supplier.getColumnModel().getColumn(2); 
        kolom.setPreferredWidth(100); 
        kolom = table_supplier.getColumnModel().getColumn(3); 
        kolom.setPreferredWidth(100); 

       
    }
    private void getDataTable() throws SQLException{
        DefaultTableModel tbm = new DefaultTableModel();
        tbm.addColumn("Id Supplier");
        tbm.addColumn("Nama Supplier");
        tbm.addColumn("Alamat");
        tbm.addColumn("No Telephone");
        table_supplier.getTableHeader().setBackground(new Color(102,153,255));
        table_supplier.getTableHeader().setForeground(Color.WHITE);
        table_supplier.getTableHeader().setPreferredSize(new Dimension(20,30));
        String queryGetData = "SELECT * FROM `supplier` ;";
        java.sql.Connection conn = (Connection) koneksi.configDB();
        java.sql.PreparedStatement prs = conn.prepareStatement(queryGetData);
        java.sql.ResultSet rs = prs.executeQuery();
        while(rs.next()){
            tbm.addRow(new Object[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
            });
        }
        this.table_supplier.setModel(tbm);
        lebar_tabelbarang();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_namasupplier = new javax.swing.JTextField();
        txt_idsupplier1 = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_notelepon = new javax.swing.JTextField();
        alert_username1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_supplier = new javax.swing.JTable();
        btn_hapus = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_namasupplier.setBackground(new java.awt.Color(196, 196, 196));
        txt_namasupplier.setForeground(new java.awt.Color(0, 0, 0));
        txt_namasupplier.setCaretColor(new java.awt.Color(196, 196, 196));
        txt_namasupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namasupplierActionPerformed(evt);
            }
        });
        txt_namasupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_namasupplierKeyTyped(evt);
            }
        });
        getContentPane().add(txt_namasupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 240, 30));

        txt_idsupplier1.setBackground(new java.awt.Color(196, 196, 196));
        txt_idsupplier1.setForeground(new java.awt.Color(0, 0, 0));
        txt_idsupplier1.setCaretColor(new java.awt.Color(196, 196, 196));
        txt_idsupplier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idsupplier1ActionPerformed(evt);
            }
        });
        txt_idsupplier1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idsupplier1KeyTyped(evt);
            }
        });
        getContentPane().add(txt_idsupplier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 240, 30));

        txt_alamat.setBackground(new java.awt.Color(196, 196, 196));
        txt_alamat.setForeground(new java.awt.Color(0, 0, 0));
        txt_alamat.setCaretColor(new java.awt.Color(196, 196, 196));
        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });
        txt_alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_alamatKeyTyped(evt);
            }
        });
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 240, 30));

        txt_notelepon.setBackground(new java.awt.Color(196, 196, 196));
        txt_notelepon.setForeground(new java.awt.Color(0, 0, 0));
        txt_notelepon.setCaretColor(new java.awt.Color(196, 196, 196));
        txt_notelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noteleponActionPerformed(evt);
            }
        });
        txt_notelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noteleponKeyTyped(evt);
            }
        });
        getContentPane().add(txt_notelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 240, 30));

        alert_username1.setForeground(new java.awt.Color(255, 51, 51));
        alert_username1.setText("panjang id supplier tidak boleh melebihi 4 karakter");
        getContentPane().add(alert_username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 280, -1));

        table_supplier.setModel(new javax.swing.table.DefaultTableModel(
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
        table_supplier.setRowHeight(30);
        table_supplier.setShowVerticalLines(false);
        table_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_supplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_supplier);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 520, 160));

        btn_hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("HAPUS");
        btn_hapus.setBorder(null);
        btn_hapus.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                btn_hapusInputMethodTextChanged(evt);
            }
        });
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 290, 50));

        btn_edit.setBackground(new java.awt.Color(101, 209, 255));
        btn_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit.setText("EDIT");
        btn_hapus.setBorder(null);
        btn_edit.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                btn_editInputMethodTextChanged(evt);
            }
        });
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 280, 50));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/adm_edithapussupplier.png"))); // NOI18N
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namasupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namasupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namasupplierActionPerformed

    private void txt_namasupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namasupplierKeyTyped
        // TODO add your handling code here:
     
    }//GEN-LAST:event_txt_namasupplierKeyTyped

    private void txt_idsupplier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idsupplier1ActionPerformed
        // TODO add your handling code here:
           if(txt_idsupplier1.getText().length() < 3){
            this.alert_username1.setVisible(false);
        } else if(txt_idsupplier1.getText().length()> 3){
            this.alert_username1.setVisible(true);
        }
    }//GEN-LAST:event_txt_idsupplier1ActionPerformed

    private void txt_idsupplier1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idsupplier1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idsupplier1KeyTyped

    private void txt_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatActionPerformed

    private void txt_alamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_alamatKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatKeyTyped

    private void txt_noteleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noteleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noteleponActionPerformed

    private void txt_noteleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noteleponKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noteleponKeyTyped

    private void btn_hapusInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_btn_hapusInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusInputMethodTextChanged

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        String hapus = "DELETE FROM supplier WHERE id_supplier = '"+txt_idsupplier1.getText()+"'";
        try{
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            stm.executeUpdate(hapus);
            JOptionPane.showMessageDialog(this, "Data Berhasil di hapus");
            this.getDataTable();
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_editInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_btn_editInputMethodTextChanged
       
    }//GEN-LAST:event_btn_editInputMethodTextChanged

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            String edit = "UPDATE `supplier` SET "+ "`nama` = '" + this.txt_namasupplier.getText() +
                    "',`alamat` = '" + this.txt_alamat.getText() +"', `no_telp` = '" + this.txt_notelepon.getText() +"' WHERE `id_supplier` = '" + this.txt_idsupplier1.getText() +"';";
            System.out.println(edit);
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            stm.executeUpdate(edit);
            JOptionPane.showMessageDialog(this, "Data Berhasil di update");
            this.getDataTable();
         
                
         } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Admin_Suplier.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }//GEN-LAST:event_btn_editActionPerformed

    private void table_supplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_supplierMouseClicked
        try {
            // TODO add your handling code here:
            int getRow = this.table_supplier.rowAtPoint(evt.getPoint());
            
            String fetchDataSupplier = "SELECT * FROM `supplier` WHERE `id_supplier` = '" +this.table_supplier.getValueAt(getRow, 0) +"';";
            
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement prs = conn.prepareStatement(fetchDataSupplier);
            java.sql.ResultSet rs = prs.executeQuery();
            while(rs.next()){
                this.txt_idsupplier1.setText(rs.getString("id_supplier"));
                this.txt_namasupplier.setText(rs.getString("nama"));
                this.txt_alamat.setText(rs.getString("alamat"));
                this.txt_notelepon.setText(rs.getString("no_telp"));
            }
   
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Admin_Suplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_table_supplierMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Admin_Fitur(userr.getText()).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_Suplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Suplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Suplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Suplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Admin_Suplier(userr.getText()).setVisible(true);
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(Admin_Suplier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert_username1;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_supplier;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_idsupplier1;
    private javax.swing.JTextField txt_namasupplier;
    private javax.swing.JTextField txt_notelepon;
    public static final javax.swing.JLabel userr = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
