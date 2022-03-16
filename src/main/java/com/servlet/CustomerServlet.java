package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accountinfo.AccountInfo;
import bankinglogic.BankingLogic;
import customerinfo.CustomerInfo;
import proexception.ProException;


public class CustomerServlet extends HttpServlet {
	
	
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api");
		
		if(request.getParameter("type").equals("add"))
		{
			try {
				
				  addCustomer(request,logic);
				  request.setAttribute("success","Customer Data Got Added Successfully");
				  RequestDispatcher rd=request.getRequestDispatcher("addcustomer.jsp?type=add");
				  rd.forward(request, response);
			    } 
			catch (ProException e) 
			{
				
				  request.setAttribute("error",e.getMessage());
				  RequestDispatcher rd=request.getRequestDispatcher("addcustomer.jsp");
				  rd.forward(request, response);
			}
		}
		else if(request.getParameter("type").equals("update"))
		{
			try {
				
				   updateCustomer(request,response,logic);
				   
			    } 
			catch (ProException e)
			{
				
				  request.setAttribute("error",e.getMessage());
				  RequestDispatcher rd=request.getRequestDispatcher("addcustomer.jsp");
				  rd.forward(request, response);
			}
		}
		
			
           
		
		 
	}
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api");
	if(request.getParameter("type").equals("deactivate"))
	{
		
	
	
	try {
		
		deactivateCustomer(request,logic);
		
	} catch (ProException e) {
		
		  request.setAttribute("error",e.getMessage());
		  RequestDispatcher rd=request.getRequestDispatcher("allcustomer.jsp");
		  rd.forward(request, response);
	}
	
	}
	
	else if(request.getParameter("type").equals("activate"))
	{
	
   try {
		
		activateCustomer(request,logic);
		
	} catch (ProException e) {
		
		  request.setAttribute("error",e.getMessage());
		  RequestDispatcher rd=request.getRequestDispatcher("allcustomer.jsp");
		  rd.forward(request, response);
	}
}

	  RequestDispatcher rd=request.getRequestDispatcher("allcustomer.jsp");
	  
	  rd.forward(request, response);
	
}


	public void addCustomer(HttpServletRequest request,BankingLogic logic) throws ProException
	{
		
		
		
		String name=request.getParameter("user1");
		
		String mobile=request.getParameter("mobile1");
		
		String mail=request.getParameter("mail1");
		System.out.println(mail);
		
		String address=request.getParameter("add1");
		
		CustomerInfo customer=new CustomerInfo();
		
		customer.setName(name);
		
		customer.setMobile(mobile);
		
		customer.setAddress(address);
		
		customer.setMail(mail);
		
		logic.addCustomer(customer);
		
		
		
	}
	
	public void updateCustomer(HttpServletRequest request,HttpServletResponse response,BankingLogic logic) throws ProException, ServletException, IOException
	{
        int customerId=Integer.parseInt(request.getParameter("customer"));
        
        String name=request.getParameter("user2");
		
		String mobile=request.getParameter("mobile2");
		
		String address=request.getParameter("add2");
		
		String mail=request.getParameter("mail2");
		
		logic.customerFullUpdate(customerId, name, mobile,mail,address);
		
		request.setAttribute("success","Customer Data Got Updated Successfully");
		
		 RequestDispatcher rd=request.getRequestDispatcher("addcustomer.jsp?type=update&customerId="+customerId);
		 
		 rd.forward(request, response);
		
        
	}
	
	public void deactivateCustomer(HttpServletRequest request,BankingLogic logic)throws ProException
	{
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		
		logic.changeCustomerStatus(customerId,0);
	}
	
	public void activateCustomer(HttpServletRequest request,BankingLogic logic)throws ProException
	{
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		
		logic.changeCustomerStatus(customerId,1);
	}
	
	
}
