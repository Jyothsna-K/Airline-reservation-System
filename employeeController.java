package application;

import java.io.IOException;
import java.sql.ResultSet;
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

public class employeeController {

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
    private TextField employeefirstnametext;

    @FXML
    private TextField employeelastnametext;

    @FXML
    private TextField employeeaddresstext;

    @FXML
    private TextField employeemailtext;

    @FXML
    private TextField employeephonenumbertext;

    @FXML
    private TextField employeedpasswordtext;

    @FXML
    private Button addbutton;
    
	public void initialize() throws SQLException {
		employeeschedules.setPlaceholder(new Label("Press View for Results"));	
	}
	
    @FXML
    void addaction(ActionEvent event) throws SQLException {
    	
    	if(employeefirstnametext.getText().isEmpty() || employeelastnametext.getText().isEmpty() || employeeaddresstext.getText().isEmpty() || employeemailtext.getText().isEmpty() || employeephonenumbertext.getText().isEmpty() || employeedpasswordtext.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setContentText( "Fill all fields" );
        	alert.show( );
    	}
    	else {
	      	Employee e= new Employee("0",employeefirstnametext.getText(), employeelastnametext.getText(),employeeaddresstext.getText(),employeemailtext.getText(),employeephonenumbertext.getText(),employeedpasswordtext.getText());
	        e.saveToDb();
	//    	DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
	//    	dh.saveFlight(Integer.parseInt(seattext.getText()), arrivalairporttext.getText(),deptairporttext.getText() , depttimetext.getText(),arrivaltimetext.getText(), statustext.getText(), Integer.parseInt(pricetext.getText()));
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setContentText( "Added Successfully" );
	    	alert.show( );
    	}
    }

    @FXML
    void afsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("employeeHome.fxml"));
    	Stage window = (Stage) afsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }


    @FXML
    void backaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
    	Stage window = (Stage) afsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }
    
   
    
    @FXML
    void dfsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("deleteEmployee.fxml"));
    	Stage window = (Stage) afsbutton.getScene().getWindow();
    	window.setScene(new Scene(root, 850, 650));

    }
    
 
    @FXML
    void   efsaction(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
    	Stage window = (Stage) afsbutton.getScene().getWindow();
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