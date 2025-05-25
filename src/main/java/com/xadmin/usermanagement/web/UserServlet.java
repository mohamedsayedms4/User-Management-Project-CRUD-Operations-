package com.xadmin.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xadmin.usermanagement.dao.USerDAO;
import com.xadmin.usermanagement.model.User;

/**
 * Servlet controller to handle all user-related requests.
 * It delegates requests to appropriate methods to perform CRUD operations
 * and then forwards or redirects to JSP pages for rendering.
 * 
 * Author: Mohamed Sayed
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // DAO object to perform database operations for users
    private USerDAO userDAO;

    /**
     * Initialize the servlet and create the DAO instance.
     */
    public void init() {
        userDAO = new USerDAO();
    }

    /**
     * Handles POST requests by delegating to doGet method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Handles GET requests.
     * Routes the request to different methods depending on the URL path.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the request action from the URL pattern (e.g. /new, /insert)
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    // Show form to create a new user
                    showNewForm(request, response);
                    break;
                case "/insert":
                    // Insert new user into the database
                    insertUser(request, response);
                    break;
                case "/delete":
                    // Delete an existing user by ID
                    deleteUser(request, response);
                    break;
                case "/edit":
                    // Show form to edit an existing user
                    showEditForm(request, response);
                    break;
                case "/update":
                    // Update user data in the database
                    updateUser(request, response);
                    break;
                default:
                    // By default, list all users
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            // If SQL exception occurs, wrap it into ServletException
            throw new ServletException(ex);
        }
    }

    /**
     * Retrieve all users from the database,
     * set them as a request attribute,
     * and forward to the user list JSP page.
     */
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Forward the request to the user form JSP page for creating a new user.
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Retrieve user by ID, set user as a request attribute,
     * and forward to the user form JSP page for editing.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    /**
     * Collect user data from the request,
     * create a new user object,
     * insert it into the database,
     * and redirect to the user list page.
     */
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    /**
     * Collect updated user data from the request,
     * create a user object,
     * update the user in the database,
     * and redirect to the user list page.
     */
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(id, name, email, country);
        userDAO.updateUser(user);
        response.sendRedirect("list");
    }

    /**
     * Delete the user with the given ID from the database,
     * then redirect to the user list page.
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
}
