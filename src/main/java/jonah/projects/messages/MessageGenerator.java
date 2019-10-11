package jonah.projects.messages;


import java.math.BigInteger;
import java.util.Random;
import jonah.projects.rsaKeys.PrivateKey;
import jonah.projects.rsaKeys.RSAUtils;

/**
 * Abstract class with a static method for generating messages.
 */
public abstract class MessageGenerator {

  private static final Integer REQUEST_MIN = 10;
  private static final Integer REQUEST_MAX = 30000;
  private static final Integer PERCENTAGE_MAX = 100;
  private static Integer percentInvalidMessages = 0;
  private static final Random random = new Random();
  private static Integer transactions = 0;

  /**
   * Sets the percentage of messages that are generated as invalid.
   *
   * @param percentInvalid Integer in range [0, {@code PERCENTAGE_MAX}]
   */
  public static void setPercentInvalidMessages(Integer percentInvalid) {
    percentInvalidMessages = percentInvalid;
  }

  /**
   * Generates a SignedMessage using a client's PrivateKey.
   *
   * @param privateKey PrivateKey to generate message with.
   * @param clientID Integer representing the client's idNumber to associate with the message.
   * @return SignedMessage object.
   */
  public static SignedMessage generateMessage(PrivateKey privateKey, Integer clientID) {
    Integer request = randomRequest();
    return new SignedMessage(++transactions, clientID,  request, makeSignature(request, privateKey));
  }

  /**
   * Heper method generates a random message in range [0, {@code MESSAGE_MAX}].
   *
   * @return Integer representing the message.
   */
  private static Integer randomRequest() {
    return random.nextInt(REQUEST_MAX - REQUEST_MIN + 1) + REQUEST_MIN;
  }

  /**
   * Helper method returns a signature that is invalid {@code percentInvalidMessages} percent of the
   * time, and valid the rest of the time.
   *
   * @param message Integer representing message to generate valid signature with.
   * @param privateKey PrivateKey object to generate valid signature with.
   * @return BigInteger representing valid or invalid signature.
   */
  private static BigInteger makeSignature(Integer message, PrivateKey privateKey) {
    return invalidMessage() ? BigInteger.valueOf(random.nextInt())
        : RSAUtils.generateSignature(message, privateKey);
  }

  /**
   * Helper method returns true {@code percentInvalidMessages} percent of the time, false the rest.
   *
   * @return true {@code percentInvalidMessages} percent of the time, false the rest of the time.
   */
  private static boolean invalidMessage() {
    return (random.nextInt(PERCENTAGE_MAX - 1) < percentInvalidMessages);
  }
}
