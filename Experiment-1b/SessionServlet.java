package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Experiment 1b - Session Tracking Servlet.
 *
 * Demonstrates:
 *   • Login form (GET when no session)
 *   • Session creation on valid login (POST)
 *   • Welcome page with session info (GET when logged in)
 *   • Logout via session invalidation
 *
 * Hardcoded credentials: admin / password
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ---------- GET ---------- */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Handle logout
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) session.invalidate();
            response.sendRedirect("SessionServlet");
            return;
        }

        // Check existing session
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            // ---- Logged-in view ----
            out.println("<html><head><title>Welcome</title></head><body>");
            out.println("<h2>Welcome, " + session.getAttribute("username") + "!</h2>");
            out.println("<p><a href='SessionServlet?action=logout'>Logout</a></p>");
            out.println("<h3>Session Information:</h3>");
            out.println("<p>Session ID: "        + session.getId() + "</p>");
            out.println("<p>Creation Time: "     + new java.util.Date(session.getCreationTime()) + "</p>");
            out.println("<p>Last Accessed Time: " + new java.util.Date(session.getLastAccessedTime()) + "</p>");
            out.println("</body></html>");
        } else {
            // ---- Login form ----
            out.println("<html><head><title>Login</title></head><body>");
            out.println("<h2>Login</h2>");
            out.println("<form method='post' action='SessionServlet'>");
            out.println("Username: <input type='text'     name='username'><br><br>");
            out.println("Password: <input type='password' name='password'><br><br>");
            out.println("<input type='submit' value='Login'>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }

    /* ---------- POST: process login ---------- */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "password".equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
        }
        // Redirect back — GET handler decides what to show
        response.sendRedirect("SessionServlet");
    }
}
