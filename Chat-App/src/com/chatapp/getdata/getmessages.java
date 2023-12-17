package com.chatapp.getdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getmessages")
public class getmessages extends HttpServlet {
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
    public getmessages() {
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
		System.out.println("this will give me new data");
		
		
		
	    	String url="jdbc:mysql://localhost:3306/users";
			String user="root";
			String pwd="durga";
		    response.setContentType("text/html");
			try {
				con=DriverManager.getConnection(url, user, pwd);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			String query="SELECT * FROM MESSAGE";
			try
			{
				pst = con.prepareStatement(query);
				rs=pst.executeQuery();
				while (rs.next())
				{
					String name=rs.getString(1);
					String message=rs.getString(2);
					String time=rs.getString(3);
					System.out.println(name+message+time);
					PrintWriter out=response.getWriter();
					out.print("<p>"+name+"-->"+"<g>"+message+"</g>"+"<br/>"+"<small>"+time+"</small>"+"</p>");
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
	}

}
