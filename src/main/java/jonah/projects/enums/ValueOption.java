package jonah.projects.enums;

/**
 * Enum class for command line arguments with values that have bounds.
 */
public enum ValueOption {
  UNIQUE_BANK_CLIENTS("Unique Bank Clients", 0, 50000),
  UNIQUE_VERIFICATIONS("Unique Verifications", 0, 10000),
  PERCENT_INVALID_MESSAGES("Percentage Invalid Messages", 0, 100);

  private String text;
  private int minValue;
  private int maxValue;

  /**
   * ValueOption Constructor.
   *
   * @param text String describing the option.
   * @param minValue Minimum Integer value allowed for the option.
   * @param maxValue Maximum Integer value allowed for the option.
   */
  ValueOption(String text, Integer minValue, Integer maxValue) {
    this.text = text;
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  /**
   * Getter for the minimum value.
   *
   * @return Integer representing minimum value.
   */
  public int getMinValue() {
    return minValue;
  }

  /**
   * Getter for the maximum value.
   *
   * @return Integer representing maximum value.
   */
  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Returns a String describing the option and it's allowed range of values.
   *
   * @return String describing option and it's range.
   */
  public String getRangeMessage() {
    return "Value for " + text + " must be in range [" + minValue + ", " + maxValue + "]";
  }
}
