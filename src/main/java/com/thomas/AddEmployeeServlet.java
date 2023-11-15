package com.thomas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // JDBC driver and database URL
        String jdbcUrl = "jdbc:mysql://localhost:3306/employeemanagement";
        String dbUser = "root";
        String dbPassword = "Emebet@1994";

        // HTTP request parameters
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        double salary = Double.parseDouble(request.getParameter("salary"));

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to insert a new employee
            String sql = "INSERT INTO employees (name, designation, salary) VALUES (?, ?, ?)";

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, designation);
            preparedStatement.setDouble(3, salary);

            // Execute the statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Employee added successfully, redirect to ViewEmployeesServlet
                response.sendRedirect("ViewEmployeesServlet");
            } else {
                out.println("<p>Failed to add employee. Please try again.</p>");
            }

            // Close resources
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>An error occurred. Please check the logs.</p>");
        }
    }
}
