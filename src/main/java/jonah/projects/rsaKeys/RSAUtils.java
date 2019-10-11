package jonah.projects.rsaKeys;


import static java.math.BigInteger.ONE;
import static java.math.BigInteger.probablePrime;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * An abstract class with static methods for RSA key generation, signature generation, and message
 * verification.
 */
public interface RSAUtils {

  Integer BIT_LENGTH = 10;
  SecureRandom rand = new SecureRandom();

  /**
   * Generate a pair of RSA Keys.
   *
   * @return Keys object with PublicKey and PrivateKey.
   */
  static Keys generateKeys() {
    BigInteger p = probablePrime(BIT_LENGTH, rand);
    BigInteger q = probablePrime(BIT_LENGTH, rand);
    BigInteger phi = calculatePhi(p, q);
    BigInteger n = p.multiply(q);
    BigInteger privateKey = generatePrivateKey(phi, n);
    BigInteger publicKey = privateKey.modInverse(phi);
    return new Keys(publicKey, privateKey, n);
  }

  /**
   * Generate a digital signature on a message request.
   *
   * @param request an Integer representing the message request.
   * @param privateKey the PrivateKey object with which to generate the signature.
   * @return a BigInteger representing the signature.
   */
  static BigInteger generateSignature(Integer request, PrivateKey privateKey) {
    return BigInteger.valueOf(request).modPow(privateKey.getKey(), privateKey.getModulus());
  }

  /**
   * Verify a signed message with a PublicKey.
   *
   * @param signature BigInteger representing the digital signature of the message.
   * @param request Integer representing the request of the message.
   * @param publicKey the PublicKey to verify the message with.
   * @return true if message is verified, false otherwise.
   */
  static boolean verifyMessage(BigInteger signature, Integer request, PublicKey publicKey) {
    BigInteger expected = signature.modPow(publicKey.getKey(), publicKey.getModulus());
    return request.equals(expected.intValue());
  }

  /**
   * Helper method calculates a phi-value of two BigIntegers p and q using the equation phi = (p −
   * 1)(q − 1).
   *
   * @param p BigInteger.
   * @param q BigInteger.
   * @return BigInteger representing phi.
   */
  static BigInteger calculatePhi(BigInteger p, BigInteger q) {
    return (p.subtract(ONE)).multiply(q.subtract(ONE));
  }

  /**
   * Helper method uses a prime (n) and phi (φ(n)) to find and return a prime (a) that satisfies gcd
   * (a, n) = 1 and gcd (a, phi) = 1.
   *
   * @param n a BigInteger Prime.
   * @param phi a BigInteger Prime representing φ(n).
   * @return a BigInteger Prime that satisfies gcd (a, n) = 1 and gcd (a, phi) = 1.
   */
  static BigInteger generatePrivateKey(BigInteger n, BigInteger phi) {
    int length = phi.bitLength() - 1;
    BigInteger aPrime;

    while (true) {
      aPrime = probablePrime(length, rand);
      if ((n.gcd(aPrime)).equals(ONE) && (phi.gcd(aPrime)).equals(ONE)) {
        return aPrime;
      }
    }
  }
}
