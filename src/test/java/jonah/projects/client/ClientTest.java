package jonah.projects.client;

import static junit.framework.TestCase.assertEquals;

import jonah.projects.constantUtils.UniqueId;
import jonah.projects.messages.SignedMessage;
import jonah.projects.rsaKeys.Keys;
import jonah.projects.rsaKeys.RSAUtils;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
  Client client1;
  Integer id1 = UniqueId.getRandomUniqueID();
  Keys keys1;
  SignedMessage message1;

  @Before
  public void setUp() throws Exception {
    keys1 = RSAUtils.generateKeys();
    client1 = new Client(id1, keys1);
  }

  @Test
  public void getIdNumber() {
    assertEquals(id1, client1.getIdNumber());
  }

  @Test
  public void getPublicKey() {
    assertEquals(keys1.getPublicKey(), client1.getPublicKey());
  }

  @Test
  public void generateMessage() {
    message1 = client1.generateMessage();
    assertEquals(client1.getIdNumber(), message1.getClientIdNumber());
  }
}