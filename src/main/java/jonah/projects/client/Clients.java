package jonah.projects.client;


import java.util.ArrayList;
import java.util.Random;
import jonah.projects.messages.SignedMessage;
import jonah.projects.rsaKeys.Keys;

/**
 * Class for clients and random client generation
 */
public class Clients {

  private final ArrayList<Client> clients = new ArrayList<>();
  private final Random rand = new Random();

  /**
   * Add a client.
   *
   * @param idNumber Integer representing new client's idNumber.
   * @param keyPair Keys representing new client's RSA keys.
   */
  public void addClient(Integer idNumber, Keys keyPair) {
    clients.add(new Client(idNumber, keyPair));
  }


  /**
   * Generate a message from a random client.
   *
   * @return SignedMessage object.
   */
  public SignedMessage generateRandomMessage() {
    return clients.get(rand.nextInt(clients.size())).generateMessage();
  }

  /**
   * Get the number of clients, method for testing.
   *
   * @return Number of clients.
   */
  public Integer getNumberClients() {
    return clients.size();
  }
}
