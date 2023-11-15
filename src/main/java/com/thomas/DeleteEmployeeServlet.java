package com.thomas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the employee ID from the request parameter
        int employeeId = Integer.parseInt(request.getParameter("id"));

        // JDBC driver and database URL
        String jdbcUrl = "jdbc:mysql://localhost:3306/employeemanagement";
        String dbUser = "root";
        String dbPassword = "Emebet@1994";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to retrieve employee details for confirmation
            String sql = "SELECT * FROM employees WHERE id = ?";

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);

            // Execute the statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Display confirmation page with employee details
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Delete Employee Confirmation</title>");
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container mt-4'>");
            out.println("<h2>Delete Employee Confirmation</h2>");

            // Display employee details for confirmation
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String designation = resultSet.getString("designation");
                double salary = resultSet.getDouble("salary");

                out.println("<p>Are you sure you want to delete the following employee?</p>");
                out.println("<p><strong>Name:</strong> " + name + "</p>");
                out.println("<p><strong>Designation:</strong> " + designation + "</p>");
                out.println("<p><strong>Salary:</strong> " + salary + "</p>");
                out.println("<form action='DeleteEmployeeServlet' method='post'>");
                out.println("<input type='hidden' name='id' value='" + employeeId + "'>");
                out.println("<button type='submit' class='btn btn-danger'>Confirm Delete</button>");
                out.println("<a href='ViewEmployeesServlet' class='btn btn-secondary'>Cancel</a>");
                out.println("</form>");
            }

            out.println("</div>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body>");
            out.println("</html>");

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>An error occurred. Please check the logs.</p>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the employee ID from the request parameter
        int employeeId = Integer.parseInt(request.getParameter("id"));

        // JDBC driver and database URL
        String jdbcUrl = "jdbc:mysql://localhost:3306/employeemanagement";
        String dbUser = "root";
        String dbPassword = "Emebet@1994";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to delete the employee
            String sql = "DELETE FROM employees WHERE id = ?";

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);

            // Execute the statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("ViewEmployeesServlet");
            } else {
                response.getWriter().println("<p>Failed to delete employee. Please try again.</p>");
            }

            // Close resources
            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>An error occurred. Please check the logs.</p>");
        }
    }
}
