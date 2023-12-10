package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import businessPackage.Booking;
import businessPackage.Flight;
import businessPackage.FlightSchedule;
import businessPackage.Payment;
import dbpackage.DbPersistanceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PaymentController {
	
	private String userId;
	private String bookedSeat, bookedFlight;
	@FXML
    private TableView<Flight> selectedflight;

    @FXML
    private TableColumn<Flight, String> bookingid;

    @FXML
    private TableColumn<Flight, String> flightid;

    @FXML
    private TableColumn<Flight, String> deptairport;

    @FXML
    private TableColumn<Flight, String> arrivalairport;

    @FXML
    private TableColumn<Flight , String> seat;

    @FXML
    private TableColumn<Flight, Integer> price;

    @FXML
    private Button bookbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button viewbutton;

    @FXML
    private TextField cardtext;

    @FXML
    private TextField amounttext;

    @FXML
    private Button confirmpayment;
    
    @FXML
    private Button viewschedbutton;
    
    @FXML
    private Button backbutton;
    
    @FXML
    void backaction(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
    	Parent root = loader.load();
    	
    	CustomerHomeController dbc = loader.getController();
    	dbc.setUserId(userId);
    	
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();

    }

    @FXML
    void bookaction(ActionEvent event) throws IOException {
//    	Parent root = FXMLLoader.load(getClass().getResource("CustomerHome.fxml"));
//    	Stage window = (Stage) bookbutton.getScene().getWindow();
//    	window.setScene(new Scene(root, 850, 650));
      	FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
    	Parent root = loader.load();
    	
    	CustomerHomeController dbc = loader.getController();
    	dbc.setUserId(userId);
    	
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();

    }

    @FXML
    void deleteaction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteBooking.fxml"));
    	Parent root = loader.load();
    	
    	DeleteBookingController dbc = loader.getController();
    	dbc.setUserId(userId);
    	
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void paymentaction(ActionEvent event) {
    	if(cardtext.getText().length() == 16) {
	    		if(cardtext.getText().matches("[0-9]+")) {
	//    		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
	//    		dh.saveBooking(userId, bookedFlight, bookedSeat);
	//    		dh.savePayment(15000, userId);
	    		Booking bk = new Booking(userId, bookedFlight,bookedSeat);
	    		Payment py = new Payment(userId, 145000);
	    		bk.saveBookingDb();
	    		py.savePaymentDb();
	    		Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setContentText( "Booking Successful" );
	        	alert.show( );	
	    	}
	    		else {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	            	alert.setContentText( "Incorrect Card Pin" );
	            	alert.show( );
	    		}
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "Incorrect Card Pin" );
        	alert.show( );	
    	}
    }

    @FXML
    void viewaction(ActionEvent event) throws IOException {
//    	Parent root = FXMLLoader.load(getClass().getResource("ViewBookings.fxml"));
//    	Stage window = (Stage) viewbutton.getScene().getWindow();
//    	window.setScene(new Scene(root, 850, 650));
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBookings.fxml"));
    	Parent root = loader.load();
    	
    	ViewBookingController dbc = loader.getController();
    	dbc.setUserId(userId);
    	
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    	
    	
    }
    
    @FXML
    void viewschedaction(ActionEvent event) throws SQLException {
    	System.out.print("\n UserName: "+userId+"\n");
  	  
			ObservableList<Flight> List = FXCollections.observableArrayList();
			FlightSchedule fs = new FlightSchedule();
			List = fs.getSpecificFlightTableList(bookedFlight);	   	 
//	   	 DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
//		 
////	   	 dh.saveFlight(250, "Pakistan","India", "15:10:00", "15:01:00", "Ontime",15200);
////	   	 dh.saveFlight(250, "Islamabad","Lahore", "14:10:00", "17:01:00", "Ontime",15200);
////	   	 dh.saveFlight(250, "London","America", "17:10:00", "19:01:00", "Ontime",15200);
////	   	 dh.saveFlight(250, "Multan","Islamabad", "12:10:00", "19:01:00", "Ontime",15200);
////	   	 dh.saveFlight(250, "Karachi","Multan", "16:10:00", "18:01:00", "Ontime",15200);
////	   	 	 
//	   	 ResultSet rs = dh.getFlightById(bookedFlight);
//	   	 Flight row;
//	   	 
//	   	 while(rs.next()) {
//	   		 row = new Flight(rs.getString(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getInt(9));
//	   		 List.add(row);
//	   	 }
	   	 
	   	 
	   	flightid.setCellValueFactory(new PropertyValueFactory<>("flightId"));
	   	deptairport.setCellValueFactory(new PropertyValueFactory<>("destination"));
	   	arrivalairport.setCellValueFactory(new PropertyValueFactory<>("takeoffLocation"));
//	   	arrivaltime.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
//	   	departuretime.setCellValueFactory(new PropertyValueFactory<>("takeoffTime"));
	   	price.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
	   	
	   	
	   	selectedflight.setItems(List);
	   	selectedflight.setPlaceholder(new Label("No results available"));
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookedFlight() {
		return bookedFlight;
	}

	public void setBookedFlight(String bookedFlight) {
		this.bookedFlight = bookedFlight;
	}

	public String getBookedSeat() {
		return bookedSeat;
	}

	public void setBookedSeat(String bookedSeat) {
		this.bookedSeat = bookedSeat;
	}

}
