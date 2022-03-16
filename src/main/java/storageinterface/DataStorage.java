package storageinterface;

import customerinfo.CustomerInfo;
import java.util.Map;
import accountinfo.AccountInfo;
import proexception.ProException;


public interface DataStorage {
	
	//get customer map
	public Map<Integer,CustomerInfo> readCustomer() throws ProException;
	
	//get account map
	public Map<Integer,Map<Integer,AccountInfo>> readAccount()throws ProException;
	
	//add customer
	public int addCustomer(CustomerInfo customerInfo)throws ProException;
	
	//get customer
	public CustomerInfo getCustomer(int customerId)throws ProException;
	
	//add account
	public int addAccount(AccountInfo accountInfo)throws ProException;
	
	//get account
	public Map<Integer,AccountInfo> getAccount(int customerId)throws ProException;
	
	//change customer status
	public boolean changeCustomerStatus(int customerId,int status)throws ProException;
	
	//change account status
	public boolean changeAccountStatus(int customerId,int accountId,int status)throws ProException;
	
	
	//deposit
	public boolean deposit(int customerId,int accountId,int amount)throws ProException;
	
	//withdraw
	public boolean withdrawal(int customerId,int accountId,int amount)throws ProException;
	
	
	//check password
	public Map<String,Integer> validatePassword(int userid,String password)throws ProException;
	
	//update password
	
	public void updatePassword(int userId,String mail,String type)throws ProException;
	
	
	public AccountInfo accountFullUpdate(int customerId, int accountId,String branch,int balance)throws ProException;
	
	
	public CustomerInfo customerFullUpdate(int customerId, String name, String mobile,String mail ,String address) throws ProException;
	
	public void addAdmin(String mail) throws ProException;

}
