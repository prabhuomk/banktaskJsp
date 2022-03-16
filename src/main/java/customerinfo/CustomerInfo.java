package customerinfo;

public class CustomerInfo
{
	
	
	private int customerId;
	
	private String name;
	
	private String address;
	
	private String mobile;
	
	private String mail;
	
	private boolean status=true;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerInfo [customerId=" + customerId + ", name=" + name + ", address=" + address + ", mobile="
				+ mobile + ", mail=" + mail + ", status=" + status + "]";
	}

	
	
	
	

	

	
	

	
	
	
}
