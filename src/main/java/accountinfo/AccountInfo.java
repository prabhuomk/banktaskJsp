package accountinfo;

public class AccountInfo {
	
	
	private int accountId;
	private int customerId;
	private String branch;
	private int balance;
	private boolean status=true;
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AccountInfo [accountId=" + accountId + ", customerId=" + customerId + ", branch=" + branch
				+ ", balance=" + balance + ", status=" + status + "]";
	}
	
	
	
	
	

	

	
	
	

	
	

}
