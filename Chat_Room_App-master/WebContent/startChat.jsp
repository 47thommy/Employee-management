<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="mychatcss.css">
</head>
<body>
<% String name=(String)session.getAttribute("getusername"); 
if(name==null){
	RequestDispatcher rs=request.getRequestDispatcher("unauthorisedaccess.html");
	rs.forward(request, response);
}
System.out.println(name);
%>
    
<div id="mychat"></div>
<br/>
<br/>
<br/>
<div id="button">
      <input type="text" id="msg" name="message" value="">
       <input type="text" style="display:none" id="username" value="<%=name%>">
      <button id="sendmessage">Send</button>
 </div>
<script src="mychatjs.js"></script>



<script>setInterval(addtext,3000)</script>



</body>
</html>