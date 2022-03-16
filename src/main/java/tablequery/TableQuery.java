package tablequery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import proexception.ProException;
import customerinfo.CustomerInfo;
import maputility.MapUtility;
import accountinfo.AccountInfo;
import test.MyMail;
import test.Test;



public class TableQuery {
	
	   Map<Integer,CustomerInfo> customerData=new HashMap<Integer,CustomerInfo>();
  	
	   Map<Integer,Map<Integer,AccountInfo>> customerAccount=new  HashMap<Integer,Map<Integer,AccountInfo>>();
	   
	   
	
	public enum connectionUtlity
	{
		
		CONNECTION;
		
	    private String url="jdbc:mysql://localhost:3306/BANK";
	    //BANK is the name of the database
	    
	    private  String user="root";
	    
	    private String password="Root@123";
	    
		private Connection connector=null;
		
		public Connection createConnection()throws ProException
		{
		          
			try
			{
				   if(connector==null)
				   {
					   try {
						   
						Class.forName("com.mysql.cj.jdbc.Driver");
					    } catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
			          connector =DriverManager.getConnection( url,user,password);
			          
				   }
				   
			       return connector;
				  
			
			 }
			 catch(SQLException exception) 
			 {
				
				   throw new ProException(exception);
				
			  }
			    
		}
		
