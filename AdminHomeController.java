package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import businessPackage.Flight;
import businessPackage.FlightSchedule;
import dbpackage.DbPersistanceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminHomeController {

    @FXML
    private Button afsbutton;

    @FXML
    private Button dfsbutton;
    
    
    @FXML
    private Button cfsbutton;
    
    @FXML
    private Button efsbutton;
    
    @FXML
    private Button viewschedueletable;
    
    @FXML
    private TableView<Flight> flightschedueles;

    @FXML
    private TableColumn<Flight, String> flightid;

    @FXML
    private TableColumn<Flight, String> deptairport;

    @FXML
    private TableColumn<Flight, String> arrivalairport;

    @FXML
    private TableColumn<Flight, String> arrivaltime;

    @FXML
    private TableColumn<Flight, String> depttime;

    @FXML
    private TableColumn<Flight, Integer> price;

    @FXML
    private Button backbutton;

    @FXML
    private Button ffsbutton;
    
    @FXML
    private TextField depttimetext;

    @FXML
    private TextField arrivaltimetext;

    @FXML
    private TextField seattext;

    @FXML
    private TextField deptairporttext;

    @FXML
    private TextField arrivalairporttext;

    @FXML
    private TextField pricetext;

    @FXML
    private TextField statustext;

    @FXML
    private Button addbutton;

	private String userId;
    
	public void initialize() throws SQLException {
		flightschedueles.setPlaceholder(new Label("Press View for Results"));	
	}
	
    @FXML
    void addaction(ActionEvent event) {
    	
    	if(depttimetext.getText().isEmpty() || deptairporttext.getText().isEmpty() || arrivalairporttext.getText().isEmpty() || arrivaltimetext.getText().isEmpty() || seattext.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "Fill all fields" );
        	alert.show( );
    	}
    	else {
	      	Flight f= new Flight("0",Integer.parseInt(seattext.getText()), arrivalairporttext.getText(),deptairporttext.getText() , depttimetext.getText(),arrivaltimetext.getText(), statustext.getText(), Integer.parseInt(pricetext.getText()));
	        f.saveToDb();
	//    	DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
	//    	dh.saveFlight(Integer.parseInt(seattext.getText()), arrivalairporttext.getText(),deptairporttext.getText() , depttimetext.getText(),arrivaltimetext.getText(), statustext.getText(), Integer.parseInt(pricetext.getText()));
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setContentText( "Added Successfully" );
	    	alert.show( );
    	}
    }

    @FXML
    void afsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
    	Stage window = (Stage) afsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }

    @FXML
    void backaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	Stage window = (Stage) backbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));
    }

    @FXML
    void dfsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("DeleteFlightScheduele.fxml"));
    	Stage window = (Stage) dfsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }
    
    
    @FXML
    void ffsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("adminviewBooking.fxml"));
    	Stage window = (Stage) dfsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));
    }
   
    @FXML
    void efsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("employeeHome.fxml"));
    	Stage window = (Stage) dfsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }
   
    @FXML
    void viewschedueletableaction(ActionEvent event) throws SQLException {
    	ObservableList<Flight> List = FXCollections.observableArrayList();
    	FlightSchedule fs = new FlightSchedule();
    	List = fs.getFlightTableList();
    	flightid.setCellValueFactory(new PropertyValueFactory<>("flightId"));
    	arrivalairport.setCellValueFactory(new PropertyValueFactory<>("destination"));
    	deptairport.setCellValueFactory(new PropertyValueFactory<>("takeoffLocation"));
    	arrivaltime.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
    	depttime.setCellValueFactory(new PropertyValueFactory<>("takeoffTime"));
    	price.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
    	
    	
    	flightschedueles.setItems(List);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



}
