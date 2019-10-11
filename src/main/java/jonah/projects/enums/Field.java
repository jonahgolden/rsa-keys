package jonah.projects.enums;

/**
 * Enum class for fields to be written for a message. NOTE: Order of fields in this class is the
 * order in which they will be written.
 */
public enum Field {

  TRANSACTION_NUMBER("Transaction number"),
  DATE("Date"),
  TIME("Time"),
  CLIENT_ID("Client ID"),
  REQUEST("Message"),
  SIGNATURE("Digital signature"),
  VERIFIED("Verified"),
  STATUS("Transactions status");

  private String fieldText;

  /**
   * Constructor for a field.
   *
   * @param fieldText The text representing that field.
   */
  Field(String fieldText) {
    this.fieldText = fieldText;
  }

  /**
   * Getter for text representing a field.
   *
   * @return text representing the field.
   */
  public String getFieldText() {
    return fieldText;
  }
}
