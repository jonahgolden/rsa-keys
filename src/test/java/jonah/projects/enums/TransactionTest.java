package jonah.projects.enums;


import static jonah.projects.enums.Transaction.DEPOSIT;
import static jonah.projects.enums.Transaction.WITHDRAWAL;
import static junit.framework.TestCase.assertEquals;

import jonah.projects.constantUtils.DepositWithdrawal;
import org.junit.Test;

public class TransactionTest {

  @Test
  public void getStatus() {
    assertEquals("deposit unprocessed", DEPOSIT.getStatus());
  }

  @Test
  public void getFromDepositCode() {
    for (Integer code : DepositWithdrawal.getDepositRequestCodes()) {
      assertEquals(DEPOSIT, Transaction.get(code));
    }
  }

  @Test
  public void getFromWithdrawalCode() {
    for (Integer code : DepositWithdrawal.getWithdrawalRequestCodes()) {
      assertEquals(WITHDRAWAL, Transaction.get(code));
    }
  }
}