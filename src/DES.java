import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
public class DES {
	Cipher ecipher;
	Cipher dcipher;
	Cipher pcipher;
	SecretKey key;
	
	DES(String password) throws Exception {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			pcipher = Cipher.getInstance("DES");
			pcipher.init(Cipher.ENCRYPT_MODE, factory.generateSecret(new DESKeySpec("encryptt".getBytes())));
			byte[] keyBytes = pcipher.doFinal(password.getBytes());
			key = factory.generateSecret(new DESKeySpec(keyBytes));
			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
				
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
	  }

	  public String encrypt(String str) throws Exception {
				// Encode the string into bytes using utf-8
		    byte[] utf8 = str.getBytes("UTF8");
				byte[] enc = ecipher.doFinal(utf8);
	
		    // Encode bytes to base64 to get a string
		    return new String(Base64.getEncoder().encode(enc));
	  }

	  public String decrypt(String str) throws Exception {
	    // Decode base64 to get bytes
	    byte[] dec = Base64.getDecoder().decode(str);
	    byte[] utf8 = dcipher.doFinal(dec);

	    // Decode using utf-8
	    return new String(utf8, "UTF8");
	  }
}
