/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksiDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Windows 10
 */
public class koneksi {
    private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException {
        try{
            String url = "jdbc:mysql://localhost:3306/appbaju";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url,user,pass);
            System.out.println("Koneksi Done");
        
        }catch(Exception e){
            System.err.println("koneksi gagal"+ e.getMessage());
        
        }
    return mysqlconfig;
        }
}
