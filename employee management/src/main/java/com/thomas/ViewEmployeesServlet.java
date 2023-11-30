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

@WebServlet("/ViewEmployeesServlet")
public class ViewEmployeesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String jdbcUrl = "jdbc:mysql://localhost:3306/employeemanagement";
        String dbUser = "root";
        String dbPassword = "Emebet@1994";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to retrieve all employees
            String sql = "SELECT * FROM employees";

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute the statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Include the viewEmployees.html content
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Employee List</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container mt-4'>");
            out.println("<h2 class='mb-4'>Employee List</h2>");
            out.println("<table class='table table-hover'>");
            out.println("<thead class='thead-dark'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Designation</th><th>Salary</th><th>Edit</th><th>Delete</th></tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Dynamic content (employee data)
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String designation = resultSet.getString("designation");
                double salary = resultSet.getDouble("salary");

                out.println("<tr class='table-light'>");
                out.println("<td>" + id + "</td><td>" + name + "</td><td>" + designation + "</td><td>" + salary + "</td>");
                out.println("<td><a href='EditEmployeeServlet?id=" + id + "' class='btn btn-primary btn-sm'>Edit</a></td>");
                out.println("<td><a href='DeleteEmployeeServlet?id=" + id + "' class='btn btn-danger btn-sm'>Delete</a></td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");

            // Add Employee button
            out.println("<a href='AddEmployees.html' class='btn btn-success'>Add New Employee</a>");

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
}
