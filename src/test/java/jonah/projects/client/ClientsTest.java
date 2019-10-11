package jonah.projects.client;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import jonah.projects.constantUtils.UniqueId;
import jonah.projects.messages.SignedMessage;
import jonah.projects.rsaKeys.Keys;
import jonah.projects.rsaKeys.RSAUtils;
import org.junit.Before;
import org.junit.Test;

public class ClientsTest {

  Clients clients;
  Integer id1 = UniqueId.getRandomUniqueID();
  Integer id2 = UniqueId.getRandomUniqueID();
  Keys keys1 = RSAUtils.generateKeys();
  Keys keys2 = RSAUtils.generateKeys();
  SignedMessage message;

  @Before
  public void setUp() {
    clients = new Clients();
    clients.addClient(id1, keys1);
  }

  @Test
  public void generateRandomMessageOneClientInClients() {
    message = clients.generateRandomMessage();
    assertEquals(id1, message.getClientIdNumber());
  }

  @Test
  public void generateRandomMessageTwoClientInClients() {
    clients.addClient(id2, keys2);
    message = clients.generateRandomMessage();
    assertTrue(message.getClientIdNumber().equals(id1) || message.getClientIdNumber().equals(id2));
  }
}