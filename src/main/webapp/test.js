function loginValidate()
{
	
	
	document.getElementById("sub").disabled=true;
	
	var userId=document.getElementById("user").value;
	var password=document.getElementById("password").value;
	
	  
if (userId==0 || userId=="")
{  
  alert("userId can't be blank or ZERO");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  
}
else if(password.length<8)
{  
  alert("Password can't be less than 8 in length");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  } 	
		
	
} 
 
function forgetValidate()
{
	var userId=document.getElementById("id").value;
	var mail=document.getElementById("mail").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	  
if (userId==0 || userId=="")
{  
  alert("userId can't be blank or ZERO"); 
  
  document.getElementById("sub").disabled=false; 
  
  return false;  
 
  
}
else if(!mail.match(mailformat))
{  
  alert("valid mail-id required");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  } 	
		
	
} 


function addForm()
{
	
	document.getElementById("sub").disabled=true;
	var user=document.getElementById("user").value;
	var mail=document.getElementById("mail").value;
	var mobile=document.getElementById("number").value;
	var address=document.getElementById("address").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	  
if (user==null || user=="")
{  
  
  
  alert("user can't be blank");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  
}
	
else if(mobile.length!=10)
{  
  alert("mobile length should be 10");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  } 
  
  else if(!mail.match(mailformat))
{ 
	 
  alert("valid mail-id required"); 
  
  document.getElementById("sub").disabled=false; 
  
  return false;  
 
  } 
  else if(address==null || address=="")
{  
  
  alert("address can't be blank");  
  
   document.getElementById("sub").disabled=false;
  
  
  return false;  
 
  } 
  }	
		
function updateForm()
{
	
	document.getElementById("sub").disabled=true;
	var user=document.getElementById("user").value;
	var mail=document.getElementById("mail").value;
	var mobile=document.getElementById("number").value;
	var address=document.getElementById("address").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	  
if (user==null || user=="")
{  
  
  alert("name can't be blank");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  
}

else if(mobile.length!=10)
{  
  alert("mobile length should be 10");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  } 
  
  else if(!mail.match(mailformat))
{  
  alert("valid mail-id required");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  } 	
  else if(address==null || address=="")
{  
  alert("address can't be blank");  
  
  document.getElementById("sub").disabled=false;
  
  return false;  
 
  } 				
	
}	
