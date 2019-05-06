import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RSAController {
	private RSA r;
	@FXML private TextField plain;
	@FXML private TextArea cipher;
	@FXML private TextArea publickey;
	@FXML private TextArea privatekey;
	@FXML private Text e0;
	@FXML private Text e1;
	@FXML private Text e2;
	
	// Encrypt by calling RSA.java
	@FXML
	private void encrypt(ActionEvent event) throws Exception {
		String plaintext = plain.getText();
		if(plaintext.length() > 0) { // Check if the plaintext text area contains text to encrypt
			if(publickey.getText().equals("")) { // Check if there are keys to use for encrypting
				publickey.setStyle("-fx-border-color: red;");
				privatekey.setStyle("-fx-border-color: red;");
				e0.setText("Keys are empty");
			} else {
				// Encrypt the plaintext and set the ciphertext to the encrypted plaintext
				String ciphertext = r.getEncrypted(plaintext);
				cipher.setText(ciphertext);
				plain.setText("");
				clearError();
			}
		} else {
			// Tell the user that the plaintext is empty
			e1.setText("Textfield is empty");
			plain.setStyle("-fx-border-color: red;");
		}
	}
	// Decrypt by calling RSA.java
	@FXML
	private void decrypt(ActionEvent event) throws Exception {
		if(cipher.getText().length() > 0) { // Check if the ciphertext text area contains text to decrypt
			if(publickey.getText().equals("")) { // Check if there are keys to use for decrypting
				publickey.setStyle("-fx-border-color: red;");
				privatekey.setStyle("-fx-border-color: red;");
				e0.setText("Keys are empty");
			} else {
				// Error handling for invalid keys or invalid ciphertext
				try { 
					byte[] byteArray = Base64.getDecoder().decode(cipher.getText());
					byteArray = r.decrypt(r.getPublicKey(), byteArray);
					plain.setText(new String(byteArray));
					cipher.setText("");
					clearError();
				} catch (Exception e) {
					e2.setText("Ciphertext cannot be decrypted. Wrong password or wrong text?");
					cipher.setStyle("-fx-border-color: red;");
				}
			}
		} else {
			// Tell the user that the ciphertext is empty
			e2.setText("Textfield is empty");
			cipher.setStyle("-fx-border-color: red;");
		}
	}
	// Generate a new set of keys, and set the text field to show their numbers
	@FXML
	private void newkey(ActionEvent event) throws Exception {
		r = new RSA();
		clearError();
		
		// Get the public key and convert it to strings
		RSAPublicKey RSAPublic = (RSAPublicKey)(r.getPublicKey());
		BigInteger mod = RSAPublic.getModulus();
		BigInteger exp = RSAPublic.getPublicExponent();
		String modString = "Public Modulus: " + mod.toString() + "\n";
		String expString = "Public Exponent: " + exp.toString();
		
		// Get the private key and convert it to string
		RSAPrivateKey RSAPrivate = (RSAPrivateKey)(r.getPrivateKey());
		BigInteger private_exp = RSAPrivate.getPrivateExponent();
		String private_exp_string = "Private Exponent: " + private_exp.toString();
		
		// Set the publickey and privatekey text area to contain the new keys
		publickey.setText(modString);
		publickey.appendText(expString);
		privatekey.setText(private_exp_string);
	}
	// Button that goes back to the main menu
	@FXML
	private void back(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainLayout.fxml")); // Load the main menu layout
		plain.getScene().setRoot(root); // Set scene (contents of the window) to the new layout
	}
	// Clear all errors upon successfull encryption/decryption
	private void clearError() {
		e0.setText("");
		e1.setText("");
		e2.setText("");
		cipher.setStyle("-fx-border-color: transparent");
		plain.setStyle("-fx-border-color: transparent");
		publickey.setStyle("-fx-border-color: transparent");
		privatekey.setStyle("-fx-border-color: transparent");
	}
	// Create a function that clears all of the text fields so that we do not have to manually empty them
	@FXML
	private void clearAll() {
		clearError();
		plain.setText("");
		cipher.setText("");
		publickey.setText("");
		privatekey.setText("");
	}
	public void initialize() throws NoSuchAlgorithmException, IOException {
		// Add a listener so that the text field will not go past 53
		plain.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldlen, Number newlen) {
				if(newlen.intValue() > oldlen.intValue()) {
					if(plain.getText().length() >= 53) {
						plain.setText(plain.getText().substring(0, 53));
					}
				}
			}
		});
		clearError();
	}
}
