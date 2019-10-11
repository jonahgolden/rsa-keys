package jonah.projects;


import static jonah.projects.enums.ValueOption.PERCENT_INVALID_MESSAGES;
import static jonah.projects.enums.ValueOption.UNIQUE_BANK_CLIENTS;
import static jonah.projects.enums.ValueOption.UNIQUE_VERIFICATIONS;

import java.io.IOException;
import jonah.projects.commandLine.ArgChecker;
import jonah.projects.writer.MessageWriter;

/**
 * A Secure Bank Verification Simulator that does what it's name suggests. Main method takes 4
 * arguments:
 * 1. The number of unique bank clients to simulate.
 * 2. The number of unique
 * verifications to simulate.
 * 3. Percentage of invalid messages to simulate via invalid (message, digital signature) pairs.
 *    Note: there may be some messages that are invalid due to randomly generated requests and
 *          corresponding withdrawal/deposit limits not included in this percentage.
 * 4. The output file - a string, representing the name of the output file to place information
 * about the generated messages.
 */
public class SecureBankVerificationSimulator {

  /**
   * Main method takes and verifies arguments, simulates clients and messages, writes messages to
   * output file.
   *
   * @param args arguments for the simulator.
   */
  public static void main(String[] args) {
    ArgChecker.checkNumberArguments(args);

    Integer uniqueBankClients = ArgChecker.checkArgValueWithBounds(UNIQUE_BANK_CLIENTS, args[0]);
    Integer uniqueVerifications = ArgChecker.checkArgValueWithBounds(UNIQUE_VERIFICATIONS, args[1]);
    Integer percentInvalid = ArgChecker.checkArgValueWithBounds(PERCENT_INVALID_MESSAGES, args[2]);
    String outputFilePath = args[3];

    Controller controller = new Controller(uniqueBankClients, uniqueVerifications, percentInvalid);
    controller.generateClients();

    try {
      MessageWriter writer = new MessageWriter(outputFilePath);
      writer.writeHeader();
      controller.writeMessages(writer);
    } catch (IOException e) {
      System.err.println("Caught IOException: " + e.getMessage());
    }
  }
}
