package jonah.projects.commandLine;


import jonah.projects.enums.ValueOption;
import org.junit.Test;

public class ArgCheckerTest {

  @Test
  public void checkNumberArgumentsGood() {
    String[] args = new String[] {"23", "asdfasd", "9e8r9", "data"};
    ArgChecker.checkNumberArguments(args);
  }

  @Test(expected = CmdLineExceptions.WrongNumberArgsException.class)
  public void checkNumberArgumentsTooFew() {
    String[] args = new String[] {"23", "asdfasd", "9e8r9"};
    ArgChecker.checkNumberArguments(args);
  }

  @Test(expected = CmdLineExceptions.WrongNumberArgsException.class)
  public void checkNumberArgumentsTooMany() {
    String[] args = new String[] {"23", "asdfasd", "9e8r9", "data", "987"};
    ArgChecker.checkNumberArguments(args);
  }

  @Test
  public void bankClientsGood() {
    ArgChecker.checkArgValueWithBounds(ValueOption.UNIQUE_BANK_CLIENTS, "3000");
  }

  @Test(expected = CmdLineExceptions.IllegalValueException.class)
  public void bankClientsTooLow() {
    ArgChecker.checkArgValueWithBounds(ValueOption.UNIQUE_BANK_CLIENTS, "-1");
  }

  @Test(expected = CmdLineExceptions.IllegalValueException.class)
  public void bankClientsTooHigh() {
    ArgChecker.checkArgValueWithBounds(ValueOption.UNIQUE_BANK_CLIENTS, "60000");
  }

  @Test
  public void verificationsGood() {
    ArgChecker.checkArgValueWithBounds(ValueOption.UNIQUE_VERIFICATIONS, "3000");
  }

  @Test(expected = CmdLineExceptions.IllegalValueException.class)
  public void verificationsTooLow() {
    ArgChecker.checkArgValueWithBounds(ValueOption.UNIQUE_VERIFICATIONS, "-1");
  }

  @Test(expected = CmdLineExceptions.IllegalValueException.class)
  public void verificationsTooHigh() {
    ArgChecker.checkArgValueWithBounds(ValueOption.UNIQUE_VERIFICATIONS, "10040");
  }

  @Test
  public void percentInvalidGood() {
    ArgChecker.checkArgValueWithBounds(ValueOption.PERCENT_INVALID_MESSAGES, "3");
  }

  @Test(expected = CmdLineExceptions.IllegalValueException.class)
  public void percentInvalidTooLow() {
    ArgChecker.checkArgValueWithBounds(ValueOption.PERCENT_INVALID_MESSAGES, "-1");
  }

  @Test(expected = CmdLineExceptions.IllegalValueException.class)
  public void percentInvalidTooHigh() {
    ArgChecker.checkArgValueWithBounds(ValueOption.PERCENT_INVALID_MESSAGES, "101");
  }
}