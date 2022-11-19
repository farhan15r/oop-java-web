package Photographers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;

import DB.ConnectionDB;
import Photographers.Objects.Orders;

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
      String photographerId = (String) session.getAttribute("id");

      PreparedStatement prSt = null;
      ResultSet rs = null;

      String selectQuery = "select orders.id, orders.date, packages.name, users.full_name, orders.status_payment from orders LEFT JOIN packages ON orders.package_id = packages.id LEFT JOIN users ON orders.user_id = users.id WHERE orders.photographer_id = "
          + photographerId + " ORDER BY orders.date;";

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

      ArrayList<Orders> orders = new ArrayList<Orders>();

      try {
        while (rs.next()) {
          Orders order = new Orders();
          order.setId(rs.getInt("id"));
          order.setDate(rs.getString("date"));
          order.setPackageName(rs.getString("name"));
          order.setUsername(rs.getString("full_name"));
          order.setStatusPayment(rs.getString("status_payment"));
          orders.add(order);
        }
      } catch (SQLException ex) {
        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
      }

      request.setAttribute("orders", orders);

      request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
  }
}
