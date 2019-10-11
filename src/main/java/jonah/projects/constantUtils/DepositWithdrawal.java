package jonah.projects.constantUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A class for constants and generating random numbers based on those constants.
 */
public abstract class DepositWithdrawal {

  private static final List<Integer> DEPOSIT_REQUEST_CODES = Collections
      .unmodifiableList(Arrays.asList(0, 1, 2, 3, 4));
  private static final List<Integer> WITHDRAWAL_REQUEST_CODES = Collections
      .unmodifiableList(Arrays.asList(5, 6, 7, 8, 9));

  private static final Random rand = new Random();
  private static final Integer DEPOSIT_UPPER_BOUND = 3001;
  private static final Integer WITHDRAWAL_UPPER_BOUND = 2001;

  /**
   * Getter for deposit request codes.
   * @return List of Integers representing the deposit request codes.
   */
  public static List<Integer> getDepositRequestCodes() {
    return DEPOSIT_REQUEST_CODES;
  }

  /**
   * Getter for withdrawal request codes.
   * @return List of Integers representing the withdrawal request codes.
   */
  public static List<Integer> getWithdrawalRequestCodes() {
    return WITHDRAWAL_REQUEST_CODES;
  }

  /**
   * Get a random positive deposit limit less than {@code DEPOSIT_UPPER_BOUND}.
   *
   * @return Integer representing the deposit limit.
   */
  public static Integer getRandomDepositLimit() {
    return rand.nextInt(DEPOSIT_UPPER_BOUND);
  }

  /**
   * Get a random positive withdrawal limit less than {@code WITHDRAWAL_UPPER_BOUND}.
   *
   * @return Integer representing the withdrawal limit.
   */
  public static Integer getRandomWithdrawalLimit() {
    return rand.nextInt(WITHDRAWAL_UPPER_BOUND);
  }

}
