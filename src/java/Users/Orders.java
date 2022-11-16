/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import DB.ConnectionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nakro
 */
public class Orders extends HttpServlet {
  private final Connection conn;

  public Orders() throws SQLException {
    conn = ConnectionDB.getConnectionDB();
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    HttpSession session = request.getSession();

    if (session.getAttribute("role") == null) {
      response.sendRedirect("../");
    } else {
      String userId = (String) session.getAttribute("id");
      String date = request.getParameter("date");
      String packageId = request.getParameter("package");
      int price = 0;

      PreparedStatement prSt = null;
      ResultSet rs = null;

      String selectQuery = "select price from packages where id = " + packageId + ";";

      System.out.println(selectQuery);

      try {
        prSt = (PreparedStatement) conn.prepareStatement(selectQuery);
      } catch (SQLException ex) {
        Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
      }

      try {
        rs = prSt.executeQuery();
      } catch (SQLException ex) {
        Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
      }

      try {
        if (rs.next()) {
          price = rs.getInt("price");
          System.out.println(price);
        }
      } catch (SQLException ex) {
        Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
      }

      // insert into orders
      prSt = null;

      String insertQuery = "insert into orders (user_id, package_id, date, price, status_order, status_payment) " +
          "values (" +
          userId + ", " +
          packageId + ", '" +
          date + "', " +
          price + ", " +
          "'Waiting', 'Pending');"; // default status order = waiting, status payment = pending

      System.out.println(insertQuery);

      try {
        prSt = conn.prepareStatement(insertQuery);
      } catch (SQLException ex) {
        Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
      }

      try {
        prSt.executeUpdate();
      } catch (SQLException ex) {
        Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
      }

      response.sendRedirect("../../dashboard");
    }
  }
}
