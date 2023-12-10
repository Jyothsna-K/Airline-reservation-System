package application;

import java.io.IOException;

import businessPackage.Flight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CustomerHomeController {
	
	private String userId;
    @FXML
    private TextField departuretext;

    @FXML
    private TextField arrivaltext;

    @FXML
    private Button continuebutton;

    @FXML
    private Button bookbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button viewbutton;
    
    @FXML
    private Button backbutton;

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
    void continueaction(ActionEvent event) throws IOException {
    	
    	
    	String arrivalcity = arrivaltext.getText();
    	String departurecity = departuretext.getText();
    	
    	System.out.print("UserID : "+userId+"\n\n");
    	
    	if(arrivaltext.getText().isEmpty() ||  departuretext.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setContentText( "Invalid Search--: Fill the both fields" );
	    	alert.show( );	
    	}
    	else if(!(arrivaltext.getText().matches("[a-zA-Z]+") &&  departuretext.getText().matches("[a-zA-Z]+"))) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setContentText( "Invalid Search--: Use alphabets only" );
	    	alert.show( );	
    	}
    	else {
    		
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Book.fxml"));
	    	Parent root = loader.load();
	    	
	    	BookController bc = loader.getController();
	    	bc.getfromcustomerhome(arrivalcity, departurecity);
	    	bc.setUserId(userId);
	    	
	    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
    	}
    }

    @FXML
    void deleteaction(ActionEvent event) throws IOException {
//    	Parent root = FXMLLoader.load(getClass().getResource("DeleteBooking.fxml"));
//    	Stage window = (Stage) deletebutton.getScene().getWindow();
//    	window.setScene(new Scene(root, 850, 650));

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

    void setUserId(String u) {
    	userId = u;
    }
}