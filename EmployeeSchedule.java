package businessPackage;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dbpackage.DbPersistanceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeSchedule {
    private Vector<Employee> employeeArr = new Vector<Employee>();

    public void addEmployee(Employee e) {
        employeeArr.add(e);
    }
    
    public ObservableList<Employee> getEmployeeTableList() throws SQLException {
        ObservableList<Employee> List = FXCollections.observableArrayList();
        DbPersistanceHandler dh = DbPersistanceHandler.getInstance();

        // Check if ResultSet is null
        ResultSet rs = dh.getEmployee();
        if (rs != null) {
            Employee row;

            while (rs.next()) {
                row = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                List.add(row);
            }
        } else {
            System.err.println("ResultSet is null");
        }

        return List;
    }



    public ObservableList<Employee> getSpecificEmployeeTableList(String dep, String des) throws SQLException {
        ObservableList<Employee> List = FXCollections.observableArrayList();
        DbPersistanceHandler dh = DbPersistanceHandler.getInstance();

        ResultSet rs = dh.runCustomQuery1("select * from Employee_A20548265");

        Employee row;

        while (rs.next()) {
            row = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            List.add(row);
        }

        return List;
    }
}
