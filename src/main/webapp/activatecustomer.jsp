<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>

<%@page import="customerinfo.CustomerInfo"%>
<%@page import="bankinglogic.BankingLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Activate</title>
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
	<div class="main">
		<h2 class="title" ><b>ALL CUSTOMER DETAILS</b></h2>
		
		<table id="customers" style=margin-top:100px;>
			<tr class="head">
				<th><b>CUSTOMER ID</b></th>
				<th><b>NAME</b></th>
				<th><b>MOBILE NO.</b></th>
				<th><b>ADDRESS</b></th>
				<th><b>ACTIVATE</b></th>
			</tr>

			<%
			
			 boolean check=false;
			
			 BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("api");

			 List<CustomerInfo> customer=logic.allCustomer();

			 for (CustomerInfo data:customer) {
				
				if (!data.getStatus()) {
					
					check=true;

			%>

			<tr>
				<td><%=data.getCustomerId()%></td>
				<td><%=data.getName()%></td>
				<td><%=data.getMobile()%></td>
				<td><%=data.getAddress()%></td>
				<td><a class="btn btn-success" role="button" href="customer?customerId=<%=data.getCustomerId()%>&type=activate" data-toggle="tooltip" data-placement="top" title="Activate customer"><i class="fa fa-plus-square"></i></a></td>
			</tr>

			<%
			}
			}

	          if(!check)
	          {
	          
	       %>
	         <tr>
	         <td colspan="5"><p>No Customer is in DeActivated State</p></td>
	         </tr>
	         <%
	         }%>


		</table>
	</div>
</body>
</html>