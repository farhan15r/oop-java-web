/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.io.IOException;
import java.sql.Connection;
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

import com.mysql.jdbc.PreparedStatement;

import DB.ConnectionDB;
import Users.Objects.Packages;
import Users.Objects.Orders;

/**
 *
 * @author nakro
 */
public class Dashboard extends HttpServlet {
    // database connection
    private final Connection conn;

    public Dashboard() throws SQLException {
        conn = ConnectionDB.getConnectionDB();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        if (session.getAttribute("role") == null) {
            response.sendRedirect("../");
        } else {
            // get user id
            String userId = (String) session.getAttribute("id");

            PreparedStatement prSt = null;
            ResultSet rs = null;

            String selectQuery = "select id, name, price, time, desc_1, desc_2, desc_3, desc_4 from packages;";

            System.out.println(selectQuery);

            try {
                prSt = (PreparedStatement) conn.prepareStatement(selectQuery);
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                rs = prSt.executeQuery();
                System.out.println("data berhasil diambil");
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            // list packages
            List<Packages> packages = new ArrayList<>();

            try {
                while (rs.next()) {
                    // get data from database
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    String time = rs.getString("time");
                    String desc_1 = rs.getString("desc_1");
                    String desc_2 = rs.getString("desc_2");
                    String desc_3 = rs.getString("desc_3");
                    String desc_4 = rs.getString("desc_4");

                    // add packeges to list
                    packages.add(new Packages(id, name, price, time, desc_1, desc_2, desc_3, desc_4));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            selectQuery = "select orders.id, orders.date, packages.name AS 'packageName', orders.price, orders.status_order, photographers.full_name FROM orders LEFT JOIN packages ON orders.package_id = packages.id LEFT JOIN photographers ON orders.photographer_id = photographers.id WHERE orders.user_id="
                    + userId + " ORDER BY orders.date;";

            System.out.println(selectQuery);

            try {
                prSt = (PreparedStatement) conn.prepareStatement(selectQuery);
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                rs = prSt.executeQuery();
                System.out.println("data berhasil diambil");
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            // list orders
            List<Orders> orders = new ArrayList<>();

            try {
                while (rs.next()) {
                    Orders order = new Orders();
                    // get data from database
                    order.setId(rs.getInt("id"));
                    order.setDate(rs.getString("date"));
                    order.setPackageName(rs.getString("packageName"));
                    order.setPrice(rs.getInt("price"));
                    order.setStatusOrder(rs.getString("status_order"));
                    order.setPhotographer(rs.getString("full_name"));

                    // add packeges to list
                    orders.add(order);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            // set packeges to request
            request.setAttribute("packages", packages);
            // set orders to request
            request.setAttribute("orders", orders);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }
}
