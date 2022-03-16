<%@page import="javax.websocket.server.ServerApplicationConfig"%>
<%@page import="accountinfo.AccountInfo"%>
<%@page import="bankinglogic.BankingLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD ACCOUNT PAGE</title>
<script type="text/javascript">  
function addform()
{  
	
	document.getElementById("sub").disabled=true;
	
var customerID1=document.cusform.customer.value; 
var branch1=document.cusform.branch.value; 
var balance1=document.cusform.bal.value; 
  
if (customerID1==0 || customerID1=="")
{  
  alert("CustomerId can't be blank"); 
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  
}else if(branch1==null||branch1=="")
{  
  alert("Branch can't be blank");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  } 
else if(balance1<500||balance1=="")
{  
  alert("Minimum balance should be 500");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  }  



}
function validateUpdateform(){  
	
	document.getElementById("sub").disabled=true;
	
	
	var branch=document.updateform.branch.value; 
	var balance=document.updateform.balance.value; 
	  
	 if(branch==null||branch=="")
	{  
	  alert("Branch can't be blank");  
	  
	  document.getElementById("sub").disabled=false;
	  
	  return false;  
		  } 
	else if(balance<500||balance=="")
	{  
	  alert("Minimum balance should be 500");  
	  
	  document.getElementById("sub").disabled=false;
	  
	  return false;  
	 
	  }  
	}  
</script>  
<link rel="stylesheet" href="addaccount.css" type="text/css">
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
<%if(request.getParameter("accountId")==null && request.getParameter("type").equals("add"))
	{
	%>

       <form name="cusform" class="data" action="account?type=add" method="post"  onSubmit="return addform()">
		<h5 class="title"><b>ADD ACCOUNT DETAILS</b></h5>
		
		<label for="id" style=margin-top:10px;><h6><b>CUSTOMER ID</b></h6></label> 
		
		<input id="id" type="number" class="entry" name="customer"> 
		
		<label for="branch"><h6><b>BRANCH</b></h6></label>
		
		<input id="branch" type="text" class="entry" name="branch"> 
		
		<label for="balance"><h6><b>BALANCE</b></h6></label> 
	
		<input id="balance" type="Number" class="entry" name="bal"> 
		
		<input class="click"  id="sub" type="submit" name="add" value="ADD">
	</form>
	<%
	}
   else
   {
	
	   int customerId=Integer.parseInt(request.getParameter("customerId"));
	   int accountId=Integer.parseInt(request.getParameter("accountId"));
	   BankingLogic logic =(BankingLogic) application.getAttribute("api");
	   AccountInfo account=logic.getOneAccount(customerId, accountId);
	%>
	
  <form name="updateform" class="data" action="account?type=update" method="post" onSubmit="return validateUpdateform()" style=height:350px;>
		<h5 class="title"><b>UPDATE ACCOUNT DETAILS</b></h5>
		<input id="cusid" type="hidden" class="entry" value="<%=account.getCustomerId()%>" name="customer"> 
		<input id="accid" type="hidden" class="entry" value="<%=account.getAccountId()%>" name="account"> 
		<label for="branch"><h6><b>BRANCH</b></h6></label>
		
		<input id="branch" type="text" class="entry" name="branch" value="<%=account.getBranch()%>"> 
	
		<label for="balance"><h6><b>BALANCE</b></h6></label> 
		
		<input id="balance" type="Number" class="entry" name="balance" value="<%=account.getBalance()%>"> 
		
		<input class="click" id="sub" type="submit" name="update" value="UPDATE">
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