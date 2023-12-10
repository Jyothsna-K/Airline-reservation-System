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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DeleteSchedueleController {

    @FXML
    private Button afsbutton;

    @FXML
    private Button dfsbutton;
    
    
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
    private TextField flightidtext;

    @FXML
    private Button deletetext;
    
    @FXML
    private Button viewscheduele;
	public void initialize() throws SQLException {
		flightschedueles.setPlaceholder(new Label("Press View for Results"));	
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
    void deleteaction(ActionEvent event) {
    	
    	if (deletetext.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "Fill the field" );
        	alert.show( );
    	}
    	else {
	    	Flight f = new Flight(flightidtext.getText());
	    	f.deleteFlightDb();
	//    	DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
	//    	dh.deleteFlight(flightidtext.getText());
	    	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "deleted Successfully" );
        	alert.show( );
    	}
    }
    	

    @FXML
    void dfsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("DeleteFlightScheduele.fxml"));
    	Stage window = (Stage) dfsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }
    
    @FXML
    void viewschedueleaction(ActionEvent event) throws SQLException {
    	ObservableList<Flight> List = FXCollections.observableArrayList();
		FlightSchedule fs = new FlightSchedule();
		List = fs.getFlightTableList();	
		
//   	 ObservableList<Flight> List = FXCollections.observableArrayList();
//	 DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
 
//	 dh.saveFlight(250, "Pakistan","India", "15:10:00", "15:01:00", "Ontime",15200);
//	 dh.saveFlight(250, "Islamabad","Lahore", "14:10:00", "17:01:00", "Ontime",15200);
//	 dh.saveFlight(250, "London","America", "17:10:00", "19:01:00", "Ontime",15200);
//	 dh.saveFlight(250, "Multan","Islamabad", "12:10:00", "19:01:00", "Ontime",15200);
//	 dh.saveFlight(250, "Karachi","Multan", "16:10:00", "18:01:00", "Ontime",15200);
//	 	 
//	 ResultSet rs = dh.getFlight();
//	 Flight row;
//	 
//	 while(rs.next()) {
//		 row = new Flight(rs.getString(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getInt(9));
//		 List.add(row);
//	 }
	 
	flightid.setCellValueFactory(new PropertyValueFactory<>("flightId"));
	arrivalairport.setCellValueFactory(new PropertyValueFactory<>("destination"));
	deptairport.setCellValueFactory(new PropertyValueFactory<>("takeoffLocation"));
	arrivaltime.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
	depttime.setCellValueFactory(new PropertyValueFactory<>("takeoffTime"));
	price.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
	flightschedueles.setItems(List);
    }



}
