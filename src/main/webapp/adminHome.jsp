<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="accountinfo.AccountInfo"%>
<%@page import="bankinglogic.BankingLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN HOME PAGE</title>
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
	if (session.getAttribute("roleId") == null) {
		request.setAttribute("error", "Login first.....");

		RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp");

		rd.forward(request, response);

	}
	%>

	<jsp:include page="sidebar.jsp">
		<jsp:param name="roleId" value="<%=1%>" />
	</jsp:include>
	

<form class="form-inline my-2 my-lg-0" style=float:right>
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
	<h2 class="title"><b>ALL ACCOUNT DETAILS</b></h2>	
	
	<div class="outer">
		
	<a class="btn btn-info" id="data" href="addaccount.jsp?type=add"
		role="button" data-toggle="tooltip" data-placement="top" title="Add account data">ADD
	</a>
	<a class="btn btn-info" id="data" href="activateaccount.jsp" data-toggle="tooltip" data-placement="top" title="Activate account "
		role="button">ACTIVATE
	</a>
</div>
	
		<table id="customers">
			<tr class="head">
				<th><b>ACCOUNT ID</b></th>
				<th><b>CUSTOMER ID</b></th>
				<th><b>BRANCH</b></th>
				<th><b>BALANCE</b></th>
				<th><b>EDIT / DEACTIVATE</b></th>
			</tr>

			<%
			BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("api");

			List<AccountInfo> account = logic.allAccount();

			for (AccountInfo data : account) {
				if (data.getStatus()) {
			%>

			<tr>
				<td><%=data.getAccountId()%></td>
				<td><%=data.getCustomerId()%></td>
				<td><%=data.getBranch()%></td>
				<td><%=data.getBalance()%></td>
				<td><a class="btn btn-success" role="button" href="addaccount.jsp?accountId=<%=data.getAccountId()%>&customerId=<%=data.getCustomerId()%>&type=update" data-toggle="tooltip" data-placement="top" title="Edit account data">
					<i class="fa fa-pencil"></i>
					</a>
						
						<a class="btn btn-danger" role="button"
					href="account?customerId=<%=data.getCustomerId()%>&accountId=<%=data.getAccountId()%>&type=deactivate" data-toggle="tooltip" data-placement="top" title="Deactivate Account"
					onclick="if (confirm('Are you sure you want to deActivate the Account?')){return true;}else{event.stopPropagation(); event.preventDefault();};"><i
						class="fa fa-trash-o"></i></a></td>
			</tr>

			<%
			}
			}
			%>


		</table>
</body>
</html>