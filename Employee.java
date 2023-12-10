package businessPackage;



import java.sql.SQLException;

import dbpackage.DbPersistanceHandler;

public class Employee {
    private String employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeAddress;
    private String employeeEmail;
    private String employeePhoneNumber;
    private String employeePassword;


    public Employee(String employeeId, String employeeFirstName, String employeeLastName, String employeeAddress, String employeeEmail, String employeePhoneNumber, String employeePassword) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeAddress = employeeAddress;
        this.employeeEmail = employeeEmail;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeePassword = employeePassword;
    }
    public Employee(String employeeid) {
		employeeId =employeeid;
	}
    public Employee(String pw, String pw2) {
		this.employeeEmail=pw;
		this.employeePassword=pw2;
	}
	public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String  employeePassword) {
        this. employeePassword =  employeePassword;;
    }

    public void saveToDb() throws SQLException {
        DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
        dh.saveEmployee(this.employeeId, this.employeeFirstName, this.employeeLastName, this.employeeAddress, this.employeeEmail, this.employeePhoneNumber, this. employeePassword);
    }
    
    public void deleteEmployeeDb() {
		DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
    	dh.deleteEmployee(employeeId);
	}
	    public String getEmployeePasswordDb(String un) throws SQLException {
	        DbPersistanceHandler dh = DbPersistanceHandler.getInstance();
	        String result = dh.runEmployee("select * from Employee_A20548265 where employeeEmail='" + un + "'", "employeePassword");
	        return result;
	    }}