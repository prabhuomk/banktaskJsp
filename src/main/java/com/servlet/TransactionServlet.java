package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bankinglogic.BankingLogic;
import proexception.ProException;
import accountinfo.AccountInfo;
import java.util.Map;
import java.util.HashMap;



public class TransactionServlet extends HttpServlet {
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int fromAccountId=Integer.parseInt(request.getParameter("from"));
		
		int fromCustomerId=0;
		
		int toAccountId=Integer.parseInt(request.getParameter("to"));
		
		int toCustomerId=0;
		
		int amount=Integer.parseInt(request.getParameter("amount"));
		
		try
		{
                BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api");
        
                Map<Integer,Integer> idDetails =logic.idDetails();
        
            	for(Map.Entry<Integer,Integer> entry :idDetails.entrySet())
                {
            	      
            	      
            	      if(entry.getKey()==fromAccountId || entry.getKey()==toAccountId)
            	      {
            	    	  
            	    	  if(entry.getKey()==fromAccountId )
            	    	  {
            	    	      fromCustomerId=entry.getValue();
            	    	      
            	    	  }
            	    	  else
            	    	  {
            	    		  toCustomerId=entry.getValue();
            	    	  }
            	      
            	      }
		
                }
            
            
            logic.withdrawal(fromCustomerId, fromAccountId, amount);
            
            logic.deposit(toCustomerId, toAccountId, amount);
            
           /* HttpSession session=request.getSession(false);  
            
            if(session.getAttribute("customerID")==null && session.getAttribute("roleId")!=null)
            {
            
                 RequestDispatcher rd=request.getRequestDispatcher("adminHome.jsp");
		    
		         rd.forward(request, response);
            }
            else if(session.getAttribute("customerID")!=null && session.getAttribute("roleId")!=null)
            {

                RequestDispatcher rd=request.getRequestDispatcher("customerHome.jsp");
    		    
    		    rd.forward(request, response);
            }*/
            
              request.setAttribute("success","Transaction Has Been Done Successfully");
			  
			  RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp");
			  
			  rd.forward(request, response);
            
            
		}
		catch(ProException e)
		{
			
			  request.setAttribute("error",e.getMessage());
			  
			  RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp");
			  
			  rd.forward(request, response);
			
		}
	}
		
	

}
