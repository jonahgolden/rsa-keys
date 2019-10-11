package jonah.projects;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

/**
 * Some Time Simulation Test Results:
 * Small: 7336 (number_ids = unique clients * 20), 1171 (number_ids = unique clients * 2)
 * Medium: 4335 (number_ids = unique clients * 20), 505 (number_ids = unique clients * 2)
 * Max: 134945 (number_ids = unique clients * 20), 13652 (number_ids = unique clients * 2)
 */
public class SecureBankVerificationSimulatorTest {

  @Test
  public void mainSimulationSmall() throws IOException {
    String[] args = new String[]{"1000", "500", "2", "data.csv"};
    assertEquals(Integer.valueOf(args[1]) + 1, runSimulation(args), 0);
  }

  @Test
  public void mainSimulationMedium() throws IOException {
    String[] args = new String[]{"2000", "7000", "10", "data.csv"};
    assertEquals(Integer.valueOf(args[1]) + 1, runSimulation(args), 0);
  }

  @Test
  public void mainSimulationMax() throws IOException {
    String[] args = new String[]{"50000", "10000", "4", "data.csv"};
    assertEquals(Integer.valueOf(args[1]) + 1, runSimulation(args), 0);
  }

  /**
   * Helper method for testing runs a simulation and returns the number of lines in the output
   * file.
   *
   * @param args String[] to run simulation with.
   * @return int representing the number of lines counted in the output file after simulation.
   * @throws IOException if there is a problem with file IO.
   */
  private int runSimulation(String[] args) throws IOException {
    long start = System.currentTimeMillis();
    SecureBankVerificationSimulator.main(args);
    long end = System.currentTimeMillis();

    System.out.println("---Simulation Stats---");
    System.out.println("Number Unique Clients: " + args[0]);
    System.out.println("Number Unique Verifications: " + args[1]);
    System.out.println("Time taken: " + (end - start) + " ms");

    BufferedReader reader = new BufferedReader(new FileReader(args[3]));
    int lines = 0;
    while (reader.readLine() != null) {
      lines++;
    }
    reader.close();
    return lines;
  }
}