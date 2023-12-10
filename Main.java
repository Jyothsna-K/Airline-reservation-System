package application;
	
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
	
public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login Form");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
      
		
	}
	
	public static void main(String[] args) throws SQLException {
		launch(args);
	
		
		
	}
}
