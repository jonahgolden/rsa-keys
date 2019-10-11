package jonah.projects.bs;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;


public class KeyPairGeneration {

  public static KeyPair generateKeyPair() throws Exception {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(512, new SecureRandom());
    KeyPair pair = generator.generateKeyPair();

    return pair;
  }

/** sign a message with private key and verify the signature with public key. */
  public static String sign(String plainText, PrivateKey privateKey) throws Exception {
    Signature privateSignature = Signature.getInstance("SHA256withRSA");
    privateSignature.initSign(privateKey);
    privateSignature.update(plainText.getBytes(UTF_8));

    byte[] signature = privateSignature.sign();

    return Base64.getEncoder().encodeToString(signature);
  }

  public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
    Signature publicSignature = Signature.getInstance("SHA256withRSA");
    publicSignature.initVerify(publicKey);
    publicSignature.update(plainText.getBytes(UTF_8));

    byte[] signatureBytes = Base64.getDecoder().decode(signature);

    return publicSignature.verify(signatureBytes);
  }

/** encrypt messages with public key which then can be decrypted with the private key */
//  public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
//    Cipher encryptCipher = Cipher.getInstance("RSA");
//    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
//
//    byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));
//
//    return Base64.getEncoder().encodeToString(cipherText);
//  }
//
//  public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
//    byte[] bytes = Base64.getDecoder().decode(cipherText);
//
//    Cipher decryptCipher = Cipher.getInstance("RSA");
//    decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
//
//    return new String(decryptCipher.doFinal(bytes), UTF_8);
//  }

}
