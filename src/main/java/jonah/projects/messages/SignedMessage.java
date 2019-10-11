package jonah.projects.messages;


import static jonah.projects.enums.Transaction.DEPOSIT_ACCEPTED;
import static jonah.projects.enums.Transaction.DEPOSIT_REJECTED;
import static jonah.projects.enums.Transaction.WITHDRAWAL;
import static jonah.projects.enums.Transaction.WITHDRAWAL_ACCEPTED;
import static jonah.projects.enums.Transaction.WITHDRAWAL_REJECTED;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import jonah.projects.enums.Field;
import jonah.projects.enums.Transaction;

/**
 * Class for a SignedMessage object with associated information.
 */
public class SignedMessage {

  private static final Integer TEN = 10;
  private static final String VERIFIED_TRUE_MESSAGE = "yes";
  private static final String VERIFIED_FALSE_MESSAGE = "no";


  private final Integer transactionNumber;
  private final LocalDate dateCreated = LocalDate.now();
  private final LocalTime timeCreated = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
  private final Integer clientIdNumber;
  private final Integer request;
  private final BigInteger signature;
  private boolean verified = false;
  private Transaction transactionType;

  /**
   * Constructor for a SignedMessage.
   *
   * @param transactionNumber number of this transaction.
   * @param clientIdNumber Integer representing the client Id Number.
   * @param request Integer representing the request associated with the message.
   * @param signature BigInteger representing the digital signature.
   */
  public SignedMessage(Integer transactionNumber, Integer clientIdNumber, Integer request,
      BigInteger signature) {
    this.transactionNumber = transactionNumber;
    this.clientIdNumber = clientIdNumber;
    this.request = request;
    this.signature = signature;
    this.transactionType = getStatusFromRequest();
  }

  /**
   * Getter for transaction number.
   *
   * @return Integer representing the transaction number.
   */
  public Integer getTransactionNumber() {
    return transactionNumber;
  }

  /**
   * Getter for date created.
   *
   * @return LocalDate representing the date this message was created.
   */
  public LocalDate getDateCreated() {
    return dateCreated;
  }

  /**
   * Getter for time created.
   *
   * @return LocalTime representing the time this message was created.
   */
  public LocalTime getTimeCreated() {
    return timeCreated;
  }

  /**
   * Getter for client ID number.
   *
   * @return Integer representing the client ID number associated with this message.
   */
  public Integer getClientIdNumber() {
    return clientIdNumber;
  }

  /**
   * Getter for message request.
   *
   * @return Integer representing the request associated with this message.
   */
  public Integer getRequest() {
    return request;
  }

  /**
   * Getter for message digital signature.
   *
   * @return BigInteger representing the signature associated with this message.
   */
  public BigInteger getSignature() {
    return signature;
  }

  /**
   * Getter for verification status.
   *
   * @return true if message is RSA verified, false if not.
   */
  public boolean isVerified() {
    return verified;
  }

  /**
   * Getter for Transaction type of this message.
   *
   * @return Transaction enum representing this messages transaction type and status.
   */
  public Transaction getTransactionType() {
    return transactionType;
  }

  /**
   * Setter for verification status.
   *
   * @param verified boolean representing if this message is RSA verified.
   */
  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  /**
   * Setter for the status of this messages transaction.
   *
   * @param accepted true if this message is accepted, false if not.
   */
  public void setStatus(boolean accepted) {
    this.transactionType =
        transactionType == WITHDRAWAL ? (accepted ? WITHDRAWAL_ACCEPTED : WITHDRAWAL_REJECTED) :
            (accepted ? DEPOSIT_ACCEPTED : DEPOSIT_REJECTED);
  }

  /**
   * Getter for the String representation of any field. This function allows the order of enums in
   * Field to determine the order in which a MessageWriter writes message information.
   *
   * @param field Transaction enum representing the field to return the String for.
   * @return String representing specified field.
   */
  public String getFieldString(Field field) {
    switch (field) {

      case TRANSACTION_NUMBER:
        return transactionNumber.toString();
      case DATE:
        return dateCreated.toString();
      case TIME:
        return timeCreated.toString();
      case CLIENT_ID:
        return clientIdNumber.toString();
      case REQUEST:
        return request.toString();
      case SIGNATURE:
        return signature.toString();
      case VERIFIED:
        return verified ? VERIFIED_TRUE_MESSAGE : VERIFIED_FALSE_MESSAGE;
      case STATUS:
        return transactionType.getStatus();
      default:
        return "";
    }
  }

  /**
   * Helper method for getting a Transaction type based on the last digit of a request.
   *
   * @return Transaction type based on the last digit of this messages request.
   */
  private Transaction getStatusFromRequest() {
    return Transaction.get(request % TEN);
  }
}
