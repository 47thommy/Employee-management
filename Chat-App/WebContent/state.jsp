<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style_login.css">
</head>
<body id="state_body">
<%
String name=(String)session.getAttribute("getusername");
if(name==null)
{
	RequestDispatcher rs=request.getRequestDispatcher("unauthorisedaccess.html");
	rs.forward(request, response);
}
String s=(String)request.getAttribute("status");
%>
<h1 id="state_jsp">Welcome <%= session.getAttribute("getusername")%> Login <%=s%></h1>

<div id="anchortag">
<%if(s=="successful"){%>
<a href="startChat.jsp">Start Chat</a>
<%} %>
</div>


</body>
</html>