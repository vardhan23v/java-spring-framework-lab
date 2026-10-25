package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Experiment 1a - Simple Servlet handling GET and POST requests.
 *
 * GET  → Shows an HTML form (name + password fields).
 * POST → Reads the submitted parameters and displays them.
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ---------- GET: render the form ---------- */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Submit Your Details</h2>");
        out.println("<form method='POST' action='SimpleServlet'>");
        out.println("Name: <input type='text' name='name'><br><br>");
        out.println("Password: <input type='password' name='password'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    /* ---------- POST: display submitted data ---------- */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name     = request.getParameter("name");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Submitted Data</h2>");
        out.println("Name: "     + name     + "<br>");
        out.println("Password: " + password + "<br>");
        out.println("</body></html>");
    }
}
