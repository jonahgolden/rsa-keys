package jonah.projects.rsaKeys;

import java.math.BigInteger;

/**
 * PublicKey object.
 */
public class PublicKey extends AbstractKey {

  /**
   * {@inheritDoc}.
   */
  public PublicKey(BigInteger key, BigInteger modulus) {
    super(key, modulus);
  }
}
