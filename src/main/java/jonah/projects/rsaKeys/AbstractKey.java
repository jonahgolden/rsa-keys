package jonah.projects.rsaKeys;

import java.math.BigInteger;

/**
 * Class representing an Abstract Key.
 */
public class AbstractKey {

  private BigInteger key;
  private BigInteger modulus;

  /**
   * Constructor.
   *
   * @param key BigInteger representing the key.
   * @param modulus BigInteger representing the modulus of the key.
   */
  public AbstractKey(BigInteger key, BigInteger modulus) {
    this.key = key;
    this.modulus = modulus;
  }

  /**
   * Getter for the Key.
   *
   * @return BigInteger representing the key.
   */
  public BigInteger getKey() {
    return key;
  }

  /**
   * Getter for the modulus.
   *
   * @return BigInteger representing the modulus of the key.
   */
  public BigInteger getModulus() {
    return modulus;
  }
}
