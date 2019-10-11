package jonah.projects.messages;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import jonah.projects.constantUtils.UniqueId;
import jonah.projects.rsaKeys.Keys;
import jonah.projects.rsaKeys.RSAUtils;
import org.junit.Before;
import org.junit.Test;

public class MessageGeneratorTest {
  private static final Integer REQUEST_MIN = 10;
  private static final Integer REQUEST_MAX = 30000;
  private int NUM_TRIALS = 100;
  private Keys key1 = RSAUtils.generateKeys();
  private Integer id1 = UniqueId.getRandomUniqueID();
  private SignedMessage message;

  @Before
  public void setUp() throws Exception {
    MessageGenerator.setPercentInvalidMessages(0);
  }

  @Test
  public void setPercentInvalidMessages() {
  }

  @Test
  public void noInvalidMessagesTest() {
    for (int i = 0; i < NUM_TRIALS; i++) {
      message = MessageGenerator.generateMessage(key1.getPrivateKey(), id1);
      assertTrue(RSAUtils.verifyMessage(message.getSignature(), message.getRequest(), key1.getPublicKey()));
    }
  }

  @Test
  public void allInvalidMessagesTest() {
    MessageGenerator.setPercentInvalidMessages(100);
    for (int i = 0; i < NUM_TRIALS; i++) {
      message = MessageGenerator.generateMessage(key1.getPrivateKey(), id1);
      assertFalse(RSAUtils.verifyMessage(message.getSignature(), message.getRequest(), key1.getPublicKey()));
    }
  }

  @Test
  public void someInvalidMessagesTest() {
    Integer percentInvalid = 20;
    MessageGenerator.setPercentInvalidMessages(percentInvalid);
    int invalids = 0;
    for (int i = 0; i < NUM_TRIALS; i++) {
      message = MessageGenerator.generateMessage(key1.getPrivateKey(), id1);
      if (!RSAUtils.verifyMessage(message.getSignature(), message.getRequest(), key1.getPublicKey()))
        invalids ++;
    }
    System.out.println("Percent invalid: " + percentInvalid);
    System.out.println("Actual invalid: " + invalids + " / " + NUM_TRIALS);
  }

  @Test
  public void requestNotLessThanMin() {
    for (int i = 0; i < NUM_TRIALS; i++) {
      message = MessageGenerator.generateMessage(key1.getPrivateKey(), id1);
      assertTrue(message.getRequest() >= REQUEST_MIN);
    }
  }

  @Test
  public void requestNotGreaterThanMax() {
    for (int i = 0; i < NUM_TRIALS; i++) {
      message = MessageGenerator.generateMessage(key1.getPrivateKey(), id1);
      assertTrue(message.getRequest() <= REQUEST_MAX);
    }
  }
}