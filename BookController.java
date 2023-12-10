package application;

import java.io.IOException;
import java.sql.SQLException;

import businessPackage.Flight;
import businessPackage.FlightSchedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BookController {
	
	private String userId;

	@FXML
	private TableView<Flight> schedueletable;
	
	@FXML
	private TableColumn<Flight, String> flightstatus;

	@FXML
	private TableColumn<Flight, String> flightid;

	@FXML
	private TableColumn<Flight, String> departureairport;

	@FXML
	private TableColumn<Flight, String> arrivalairport;

	@FXML
	private TableColumn<Flight, String> arrivaltime;

	@FXML
	private TableColumn<Flight, String> departuretime;

	@FXML
	private TableColumn<Flight, Integer> price;

	@FXML
	private TextField flightidtext;

	@FXML
	private TextField seatnotext;

	@FXML
	private Button bookbutton;

	@FXML
	private Button bookflightbutton;

	@FXML
	private Button deletebutton;

	@FXML
	private Button viewbutton;

	@FXML
	private Button backbutton;

	@FXML
	private Button viewschedbutton;

	public void initialize() throws SQLException {
		schedueletable.setPlaceholder(new Label("Press View for Results"));	
	}
	
	@FXML
	void backaction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("CustomerHome.fxml"));
		Stage window = (Stage) backbutton.getScene().getWindow();
		window.setScene(new Scene(root, 850, 650));

	}

	@FXML
	void bookaction(ActionEvent event) throws IOException, SQLException {
	
		if(flightidtext.getText().isEmpty() ||  seatnotext.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setContentText( "Invalid Search--: Fill the both fields" );
	    	alert.show( );	
    	}
    	else if(!(flightidtext.getText().matches("[0-9]+") &&  seatnotext.getText().matches("^[a-zA-Z0-9]*$"))) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setContentText( "Invalid Search--: Use numeric for id and alphanumeric for seats only" );
	    	alert.show( );	
    	}
    	else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
	    	Parent root = loader.load();
	    	
	    	PaymentController dbc = loader.getController();
	    	dbc.setUserId(userId);
	    	dbc.setBookedFlight(flightidtext.getText());
	    	dbc.setBookedSeat(seatnotext.getText());
	    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
    	}
	}

	@FXML
	void bookflightaction(ActionEvent event) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
//		Stage window = (Stage) bookflightbutton.getScene().getWindow();
//		window.setScene(new Scene(root, 850, 650));
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
	void viewaction(ActionEvent event) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("ViewBookings.fxml"));
//		Stage window = (Stage) viewbutton.getScene().getWindow();
//		window.setScene(new Scene(root, 850, 650));

		FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBookings.fxml"));
    	Parent root = loader.load();
    	
    	ViewBookingController dbc = loader.getController();
    	dbc.setUserId(userId);
    	
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    	
	}
	String departureLocation, arrivalLocation;
	
	public void getfromcustomerhome(String d, String a)
	{
		departureLocation=d;
		arrivalLocation=a;	
	}

	@FXML
    void viewschedaction(ActionEvent event) throws SQLException {
    	
		System.out.print("Departure : "+departureLocation+"\n Arrival: "+arrivalLocation+"\n UserName: "+userId+"\n");
	   	
		ObservableList<Flight> List = FXCollections.observableArrayList();
		FlightSchedule fs = new FlightSchedule();
		List = fs.getSpecificFlightTableList(departureLocation,arrivalLocation);
			
	   	 
	   	flightid.setCellValueFactory(new PropertyValueFactory<>("flightId"));
	   	departureairport.setCellValueFactory(new PropertyValueFactory<>("destination"));
	   	arrivalairport.setCellValueFactory(new PropertyValueFactory<>("takeoffLocation"));
	   	arrivaltime.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
	   	departuretime.setCellValueFactory(new PropertyValueFactory<>("takeoffTime"));
	   	price.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
	   	flightstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
	   	
	   	schedueletable.setItems(List);
			schedueletable.setPlaceholder(new Label("No results available"));
    }
	
	void setUserId(String u) {
		userId = u;
	}
}
