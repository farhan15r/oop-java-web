/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

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

import com.mysql.jdbc.PreparedStatement;

import DB.ConnectionDB;
import Objects.Packages;

/**
 *
 * @author nakro
 */
public class Index extends HttpServlet {
  // database connection
  private final Connection conn;

  public Index() throws SQLException {
    conn = ConnectionDB.getConnectionDB();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    System.out.println("Home Page");

    PreparedStatement prSt = null;
    ResultSet rs = null;

    String selectQuery = "select id, name, price, time, desc_1, desc_2, desc_3, desc_4 from packages;";

    System.out.println(selectQuery);

    try {
      prSt = (PreparedStatement) conn.prepareStatement(selectQuery);
    } catch (SQLException ex) {
      Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
      rs = prSt.executeQuery();
      System.out.println("data berhasil diambil");
    } catch (SQLException ex) {
      Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
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
      Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
    }

    // set packeges to request
    request.setAttribute("packages", packages);

    request.getRequestDispatcher("home.jsp").forward(request, response);
  }
}
