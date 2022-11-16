/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admins;

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

import Admins.Objects.Orders;
import DB.ConnectionDB;

/**
 *
 * @author nakro
 */
public class Dashboard extends HttpServlet {

    private final Connection conn;

    public Dashboard() throws SQLException {
        conn = ConnectionDB.getConnectionDB();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        Object role = "admin";

        if (role != session.getAttribute("role")) {
            response.sendRedirect("../");
        } else {
            // db connection
            PreparedStatement prSt = null;
            ResultSet rs = null;

            String selectQuery = "select orders.id, users.username, orders.date, packages.name AS 'packageName', orders.status_order, orders.status_payment FROM orders LEFT JOIN packages ON orders.package_id = packages.id LEFT JOIN users ON orders.user_id = users.id ORDER BY orders.date;";

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

            List<Orders> orders = new ArrayList<>();

            try {
                while (rs.next()) {
                    Orders orderData = new Orders();
                    orderData.setId(rs.getInt("id"));
                    orderData.setUsername(rs.getString("username"));
                    orderData.setDate(rs.getString("date"));
                    orderData.setPackageName(rs.getString("packageName"));
                    orderData.setStatusOrder(rs.getString("status_order"));
                    orderData.setStatusPayment(rs.getString("status_payment"));
                    orders.add(orderData);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("orders", orders);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }
}
