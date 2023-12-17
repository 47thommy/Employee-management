package com.chatapp.login;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String uname;
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
    public login() {
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
	
		HttpSession s=request.getSession();
		
		
		String name=request.getParameter("uname");
		uname=name;
		String password=request.getParameter("upwd");
		String url="jdbc:mysql://localhost:3306/users";
		
		
		String user="root";
		String pwd="durga";
		try {
			con=DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query="SELECT * FROM USER_INFO WHERE NAME=? AND PASSWORD=?";
		try {
			pst=con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, password);
			rs=pst.executeQuery();
			
			if(rs.next())
			{
				System.out.println("this1");
				request.setAttribute("status", "successful");
				s.setAttribute("getusername",uname);

				RequestDispatcher rs=request.getRequestDispatcher("state.jsp");
				rs.forward(request, response);
			}
			else
			{
				System.out.println("this2");
				request.setAttribute("status","fail");
				s.setAttribute("getusername", uname);

				RequestDispatcher rs=request.getRequestDispatcher("state.jsp");
				rs.forward(request, response);
				
			}
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

}
