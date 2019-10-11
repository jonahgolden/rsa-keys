package jonah.projects.rsaKeys;

import java.math.BigInteger;

/**
 * PrivateKey object.
 */
public class PrivateKey extends AbstractKey {

  /**
   * {@inheritDoc}.
   */
  public PrivateKey(BigInteger key, BigInteger modulus) {
    super(key, modulus);
  }
}
