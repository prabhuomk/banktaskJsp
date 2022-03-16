package filelayer;

import java.util.Map;

import accountinfo.AccountInfo;
import customerinfo.CustomerInfo;
import filequery.FileQuery;
import proexception.ProException;
import storageinterface.DataStorage;

public class FileLayer implements DataStorage{

	
	FileQuery query=new FileQuery();
	
	public Map<Integer, CustomerInfo> readCustomer()throws ProException {
	
		return query.readCustomer();
	}


	public Map<Integer, Map<Integer, AccountInfo>> readAccount()throws ProException {
		
		return query.readAccount();
	}

	

	public int addCustomer(CustomerInfo customerInfo) throws ProException {
		
		return query.addCustomer(customerInfo);
		
		
	}

	
	public CustomerInfo getCustomer(int customerId) throws ProException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int addAccount(AccountInfo accountInfo) throws ProException {
		
		return query.addAccount(accountInfo);
	}

	@Override
	public Map<Integer, AccountInfo> getAccount(int customerId) throws ProException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeCustomerStatus(int customerId, int status) throws ProException {
		
		query.changeCustomerStatus(customerId,status);
		return true;
	}

	@Override
	public boolean changeAccountStatus(int customerId, int accountId, int status) throws ProException {
		
		query.changeAccountStatus(customerId,accountId,status);
		return true;
	}

	@Override
	public boolean deposit(int customerId, int accountId, int amount) throws ProException {
		
		query.deposit(customerId,accountId,amount);
		return true;
	}

	@Override
	public boolean withdrawal(int customerId, int accountId, int amount) throws ProException {
		
		query.withdrawal(customerId,accountId,amount);
		
		return true;
		
	}


	@Override
	public Map<String,Integer> validatePassword(int userid, String password) throws ProException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AccountInfo accountFullUpdate(int customerId, int accountId, String branch, int balance) throws ProException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CustomerInfo customerFullUpdate(int customerId, String name, String mobile,String mail, String address) throws ProException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updatePassword(int userId, String mail,String type) throws ProException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addAdmin(String mail) throws ProException {
		// TODO Auto-generated method stub
		
	}
	
	

}
