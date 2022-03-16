<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="change.css" type="text/css"></link>

<script type="text/javascript">  

function passform()
{  
var pass=document.changeform.password.value; 
var newpass=document.changeform.newpass.value; 
var checkpass=document.changeform.checkpass.value; 

  
if (pass.length<8 || newpass.length<8 ||checkpass.length<8)
{  
  alert("password length should be greater or equal to 8");  
  
  return false;  
 
  
}
else if(newpass!=checkpass)
{  
  alert("new password and confirm password are not not equal");  
  
  return false;  
 
}
}
</script>
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
<%
if(session.getAttribute("customerID")==null &&session.getAttribute("roleId")!=null)
{
%>
<jsp:include page="sidebar.jsp" >
  <jsp:param name="roleId" value="<%=1%>" />
</jsp:include>
<%
}
else if(session.getAttribute("customerID")!=null &&session.getAttribute("roleId")!=null)
{
%>
<jsp:include page="sidebar.jsp" >
  <jsp:param name="roleId" value="<%=2%>" />
</jsp:include>
<%
}%>

<form name="changeform" class="data" action="forget?type=reset" method="post" onSubmit="return passform()">

		<h5 class="title"><b>CHANGE PASSWORD</b></h5>
		
		<label for="current" style=margin-top:5px;>CURRENT PASSWORD</label> 
		
		<input id="current" type="password" class="entry" name="password"> 
	
		<label for="new">NEW PASSWORD</label>
		
		<input id="new" type="password" class="entry" name="newpass">  
		
		
		<label for="resset">CONFIRM PASSWORD</label>
		
		<input id="reset" type="password" class="entry" name="checkpass"> 
 
		
		<input type="submit" class="click" value="CHANGE" >
		
	</form>
	<%  if(request.getAttribute("error")!=null)
	    {
	%>
	    <h3 style=text-align:center;color:red;><b><%=request.getAttribute("error")%></b></h3>
	    
	    <%
	    }
	      else if(request.getAttribute("success")!=null)
	    {%>
	         <h3 style=text-align:center;color:green;><b><%=request.getAttribute("success")%></b></h3>
	         <%
	         }%>
</body>
</html>