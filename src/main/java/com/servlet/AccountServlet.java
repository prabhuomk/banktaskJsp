package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bankinglogic.BankingLogic;
import proexception.ProException;
import accountinfo.AccountInfo;


public class AccountServlet extends HttpServlet {
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api");
		
		if(request.getParameter("type").equals("add"))
		{
			try {
				
				   addAccount(request,logic);
				   request.setAttribute("success","Account Data Got Added Successfully");
				   RequestDispatcher rd=request.getRequestDispatcher("addaccount.jsp?type=add");
				   rd.forward(request, response);  
				   
				  
			    } 
			catch (ProException e) 
			{
				   
				  request.setAttribute("error",e.getMessage());
				  RequestDispatcher rd=request.getRequestDispatcher("addaccount.jsp");
				  rd.forward(request, response);
				
			}
		}
		else if(request.getParameter("type").equals("update"))
		{
			try {
				
				    updateAccount(request,response,logic);
				   
				    
			    } 
			catch (ProException e)
			{
				   request.setAttribute("error",e.getMessage());
				   request.setAttribute("type","update");
				   RequestDispatcher rd=request.getRequestDispatcher("addaccount.jsp");
				   rd.forward(request, response);
				
			}
		}
		
		  
		 
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("api");

		if (request.getParameter("type").equals("deactivate")) {

			try {

				deactivateCustomer(request, logic);

			} catch (ProException e) {

				request.setAttribute("error", e.getMessage());

				RequestDispatcher rd = request.getRequestDispatcher("adminHome.jsp");

				rd.forward(request, response);
			}
		}
		
		else if (request.getParameter("type").equals("activate")) {
			try {

				activateCustomer(request, logic);

			} catch (ProException e) {

				request.setAttribute("error", e.getMessage());

				RequestDispatcher rd = request.getRequestDispatcher("adminHome.jsp");

				rd.forward(request, response);
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("adminHome.jsp");

		rd.forward(request, response);

	}
	
	public void addAccount(HttpServletRequest request,BankingLogic logic) throws ProException
	{
		
		int customerId=Integer.parseInt(request.getParameter("customer"));
		
		String branch=request.getParameter("branch");
		
		int balance=Integer.parseInt(request.getParameter("bal"));
		
		AccountInfo account=new AccountInfo();
		
		account.setCustomerId(customerId);
		
		account.setBranch(branch);
		
		account.setBalance(balance);
		
		logic.addAccount(account);
		
		
		
	}
	
	public void updateAccount(HttpServletRequest request,HttpServletResponse response,BankingLogic logic) throws ProException, ServletException, IOException
	{
        int customerId=Integer.parseInt(request.getParameter("customer"));
        
        int accountId=Integer.parseInt(request.getParameter("account"));
		
		String branch=request.getParameter("branch");
		
		int balance=Integer.parseInt(request.getParameter("balance"));
		
		logic.accountFullUpdate(customerId, accountId, branch, balance);
		
		    request.setAttribute("success","Account Data Got Updated Successfully");
		 
		    RequestDispatcher rd=request.getRequestDispatcher("addaccount.jsp?type=update&customerId="+customerId+"&accountId="+accountId);
		    
		    rd.forward(request, response);
	}
	
	public void deactivateCustomer(HttpServletRequest request,BankingLogic logic)throws ProException
	{
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		
		int accountId=Integer.parseInt(request.getParameter("accountId"));
		
		logic.changeAccountStatus(customerId, accountId,0);
	}
	
	public void activateCustomer(HttpServletRequest request,BankingLogic logic)throws ProException
	{
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		
		int accountId=Integer.parseInt(request.getParameter("accountId"));
		
		logic.changeAccountStatus(customerId, accountId,1);
	}
	
	

}
