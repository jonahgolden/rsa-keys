package jonah.projects.commandLine;

/**
 * Class to handle all Command Line Arguments related exceptions.
 */
public abstract class CmdLineExceptions extends RuntimeException {

  /**
   * This constructor prints the error message on command line and exits successfully.
   *
   * @param msg Error message
   */
  public CmdLineExceptions(String msg) {
    super(msg);
  }


  /**
   * Class to handle errors related to wrong number of command line arguments.
   */
  public static class WrongNumberArgsException extends CmdLineExceptions {

    /**
     * This constructor passes the error message to its super class.
     *
     * @param msg Error message
     */
    public WrongNumberArgsException(String msg) {
      super(msg);
    }
  }


  /**
   * Class to handle errors related to illegal command line values.
   */
  public static class IllegalValueException extends CmdLineExceptions {

    /**
     * This constructor passes the error message to its super class.
     *
     * @param msg Error message
     */
    public IllegalValueException(String msg) {
      super(msg);
    }
  }
}
