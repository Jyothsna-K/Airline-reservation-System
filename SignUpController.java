package application;

import java.io.IOException;

import businessPackage.Customer;
import dbpackage.DbPersistanceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private TextField usernametext;

    @FXML
    private PasswordField passwordtext;

    @FXML
    private Button signupbutton;

    
    @FXML
    private TextField cnictext;

    @FXML
    void signupaction(ActionEvent event) throws IOException {
    	if(usernametext.getText().isEmpty() || passwordtext.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "Fill all fields" );
        	alert.show( );
        	
    	}
    	else {
    		
	    		Customer c= new Customer(usernametext.getText(), passwordtext.getText());
	        	c.saveCustomerDb();
	        	
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setContentText( "New User Created" );
		    	alert.show( );
		        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		    	Stage window = (Stage) signupbutton.getScene().getWindow();
		    	window.setScene(new Scene(root, 850, 650));
    		}
    	
    	}
//    	DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
//    	dh.saveCustomer(usernametext.getText(), passwordtext.getText(), phonenotext.getText());
//    	
//    	
//    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//        Scene scene = new Scene(root);
//        stage.setTitle("Login Form");
//        stage.setScene(scene);
//        stage.show();
        
    
    }


