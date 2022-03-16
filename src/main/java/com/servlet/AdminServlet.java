package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bankinglogic.BankingLogic;
import proexception.ProException;

public class AdminServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail=request.getParameter("mailId");
		
		BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api");
		
		   try
		     {
			        logic.addAdmin(mail);
			      
			    	request.setAttribute("success","New admin added successfully");
				    
		            RequestDispatcher rd=request.getRequestDispatcher("addadmin.jsp");
		            
				    rd.forward(request, response);
			
		    } catch (ProException e)
		    {
			  
		    	
		    	request.setAttribute("error",e.getMessage());
			    
	            RequestDispatcher rd=request.getRequestDispatcher("addadmin.jsp");
	            
			    rd.forward(request, response);
			
		    }
		
		
	}

}
