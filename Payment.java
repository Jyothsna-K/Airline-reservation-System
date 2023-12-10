package businessPackage;

import dbpackage.DbPersistanceHandler;

public class Payment {
	private String paymentId;
	private String customerId;
	private String bookingId;
	private int amount;
	
	public Payment(String id, int a ) {
		customerId = id;
		amount=a;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public void reccordPayment() {
		
	}
	public void savePaymentDb() {
		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
		dh.savePayment(amount, customerId);
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
