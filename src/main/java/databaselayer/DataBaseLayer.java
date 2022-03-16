package databaselayer;

import java.util.Map;
import accountinfo.AccountInfo;
import customerinfo.CustomerInfo;
import proexception.ProException;
import storageinterface.DataStorage;
import tablequery.TableQuery;
import test.MyMail;
import test.Test;

public class DataBaseLayer implements DataStorage{

	
	TableQuery query =new TableQuery();
	
	
	
	
	public Map<Integer, CustomerInfo> readCustomer()throws ProException {
		
		return query.readCustomer();
	}

	
	public Map<Integer, Map<Integer, AccountInfo>> readAccount()throws ProException {
		
		return query.readAccount();
	}

	
	public int addCustomer(CustomerInfo customerInfo) throws ProException {
		
        String name=customerInfo.getName();
		
		String address=customerInfo.getAddress();
		
		String mobile=customerInfo.getMobile();
		
		String mailId=customerInfo.getMail();
		
		int addcustomer= query.insertIntoTable("INSERT INTO CUSTOMER(Name,Address,MailID,Mobile) VALUES('"+name+"','"+address+"','"+mailId+"','"+mobile+"');");
		
		String pass=Test.generatePassword(8);
		   
	    String hashPass=Test.encryptPassword(pass);
		
		int adduser=query.insertIntoTable("INSERT INTO USER(Password,RoleID,CustomerID) VALUES('"+hashPass+"',"+2+","+addcustomer+");");
		
		MyMail.startOperation(mailId,adduser,pass);
		
		return addcustomer;
		
		
	}

	
	public CustomerInfo getCustomer(int customerId) throws ProException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int addAccount(AccountInfo accountInfo) throws ProException {
		

		int customerID=accountInfo.getCustomerId();
		
		String branch=accountInfo.getBranch();
		
		int balance=accountInfo.getBalance();
		
		return query.insertIntoTable("INSERT INTO ACCOUNT(CustomerID,Branch,Balance) VALUES("+customerID+",'"+branch+"',"+balance+");");
		
		
		
		
	}

	
	public Map<Integer, AccountInfo> getAccount(int customerId) throws ProException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean changeCustomerStatus(int customerId, int status) throws ProException {
		
		boolean value;
		
		if(status==1)
		{
			value=true;
		}
		else
		{
			value=false;
		}
		
		query.changeStatus(value,customerId,"UPDATE CUSTOMER SET Status=? WHERE CustomerID=?;");
		
		return true;
		
	}

	
	public boolean changeAccountStatus(int customerId,int accountId, int status) throws ProException {
		
        boolean value;
		
		if(status==1)
		{
			value=true;
		}
		else
		{
			value=false;
		}
		
		query.changeStatus(value,accountId,customerId,"UPDATE ACCOUNT SET Status=? WHERE AccountID=? AND CustomerID=?;");
		
		return true;
		
	}

	
	public boolean deposit(int customerId, int accountId, int amount) throws ProException {
		
		int balance=query.getAmount(accountId);
		
		int deposit=balance+amount;
		
		query.updateBalance(deposit,accountId,customerId,"UPDATE ACCOUNT SET Balance=? WHERE AccountID=? AND CustomerID=?;");
	
	    return true;
	}


	public boolean withdrawal(int customerId, int accountId, int amount) throws ProException {
		
        int balance=query.getAmount(accountId);
        
        if(balance>amount)
        {
        	int deposit=balance-amount;
    		
    		query.updateBalance(deposit,accountId,customerId,"UPDATE ACCOUNT SET Balance=? WHERE AccountID=? AND CustomerID=?;");
        	
        }
		
		return true;
		
	}
    
    public AccountInfo accountFullUpdate(int customerId, int accountId,String branch,int balance) throws ProException {
		
       
    		
    		return query.accountFullUpdate(branch,balance,accountId,customerId,"UPDATE ACCOUNT SET Branch=?,Balance=? WHERE AccountID=? AND CustomerID=?;");
        	
        
		
		
		
	}


	
	public Map<String,Integer> validatePassword(int userID, String password) throws ProException {
		
		return query.validatePassword(userID,password);
	}


	
	public CustomerInfo customerFullUpdate(int customerId, String name, String mobile,String mail, String address) throws ProException {
		
		return query.customerFullUpdate(name,mobile,mail,address,customerId,"UPDATE CUSTOMER SET Name=?,Mobile=?,MailID=?,Address=? WHERE CustomerID=?;");
		
		
		
	}


	@Override
	public void updatePassword(int userId, String mail,String type) throws ProException {
		
		
		if(type.equals("forget"))
		{
		
         
		      boolean result=query.validateMail(userId, mail);
		
		      if(result)
		      {
		
		              String pass=Test.generatePassword(8);
		   
	                  String hashPass=Test.encryptPassword(pass);
		
		              query.updatePassword(hashPass,userId,"UPDATE USER SET Password=? WHERE UserID=?;");
		
		             MyMail.startOperation(mail,userId,pass);
		
		     }
		}
		else if(type.equals("reset"))
		{
			String password=mail;
			
			String hashPass=Test.encryptPassword(password);
			
            query.updatePassword(hashPass,userId,"UPDATE USER SET Password=? WHERE UserID=?;");
			
		}
	}

public void addAdmin(String mail)throws ProException
{
	
        query.insertIntoUser(mail);

}

	


	

}