		public void closeConnection()
	    {
	    	try
	    	{
	    	    if(connector!=null)
	    	    {
	    		   connector.close();
	    	    }
	    	}
	    	catch(SQLException e) {}
	    	
	    	
	    }

	}
	
	
	public Map<Integer,CustomerInfo> readCustomer()throws ProException
	{
        
		try
		{
		
		createCustomerTable();
		
		createUserTable();
			
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(Statement statement = connection.createStatement())
		{
			
			 String sql="SELECT * FROM CUSTOMER;";
			
			 ResultSet result=statement.executeQuery(sql);
			 
			 if(result!=null)
			 {
			 
			 while(result.next())
			 {
				 
				 CustomerInfo customerInfo=new CustomerInfo();
				 
				 customerInfo.setCustomerId(result.getInt("CustomerID"));
				 
				 customerInfo.setName(result.getString("Name"));
				 
				 customerInfo.setAddress(result.getString("Address"));
				 
				 customerInfo.setMobile(result.getString("Mobile"));
				 
				 customerInfo.setStatus(result.getBoolean("Status"));
				 
				 customerInfo.setMail(result.getString("MailID"));
				 
				 int customerID=result.getInt("CustomerID");
				 
				 MapUtility.addCustomer(customerID, customerInfo,customerData);
				 	 
			 }
			
			 return customerData;
			 }
			 
			 return customerData;
		
		}
		catch(SQLException e)
		{
			throw new ProException(e);
			
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		throw new ProException(e);
		
		
	}
	}
	
	public Map<Integer,Map<Integer,AccountInfo>> readAccount()throws ProException
	{
        
		try
		{
		createAccountTable();
			
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(Statement statement = connection.createStatement())
		{
		
			
			 String sql="SELECT * FROM ACCOUNT;";
			
			 ResultSet result=statement.executeQuery(sql);
			 
			 if(result!=null)
			 {
			 
			 while(result.next())
			 {
				 
				 AccountInfo accountInfo=new AccountInfo();
				 
				 accountInfo.setAccountId(result.getInt("AccountID"));
				 
				 accountInfo.setCustomerId(result.getInt("CustomerID"));
				 
				 accountInfo.setBranch(result.getString("Branch"));
				 
				 accountInfo.setBalance(result.getInt("Balance"));
				 
				 accountInfo.setStatus(result.getBoolean("Status"));
				 
				 int accountId=result.getInt("AccountID");
				 
				 MapUtility.addAccount(accountId, accountInfo,customerAccount);
				
			 }
			
			 return customerAccount;
			 }
			 return customerAccount;
		
		}
		catch(SQLException e)
		{
			throw new ProException(e);
			
		}
		}
		catch(Exception e)
		{
			throw new ProException(e);
			
		}
	}
	
	
			 
			 
			 
			
	

	public void createCustomerTable()throws ProException
	{
		
        Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(Statement statement = connection.createStatement();)
		{
		   
			
			String sql="CREATE TABLE IF NOT EXISTS CUSTOMER(CustomerID INTEGER NOT NULL AUTO_INCREMENT,Name VARCHAR(20),Address VARCHAR(40),Mobile VARCHAR(15),MailID VARCHAR(20),Status BOOLEAN default TRUE,PRIMARY KEY(CustomerID));";
			
			statement.executeUpdate(sql);
			
			
			
		
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while creating customer table");
			
		}
		
		
		 
	}
	
	public void createAccountTable()throws ProException
	{
		
        Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(Statement statement = connection.createStatement();)
		{
		   
			
			String sql="CREATE TABLE IF NOT EXISTS ACCOUNT (AccountID INTEGER NOT NULL AUTO_INCREMENT,CustomerID INTEGER NOT NULL,Branch VARCHAR(20),Balance INTEGER,Status BOOLEAN default TRUE,PRIMARY KEY(AccountID),FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID));";
			
			statement.executeUpdate(sql);
			
			
			
		
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while creating account table");
			
		}
		
		
		
	}
	
	public void createUserTable()throws ProException
	{

		 
		 
		
        Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(Statement statement = connection.createStatement();)
		{
		   
			
			String sql="CREATE TABLE IF NOT EXISTS USER (UserID INTEGER NOT NULL AUTO_INCREMENT,Password VARCHAR(50),RoleID INTEGER,CustomerID INTEGER,PRIMARY KEY(UserID),FOREIGN KEY (RoleID) REFERENCES ROLE(RoleID));";
			
			statement.executeUpdate(sql);
			
			checker();
			
			
			
		
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while creating user table");
			
		}
		
		
		
	}
	
	
	public void checker() throws ProException
	{

		   Connection connection=connectionUtlity.CONNECTION.createConnection();
		   
		   try(Statement statement = connection.createStatement())
		   {

				 String sql="SELECT COUNT(*) FROM USER;";
				
				 ResultSet result=statement.executeQuery(sql);
				
				 while(result.next())
				 {
					 
					 int count=result.getInt(1);
					
					 if(count==0)
					 {
						 insertIntoUser("prabhaomk@gmail.com");
					 }
					
					 	 
				 }
				
				 
		   }
		catch(SQLException e)
		{
			throw new ProException("Sorry server side error occured while adding data's");
			
		}
	}
	
	public void insertIntoUser(String mailId)throws ProException
	{
		    String pass=Test.generatePassword(8);
		   
		    String hashPass=Test.encryptPassword(pass);
           
		   
		
		   Connection connection=connectionUtlity.CONNECTION.createConnection();
		   
		   try(Statement statement = connection.createStatement())
		   {
			  
			     String sql="INSERT INTO USER(Password,RoleID,CustomerID) VALUES('"+hashPass+"',"+1+","+null+");";
			     
				 statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				 

				   ResultSet rs = statement.getGeneratedKeys();
				   
				   
				   int userId=0;
				 
					if (rs.next()) {
						
						userId=rs.getInt(1);
					}
		           
					
				 
				 MyMail.startOperation(mailId, userId, pass);
				 
		   }
		catch(SQLException e)
		{
			throw new ProException("Sorry server side error occured while adding data's");
			
		}
		
		
		
	}
	
	
		
	   public int insertIntoTable(String sql)throws ProException
	   {
	      
		   
		   
		   Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		   try(Statement statement = connection.createStatement())
		   {
			  
				 statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				 
				 ResultSet rs = statement.getGeneratedKeys();
				 
					if (rs.next()) {
						
						return rs.getInt(1);
					}
		           
					return 0;
		   }
		catch(SQLException e)
		{
			throw new ProException("Sorry server side error occured while adding data's");
			
		}
		
	}
	   
	  
	
	
	
	public void changeStatus(boolean status,int Id,String sql)throws ProException
	{
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(PreparedStatement statement = connection.prepareStatement(sql))
		{
		      
			
			statement.setBoolean(1, status);
			
			statement.setInt(2, Id);
			
			statement.executeUpdate();
			
		
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while deactivating the customer");
			
		}
		
	}	
	
	public void changeStatus(boolean status,int accountId,int customerId,String sql)throws ProException
	{
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(PreparedStatement statement = connection.prepareStatement(sql))
		{
		      
			
			statement.setBoolean(1, status);
			
			statement.setInt(2, accountId);
			
			statement.setInt(3, customerId);
			
			statement.executeUpdate();
			
		
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while deactivating the account");
			
		}
		
	}	
	
	
	public void updateBalance(int balance,int accountId,int customerId,String sql)throws ProException
	{
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(PreparedStatement statement = connection.prepareStatement(sql))
		{
		      
			
			statement.setInt(1, balance);
			
			statement.setInt(2, accountId);
			
			statement.setInt(3, customerId);
			
			statement.executeUpdate();
				
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while updating balance");
			
		}
		
	}	
	
	public AccountInfo accountFullUpdate(String branch,int balance,int accountId,int customerId,String sql)throws ProException
	{
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setString(1,branch); 
			
			statement.setInt(2, balance);
			
			statement.setInt(3, accountId);
			
			statement.setInt(4, customerId);
			
			statement.executeUpdate();
			
			return getUpdateAccount(accountId);
				
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while updating account data");
			
		}
		
	}
	
	
	
