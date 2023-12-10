package dbpackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import businessPackage.Booking;

public class DbPersistanceHandler {
    private String user;
    private String password;
    private String connectionString;
    private static DbPersistanceHandler dh;

    private DbPersistanceHandler() {
        user = "root";
        password = "@Namaye98";
        connectionString = "jdbc:mysql://localhost:3306/flight";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver Loaded Successfully!");

            // step2 create the connection object
            Connection con = DriverManager.getConnection(connectionString, user, password);

            System.out.println("MySQL Connection Established!");

     
				
				con.close();
			
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		}  
	}
	public static DbPersistanceHandler getInstance() {
		if(dh == null) {
			dh= new DbPersistanceHandler();
		}
		
		return dh;	
	}
	
	private String idAssigner(String table) throws SQLException {	
		
		Connection con=DriverManager.getConnection(  
				connectionString,user,password);
		int counter=0;
		
		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery("select * from "+table+"");  
		while(rs.next()) {
			counter = Integer.parseInt(rs.getString(1));
			counter++;
		}
		
		
		return Integer.toString(counter);
	}
	
	
	public ResultSet runCustomQueryWithParams(String query, Object... params) throws SQLException {
        try (Connection con = DriverManager.getConnection(connectionString, user, password);
             PreparedStatement statement = con.prepareStatement(query)) {
            // Set parameters for the prepared statement
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            // Execute the query and return the result set
            return statement.executeQuery();
        }
    } 
	
	public void saveCustomer(String customerId, String customerName, String customerPassword) {
	    try (Connection con = DriverManager.getConnection(connectionString, user, password)) {
	        String sql = "INSERT INTO Customer_A20548265 (customerId, customerName, customerPassword) VALUES (?, ?, ?)";

	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setString(1, customerId);
	            preparedStatement.setString(2, customerName);
	            preparedStatement.setString(3, customerPassword);

	            // Use executeUpdate for INSERT, UPDATE, DELETE statements
	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Customer saved successfully");
	            } else {
	                System.out.println("Failed to save customer");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void saveFlight(int totalSeats, String des, String start, String uptime, String downtime, String status, int price) {
	    try {
	        Connection con = DriverManager.getConnection(connectionString, user, password);

	        String sql = "INSERT INTO Flight_A20548265 (flightId, totalSeat, destination, startingPlace, uptime, downtime, status, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	        	preparedStatement.setString(1, idAssigner("Flight"));
	            preparedStatement.setInt(2, totalSeats);
	      
	            preparedStatement.setString(3, des);
	            preparedStatement.setString(4, start);
	            preparedStatement.setString(5, uptime);
	            preparedStatement.setString(6, downtime);
                preparedStatement.setString(7, status);
	            preparedStatement.setInt(8, price);

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Flight saved successfully");
	            } else {
	                System.out.println("Failed to save flight");
	            }
	        }

	        con.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void saveAdmin(String id, String pass) {
	    try {
	        Connection con = DriverManager.getConnection(connectionString, user, password);

	        String sql = "INSERT INTO Admin_A20548265 (adminId, password) VALUES (?, ?)";

	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setString(1, id);
	            preparedStatement.setString(2, pass);

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Admin saved successfully");
	            } else {
	                System.out.println("Failed to save admin");
	            }
	        }

	        con.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void savePayment(int amount, String cusid) {
	    try {
	        Connection con = DriverManager.getConnection(connectionString, user, password);

	        String sql = "INSERT INTO Payment_A20548265 (paymentId, amount, customerId) VALUES (?, ?, ?)";

	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setString(1, idAssigner("Payment"));
	            preparedStatement.setInt(2, amount);
	            preparedStatement.setString(3, cusid);

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Payment saved successfully");
	            } else {
	                System.out.println("Failed to save payment");
	            }
	        }

	        con.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void saveBooking(String custid, String fid, String seatNo) {
	    try {
	        Connection con = DriverManager.getConnection(connectionString, user, password);

	        String sql = "INSERT INTO Booking_A20548265 (bookingId, customerId, flightId, noOfSeat) VALUES (?, ?, ?, ?)";

	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setString(1, idAssigner("Booking"));
	            preparedStatement.setString(2, custid);
	            preparedStatement.setString(3, fid);
	            preparedStatement.setString(4, seatNo);

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Booking saved successfully");
	            } else {
	                System.out.println("Failed to save booking");
	            }
	        }

	        con.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	 
	public ResultSet getCustomer() {
	    try (Connection con = DriverManager.getConnection(connectionString, user, password);
	         Statement st = con.createStatement()) {

	        ResultSet rs = st.executeQuery("SELECT * FROM Customer_A20548265");
	        return rs;

	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle or log the exception appropriately
	    }

	    return null;
	}

	 public ResultSet getBooking() {
		 try {
				Connection con=DriverManager.getConnection(  
						connectionString,user,password);
	
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery("select * from Booking_A20548265 ");  
				return rs;

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null; 
			
	 }
	 public ResultSet getBookingByCustomer(String id) {
		 try {
				Connection con=DriverManager.getConnection(  
						connectionString,user,password);
	
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery("select * from Booking_A20548265 where customerId = '"+id+"'");  
				return rs;

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null; 
			
	 }
	 
	 public void deleteBooking(String id) {
		    try {
		        Connection con = DriverManager.getConnection(
		                connectionString, user, password);

		        Statement st = con.createStatement();
		        int rowsAffected = st.executeUpdate("DELETE FROM booking_A20548265 WHERE bookingId = '" + id + "'");
		        System.out.println("Rows affected: " + rowsAffected);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	 public ResultSet getPayment() {
		 try {
				Connection con=DriverManager.getConnection(  
						connectionString,user,password);
				
				
		
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery("select * from Payment_A20548265");  
				return rs;

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null; 
			
	 }
	 public ResultSet getFlight() {
		 try {
				Connection con=DriverManager.getConnection(  
						connectionString,user,password);
				
				
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery("select * from Flight_A20548265");  
				return rs;

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 
		 
			return null; 
			
	 }
	 
	 public ResultSet getFlightById(String fid) {
		 try {
				Connection con=DriverManager.getConnection(  
						connectionString,user,password);
				
				
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery("select * from Flight_A20548265 where flightId = '"+fid+"'");  
				return rs;

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null; 
			
	 }
	 public void deleteFlight(String flightId) {
		    try (Connection con = DriverManager.getConnection(connectionString, user, password)) {
		        String sql = "DELETE FROM Flight_A20548265 WHERE flightId = ?";
		        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		            preparedStatement.setString(1, flightId);

		            int rowsAffected = preparedStatement.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("Flight deleted successfully");
		            } else {
		                System.out.println("No flight found with ID: " + flightId);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	 public void deleteEmployee(String employeeId) {
		    try (Connection con = DriverManager.getConnection(connectionString, user, password)) {
		        String sql = "DELETE FROM Employee_A20548265 WHERE employeeId = ?";
		        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		            preparedStatement.setString(1, employeeId);

		            int rowsAffected = preparedStatement.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("Employee deleted successfully");
		            } else {
		                System.out.println("No Employee found with ID: " + employeeId);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	 public String getCustomerPassword(String custname) {
		 try {
				Connection con=DriverManager.getConnection(connectionString,user,password);
				
				
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery("select * from Customer_A20548265 where customerName = '"+custname+"'");  
				rs.next();
				//System.out.print(rs.getString("customerId"));
				return rs.getString("customerPassword");

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null; 
	 }
	 
	 public ResultSet runCustomQuery1(String query) {
		 try {
				Connection con=DriverManager.getConnection(connectionString,user,password);
				
				
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery(query);  
				//System.out.print(rs.getString("customerId"));
				return rs;

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null; 
	 }
	 public String runCustomQuery2(String query, String attr) {
		 try {
				Connection con=DriverManager.getConnection(connectionString,user,password);
				
				
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery(query);  
				if(rs.next()) {
					System.out.print("\n"+rs.getString(attr)+"\n");
					
					//System.out.print(rs.getString("customerId"));
					return rs.getString(attr);
				}
				else {
					return null;
				}
				
			

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null; 
	 }
	 /*
	 public void saveEmployee(Employee employee) throws SQLException {
	       Connection con = DriverManager.getConnection(connectionString, user, password);
		    String sql = "INSERT INTO employee (employeeId, employeeFirstName, employeeLastName, employeeEmail) VALUES (?, ?, ?, ?)";
		    PreparedStatement preparedStatement = con.prepareStatement(sql);
		    preparedStatement.setString(1, employee.getEmployeeId());
		    preparedStatement.setString(2, employee.getEmployeeFirstName());
		    preparedStatement.setString(3, employee.getEmployeeLastName());
		    preparedStatement.setString(4, employee.getEmployeeEmail());
		    preparedStatement.executeUpdate();
		    preparedStatement.close();
		    con.close();
		}

		public List<Employee> getEmployeeList() throws SQLException {
		       Connection con = DriverManager.getConnection(connectionString, user, password);
		    String sql = "SELECT * FROM employee";
		    PreparedStatement preparedStatement = con.prepareStatement(sql);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    List<Employee> employeeList = new ArrayList<>();
		    while (resultSet.next()) {
		        String employeeId = resultSet.getString("employeeId");
		        String employeeFirstName = resultSet.getString("employeeFirstName");
		        String employeeLastName = resultSet.getString("employeeLastName");
		        String employeeEmail = resultSet.getString("employeeEmail");
		        String employeePassword=resultSet.getString("employeePassword");
		        Employee employee = new Employee(employeeId, employeeFirstName, employeeLastName, employeeEmail, employeePassword);
		        employeeList.add(employee);
		    }
		    preparedStatement.close();
		    resultSet.close();
		    con.close();
		    return employeeList;
		}
		*/
	 public void saveEmployee(String employeeId, String employeeFirstName, String employeeLastName, String employeeAddress, String employeeEmail, String employeePhoneNumber, String employeePassword) {
		    try (Connection con = DriverManager.getConnection(connectionString, user, password)) {

		        String sql = "INSERT INTO Employee_A20548265 (employeeId, employeeFirstName, employeeLastName, employeeAddress, employeeEmail, employeePhoneNumber, employeePassword) VALUES (?, ?, ?, ?, ?, ?, ?)";

		        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		            preparedStatement.setString(1, employeeId);
		            preparedStatement.setString(2, employeeFirstName);
		            preparedStatement.setString(3, employeeLastName);
		            preparedStatement.setString(4, employeeAddress);
		            preparedStatement.setString(5, employeeEmail);
		            preparedStatement.setString(6, employeePhoneNumber);
		            preparedStatement.setString(7, employeePassword);

		            int rowsAffected = preparedStatement.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("Employee saved successfully");
		            } else {
		                System.out.println("Failed to save employee");

		                // Check if the error is due to a duplicate email address
		                ResultSet rs = con.prepareStatement("SELECT * FROM Employee_A20548265 WHERE employeeEmail = ?").executeQuery(employeeEmail);
		                if (rs.next()) {
		                    System.out.println("Email address already exists");
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	public ResultSet getEmployee() {
		// TODO Auto-generated method stub
		 try {
				Connection con=DriverManager.getConnection(  
						connectionString,user,password);
				
				
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery("select * from Employee_A20548265");  
				return rs;

				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 
		 
			return null; 
			
	 }
	public List<Booking> getAllBookings() throws SQLException {
	    List<Booking> bookings = new ArrayList<>();

	    String sql = "SELECT bookingId, customerId, flightId, seatNo FROM booking_A20548265";
	    try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            String bookingId = resultSet.getString("bookingId");
	            String customerId = resultSet.getString("customerId");
	            String flightId = resultSet.getString("flightId");
	            String seatNo = resultSet.getString("seatNo");

	            Booking booking = new Booking(bookingId, customerId, flightId, seatNo);
	            bookings.add(booking);
	        }
	    }

	    return bookings;
	}
	
	 public String runEmployee(String query, String column) throws SQLException {
	        String result = "";

	        try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                result = resultSet.getString(column);
	            }
	        }

	        return result;
	    }

	}