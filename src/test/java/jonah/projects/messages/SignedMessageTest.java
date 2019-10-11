package jonah.projects.messages;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import jonah.projects.enums.Field;
import jonah.projects.enums.Transaction;
import org.junit.Before;
import org.junit.Test;

public class SignedMessageTest {

  private SignedMessage newSignedMessage;
  private Integer transactionNumber = 123456;
  private LocalDate dateCreated;
  private LocalTime timeCreated;
  private Integer clientIdNumber = 45667;
  private Integer request = 788983;
  private BigInteger signature = BigInteger.valueOf(232132432);
  private Transaction status = Transaction.DEPOSIT;

  @Before
  public void setUp() throws Exception {
    dateCreated = LocalDate.now();
    timeCreated = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
    newSignedMessage = new SignedMessage(transactionNumber, clientIdNumber, request, signature);
  }

  @Test
  public void getFieldsInOrder() {
    String[] expectedFields = {"123456", dateCreated.toString(), timeCreated.toString(), "45667", "788983",
        "232132432", "no", "deposit unprocessed"};
    int i = 0;
    for (Field f : Field.values()) {
      assertEquals(expectedFields[i++], newSignedMessage.getFieldString(f));
    }
  }

  @Test
  public void getTransactionNumber() {
    assertEquals(123456, newSignedMessage.getTransactionNumber(), 0);
  }

  @Test
  public void getDateCreated() {
    assertEquals(dateCreated, newSignedMessage.getDateCreated());
  }

  @Test
  public void getTimeCreated() {
    assertEquals(timeCreated, newSignedMessage.getTimeCreated());
  }

  @Test
  public void getClientIdNumber() {
    assertEquals(45667, newSignedMessage.getClientIdNumber(), 0);
  }

  @Test
  public void getRequest() {
    assertEquals(788983, newSignedMessage.getRequest(), 0);
  }

  @Test
  public void getSignature() {
    BigInteger checkSignature = new BigInteger("232132432");
    assertEquals(checkSignature, newSignedMessage.getSignature());
  }

  @Test
  public void isVerified() {
    assertFalse(newSignedMessage.isVerified());
  }

  @Test
  public void getStatus() {
    assertEquals(status, newSignedMessage.getTransactionType());
  }

  @Test
  public void setVerified() {
    newSignedMessage.setVerified(true);
    assertTrue(newSignedMessage.isVerified());
  }

  @Test
  public void verifiedMessageNo() {
    assertFalse(newSignedMessage.isVerified());
  }

  @Test
  public void verifiedMessageYes() {
    newSignedMessage.setVerified(true);
    assertTrue(newSignedMessage.isVerified());
  }

  @Test
  public void statusMessageUnprocessed() {
    assertEquals("deposit unprocessed", newSignedMessage.getTransactionType().getStatus());
  }

  @Test
  public void statusMessageAccepted() {
    System.out.println(newSignedMessage.getTransactionType());
    newSignedMessage.setStatus(true);
    System.out.println(newSignedMessage.getTransactionType());
    assertEquals("deposit accepted", newSignedMessage.getTransactionType().getStatus());
  }

  @Test
  public void statusMessageRejected() {
    newSignedMessage.setStatus(false);
    assertEquals("deposit rejected", newSignedMessage.getTransactionType().getStatus());
  }
}