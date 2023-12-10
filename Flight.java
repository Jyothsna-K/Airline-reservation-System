package businessPackage;

import dbpackage.DbPersistanceHandler;

public class Flight {
	private String flightId;
	private String takeoffTime;
	private String landingTime;
	private String destination;
	private String takeoffLocation;
	private int totalSeats;

	private String status;
	private int ticketPrice;

	public Flight(String fid, int st,String des,String tol, String tot, String lt, String stat, int p) {
		this.flightId=fid;
		this.takeoffLocation=tol;
		this.landingTime=lt;
		this.destination=des;
		this.takeoffTime=tot;
		this.totalSeats=st;
		this.setStatus(stat);
		this.setTicketPrice(p);
	}
	public Flight(String fid) {
		flightId = fid;
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
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;	
	}

	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void saveToDb(){
    	DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
    	dh.saveFlight(totalSeats, destination,takeoffLocation , takeoffTime,landingTime, status,ticketPrice);
	}
	public void deleteFlightDb() {
		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
    	dh.deleteFlight(flightId);
	}
}
