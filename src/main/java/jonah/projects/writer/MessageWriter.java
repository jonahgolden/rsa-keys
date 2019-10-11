package jonah.projects.writer;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jonah.projects.enums.Field;
import jonah.projects.messages.SignedMessage;

/**
 * A MessageWriter object that writes messages to an output file.
 */
public class MessageWriter {

  private static final Field[] FIELDS = Field.values();
  private static final String COMMA = ",";
  private static final String NEW_LINE = "\n";

  private String filePath;
  private BufferedWriter writer;

  /**
   * Constructor for a MessageWriter.
   *
   * @param filePath The file path of the file to write to.
   */
  public MessageWriter(String filePath) throws IOException {
    this.filePath = filePath;
    openWriter();
  }

  /**
   * Opens a BufferedWriter for the output file.
   *
   * @throws IOException if there is a problem opening the FileWriter.
   */
  private void openWriter() throws IOException {
    this.writer = new BufferedWriter(new FileWriter(filePath, false));
  }

  /**
   * Writes the header defined in {@code HEADER} to the output file.
   *
   * @throws IOException if there is a problem writing the header.
   */
  public void writeHeader() throws IOException {
    List<String> row = new ArrayList<>();
    for (Field f : FIELDS) {
      row.add(f.getFieldText());
    }
    writeLine(row);
  }

  /**
   * Writes a SignedMessage object as a row in the output file.
   *
   * @param message SignedMessage to write.
   * @throws IOException if there is a problem writing the message.
   */
  public void writeMessage(SignedMessage message) throws IOException {
    List<String> row = new ArrayList<>();
    for (Field f : FIELDS) {
      row.add(message.getFieldString(f));
    }
    writeLine(row);
  }

  /**
   * Helper method writes a String array to the output file as a row.
   *
   * @param row String array to write.
   * @throws IOException If there is a problem writing the row.
   */
  private void writeLine(List<String> row) throws IOException {
    writer.append(String.join(COMMA, row));
    writer.append(NEW_LINE);
  }

  /**
   * Flushes and closes the writer.
   *
   * @throws IOException If there is a problem flushing or closing the writer.
   */
  public void flushAndClose() throws IOException {
    writer.flush();
    writer.close();
  }
}
