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
import Users.Objects.Albums;

/**
 *
 * @author nakro
 */
public class MyAlbums extends HttpServlet {
    private final Connection conn;

    public MyAlbums() throws SQLException {
        conn = ConnectionDB.getConnectionDB();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        if (session.getAttribute("role") == null) {
            response.sendRedirect("../../");
        } else {
            // get user id
            String userId = (String) session.getAttribute("id");

            // get user albums
            PreparedStatement prSt = null;

            ResultSet rs = null;

            String selectQuery = "SELECT orders.date, orders.id FROM `images` LEFT JOIN orders ON orders.id = images.order_id WHERE orders.user_id = "
                    + userId + " GROUP BY images.order_id;";

            System.out.println(selectQuery);

            try {
                prSt = conn.prepareStatement(selectQuery);
                rs = prSt.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(MyAlbums.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<Albums> albums = new ArrayList<>();

            try {
                while (rs.next()) {
                    // get data from database
                    String date = rs.getString("date");
                    String id = rs.getString("id");

                    // add packeges to list
                    albums.add(new Albums(date, id));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MyAlbums.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("albums", albums);

            request.getRequestDispatcher("../myAlbums.jsp").forward(request, response);
        }
    }
}
