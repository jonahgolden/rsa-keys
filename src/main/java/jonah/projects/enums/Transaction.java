package jonah.projects.enums;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jonah.projects.constantUtils.DepositWithdrawal;

/**
 * Enum for Transaction types.
 */
public enum Transaction {


  DEPOSIT(DepositWithdrawal.getDepositRequestCodes(), "deposit unprocessed"),
  WITHDRAWAL(DepositWithdrawal.getWithdrawalRequestCodes(), "withdrawal unprocessed"),
  DEPOSIT_ACCEPTED("deposit accepted"),
  DEPOSIT_REJECTED("deposit rejected"),
  WITHDRAWAL_ACCEPTED("withdrawal accepted"),
  WITHDRAWAL_REJECTED("withdrawal rejected");

  private List<Integer> requestCodes = null;
  private String status;

  /**
   * Constructor for a Transaction with a code.
   *
   * @param requestCodes Array of Integers of the codes that represent this Transaction type.
   * @param status String representing transaction status.
   */
  Transaction(List<Integer> requestCodes, String status) {
    this.requestCodes = requestCodes;
    this.status = status;
  }

  /**
   * Constructor for a Transaction with a status.
   *
   * @param status String representing status.
   */
  Transaction(String status) {
    this.status = status;
  }

  /**
   * Getter for transaction's codes.
   *
   * @return Array of Integers of the codes that represent this Transaction type.
   */
  private List<Integer> getRequestCodes() {
    return requestCodes;
  }

  /**
   * Getter for String representing status.
   * @return String representing status.
   */
  public String getStatus() {
    return status;
  }

  private static final Map<Integer, Transaction> CODE_MAP;

  static {
    Map<Integer, Transaction> map = new HashMap<>();
    for (Transaction trans : Transaction.values()) {
      if (trans.getRequestCodes() != null) {
        for (Integer code : trans.getRequestCodes()) {
          map.put(code, trans);
        }
      }
    }
    CODE_MAP = Collections.unmodifiableMap(map);
  }

  /**
   * Get a Transaction based on a code.
   *
   * @param requestCode code to get transaction of.
   * @return Transaction associated with {@code code}, null if code has no associations.
   */
  public static Transaction get(Integer requestCode) {
    return CODE_MAP.get(requestCode);
  }
}
