package jonah.projects;


import static junit.framework.TestCase.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import jonah.projects.writer.MessageWriter;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {
  private Controller controller;
  private Integer uniqueClients = 1000;
  private Integer uniqueVerifications = 500;
  private Integer percentInvalid = 5;
  private String filePath = "data.csv";

  @Before
  public void setUp() throws Exception {
    controller = new Controller(uniqueClients, uniqueVerifications, percentInvalid);
  }

  @Test
  public void generateClients() {
    controller.generateClients();
    assertEquals(uniqueClients, controller.getNumberClients());
    assertEquals(controller.getNumberClients(), controller.getNumberBankClients());
  }

  @Test
  public void writeMessages() throws IOException {
    MessageWriter writer = new MessageWriter(filePath);
    controller.generateClients();
    controller.writeMessages(writer);
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    int lines = 0;
    while (reader.readLine() != null) lines++;
    reader.close();
    assertEquals(uniqueVerifications, lines, 0);
  }
}