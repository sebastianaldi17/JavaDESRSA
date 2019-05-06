# Demonstrating DES and RSA Encryption in Java 8
This project will demonstrate RSA and DES encryption in java using the javax.crypto library and the java.security library. It is a group project for Computer Security (which was finished at April 29th, 2019) and it is put here for legacy purposes/if we/someone are planning to update the application. The UI is made using JavaFX, so if someone plans on updating this to the newest java version, they may have to download JavaFX because [it is no longer is included in the JDK after Java 11.](https://jaxenter.com/jdk-11-javafx-separate-module-142186.html)

## Team Members
* [Sebastian Aldi](https://github.com/sebastianaldi17)
* [Dave Hong](https://github.com/acailuv)
* [Denny Raymond](https://github.com/raymondddenny)
* [Leon Chrisdion](https://github.com/leonchrisdion)
* [Winston Renatan](https://github.com/Ebnhzr)

## Prerequisites
* (Java 8, the JDK for compiling, the JRE for running)[https://www.oracle.com/technetwork/java/javase/downloads/index.html]
## Opening the appllication
Compile the java files using `javac *.java` and then launch the app by calling `java App`. Or, simply download the jar file in this repository and launch that instead by double clicking.

## Additional details
1. RSA plaintext is limited to 53 characters due to the key length (512 bits = 64 bytes, and 11 bytes is used for header stuff in PKCS)
2. DES key is your specified password, encrypted with DES using the key "encryptt" to prevent multiple keys being able to decrypt the same ciphertext.
3. DES password is limited to 8 characters due to the key length (56 bits + 8 parity bits = 64 bits or 8 Bytes)

## Issues
* Even with using the workaround in detail number 2, there might still be some cases in which different passwords can decrypt the same ciphertext.