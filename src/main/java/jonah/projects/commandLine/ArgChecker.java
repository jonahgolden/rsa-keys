package jonah.projects.commandLine;


import jonah.projects.enums.ValueOption;

/**
 * Interface for checking command line arguments.
 */
public interface ArgChecker {

  Integer REQUIRED_NUMBER_ARGS = 4;
  String WRONG_NUMBER_ARGS_MESSAGE = "Must provide " + REQUIRED_NUMBER_ARGS + " arguments.";

  /**
   * Checks the number of arguments provided.
   *
   * @param args The arguments provided
   * @throws CmdLineExceptions.WrongNumberArgsException if number of args is not equal to {@code
   * REQUIRED_NUMBER_ARGS}
   */
  static void checkNumberArguments(String[] args) {
    if (args.length != REQUIRED_NUMBER_ARGS) {
      throw new CmdLineExceptions.WrongNumberArgsException(WRONG_NUMBER_ARGS_MESSAGE);
    }
  }

  /**
   * Checks if an argument is within it's required bounds.
   *
   * @param option ValueOption representing the argument.
   * @param arg String of the argument provided
   * @return Integer representing the argument if it is within required bounds.
   * @throws CmdLineExceptions.IllegalValueException if the argument is not within required bounds.
   */
  static Integer checkArgValueWithBounds(ValueOption option, String arg) {
    Integer passedValue = Integer.valueOf(arg);
    if (passedValue < option.getMinValue() || passedValue > option.getMaxValue()) {
      throw new CmdLineExceptions.IllegalValueException(option.getRangeMessage());
    } else {
      return passedValue;
    }
  }
}