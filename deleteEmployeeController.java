package application;

import java.io.IOException;
import java.sql.SQLException;

import businessPackage.Employee;
import businessPackage.EmployeeSchedule;
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

public class deleteEmployeeController {

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
    private TableView<Employee> employeeschedules;

    @FXML
    private TableColumn<Employee, String> employeeid;

    @FXML
    private TableColumn<Employee, String> employeefirstname;

    @FXML
    private TableColumn<Employee, String> employeelastname;

    @FXML
    private TableColumn<Employee, String> employeeaddress;

    @FXML
    private TableColumn<Employee, String> employeemail;

    @FXML
    private TableColumn<Employee, String> employeephonenumber;

    @FXML
    private TableColumn<Employee, String> employeepassword;


    @FXML
    private Button backbutton;

    @FXML
    private TextField employeeidtext;

    @FXML
    private Button deletetext;
    
    @FXML
    private Button viewscheduele;
    
	public void initialize() throws SQLException {
		employeeschedules.setPlaceholder(new Label("Press View for Results"));	
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
	    	Employee f = new Employee(employeeidtext.getText());
	    	f.deleteEmployeeDb();
	//    	DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
	//    	dh.deleteFlight(flightidtext.getText());
	    	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "deleted Successfully" );
        	alert.show( );
    	}
    }
    	

    @FXML
    void dfsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("deleteEmployee.fxml"));
    	Stage window = (Stage) dfsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }
    
    @FXML
    void viewschedueletableaction(ActionEvent event) throws SQLException {
        ObservableList<Employee> List = FXCollections.observableArrayList();
        EmployeeSchedule es = new EmployeeSchedule();
        List = es.getEmployeeTableList();
        employeeid.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeefirstname.setCellValueFactory(new PropertyValueFactory<>("employeeFirstName"));
        employeelastname.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
        employeeaddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        employeemail.setCellValueFactory(new PropertyValueFactory<>("employeeEmail"));
        employeephonenumber.setCellValueFactory(new PropertyValueFactory<>("employeePhoneNumber"));
        employeeschedules.setItems(List);
    }
}