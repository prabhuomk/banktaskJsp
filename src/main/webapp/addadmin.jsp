<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="addadmin.css" type="text/css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
	
<script type="text/javascript">  

function addform()
{  
var mailId=document.adminform.mailId.value; 

  
if (mailId=="" || mailId==null)
{  
  alert("mail can't be empty");  
  
  return false;  
}


}
</script>
	
</head>
<body>
<%

if(session.getAttribute("roleId")==null)
{
    request.setAttribute("error","Login first.....");
    
    RequestDispatcher rd=request.getRequestDispatcher("loginpage.jsp");
    
    rd.forward(request, response);
   
}  

%>
<jsp:include page="sidebar.jsp" >
  <jsp:param name="roleId" value="<%=1%>" />
</jsp:include>


<form name="adminform" class="data" action="admin" method="post" onsubmit="return addform()">

		<h5 class="title"><b>ADD ADMIN/MANGER</b></h5>
		
		<label for="mail">MAIL-ID</label> 
	
		<input id="mail" type="text" class="entry" name="mailId"> 
		
		<input class="click" type="submit" name="add" value="ADD">
		
		</form>
		<%
   
	
    if(request.getAttribute("error")!=null)
     {
	%>
	    <h3 style=text-align:center;color:red;><b><%=request.getAttribute("error")%></b></h3>
	    
	 <%
	    }
        else if(request.getAttribute("success")!=null)
        {
	    %>
	<h3 style=text-align:center;color:green;><b><%=request.getAttribute("success")%></b></h3>
	<%
	}
	%>
</body>
</html>