/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyAuthentications;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;

import DB.ConnectionDB;

/**
 *
 * @author nakro
 */
public class UserLogin extends HttpServlet {
    private final Connection conn;

    public UserLogin() throws SQLException {
        conn = ConnectionDB.getConnectionDB();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        if (session.getAttribute("role") != null) {
            response.sendRedirect("../"); // home
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PreparedStatement prSt = null;
        ResultSet rs = null;

        String selectQuery = "select * from users where " +
                "username = '" + username + "' " +
                "and password = '" + password + "';";

        System.out.println(selectQuery);

        try {
            prSt = (PreparedStatement) conn.prepareStatement(selectQuery);
        } catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = prSt.executeQuery();
            System.out.println("data berhasil diambil");
        } catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession(); /* Creating a new session */
        try {
            if (rs.next()) {
                System.out.println("akun ditemukan");
                // if admin exist
                session.setAttribute("username", username);
                session.setAttribute("role", "user");

                // session.invalidate(); /* Invalidating the session */
                response.sendRedirect("dashboard");
            } else {
                System.out.println("akun tidak ditemukan");
                // if admin not exist
                session.setAttribute("login", "failed");
                response.sendRedirect("login");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
