package jonah.projects.client;


import jonah.projects.rsaKeys.PublicKey;

/**
 * Class representing an AbstractClient with properties and methods that all clients share.
 */
public abstract class AbstractClient {

  private Integer idNumber;
  private PublicKey publicKey;

  /**
   * Constructor.
   *
   * @param idNumber Integer representing client's ID number.
   * @param publicKey client's PublicKey.
   */
  public AbstractClient(Integer idNumber, PublicKey publicKey) {
    this.idNumber = idNumber;
    this.publicKey = publicKey;
  }

  /**
   * Getter for ID Number.
   *
   * @return Integer representing client's ID number.
   */
  public Integer getIdNumber() {
    return idNumber;
  }

  /**
   * Getter for Public Key.
   *
   * @return client's PublicKey.
   */
  public PublicKey getPublicKey() {
    return publicKey;
  }
}
