package jonah.projects.bank;

import static jonah.projects.enums.Transaction.DEPOSIT;

import java.util.HashMap;
import java.util.Map;
import jonah.projects.client.BankClient;
import jonah.projects.messages.SignedMessage;
import jonah.projects.rsaKeys.PublicKey;
import jonah.projects.rsaKeys.RSAUtils;

/**
 * Bank class for keeping track of bank clients and processing/ verifying messages.
 */
public class Bank {

  private static final Integer TEN = 10;
  private final Map<Integer, BankClient> clients = new HashMap<>();

  /**
   * Add a BankClient to this bank.
   *
   * @param idNumber Integer representing client's id Number.
   * @param publicKey client's PublicKey.
   * @param depositLimit client's deposit limit.
   * @param withdrawalLimit client's withdrawal limit.
   */
  public void addClient(Integer idNumber, PublicKey publicKey, Integer depositLimit,
      Integer withdrawalLimit) {
    clients.put(idNumber, new BankClient(idNumber, publicKey, depositLimit, withdrawalLimit));
  }

  /**
   * Validate a SignedMessage. A message is invalid if deposit/withdrawal amount exceeds client's
   * specified limit, or the message signature is invalid.
   *
   * @param message SignedMessage object with message, signature, and client Id number of sender.
   * @return true if valid, false if invalid.
   */
  public SignedMessage verifyMessage(SignedMessage message) {
    BankClient client = clientFromId(message.getClientIdNumber());
    setMessageVerification(message, client.getPublicKey());
    setMessageStatus(client, message);
    return message;
  }

  /**
   * Checks if a message is valid using RSA verification, ans sets message verification accordingly.
   * @param message SignedMessage to verify.
   * @param key PublicKey to verify message with.
   */
  private void setMessageVerification(SignedMessage message, PublicKey key) {
    message.setVerified(RSAUtils.verifyMessage(message.getSignature(), message.getRequest(), key));
  }

  /**
   * Checks if a withdrawal or deposit amount is valid based on the client's corresponding limit,
   * and sets the message's status based on that check.
   *
   * @param client Client to check against.
   * @param message SignedMessage with the request and transaction type.
   */
  private void setMessageStatus(BankClient client, SignedMessage message) {
    message.setStatus(removeLastDigit(message.getRequest()) <= (message.getTransactionType() == DEPOSIT ?
        client.getDepositLimit() : client.getWithdrawalLimit()));
  }

  /**
   * Helper method removes the last digit of an integer.
   *
   * @param request Integer to remove last digit of.
   * @return {@code request} with last digit removed.
   */
  private Integer removeLastDigit(Integer request) {
    return Math.floorDiv(request, TEN);
  }

  /**
   * Helper method returns a client based on a client id Number.
   *
   * @param idNumber client's id Number.
   * @return BankClient associated with {@code idNumber}, null if client is not in bank's clients.
   */
  private BankClient clientFromId(Integer idNumber) {
    return clients.get(idNumber);
  }

  /**
   * Getter for number of bank clients for testing.
   *
   * @return Number of bank clients.
   */
  public Integer getNumberClients() {
    return clients.size();
  }
}
