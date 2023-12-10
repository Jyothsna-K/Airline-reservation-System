package businessPackage;

import dbpackage.DbPersistanceHandler;

public class Customer {

	private String customerName;
	private String customerId;
	private String customerPassword;
	

	public Customer(String n, String p){ 
		customerName = n;
		customerPassword = p;
		
	}


	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerPasswordDb(String name) {
		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
		String result = dh.runCustomQuery2("select * from customer_A20548265 where customerName='"+name+"'", "customerPassword");
    	return result;
	}
	public String getCustomerIdDb(String name) {
		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
		String result =  dh.runCustomQuery2("select * from customer_A20548265 where customerName='"+name+"'", "customerId");
    	return result;
	}
	public void saveCustomerDb() {
		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
    	dh.saveCustomer(customerId, customerName,customerPassword);
    	
	}
		
}
