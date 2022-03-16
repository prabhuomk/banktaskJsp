<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FORGET PASSWORD</title>
<link rel="stylesheet" href="forget.css" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />	
<script src="test.js"></script>
</head>
<body>
<h1 style=text-align:center;>PK's BANK</h1>
<div class="container">
<div class="left">
</div>
<div class="middle">
<form name="validate" class="data" action="forget?type=forget" method="post" onSubmit="return forgetValidate();">

		<h3 class="title">FORGET PASSWORD</h3>
		 <i class="fa fa-user-circle-o fa-2x"></i>
		<label for="id"><h4>USER ID</h4></label> 
		
		<input id="id" type="number" class="entry" name="userId"> 
		
		<label for="mail"><h4>MAIL-ID</h4></label> 
	
		<input id="mail" type="text" class="entry" name="mailId"> 
		
		<input id="click"  id="sub" type="submit"  value="RESET">
		
		</br>
		</br>
		<a class="btn btn-primary" href="loginpage.jsp" role="button" >login?</a>
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
	         
	         </div>
	         <div class="right">
	    <h1 class="tag">New Bank For New Age</h1>
<img  class="rightimg" src="https://media.istockphoto.com/photos/multiethnic-couple-handshake-with-consultant-at-home-picture-id1319763433?b=1&k=20&m=1319763433&s=170667a&w=0&h=wsPSqHYdRNSlyDK9D_HCUNhJaSYxGiFJjgyGL346rm4="/>
	    </div>
	    </div>
	    

</body>
</html>