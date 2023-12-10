package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import businessPackage.Booking;
import dbpackage.DbPersistanceHandler;
import dbpackage.showSelectedFlightModel;
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

public class DeleteBookingController {
	private String userId;

    @FXML
    private Button bookbutton;

    @FXML
    private Button deletemenubutton;

    @FXML
    private Button viewbutton;

    @FXML
    private Button backbutton;
    
    @FXML
    private Button viewbookingtable;
    
    @FXML
    private TableView<showSelectedFlightModel> mybookings;

    @FXML
    private TableColumn<showSelectedFlightModel,String> bookingid;

    @FXML
    private TableColumn<showSelectedFlightModel,String> flightid;

    @FXML
    private TableColumn<showSelectedFlightModel,String> deptairport;

    @FXML
    private TableColumn<showSelectedFlightModel,String> arrivalairport;

    @FXML
    private TableColumn<showSelectedFlightModel,String> arrivaltime;

    @FXML
    private TableColumn<showSelectedFlightModel,String> depttime;

    @FXML
    private TableColumn<showSelectedFlightModel,Integer> price;

    @FXML
    private TableColumn<showSelectedFlightModel,String> seatno;

    @FXML
    private TextField bookingidtext;

    @FXML
    private Button deletebutton;

	public void initialize() throws SQLException {
		mybookings.setPlaceholder(new Label("Press View for Results"));	
	}
	
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
    void deleteaction(ActionEvent event) {
    	//DB CODE FOR WHAT HAPPENS IF WE DELETE----GENERATE AN ALERT TOO
//    	DbPersistanceHandler dh = DbPersistanceHandler.getInstance();    	
//    	dh.deleteBooking(bookingidtext.getText());
    	
    	if(bookingidtext.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "Fill the field" );
        	alert.show( );	
    	}
    	else {
    		Booking bk = new Booking(bookingidtext.getText());
	    	bk.deleteBooking();
	    	System.out.print(bookingidtext.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setContentText( "SuccessFully Deleted" );
	    	alert.show( );		    
    	}
    }

    @FXML
    void deletebookingaction(ActionEvent event) throws IOException {
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
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBookings.fxml"));
    	Parent root = loader.load();
    	
    	ViewBookingController dbc = loader.getController();
    	dbc.setUserId(userId);
    	
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    	
//    	Parent root = FXMLLoader.load(getClass().getResource("ViewBookings.fxml"));
//    	Stage window = (Stage) viewbutton.getScene().getWindow();
//    	window.setScene(new Scene(root, 850, 650));

    }
    
    @FXML
    void viewbookingtableaction(ActionEvent event) throws SQLException {
    	System.out.print("UserID : "+userId+"\n\n");
    
	   	 ObservableList<showSelectedFlightModel> List = FXCollections.observableArrayList();
	   	 showSelectedFlightModel sm = new showSelectedFlightModel();
	   	 List = sm.getModelTableList(userId);
//		 DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
////		 dh.saveBooking("1","1","1A");
////		 dh.saveBooking("1","2","1A");
////		 dh.saveBooking("1","3","1A");
////		 dh.saveBooking("1","4","12M");
////		 dh.saveBooking("2","4","2A");
//
//		 
//		 ResultSet rs = dh.getBookingByCustomer(userId);
//		 showSelectedFlightModel row;
//	 
//		 while(rs.next()) {
//			 ResultSet rs2 = dh.getFlightById(rs.getString(2));
//			 rs2.next();
////			 
//			 System.out.print(rs.getString(1)+rs.getString(3)+rs2.getString(4)+rs2.getString(5)+rs2.getString(6)+rs2.getString(7)+rs.getString(4)+"\n");
//			row = new showSelectedFlightModel(rs.getString(1),rs.getString(3),rs2.getString(4),rs2.getString(5),rs2.getString(6),rs2.getString(7),rs.getString(4),rs2.getInt(9));
//			List.add(row);
//		 }
//		 
//		 
		 bookingid.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
		flightid.setCellValueFactory(new PropertyValueFactory<>("flightId"));
		deptairport.setCellValueFactory(new PropertyValueFactory<>("takeoffLocation"));
		arrivalairport.setCellValueFactory(new PropertyValueFactory<>("destination"));
		arrivaltime.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
		depttime.setCellValueFactory(new PropertyValueFactory<>("takeoffTime"));
		seatno.setCellValueFactory(new PropertyValueFactory<>("seatNo"));
		price.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

		mybookings.setItems(List);
		
		
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
