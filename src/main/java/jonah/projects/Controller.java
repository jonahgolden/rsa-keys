package jonah.projects;


import java.io.IOException;
import jonah.projects.bank.Bank;
import jonah.projects.client.Clients;
import jonah.projects.constantUtils.DepositWithdrawal;
import jonah.projects.constantUtils.UniqueId;
import jonah.projects.messages.MessageGenerator;
import jonah.projects.messages.SignedMessage;
import jonah.projects.rsaKeys.Keys;
import jonah.projects.rsaKeys.RSAUtils;
import jonah.projects.writer.MessageWriter;

/**
 * Controller class for clients, bank, and transmitting messages.
 */
class Controller {

  private final Clients clients = new Clients();
  private final Bank bank = new Bank();

  private final Integer uniqueBankClients;
  private final Integer uniqueVerifications;


  /**
   * Constructor for Secure Bank Verification Simulator controller.
   *
   * @param uniqueBankClients Number of unique bank clients to simulate.
   * @param uniqueVerifications Number of unique verifications to simulate.
   * @param percentInvalidMessages Percent of messages to simulate invalid.
   */
  Controller(Integer uniqueBankClients, Integer uniqueVerifications,
      Integer percentInvalidMessages) {
    this.uniqueBankClients = uniqueBankClients;
    this.uniqueVerifications = uniqueVerifications;
    MessageGenerator.setPercentInvalidMessages(percentInvalidMessages);
  }

  /**
   * Adds {@code uniqueBankClients} unique clients to {@code clients} and {@code bank}.
   */
  void generateClients() {
    for (int i = 0; i < uniqueBankClients; i++) {
      addClient(UniqueId.getRandomUniqueID(), RSAUtils.generateKeys());
    }
  }

  /**
   * Uses a provided MessageWriter to write {@code uniqueVerifications} messages.
   *
   * @param writer MessageWriter to write messages with.
   */
  void writeMessages(MessageWriter writer) throws IOException {
    for (int m = 0; m < uniqueVerifications; m++) {
      writer.writeMessage(generateMessage());
    }
    writer.flushAndClose();
  }

  /**
   * Helper method adds a single Client to both {@code clients} and {@code bank}.
   *
   * @param idNumber new client's ID number.
   * @param keyPair new client's keys.
   */
  private void addClient(Integer idNumber, Keys keyPair) {
    clients.addClient(idNumber, keyPair);
    bank.addClient(idNumber, keyPair.getPublicKey(), DepositWithdrawal.getRandomDepositLimit(),
        DepositWithdrawal.getRandomWithdrawalLimit());
  }

  /**
   * Helper method generates and verifies a random message.
   *
   * @return verified SignedMessage object.
   */
  private SignedMessage generateMessage() {
    return bank.verifyMessage(clients.generateRandomMessage());
  }

  /**
   * Get number of clients in clients object for testing.
   *
   * @return number of clients in clients.
   */
  Integer getNumberClients() {
    return clients.getNumberClients();
  }

  /**
   * Get number of clients in bank object for testing.
   *
   * @return number of clients in bank.
   */
  Integer getNumberBankClients() {
    return bank.getNumberClients();
  }
}
