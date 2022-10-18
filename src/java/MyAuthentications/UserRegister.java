/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyAuthentications;

import DB.ConnectionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nakro
 */
public class UserRegister extends HttpServlet {
    private final Connection conn;
    
    public UserRegister() throws SQLException {
        conn = ConnectionDB.getConnectionDB();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String no_tlp = request.getParameter("no_tlp");
        String password = request.getParameter("password");
       
        PreparedStatement prSt = null;
        
        String insertQuery = "insert into users(username, full_name, email, no_tlp, password) "
                + "values ('"
                +username+"','"
                +name+"','"
                +email+"','"
                +no_tlp+"','"
                +password+"')";
        
        
        try {
            prSt = conn.prepareStatement(insertQuery);
        } catch (SQLException ex) {
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            prSt.executeUpdate();
            System.out.println("data berhasil ditambah");
        } catch (SQLException ex) {
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("login");
    }
}
