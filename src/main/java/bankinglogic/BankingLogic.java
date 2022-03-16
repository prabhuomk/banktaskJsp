package bankinglogic;

import customerinfo.CustomerInfo;
import accountinfo.AccountInfo;
import cache.CacheStorage;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import storageinterface.DataStorage;
import utility.Utility;
import proexception.ProException;

public class BankingLogic {

	CacheStorage cache = new CacheStorage();

	Properties property = new Properties();

	DataStorage store = null;

	public BankingLogic() throws ProException {
		
		loadInterface();

		loadData();

	}

	private void loadInterface() throws ProException {
		try {

			
			/*  FileReader readData = new FileReader("className.properties");
			  
			  BufferedReader buffer = new BufferedReader(readData);
			  
			  property.load(buffer);
			  
			  String name=property.getProperty("key");
			*/

			Class className = Class.forName("databaselayer.DataBaseLayer");

			Object object = className.getDeclaredConstructor().newInstance();

			store = (DataStorage) object;

		} catch (Exception e) {

			throw new ProException(e);

		}

	}

	private void loadData() throws ProException 
	{
		Map<Integer, CustomerInfo> customerMap = store.readCustomer();

		Map<Integer, Map<Integer, AccountInfo>> accountMap = store.readAccount();

		cache.setData(customerMap, accountMap);
	}
	
	

	public List<CustomerInfo> allCustomer() throws ProException 
	{
		
		
		     Map<Integer, CustomerInfo> customer =cache.allCustomer();
	        
	         List<CustomerInfo> customerInfoList=new ArrayList();

			for (Map.Entry<Integer, CustomerInfo> entrie : customer.entrySet()) 
			{
				CustomerInfo customerData = entrie.getValue();
				
				customerInfoList.add(customerData);
				
			}
			
			return customerInfoList;
		
	}

	
	
	public List<AccountInfo> allAccount() throws ProException 
	{
		 

			  Map<Integer,Map<Integer,AccountInfo>> account =cache.allAccount();
			   
			  List<AccountInfo> accountInfoList=new ArrayList();
			   
			  Map<Integer,Integer> idDetails=new HashMap<Integer,Integer>();
	        
	           for(Map.Entry<Integer,Map<Integer,AccountInfo>> entry :account.entrySet())
	           {
	        	   
	        	Map<Integer,AccountInfo> innerMap =entry.getValue();
	        	
	        	for(Map.Entry<Integer,AccountInfo> entrie :innerMap.entrySet())
	            { 
	        	      AccountInfo accountData=entrie.getValue(); 
	        	      
	        	      accountInfoList.add(accountData);
	        	      
	        	      int accountId=accountData.getAccountId();
	        	      
	        	      int customerId=accountData.getCustomerId();
	        	      
	        	      idDetails.put(accountId,customerId);
	        	      
			
	            }
	        }
	           
	           return accountInfoList;
	}
	
	public Map<Integer,Integer> idDetails() throws ProException
	{
		
		  Map<Integer,Map<Integer,AccountInfo>> account =cache.allAccount();
		   
		   
		  Map<Integer,Integer> idDetails=new HashMap<Integer,Integer>();
      
         for(Map.Entry<Integer,Map<Integer,AccountInfo>> entry :account.entrySet())
         {
      	   
      	Map<Integer,AccountInfo> innerMap =entry.getValue();
      	
      	for(Map.Entry<Integer,AccountInfo> entrie :innerMap.entrySet())
          { 
      	      AccountInfo accountData=entrie.getValue(); 
      	      
      	      
      	      
      	      int accountId=accountData.getAccountId();
      	      
      	      int customerId=accountData.getCustomerId();
      	      
      	      idDetails.put(accountId,customerId);
      	      
		
          }
      }
         
         return idDetails;
		
	}
	
	

	public boolean addCustomer(CustomerInfo customerInfo) throws ProException 
	{
		
		 Utility.validatorForObject(customerInfo);
		 
		 int customerId = store.addCustomer(customerInfo);
		
	     customerInfo.setCustomerId(customerId);

		 cache.addCustomer(customerId, customerInfo);
		 
		 return true;

	}

	public CustomerInfo getCustomer(int customerId) 
	{
		return cache.getCustomer(customerId);
	}

	public boolean addAccount(AccountInfo accountInfo) throws ProException
	{
         
		Utility.validatorForObject(accountInfo);
		
		int accountId = store.addAccount(accountInfo);
		
		accountInfo.setAccountId(accountId);

		cache.addAccount(accountId, accountInfo);
		
		return true;
	}

	public Map<Integer, AccountInfo> getAccount(int customerId) 
	{
		return cache.getAccount(customerId);
	}

	public boolean changeCustomerStatus(int customerId, int status) throws ProException
	{
		store.changeCustomerStatus(customerId, status);

		cache.changeCustomerStatus(customerId, status);

		return true;

	}

	public boolean changeAccountStatus(int customerId, int accountId, int status) throws ProException 
	{
		store.changeAccountStatus(customerId, accountId, status);

		cache.changeAccountStatus(customerId, accountId, status);

		return true;

	}

	public boolean deposit(int customerId, int accountId, int amount) throws ProException {

		store.deposit(customerId, accountId, amount);

		cache.deposit(customerId, accountId, amount);

		return true;

	}

	public boolean withdrawal(int customerId, int accountId, int amount) throws ProException {

		store.withdrawal(customerId, accountId, amount);

		cache.withdrawal(customerId, accountId, amount);

		return true;

	}

	public Map<String, Integer> validatePassword(int userId, String password) throws ProException {

		return store.validatePassword(userId, password);
	}
	
	public boolean updatePassword(int userId,String mail,String type)throws ProException
	{
		 store.updatePassword(userId,mail,type);
		 
		 return true;
		
	}

	public AccountInfo getOneAccount(int customerId, int accountId)
	{
		return cache.getOneAccount(customerId, accountId);
	}

	public boolean accountFullUpdate(int customerId, int accountId, String branch, int balance) throws ProException
	{
		AccountInfo info=store.accountFullUpdate(customerId, accountId, branch, balance);
		
		cache.addAccount(accountId, info);
		
		return true;
	}
	
	public boolean customerFullUpdate(int customerId, String name, String mobile,String mail, String address) throws ProException
	{
		CustomerInfo info=store.customerFullUpdate(customerId, name, mobile,mail, address);
		
		cache.addCustomer(customerId, info);
		
		return true;
	}
	
	public void addAdmin(String mailID)throws ProException
	{
		
		store.addAdmin(mailID);
	}
}
