package cache;


import customerinfo.CustomerInfo;
import accountinfo.AccountInfo;
import maputility.MapUtility;
import proexception.ProException;
import java.util.HashMap;
import java.util.Map;

public class CacheStorage {
	
	
	   Map<Integer,CustomerInfo> customerData=new HashMap<Integer,CustomerInfo>();
	  	
	   Map<Integer,Map<Integer,AccountInfo>> customerAccount=new  HashMap<Integer,Map<Integer,AccountInfo>>();
	   
	  
	 
	  public void setData(Map<Integer,CustomerInfo> customerMap,Map<Integer,Map<Integer,AccountInfo>> accountMap)
	  {
		  
	    	  customerData=customerMap;
	    
	    	  customerAccount=accountMap;
	      
	  }
	  
	  public Map<Integer, CustomerInfo> allCustomer() throws ProException 
		{
			return customerData;
		}

		public Map<Integer, Map<Integer, AccountInfo>> allAccount() throws ProException 
		{
			return customerAccount;
		}

	  
	  public void addCustomer(int customerId,CustomerInfo customerInfo)
	  {
		  
		  MapUtility.addCustomer(customerId, customerInfo,customerData);
		 
	  }
	
	  public CustomerInfo getCustomer(int customerId)
	  {
		  return  MapUtility.getCustomer(customerId,customerData);
	  }

	  public void addAccount(int accountId,AccountInfo accountInfo)
	  {
		  
		  MapUtility.addAccount(accountId,accountInfo,customerAccount);
	  }
	  
	  public Map<Integer,AccountInfo> getAccount(int customerId)
	  {
		  return  MapUtility.getAccount(customerId,customerAccount);
	  }
	  
	  public void changeCustomerStatus(int customerId,int status)throws ProException
	  {
		  MapUtility.changeCustomerStatus(customerId,status,customerData);
		  
	  }
	  
	  public void changeAccountStatus(int customerId,int accountId,int status)throws ProException
		{
			
		  MapUtility.changeAccountStatus(customerId,accountId,status,customerAccount);
			
		}
	  
	  public void deposit(int customerId,int accountId,int amount)throws ProException
		{
			
		  MapUtility.deposit(customerId, accountId, amount,customerData,customerAccount);
				
		}
		
		public void withdrawal(int customerId,int accountId,int amount)throws ProException
		{
			
			 MapUtility.withdrawal(customerId, accountId, amount,customerData,customerAccount);
			
			
			
		}
		public AccountInfo getOneAccount(int customerId,int accountId)
		{
			return  MapUtility.getOneAccount(customerId, accountId,customerAccount);
		}
}

