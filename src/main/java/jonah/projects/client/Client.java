package jonah.projects.client;


import jonah.projects.messages.MessageGenerator;
import jonah.projects.messages.SignedMessage;
import jonah.projects.rsaKeys.Keys;
import jonah.projects.rsaKeys.PrivateKey;

/**
 * Class for a client with properties accessible to the client.
 */
public class Client extends AbstractClient {

  private PrivateKey privateKey;

  /**
   * Constructor for a Client.
   *
   * @param idNumber Integer representing client's ID number.
   * @param keyPair Keys object with PublicKey and PrivateKey.
   */
  public Client(Integer idNumber, Keys keyPair) {
    super(idNumber, keyPair.getPublicKey());
    this.privateKey = keyPair.getPrivateKey();
  }

  /**
   * Generate a message.
   *
   * @return SignedMessage object representing the message.
   */
  public SignedMessage generateMessage() {
    return MessageGenerator.generateMessage(privateKey, super.getIdNumber());
  }
}
