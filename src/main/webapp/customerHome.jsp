<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="accountinfo.AccountInfo"%>
<%@page import="customerinfo.CustomerInfo"%>
<%@page import="bankinglogic.BankingLogic"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME PAGE</title>
<link rel="stylesheet" href="customerhome.css" type="text/css"></link>

</head>
<body>
<%

if(session.getAttribute("customerID")==null)
{
    request.setAttribute("error","Login first.....");
    
    RequestDispatcher rd=request.getRequestDispatcher("loginpage.jsp");
    
    rd.forward(request, response);
   
}  
int customerId=(int)session.getAttribute("customerID");

BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api"); 

CustomerInfo customer=logic.getCustomer(customerId);
%>

<jsp:include page="sidebar.jsp" >
  <jsp:param name="roleId" value="<%=2%>" />
  <jsp:param name="customerId" value="<%=customerId%>" />
</jsp:include>
<div>
<%

SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
Date date = new Date();  
 
%>  
      
      </br>
      <h3 class="time"><%=formatter.format(date)%></h3>
      <h3 class="title"><b>WELCOME <%=customer.getName().toUpperCase()%></b></h3>
      <h3 class="title">YOUR ACCOUNT DETAILS</h3>
     
      <table id="customers">
         <tr style="background-color:red">
          <th><b>ACCOUNT ID</b></th>
          <th><b>CUSTOMER ID</b></th>
          <th><b>BRANCH</b></th>
          <th><b>BALANCE</b></th>
         </tr>
        
        <%Map<Integer,AccountInfo> account =logic.getAccount(customerId);
        
            for(Map.Entry<Integer,AccountInfo> entry : account.entrySet())
            {
        	   AccountInfo data=entry.getValue(); 
        %>
        
            <tr>
                <td><%=entry.getKey()%></td>
                <td><%=data.getCustomerId()%></td>
                <td><%=data.getBranch()%></td>
                <td><%=data.getBalance()%></td>
                
            </tr>
            
        <%
            }
            
         %>
        </table> 
      
       </div>
    </body>
</html>