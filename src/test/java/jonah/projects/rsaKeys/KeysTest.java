package jonah.projects.rsaKeys;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import org.junit.Before;
import org.junit.Test;

public class KeysTest {
  private Keys testKey;
  private BigInteger pkBig = BigInteger.valueOf(134);
  private BigInteger skBig = BigInteger.valueOf(254);
  private BigInteger modulus = BigInteger.valueOf(233);

  @Before
  public void setUp() throws Exception {
    testKey = new Keys(pkBig, skBig, modulus);
  }

  @Test
  public void getPublicKey() {
    assertEquals(pkBig, testKey.getPublicKey().getKey());
  }

  @Test
  public void getPrivateKey() {
    assertEquals(skBig, testKey.getPrivateKey().getKey());
  }
}