	public CustomerInfo getUpdateCustomer(int customerId)throws ProException
	{
			
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		
			 String sql="SELECT * FROM CUSTOMER where CustomerId=?;";
			 
			 try(PreparedStatement statement = connection.prepareStatement(sql))
				{
					statement.setInt(1,customerId); 
					
					ResultSet result=statement.executeQuery();
					 
					 if(result!=null)
					 {
					 
						 CustomerInfo customerInfo=new CustomerInfo();
						 
					 while(result.next())
					 {
						 
						
						 
						 customerInfo.setCustomerId(result.getInt("CustomerID"));
						 
						 customerInfo.setName(result.getString("Name"));
						 
						 customerInfo.setAddress(result.getString("Address"));
						 
						 customerInfo.setMobile(result.getString("Mobile"));
						 
						 customerInfo.setStatus(result.getBoolean("Status"));
						 
						 customerInfo.setMail(result.getString("MailID"));
					 }
					 
					 return customerInfo;
				    }
						
					 return null;
				}
				catch(SQLException e)
				{
					throw new ProException("Sorry error occured at server side while getting customer data");
					
				}
			
			
	}
	
	public AccountInfo getUpdateAccount(int accountId)throws ProException
	{
			
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		
			 String sql="SELECT * FROM ACCOUNT where AccountId=?;";
			 
			 try(PreparedStatement statement = connection.prepareStatement(sql))
				{
					statement.setInt(1,accountId); 
					
					ResultSet result=statement.executeQuery();
					 
					 if(result!=null)
					 {
					 
						 AccountInfo accountInfo=new AccountInfo();
						 
					 while(result.next())
					 {
						 
						
						 accountInfo.setAccountId(result.getInt("AccountID"));
						 
						 accountInfo.setCustomerId(result.getInt("CustomerID"));
						 
						 accountInfo.setBranch(result.getString("Branch"));
						 
						 accountInfo.setBalance(result.getInt("Balance"));
						 
						 accountInfo.setStatus(result.getBoolean("Status"));
					 }
					 
					 return accountInfo;
				    }
						
					 return null;
				}
				catch(SQLException e)
				{
					throw new ProException("Sorry error occured at server side while getting account data");
					
				}
			
			
	}
	
	
	public CustomerInfo customerFullUpdate(String name,String mobile,String mail,String address,int customerId,String sql)throws ProException
	{
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setString(1,name); 
			
			statement.setString(2, mobile);
			
			statement.setString(3, mail);
			
			statement.setString(4, address);
			
			statement.setInt(5, customerId);
			
			statement.executeUpdate();
			
			return getUpdateCustomer(customerId);
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry server side error occured while updating customer data's");
			
		}
		
	}
	
	   public int getAmount(int accountId)throws ProException
	   {

		   Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		   try(PreparedStatement statement = connection.prepareStatement("SELECT Balance FROM ACCOUNT where AccountID=?;"))
		   {
			  
			   statement.setInt(1,accountId);
			   
			   ResultSet amount=statement.executeQuery();
			   
			   int balance=0;
			   
			   while(amount.next())
			   {
				   balance=amount.getInt("Balance");
			   }
			   
			   return balance;
		   }
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while fetching data");
			
		}
	   }
	
	public Map<String,Integer> validatePassword(int userID,String password)throws ProException
	{
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		   try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER where UserID=?;"))
		   {
			  
			   statement.setInt(1,userID);
			   
			   ResultSet validate=statement.executeQuery();
			  
			   String Password=null;
			   
			   int rollId=0;
			   
			   int customerId=0;
			   
			   while(validate.next())
			   {
				   Password=validate.getString("Password");
				  
				   rollId=validate.getInt("RoleID");
				   
				   customerId=validate.getInt("CustomerID");
			   }
			   
			if(Password!=null)
			{
			   
			   if(Password.equals(Test.encryptPassword(password)))
			   {
				   
				   Map<String,Integer> data=new HashMap<String,Integer>();
				   
				   data.put("roleID",rollId);
				   
				   data.put("customerID",customerId);
				   
				   return data;
				   
				   
			   }
			   
			   throw new ProException("Sorry you have entered an invalid password");
			  
		   }
			throw new ProException("Sorry no such userId Exist");
			
		   }
		catch(SQLException e)
		{
			throw new ProException("Sorry server side error occured while validating your credentials");
			
		}
	}
	
	public void updatePassword(String password,int userId,String sql)throws ProException
	{
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		try(PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setString(1,password); 
			
			statement.setInt(2,userId);
			
			statement.executeUpdate();
	
		}
		catch(SQLException e)
		{
			throw new ProException("Sorry error occured at server side while updating password");
			
		}
		
	}
	
	public boolean validateMail(int userId,String mail)throws ProException
	{
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		   try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER where UserID=?;"))
		   {
			  
			   statement.setInt(1,userId);
			   
			   ResultSet validate=statement.executeQuery();
			 
			   int customerId=0;
			   
			   while(validate.next())
			   {
				   
				   customerId=validate.getInt("CustomerID");
			   }
			   
			if(customerId!=0)
			{
			   
				boolean result=secondaryValidate(customerId,mail);
				
				if(result)
				{
					return true;
				}
					
			  
		   }
			throw new ProException("Sorry no such userId Exist");
			
		   }
		catch(SQLException e)
		{
			throw new ProException("Sorry server side error occured while validating your credentials");
			
		}
		
	}
	
	
	public boolean secondaryValidate(int customerId,String mail)throws ProException
	{
		
		Connection connection=connectionUtlity.CONNECTION.createConnection();
		
		   try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM CUSTOMER where CustomerID=?;"))
		   {
			  
			   statement.setInt(1,customerId);
			   
			   ResultSet validate=statement.executeQuery();
			 
			   String mails=null;
			   
			   while(validate.next())
			   {
				   
				   mails=validate.getString("MailID");
			   }
			   
			if(mails!=null)
			{
			   
			   if(mail.equals(mails))
			   {
				   
				  return true;
				   
			   }
			   
			   throw new ProException("Sorry you have entered an invalid mailId");
			  
		   }
			throw new ProException("Sorry no such customerID Exist");
			
		   }
		catch(SQLException e)
		{
			throw new ProException("Sorry server side error occured while reseting your password");
			
		}
	}
	
	
}
