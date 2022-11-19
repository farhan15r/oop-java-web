package Photographers;

import java.io.File;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DB.ConnectionDB;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10, // 10 MB
    maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class Album extends HttpServlet {
  private final Connection conn;

  public Album() throws SQLException {
    conn = ConnectionDB.getConnectionDB();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    // get id from path
    String id = request.getPathInfo().substring(1);

    // get album from db
    PreparedStatement prSt = null;

    String selectQuery = "SELECT name FROM images WHERE order_id =" + id;

    ResultSet rs = null;

    List<String> images = new ArrayList<>();

    try {
      prSt = (PreparedStatement) conn.prepareStatement(selectQuery);
    } catch (SQLException ex) {
      Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
      rs = prSt.executeQuery();
      System.out.println("data berhasil diambil");
    } catch (SQLException ex) {
      Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
      while (rs.next()) {
        images.add(rs.getString("name"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, ex);
    }

    request.setAttribute("images", images);
    request.setAttribute("id", id);

    request.getRequestDispatcher("../../album.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    // get id from path
    String id = request.getPathInfo().substring(1);
    String folder = "images/upload/" + id + "/";

    Part filePart = request.getPart("image");
    String fileName = filePart.getSubmittedFileName();

    // save image to storage
    String uploadPath = getServletContext().getRealPath("") + folder;
    File uploadDir = new File(uploadPath);

    if (!uploadDir.exists()) {
      uploadDir.mkdir();
    }

    filePart.write(uploadPath + fileName);
    System.out.println(uploadPath + fileName);

    // add to database
    PreparedStatement prSt = null;

    String insertQuery = "insert into images(order_id, name) " +
        "values (" + id + ",'" + folder + fileName + "')";

    System.out.println(insertQuery);

    try {
      prSt = conn.prepareStatement(insertQuery);
    } catch (SQLException ex) {
      Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
      prSt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, ex);
    }

    response.sendRedirect("../album/" + id);
  }
}
