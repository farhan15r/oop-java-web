/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nakro
 */
public class ConnectionDB {
    private static Connection connection=null;
    
    public static Connection getConnectionDB() throws SQLException {
        if (connection != null){
            return connection;
        } else {
            try {
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/db_pbo";
                String user = "root";
                String password = "";
                
                Class.forName(driver);
                connection = (Connection) DriverManager.getConnection(url, user, password);
                
                System.out.println("ex");
                
                return connection;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
