package application;

import java.io.IOException;
import java.sql.SQLException;

import businessPackage.Flight;
import businessPackage.FlightSchedule;
import dbpackage.showSelectedFlightModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BoardingPassController {
	private String userId;
	@FXML
    private TableView<showSelectedFlightModel> boardingpass;

    @FXML
    private TableColumn<showSelectedFlightModel,String> flightid;

    @FXML
    private TableColumn<showSelectedFlightModel,String> deptairport;

    @FXML
    private TableColumn<showSelectedFlightModel,String> arrivalairport;

    @FXML
    private TableColumn<showSelectedFlightModel,String> seat;

    @FXML
    private Button bookbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button viewbutton;

    @FXML
    private Button backbutton;
    
    @FXML
    private Button viewboardingpasstable;


    @FXML
    void backaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	Stage window = (Stage) backbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));
    	

    }

    @FXML
    void bookaction(ActionEvent event) throws IOException {
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
    void viewboardingpasstableaction(ActionEvent event) throws SQLException {
    	System.out.print("\n UserName: "+userId+"\n");
    	  
    	 ObservableList<showSelectedFlightModel> List = FXCollections.observableArrayList();
	   	 showSelectedFlightModel sm = new showSelectedFlightModel();
	   	 List = sm.getModelTableList(userId);
		
	   	flightid.setCellValueFactory(new PropertyValueFactory<>("flightId"));
	   	deptairport.setCellValueFactory(new PropertyValueFactory<>("destination"));
	   	arrivalairport.setCellValueFactory(new PropertyValueFactory<>("takeoffLocation"));
//	   	arrivaltime.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
//	   	departuretime.setCellValueFactory(new PropertyValueFactory<>("takeoffTime"));


	   	
		boardingpass.setItems(List);
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
