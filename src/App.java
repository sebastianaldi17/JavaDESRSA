import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	// Set window properties
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setWidth(600);
		stage.setHeight(550);
		stage.setTitle("Encryptor v1.4");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
