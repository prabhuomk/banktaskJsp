<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous" />
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
<% 
if(session.getAttribute("customerID")==null && session.getAttribute("roleId")==null)
{
    request.setAttribute("error","Login first.....");
    
    RequestDispatcher rd=request.getRequestDispatcher("loginpage.jsp");
    
    rd.forward(request, response);
   
}  
%>

<%if(request.getParameter("roleId").equals("1"))
	{%>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="adminHome.jsp" data-toggle="tooltip" data-placement="top" title="All account related activities">ACCOUNT</a>
   <a class="navbar-brand" href="allcustomer.jsp" data-toggle="tooltip" data-placement="top" title="All customer related activities">CUSTOMER</a>
    <a class="navbar-brand" href="transaction.jsp" data-toggle="tooltip" data-placement="top" title="Transaction between accounts">TRANSFER</a>
    
    
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    </ul>
    <a class="navbar-brand" href="addadmin.jsp" data-toggle="tooltip" data-placement="top" title="Add admin,manager"><i class="fa fa-user-plus" aria-hidden="true"></i></a>
    <a class="navbar-brand" href="changepassword.jsp" data-toggle="tooltip" data-placement="top" title="Change Password"><i class="fa fa-cog " aria-hidden="true"></i></a>
    <a class="navbar-brand" href="logout" data-toggle="tooltip" data-placement="top" title="sign-out"><i class="fa fa-sign-out"></i></a>
  </div>
</nav>
  <%
	}
  else if(request.getParameter("roleId").equals("2"))
  {  
  %>
  
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="customerHome.jsp" data-toggle="tooltip" data-placement="top" title="Home page">HOME</a>
    <a class="navbar-brand" href="transaction.jsp" data-toggle="tooltip" data-placement="top" title="Transaction between accounts">TRANSACTION</a>
    
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    </ul>
    <a class="navbar-brand" href="changepassword.jsp" data-toggle="tooltip" data-placement="top" title="Change Password"><i class="fa fa-cog" aria-hidden="true"></i></a>
    <a class="navbar-brand" href="logout" data-toggle="tooltip" data-placement="top" title="sign-out"><i class="fa fa-sign-out"></i></a>
  </div>
</nav>
   <%
	}
 
  %>
</body>
</html>