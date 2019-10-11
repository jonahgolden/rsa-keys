package jonah.projects.bank;


import static jonah.projects.enums.Transaction.DEPOSIT_ACCEPTED;
import static jonah.projects.enums.Transaction.DEPOSIT_REJECTED;
import static jonah.projects.enums.Transaction.WITHDRAWAL_ACCEPTED;
import static jonah.projects.enums.Transaction.WITHDRAWAL_REJECTED;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import jonah.projects.constantUtils.UniqueId;
import jonah.projects.messages.SignedMessage;
import jonah.projects.rsaKeys.Keys;
import jonah.projects.rsaKeys.RSAUtils;
import org.junit.Before;
import org.junit.Test;

public class BankTest {

  private Bank bank;
  private SignedMessage message;
  private Integer id = UniqueId.getRandomUniqueID();
  private Keys key = RSAUtils.generateKeys();

  private Integer depositLimit = 1500;
  private Integer validDepositRequest = 14001;
  private Integer invalidDepositRequest = 16004;

  private Integer withdrawalLimit = 2500;
  private Integer validWithdrawalRequest = 3258;
  private Integer invalidWithdrawalRequest = 29005;

  @Before
  public void setUp() throws Exception {
    bank = new Bank();
    bank.addClient(id, key.getPublicKey(), depositLimit, withdrawalLimit);
  }

  @Test
  public void acceptedValidDeposit() {
    message = new SignedMessage(1, id, validDepositRequest,
        RSAUtils.generateSignature(validDepositRequest, key.getPrivateKey()));
    message = bank.verifyMessage(message);
    assertTrue(message.isVerified());
    assertEquals(DEPOSIT_ACCEPTED, message.getTransactionType());
  }

  @Test
  public void rejectedValidDeposit() {
    message = new SignedMessage(1201, id, invalidDepositRequest,
        RSAUtils.generateSignature(invalidDepositRequest, key.getPrivateKey()));
    message = bank.verifyMessage(message);
    assertTrue(message.isVerified());
    assertEquals(DEPOSIT_REJECTED, message.getTransactionType());
  }

  @Test
  public void acceptedValidWithdrawal() {
    message = new SignedMessage(500, id, validWithdrawalRequest,
        RSAUtils.generateSignature(validWithdrawalRequest, key.getPrivateKey()));
    message = bank.verifyMessage(message);
    assertTrue(message.isVerified());
    assertEquals(WITHDRAWAL_ACCEPTED, message.getTransactionType());
  }

  @Test
  public void rejectedValidWithdrawal() {
    message = new SignedMessage(69, id, invalidWithdrawalRequest,
        RSAUtils.generateSignature(invalidWithdrawalRequest, key.getPrivateKey()));
    message = bank.verifyMessage(message);
    assertTrue(message.isVerified());
    assertEquals(WITHDRAWAL_REJECTED, message.getTransactionType());
  }

  @Test
  public void invalidSignature() {
    message = new SignedMessage(69, id, validWithdrawalRequest,
        RSAUtils.generateSignature(validDepositRequest, key.getPrivateKey()));
    message = bank.verifyMessage(message);
    assertFalse(message.isVerified());
  }
}