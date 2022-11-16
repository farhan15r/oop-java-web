/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admins;

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

import Admins.Objects.Order;
import Admins.Objects.Packages;
import DB.ConnectionDB;

/**
 *
 * @author nakro
 */
public class OrderUpdate extends HttpServlet {
    private final Connection conn;

    public OrderUpdate() throws SQLException {
        conn = ConnectionDB.getConnectionDB();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // get id from path
        String id = request.getPathInfo().substring(1);
        System.out.println(id);

        HttpSession session = request.getSession();

        Object role = "admin";

        if (role != session.getAttribute("role")) {
            response.sendRedirect("../../../");
        } else {
            PreparedStatement prSt = null;
            ResultSet rs = null;

            // get packages from db
            String selectQuery = "SELECT id, name, price FROM packages;";

            System.out.println(selectQuery);

            try {
                prSt = conn.prepareStatement(selectQuery);
                rs = prSt.executeQuery();

                List<Packages> packages = new ArrayList<>();

                while (rs.next()) {
                    Packages pack = new Packages();

                    pack.setId(rs.getInt("id"));
                    pack.setName(rs.getString("name"));
                    pack.setPrice(rs.getInt("price"));

                    packages.add(pack);
                }

                request.setAttribute("packages", packages);
            } catch (SQLException ex) {
                Logger.getLogger(OrderUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }

            // get order from db
            selectQuery = "SELECT orders.id, orders.date, orders.price, users.full_name AS 'name', users.username, orders.package_id, orders.status_order, orders.status_payment FROM orders LEFT JOIN users ON users.id = orders.user_id  WHERE orders.id = "
                    + id + ";";
            System.out.println(selectQuery);

            try {
                prSt = conn.prepareStatement(selectQuery);
                rs = prSt.executeQuery();

                Order order = new Order();

                while (rs.next()) {

                    order.setId(rs.getInt("id"));
                    order.setDate(rs.getString("date"));
                    order.setPrice(rs.getInt("price"));
                    order.setName(rs.getString("name"));
                    order.setUsername(rs.getString("username"));
                    order.setPackageId(rs.getInt("package_id"));
                    order.setStatusOrder(rs.getString("status_order"));
                    order.setStatusPayment(rs.getString("status_payment"));
                }

                request.setAttribute("order", order);
            } catch (SQLException ex) {
                Logger.getLogger(OrderUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("../../orderUpdate.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // get id from path
        String id = request.getPathInfo().substring(1);
        System.out.println(id);

        // get data from form
        String date = request.getParameter("date");
        String packageId = request.getParameter("package");
        // buat fotografer nanti
        String statusPayment = request.getParameter("status_payment");
        String statusOrder = request.getParameter("status_order");
        int price = 0; // from databse

        // get price from db
        String selectQuery = "SELECT price FROM packages WHERE id = " + packageId + ";";
        System.out.println(selectQuery);

        PreparedStatement prSt = null;
        ResultSet rs = null;

        try {
            prSt = conn.prepareStatement(selectQuery);
            rs = prSt.executeQuery();

            while (rs.next()) {
                price = rs.getInt("price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        // update data to db

        String updateQuery = "UPDATE orders SET date = '" + date + "', price = " + price + ", package_id = " + packageId
                + ", status_payment = '" + statusPayment + "', status_order = '" + statusOrder + "' WHERE id = " + id
                + ";";

        System.out.println(updateQuery);

        try {
            prSt = conn.prepareStatement(updateQuery);
            prSt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("../order/" + id);
    }
}
