package com.chatapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mychatcontroller")
public class mychatcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    public mychatcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String msg=request.getParameter("message");
		java.util.Date date = new java.util.Date();
		String time=date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        String url="jdbc:mysql://localhost:3306/users";
		
		
		String user="root";
		String pwd="durga";
		
		
		try {
			con=DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		String query="INSERT INTO MESSAGE (NAME,MESSAGE,TIME) VALUES(?,?,?)";
		try {
		pst=con.prepareStatement(query);
		pst.setString(1, name);
		pst.setString(2, msg);
		pst.setString(3, time);
		pst.execute();
		}
		catch (Exception e) {
		
		}
		
		doGet(request, response);
	}

}
