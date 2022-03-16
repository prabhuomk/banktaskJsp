<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<%@page import="customerinfo.CustomerInfo"%>
<%@page import="bankinglogic.BankingLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN CUSTOMER PAGE</title>
<link rel="stylesheet" href="adminhome.css" type="text/css"></link>
<link rel="stylesheet" href="adminhome.css" type="text/css"></link>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
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
<h2 class="title">
			<b>ALL CUSTOMER DETAILS</b>
		</h2>	
		<div class="outer">
		
	<a class="btn btn-info" id="data" href="addcustomer.jsp?type=add"
		role="button" data-toggle="tooltip" data-placement="top" title="Add customer data">ADD</a>
		<a class="btn btn-info" id="data" href="activatecustomer.jsp"
		role="button" data-toggle="tooltip" data-placement="top" title="Activate customer">ACTIVATE
	
	</a>
	</div>
	<div class="main">
		
		<table id="customers">
			<tr class="head">
				<th><b>CUSTOMER ID</b></th>
				<th><b>NAME</b></th>
				<th><b>MOBILE NO.</b></th>
				<th><b>MAIL-ID</b></th>
				<th><b>ADDRESS</b></th>
				<th><b>EDIT / DEACTIVATE</b></th>
			</tr>

			<%
			 BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("api");

			 List<CustomerInfo> customer=logic.allCustomer();

			 for (CustomerInfo data:customer) {
				
				if (data.getStatus()) {
			%>

			<tr>
				<td><%=data.getCustomerId()%></td>
				<td><%=data.getName()%></td>
				<td><%=data.getMobile()%></td>
				<td><%=data.getMail()%></td>
				<td><%=data.getAddress()%></td>
				<td><a class="btn btn-success" role="button" href="addcustomer.jsp?customerId=<%=data.getCustomerId()%>&type=update"  data-toggle="tooltip" data-placement="top" title="Edit customer data">
					<i class="fa fa-pencil"></i>
					</a>
						
						<a class="btn btn-danger" role="button" 
					href="customer?customerId=<%=data.getCustomerId()%>&type=deactivate" data-toggle="tooltip" data-placement="top" title="Deactivate customer"
					onclick="if (confirm('Are you sure you want to deActivate the Customer?')){return true;}else{event.stopPropagation(); event.preventDefault();};"><i
						class="fa fa-trash-o"></i></a></td>
			</tr>

			<%
			}
			}
			%>


		</table>
	</div>
</body>
</html>