/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyAuthentications;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nakro
 */
public class UserLogin extends HttpServlet {
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
        // String password = request.getParameter("password");

        HttpSession session = request.getSession(); /* Creating a new session */

        session.setAttribute("username", username);
        session.setAttribute("role", "user");

        // session.invalidate();
        response.sendRedirect("dashboard");
    }
}
