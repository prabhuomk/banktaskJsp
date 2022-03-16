<%@page import="customerinfo.CustomerInfo"%>
<%@page import="bankinglogic.BankingLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD CUSTOMER PAGE</title>
<link rel="stylesheet" href="addcustomer.css" type="text/css"></link>
<script src="test.js"></script>
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

<%if(request.getParameter("customerId")==null && request.getParameter("type").equals("add"))
	{
	%>

   <form name="cusadd" class="data" action="customer?type=add" method="post" onsubmit="return addForm();">
   
		<h5 class="title"><b>ADD CUSTOMER</b></h5>
		<label for="user" style=margin-top:5px;>CUSTOMER NAME</label> 
		
		<input id="user" type="text" class="entry" name="user1"> 
	
		<label for="number">MOBILE NUMBER</label>
		
		<input id="number" type="text" class="entry" name="mobile1">  
		
		
		<label for="mail">MAIL-ID</label>
		
		<input id="mail" type="text" class="entry" name="mail1"> 
	
		
		<label for="address">ADDRESS</label>
		
		<input id="address" type="text" class="entry" name="add1">  
		
		<input type="submit" id="sub" class="click" value="ADD" >
		
		
		
	</form>
	
	<%
	}
   else
   {
	
	   int customerId=Integer.parseInt(request.getParameter("customerId"));
	   BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api");
	   CustomerInfo customer=logic.getCustomer(customerId);
	%>
	
   <form name="cusupdate" class="data" action="customer?type=update" method="post" onsubmit="return updateForm();">
   
		<h5 class="title"><b>UPDATE CUSTOMER</b></h5>
		<input id="customer" type="hidden" class="entry" value="<%=customer.getCustomerId()%>" name="customer"> 
		
		<label for="user">CUSTOMER NAME</label> 
		
		<input id="user" type="text" class="entry" value="<%=customer.getName()%>" name="user2"> 
		
		<label for="number">MOBILE NUMBER</label>
		
		<input id="number" type="text" class="entry" value="<%=customer.getMobile()%>" name="mobile2">
	
		<label for="mail">MAIL-ID</label>
		
		<input id="mail" type="text" class="entry" value="<%=customer.getMail()%>" name="mail2"> 
		  
		<label for="address">ADDRESS</label>
		
		<input id="address" type="text" class="entry" value="<%=customer.getAddress()%>" name="add2">  
		
		<input type="submit" id="sub" class="click" value="UPDATE">
		
	</form>
	
	<%
   }
	
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