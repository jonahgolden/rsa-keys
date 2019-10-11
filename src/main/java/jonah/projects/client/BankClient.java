package jonah.projects.client;


import jonah.projects.rsaKeys.PublicKey;

/**
 * BankClient object with all Client properties accessible to the Bank.
 */
public class BankClient extends AbstractClient {

  private Integer depositLimit;
  private Integer withdrawalLimit;

  /**
   * Constructor for BankClient object.
   *
   * @param idNumber Integer representing client's ID number.
   * @param publicKey client's PublicKey.
   * @param depositLimit Integer representing client's deposit limit.
   * @param withdrawalLimit Integer representing client's withdrawal limit.
   */
  public BankClient(Integer idNumber, PublicKey publicKey, Integer depositLimit,
      Integer withdrawalLimit) {
    super(idNumber, publicKey);
    this.depositLimit = depositLimit;
    this.withdrawalLimit = withdrawalLimit;
  }

  /**
   * Getter for client's deposit limit.
   *
   * @return Integer representing client's deposit limit.
   */
  public Integer getDepositLimit() {
    return depositLimit;
  }

  /**
   * Getter for client's withdrawal limit.
   *
   * @return Integer representing client's withdrawal limit.
   */
  public Integer getWithdrawalLimit() {
    return withdrawalLimit;
  }
}
