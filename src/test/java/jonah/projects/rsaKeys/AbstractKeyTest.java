package jonah.projects.rsaKeys;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import jonah.projects.rsaKeys.AbstractKey;
import jonah.projects.rsaKeys.Keys;
import org.junit.Before;
import org.junit.Test;

public class AbstractKeyTest {
  private Keys keys;
  private AbstractKey ak;
  private BigInteger pkKey = BigInteger.valueOf(123456);
  private BigInteger modulus = BigInteger.valueOf(12345);

  @Before
  public void setUp() throws Exception {
    keys = RSAUtils.generateKeys();
    ak = new AbstractKey(pkKey, modulus);
  }

  @Test
  public void getKey() {
    assertEquals(pkKey, ak.getKey());
  }

  @Test
  public void getModulus() {
    assertEquals(modulus, ak.getModulus());
  }
}