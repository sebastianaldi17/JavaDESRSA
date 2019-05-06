import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class DESController {
	@FXML private Button backbutton;
	@FXML private TextArea plaintext;
	@FXML private TextArea password;
	@FXML private TextArea ciphertext;
	@FXML private Text e1;
	@FXML private Text e2;
	@FXML private Text e3;
	private DES d;
	@FXML
	private void back(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainLayout.fxml")); // Load the main menu layout
		backbutton.getScene().setRoot(root); // Set scene (contents of the window) to the new layout
	}
	
	// Encrypt by calling DES.java
	@FXML
	private void Encrypt(ActionEvent event) throws Exception {
		if(password.getText().length() == 8) { // Check if the password is exactly 64 bits (8 characters)
			if(plaintext.getText().length() > 0) { // Check if there is text to encrypt
				// Encrypt the text and set the ciphertext to the encrypted plaintext
				d = new DES(password.getText());
				ciphertext.setText(d.encrypt(plaintext.getText()));
				plaintext.setText("");
				clearError();
			} else {
				// Tell the user that the plaintext is empty
				e1.setText("Text field is empty");
				plaintext.setStyle("-fx-border-color: red;");
			}
		} else {
			// Tell the user that the password length is wrong
			wrongLength();
		}
	}
	
	// Decrypt by calling DES.java
	@FXML
	private void Decrypt(ActionEvent event) throws Exception {
		if(password.getText().length() == 8) { // Check if the password is exactly 8 characters
			if(ciphertext.getText().length() > 0) { // Check if there is text to decrypt
				d = new DES(password.getText());
				// Error handling for invalid password or ciphertext
				try {
					plaintext.setText(d.decrypt(ciphertext.getText()));
					ciphertext.setText("");
					clearError();
				} catch(Exception e) {
					e3.setText("Ciphertext cannot be decrypted. Wrong password or wrong text?");
					ciphertext.setStyle("-fx-border-color: red;");
				}
			} else {
				// Tell the user that the ciphertext is empty
				e3.setText("Text field is empty");
				ciphertext.setStyle("-fx-border-color: red;");
			}
		} else {
			// Tell the user that the password length is wrong
			wrongLength();
		}
	}
	// Create a function that clears all of the text fields
	@FXML
	private void clear() {
		clearError();
		plaintext.setText("");
		password.setText("");
		ciphertext.setText("");
	}
	// Clear all errors upon successful encryption or decryption
	private void clearError() {
		e1.setText("");
		e2.setText("");
		e3.setText("");
		plaintext.setStyle("-fx-border-color: transparent;");
		password.setStyle("-fx-border-color: transparent;");
		ciphertext.setStyle("-fx-border-color: transparent");
	}
	// Tell user that the password is not 8 characters long
	private void wrongLength() {
		e2.setText("Password must be exactly 8 characters");
		password.setStyle("-fx-border-color: red;");
	}
	public void initialize() {
		// Add a listener so that the password length will not go past 8
		password.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldlen, Number newlen) {
				if(newlen.intValue() > oldlen.intValue()) {
					if(password.getText().length() > 8) {
						password.setText(password.getText().substring(0, 8));
					}
				}
			}
		});
	}
}
