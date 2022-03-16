package com.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bankinglogic.BankingLogic;
import proexception.ProException;

public class ForgetPasswordServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		 BankingLogic logic =(BankingLogic) request.getServletContext().getAttribute("api");
		
		if(request.getParameter("type").equals("forget"))
		{
		
               int userId=Integer.parseInt(request.getParameter("userId"));
		
		       String mail=request.getParameter("mailId");
		
		      
		
		       try 
		       {
			
			          logic.updatePassword(userId, mail,"forget");
			
			          request.setAttribute("success","New password have been mailed to you");
			
			          RequestDispatcher rd=request.getRequestDispatcher("forgetpassword.jsp");
	        
	                  rd.forward(request, response);
		        } 
		       catch (ProException e)
		        {
			
                         request.setAttribute("error",e.getMessage());
		    
                         RequestDispatcher rd=request.getRequestDispatcher("forgetpassword.jsp");
            
		                 rd.forward(request, response);
		        }
		
		}
		else if(request.getParameter("type").equals("reset"))
		{
			
			    HttpSession session=request.getSession(false);     
			
			    int userId=(int)session.getAttribute("userId");
			    
			    String password=request.getParameter("password");
			   
			    String confirm=request.getParameter("newpass");
			   
			    
			    
			    
			    try
			    {
			    	logic.validatePassword(userId, password);
			    	
			    	logic.updatePassword(userId, confirm,"reset");
			    	
			    	request.setAttribute("success","password changed successfully");
				    
                    RequestDispatcher rd=request.getRequestDispatcher("changepassword.jsp");
      
	                 rd.forward(request, response);
			    	
			    }
			    catch(ProException e)
			    {
			    	
			    	 request.setAttribute("error",e.getMessage());
				    
                     RequestDispatcher rd=request.getRequestDispatcher("changepassword.jsp");
       
	                 rd.forward(request, response);
			    	
			    }
			
			
			
		}
		
		
	}

}
