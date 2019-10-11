package jonah.projects.rsaKeys;

import java.math.BigInteger;

/**
 * Class representing a pair of public and private keys.
 */
public class Keys {

  private PublicKey publicKey;
  private PrivateKey privateKey;

  /**
   * Constructor for a Keys object.
   *
   * @param publicKey BigInteger representing the public key.
   * @param privateKey BigInteger representing the private key.
   * @param modulus BigInteger representing the modulus of the keys.
   */
  public Keys(BigInteger publicKey, BigInteger privateKey, BigInteger modulus) {
    this.publicKey = new PublicKey(publicKey, modulus);
    this.privateKey = new PrivateKey(privateKey, modulus);
  }

  /**
   * Getter for the PublicKey.
   *
   * @return PublicKey object.
   */
  public PublicKey getPublicKey() {
    return publicKey;
  }

  /**
   * Getter for the PrivateKey.
   *
   * @return PrivateKey object.
   */
  public PrivateKey getPrivateKey() {
    return privateKey;
  }
}
