package jonah.projects.client;

import static junit.framework.TestCase.assertEquals;

import jonah.projects.constantUtils.DepositWithdrawal;
import jonah.projects.constantUtils.UniqueId;
import jonah.projects.rsaKeys.Keys;
import jonah.projects.rsaKeys.RSAUtils;
import org.junit.Before;
import org.junit.Test;

public class BankClientTest {
  BankClient client1;
  Integer id1 = UniqueId.getRandomUniqueID();
  Keys keys1 = RSAUtils.generateKeys();
  Integer depositLimit = DepositWithdrawal.getRandomDepositLimit();
  Integer withdrawalLimit = DepositWithdrawal.getRandomWithdrawalLimit();

  @Before
  public void setUp() {
    client1 = new BankClient(id1, keys1.getPublicKey(), depositLimit, withdrawalLimit);
  }

  @Test
  public void getDepositLimit() {
    assertEquals(depositLimit, client1.getDepositLimit());
  }

  @Test
  public void getWithdrawalLimit() {
    assertEquals(withdrawalLimit, client1.getWithdrawalLimit());
  }
}