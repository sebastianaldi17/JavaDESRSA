import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSA {
	private PrivateKey pr;
	private PublicKey pb;

	public RSA() throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(512);
		KeyPair pair = keyGen.generateKeyPair();
		this.pr = pair.getPrivate();
		this.pb = pair.getPublic();
	}
	//ENCRYPTION
	public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());  
    }
    //DECRYPTION
    public byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encrypted);
    }
	//Obtain the value of Private Key
    public PrivateKey getPrivateKey() {
    	return pr;
    }
	//Obtain the value of Public Key
    public PublicKey getPublicKey() {
    	return pb;
    }
	//Generate new key for the program
    public void newKey() throws NoSuchAlgorithmException {
    	KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(512);
		KeyPair pair = keyGen.generateKeyPair();
		this.pr = pair.getPrivate();
		this.pb = pair.getPublic();
    }
	//Change byte[] to Base64 String
    public String getEncrypted(String text) throws Exception {
    	byte[] signed = encrypt(getPrivateKey(), text);
    	return Base64.getEncoder().encodeToString(signed);
    }
}
