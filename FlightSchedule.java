package businessPackage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dbpackage.DbPersistanceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FlightSchedule {
	private Vector<Flight> FlightArr= new Vector<Flight>();

	public void addFlight(Flight f) {
		FlightArr.add(f);
	}
	 public ObservableList<Flight> getFlightTableList() throws SQLException{
		 ObservableList<Flight> List = FXCollections.observableArrayList();
    	 DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
	 
    	 ResultSet rs = dh.getFlight();
    	 Flight row;
    	 
    	 while(rs.next()) {
    		 row = new Flight(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7), rs.getInt(8));
    		 List.add(row);
    	 }
    	 
    	 return List;

	 }
	 
	 
	 
	 
	 


	 
	 public ObservableList<Flight> getSpecificFlightTableList(String bookedFlight) throws SQLException{
		 ObservableList<Flight> List = FXCollections.observableArrayList();
    	 DbPersistanceHandler dh = DbPersistanceHandler.getInstance();

    	 ResultSet rs = dh.getFlightById(bookedFlight);
    	 Flight row;
    	 
    	 while(rs.next()) {
    		 row = new Flight(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(5), rs.getString(5),rs.getString(6), rs.getString(7), rs.getInt(8));
    		 List.add(row);
    	 }
    	 
    	 return List;

	 }
	 
	 public ObservableList<Flight> getSpecificFlightTableList(String dep, String des) throws SQLException{
		 ObservableList<Flight> List = FXCollections.observableArrayList();
    	 DbPersistanceHandler dh = DbPersistanceHandler.getInstance();

    	 ResultSet rs = dh.runCustomQuery1("select * from flight_A20548265 ");
    			 
    	 Flight row;
    	 
    	 while(rs.next()) {
    		 row = new Flight(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7), rs.getInt(8));
    		 System.out.println("ok ok ");
    		 List.add(row);
    	 }
    	 
    	 return List;

	 }
}