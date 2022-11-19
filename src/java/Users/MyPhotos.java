/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.ConnectionDB;

/**
 *
 * @author nakro
 */
public class MyPhotos extends HttpServlet {
    private final Connection conn;

    public MyPhotos() throws SQLException {
        conn = ConnectionDB.getConnectionDB();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        if (session.getAttribute("role") == null) {
            response.sendRedirect("../../../");
        } else {
            String id = request.getPathInfo().substring(1);

            PreparedStatement prSt = null;

            String selectQuery = "SELECT name FROM images WHERE order_id =" + id;

            ResultSet rs = null;

            List<String> images = new ArrayList<>();

            try {
                prSt = (PreparedStatement) conn.prepareStatement(selectQuery);
            } catch (SQLException ex) {
                Logger.getLogger(MyPhotos.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                rs = prSt.executeQuery();
                System.out.println("data berhasil diambil");
            } catch (SQLException ex) {
                Logger.getLogger(MyPhotos.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                while (rs.next()) {
                    images.add(rs.getString("name"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MyPhotos.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("images", images);

            request.getRequestDispatcher("../../myPhotos.jsp").forward(request, response);
        }
    }
}
