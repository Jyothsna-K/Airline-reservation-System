package application;

import java.io.IOException;
import java.sql.SQLException;

import businessPackage.Customer;
import businessPackage.Employee;
import dbpackage.DbPersistanceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class LoginController {

    @FXML
    public TextField usernametext;

    @FXML
    private PasswordField passwordtext;

    @FXML
    private Button loginbutton;

    @FXML
    private Button signupbutton;
    
    @FXML
    private CheckBox admincheckbox;
    
    @FXML
    private CheckBox employeecheckbox;

    @FXML
    void checkboxaction(ActionEvent event) {
    	
    }

    
    
    
    @FXML
    void loginaction(ActionEvent event) throws IOException, SQLException {
    	String adminUser = "admin";
    	String adminPassword = "admin123";
    	DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
    	
    	String un = usernametext.getText();
    	String pw = passwordtext.getText();
    	
    	
    	System.out.print("Username entered in textbox is: ");
    	System.out.println(un);
    	
    	System.out.print("Password entered in textbox is: ");
    	System.out.println(pw);
    	
    	if(usernametext.getText().isEmpty() || passwordtext.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "Incorrect password or user" );
        	alert.show( );
    	}
    	else {
	    	if(admincheckbox.isSelected()) {
	    		
	    		if(un.equals(adminUser)  && pw.equals(adminPassword)) {
	    			Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
		        	Stage window = (Stage) loginbutton.getScene().getWindow();
		        	window.setScene(new Scene(root, 850, 650));
	    		}
	    		else {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	            	alert.setContentText( "Incorrect password or username " );
	            	alert.show( );
	    		}}
	    	
	    	else if
	    		(employeecheckbox.isSelected()) {
		    		
	    		usernametext.getText();
	        	 passwordtext.getText();

	    		    // Check if credentials match an employee record
	    		    Employee c = new Employee(un, pw);
	    		    String result = c.getEmployeePasswordDb(un);

	    		    if (pw.equals(result)) {
	    		        // Redirect to AdminHome.fxml
	    		        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
	    		        try {
	    		            Parent root = loader.load();
	    		            AdminHomeController ahc = loader.getController();
	    		            ahc.setUserId(c.getEmployeeEmail());

	    		            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    		            Scene scene = new Scene(root);
	    		            stage.setScene(scene);
	    		            stage.show();
	    		        } catch (IOException e) {
	    		            e.printStackTrace();
	    		        }
	    		    } else {
	    		        // Display error message
	    		        Alert alert = new Alert(AlertType.INFORMATION);
	    		        alert.setContentText("Incorrect employee email or password");
	    		        alert.show();
	    		    }
	    		}
	    	else {
	    		Customer c= new Customer(pw, pw);
	    		
	    		String result = c.getCustomerPasswordDb(un);
	    		if(result == null) {
				// System.out.print("\nIncorrect password or user"+result+"\n");
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    	    	alert.setContentText( "Incorrect password or user" );
	    	    	alert.show( );
	    		}
	    		else {
	    			if(pw.equals(result)) {
	    		      	FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
	    		    	Parent root = loader.load();
	    		    	CustomerHomeController ch = loader.getController();
	    		    	ch.setUserId(c.getCustomerIdDb(un));
	    		    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		    	Scene scene = new Scene(root);
	    		    	stage.setScene(scene);
	    		    	stage.show();
					}
	    		}
	    	}
    	}

    	

  
    	
    }

    @FXML
    void signupaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
    	Stage window = (Stage) signupbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }

}