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

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String jdbcUrl = "jdbc:mysql://localhost:3306/employeemanagement";
        String dbUser = "root";
        String dbPassword = "Emebet@1994";

        // Get the employee ID from the request parameter
        int employeeId = Integer.parseInt(request.getParameter("id"));

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to retrieve the details of the specific employee
            String sql = "SELECT * FROM employees WHERE id = ?";

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);

            // Execute the statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Display the edit form with the employee details
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Edit Employee</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container mt-4'>");
            out.println("<h2>Edit Employee</h2>");
            out.println("<form action='EditEmployeeServlet' method='post'>");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String designation = resultSet.getString("designation");
                double salary = resultSet.getDouble("salary");

                // Populate the form with the current employee details
                out.println("<div class='mb-3'>");
                out.println("<label for='name' class='form-label'>Name:</label>");
                out.println("<input type='text' class='form-control' id='name' name='name' value='" + name + "' required>");
                out.println("</div>");

                out.println("<div class='mb-3'>");
                out.println("<label for='designation' class='form-label'>Designation:</label>");
                out.println("<input type='text' class='form-control' id='designation' name='designation' value='" + designation + "' required>");
                out.println("</div>");

                out.println("<div class='mb-3'>");
                out.println("<label for='salary' class='form-label'>Salary:</label>");
                out.println("<input type='number' class='form-control' id='salary' name='salary' value='" + salary + "' required>");
                out.println("</div>");

                // Include the employee ID as a hidden field
                out.println("<input type='hidden' name='id' value='" + employeeId + "'>");

                // Submit button
                out.println("<button type='submit' class='btn btn-primary'>Update Employee</button>");
            }

            out.println("</form>");
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
            out.println("<p>An error occurred. Please check the logs.</p>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // JDBC driver and database URL
        String jdbcUrl = "jdbc:mysql://localhost:3306/employeemanagement";
        String dbUser = "root";
        String dbPassword = "Emebet@1994";

        // Get data from the form submission
        int employeeId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        double salary = Double.parseDouble(request.getParameter("salary"));

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to update the employee information
            String sql = "UPDATE employees SET name = ?, designation = ?, salary = ? WHERE id = ?";

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, designation);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setInt(4, employeeId);

            // Execute the statement
            int rowsAffected = preparedStatement.executeUpdate();

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Edit Employee</title>");
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container mt-4'>");

            if (rowsAffected > 0) {
                out.println("<p class='alert alert-success'>Employee updated successfully!</p>");

                // Redirect to ViewEmployeesServlet
                response.sendRedirect("ViewEmployeesServlet");
            } else {
                out.println("<p class='alert alert-danger'>Failed to update employee. Please try again.</p>");
            }

            out.println("</div>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body>");
            out.println("</html>");

            // Close resources
            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p>An error occurred. Please check the logs.</p>");
        }
    }

}
