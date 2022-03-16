package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accountinfo.AccountInfo;
import bankinglogic.BankingLogic;
import proexception.ProException;
import customerinfo.CustomerInfo;


public class LoginServlet extends HttpServlet{
	
	
	public void init(ServletConfig config) throws ServletException 
	{
		
		try {
			
			BankingLogic logic =new BankingLogic();
			
			config.getServletContext().setAttribute("api",logic);
		
			
		    } 
		catch(ProException e) {
			
			e.printStackTrace();
		}
		
		
	} 
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException 
	{
		
		
		try
		{
		 	
			
		BankingLogic logic =(BankingLogic) req.getServletContext().getAttribute("api");
		
		// validate password
		
		int userId=Integer.parseInt(req.getParameter("userID"));
		
		String password=req.getParameter("password");
		
		Map<String,Integer> data=logic.validatePassword(userId, password);
		
		int roleID=data.get("roleID");
		
		int customerID=data.get("customerID");
		
		
		
		if(roleID==1)
		{

			  HttpSession session=req.getSession();  
		
	          session.setAttribute("roleId",1);  
	          
	          session.setAttribute("userId",userId);
	
			  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
		    
		      rd.forward(req, res);
			
		}
		else
		{
			
			
			HttpSession session=req.getSession();  
			
			 session.setAttribute("roleId",2);
			
			 session.setAttribute("userId",userId);
			 
	         session.setAttribute("customerID",customerID); 
	       
            RequestDispatcher rd=req.getRequestDispatcher("customerHome.jsp");
		    
		    rd.forward(req, res);
			
		}
		
		
		
		}
		catch(ProException e)
		{
		    req.setAttribute("error",e.getMessage());
		    
            RequestDispatcher rd=req.getRequestDispatcher("loginpage.jsp");
            
		    rd.forward(req, res);
			
		}
		
		
	}

}
