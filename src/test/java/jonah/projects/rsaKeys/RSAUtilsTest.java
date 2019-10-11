package jonah.projects.rsaKeys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import org.junit.Before;
import org.junit.Test;

public class RSAUtilsTest {
  private Keys keyPair1, keyPair2;
  private Integer request1, request2;

  @Before
  public void setUp() throws Exception {
    keyPair1 = RSAUtils.generateKeys();
    keyPair2 = RSAUtils.generateKeys();
    request1 = 15005;
    request2 = 23004;
  }

  @Test
  public void generateKeys() {
    assertNotEquals(keyPair1, keyPair2);
  }

  @Test
  public void generateSignatureSame() {
    BigInteger signature = RSAUtils.generateSignature(request1, keyPair1.getPrivateKey());
    BigInteger same = RSAUtils.generateSignature(request1, keyPair1.getPrivateKey());
    assertEquals(signature, same);
  }

  @Test
  public void generateSignatureDifferentRequest() {
    BigInteger signature1 = RSAUtils.generateSignature(request1, keyPair1.getPrivateKey());
    BigInteger signature2 = RSAUtils.generateSignature(request2, keyPair1.getPrivateKey());
    assertNotEquals(signature1, signature2);
  }

  @Test
  public void generateSignatureDifferentKey() {
    BigInteger signature1 = RSAUtils.generateSignature(request1, keyPair1.getPrivateKey());
    BigInteger signature2 = RSAUtils.generateSignature(request1, keyPair2.getPrivateKey());
    assertNotEquals(signature1, signature2);
  }

  @Test
  public void verifyMessageTrue() {
    BigInteger signature = RSAUtils.generateSignature(request1, keyPair1.getPrivateKey());
    assertTrue(RSAUtils.verifyMessage(signature, request1, keyPair1.getPublicKey()));
  }

  @Test
  public void verifyMessageFalseDifferentRequest() {
    BigInteger signature = RSAUtils.generateSignature(request1, keyPair1.getPrivateKey());
    assertFalse(RSAUtils.verifyMessage(signature, request2, keyPair1.getPublicKey()));
  }

  @Test
  public void verifyMessageFalseDifferentKey() {
    BigInteger signature = RSAUtils.generateSignature(request1, keyPair1.getPrivateKey());
    assertFalse(RSAUtils.verifyMessage(signature, request1, keyPair2.getPublicKey()));
  }
}