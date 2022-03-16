<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="accountinfo.AccountInfo"%>
<%@page import="bankinglogic.BankingLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD CUSTOMER PAGE</title>
<link rel="stylesheet" href="transaction.css" type="text/css"></link>
<script type="text/javascript">
function checkAmount()
{  
 
var amount=document.money.amount.value; 
var send=document.money.to.value;
var receive=document.money.from.value;

  
if (amount==0 || amount=="")
{  
  alert("amount can't be blank");  
  
  return false;  
}
else if(send=="entry"|| send=="")
{
	
	alert("select an Id");  
	  
	  return false;

}
else if(receive=="entry"|| receive=="")
{
	
	alert("select an Id");  
	  
	  return false;

}
else if(send==receive)
{
	
	alert("both ID can't be same");  
	  
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
<form name="money" class="data" action="transfer" method="post" onSubmit="return checkAmount()">
		<h5 class="title"><b>TRANSACTION</b></h5>
		<label for="sender" style=margin-top:5px;>SENDER ACCOUNT ID</label> 
		<select  id="sender" class="entry" name="from">
		<option value="entry" >Select Account ID</option>
		<%
		
		BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api");
        
		if(session.getAttribute("customerID")==null&& session.getAttribute("roleId")!=null)
		{   
	          List<AccountInfo> account=logic.allAccount();
	          
	          for(AccountInfo data:account)
	          {
	          
        %>
                 
                 <option value="<%=data.getAccountId()%>"><%=data.getAccountId() %></option>
        
		 <%    
            	
            }
            
	    }
		else if(session.getAttribute("customerID")!=null&& session.getAttribute("roleId")!=null)
		{
			int customerId=(int)session.getAttribute("customerID");
			 
			Map<Integer,AccountInfo> accountmap =logic.getAccount(customerId);
	        
            for(Map.Entry<Integer,AccountInfo> entry : accountmap.entrySet())
            {
        	   AccountInfo data=entry.getValue(); 
		
           %>
           
            <option value="<%=data.getAccountId()%>"><%=data.getAccountId() %></option>
            
           <%
           
           }
            	
       }
            
		 %>
           
       </select> 
       
		<label for="receiver">RECEIVER ACCOUNT ID</label>
		<select  id="receiver" class="entry" name="to">
		<option value="entry" >Select Account ID</option>
		<%
		List<AccountInfo> account=logic.allAccount();
        
        for(AccountInfo data:account)
        {
        %>
        <option value="<%=data.getAccountId() %>"><%=data.getAccountId() %></option>
        <%
           
        }  	    
           
          
       %>
        
        </select>
		
		<label for="amount">AMOUNT TO TRANSFER</label>
		<input id="amount" type="number" class="entry" name="amount">  
		<input type="submit" class="click" value="TRANSFER">
		
	</form>
	<%  if(request.getAttribute("error")!=null)
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