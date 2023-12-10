package dbpackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class showSelectedFlightModel {
	private String bookingId;
	private String flightId;
	private String seatNo;
	private String takeoffTime;
	private String landingTime;
	private String destination;
	private String takeoffLocation;
	private int ticketPrice;
	

	public showSelectedFlightModel(String bid, String fid,String des,String tol, String tot, String lt, String seat, int p) {
		this.setBookingId(bid);
		this.setFlightId(fid);
		this.setTakeoffLocation(tol);
		this.setLandingTime(lt);
		this.setDestination(des);
		this.setTakeoffTime(tot);
		this.setSeatNo(seat);
		this.setTicketPrice(p);
		
	}

	public showSelectedFlightModel() {
	}
	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getTakeoffTime() {
		return takeoffTime;
	}

	public void setTakeoffTime(String takeoffTime) {
		this.takeoffTime = takeoffTime;
	}

	public String getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(String landingTime) {
		this.landingTime = landingTime;
	}

	public String getTakeoffLocation() {
		return takeoffLocation;
	}

	public void setTakeoffLocation(String takeoffLocation) {
		this.takeoffLocation = takeoffLocation;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	 public ObservableList<showSelectedFlightModel> getModelTableList(String userId) throws SQLException{
		 
	   	 ObservableList<showSelectedFlightModel> List = FXCollections.observableArrayList();
		 DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
//		 dh.saveBooking("1","1","1A");
//		 dh.saveBooking("1","2","1A");
//		 dh.saveBooking("1","3","1A");
//		 dh.saveBooking("1","4","12M");
//		 dh.saveBooking("2","4","2A");

		 
		 ResultSet rs = dh.getBookingByCustomer(userId);
		 showSelectedFlightModel row;
	 
		 while (rs.next()) {
			    ResultSet rs2 = dh.getFlightById(rs.getString(3));

			    if (rs2.next()) {
			        System.out.print(rs.getString(1) + rs.getString(3) + rs2.getString(4) + rs2.getString(5) + rs2.getString(6) + rs2.getString(7) + rs.getString(4) + "\n");
			        row = new showSelectedFlightModel(rs.getString(1), rs.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs.getString(4), rs2.getInt(8));
			        List.add(row);
			    } else {
			        // Handle the case where there is no flight information for the booking
			   
			        Alert alert = new Alert(AlertType.INFORMATION);
			    	alert.setContentText( "No results avaialble"+ rs.getString(1));
			    	alert.show( );	
			    }
		 }
		return List;}}