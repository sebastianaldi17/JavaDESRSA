import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class SceneController {
	@FXML private Button RSAButton;
	@FXML
	private void RSAScene(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("RSALayout.fxml")); // Load the RSA layout
		RSAButton.getScene().setRoot(root); // Set scene (contents of the window) to the new layout
	}
	@FXML
	private void DESScene(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("DESLayout.fxml")); // Load the DES layout
		RSAButton.getScene().setRoot(root); // Set scene(contents of the window) to the new layout
	}
	
}
