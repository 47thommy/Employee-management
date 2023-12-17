package com.chatapp.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pst;
	PreparedStatement pst1;

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

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		// TODO Auto-generated method stub
		
		
		
		String name=request.getParameter("uname");
		String email=request.getParameter("uemail");
		String password=request.getParameter("upassword");
		System.out.println(name+email+password);
		String url="jdbc:mysql://localhost:3306/users";
		String user="root";
		String pwd="durga";
		try
		{
			con=DriverManager.getConnection(url, user, pwd);
			
			String query="SELECT * FROM USER_INFO WHERE NAME=? AND PASSWORD=?";
			pst=con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, password);
			rs=pst.executeQuery();
			if(rs.next())
			{
				System.out.println("akhane");
				request.setAttribute("registerstate","You are already registered with us");
				RequestDispatcher rs=request.getRequestDispatcher("registerstatus.jsp");
				rs.forward(request, response);
			}
			
			
			
			
			
			else
			{
			String query1="INSERT INTO USER_INFO (NAME,EMAIL,PASSWORD) VALUES(?,?,?)";
			pst=con.prepareStatement(query1);
			pst.setString(1,name);
			pst.setString(2,email);
			pst.setString(3,password);
			System.out.println(pst.execute());
			request.setAttribute("registerstate","yepiee!! Registered Log in to enter chat room");
			RequestDispatcher rs=request.getRequestDispatcher("registerstatus.jsp");
			rs.forward(request, response);
			
			}
			
			
			
			
		}
		catch (Exception e) {}
		
	}
}
