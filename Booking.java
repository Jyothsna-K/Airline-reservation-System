package businessPackage;

import dbpackage.DbPersistanceHandler;

public class Booking {
	private String bookingId;
	private String customerId;
	private String flightId;
	private String seatNo;
	
	public Booking(String bid, String custid, String fid, String seat) {
		this.bookingId = bid;
		this.customerId = custid;
		this.flightId=fid;
		this.seatNo=seat;
	}
	public Booking(String bid) {
		this.bookingId = bid;
		this.customerId = "";
		this.flightId="";
		this.seatNo="";
	}
	public Booking(String custid, String fid, String seat) {
		this.bookingId = "";
		this.customerId = custid;
		this.flightId=fid;
		this.seatNo=seat;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public void deleteBooking() {
		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();    	
    	dh.deleteBooking(bookingId);
	}
	public void saveBookingDb() {
		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
		dh.saveBooking(customerId, flightId, seatNo);
	}
}
