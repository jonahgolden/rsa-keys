package jonah.projects.writer;


import static jonah.projects.enums.Transaction.WITHDRAWAL;
import static junit.framework.TestCase.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import jonah.projects.constantUtils.UniqueId;
import jonah.projects.messages.SignedMessage;
import jonah.projects.rsaKeys.Keys;
import jonah.projects.rsaKeys.RSAUtils;
import org.junit.Before;
import org.junit.Test;

public class MessageWriterTest {

  private String expectedHeader = "Transaction number,Date,Time,Client ID,Message,Digital signature,Verified,Transactions status";
  private MessageWriter writer1;
  private String validFilePath = "data.csv";
  private String invalidFilePath = "doesntExist/someShit.csv";
  private SignedMessage message;

  @Before
  public void setUp() throws Exception {
    writer1 = new MessageWriter(validFilePath);
  }

  @Test(expected = IOException.class)
  public void invalidOpenWriter() throws IOException {
    writer1 = new MessageWriter(invalidFilePath);
  }

  @Test
  public void writeHeader() throws IOException {
    writer1.writeHeader();
    writer1.flushAndClose();
    BufferedReader reader = new BufferedReader(new FileReader(validFilePath));
    assertEquals(expectedHeader, reader.readLine());
    reader.close();
  }

  @Test
  public void writeMessage() throws IOException {
    Integer transNumber = 42;
    Integer id = UniqueId.getRandomUniqueID();
    Keys keys = RSAUtils.generateKeys();
    Integer request = 15005;
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
    message = new SignedMessage(transNumber, id, request,
        RSAUtils.generateSignature(request, keys.getPrivateKey()));
    writer1.writeMessage(message);
    writer1.flushAndClose();

    String expectedLine = transNumber.toString() + "," + currentDate.toString() + ","
        + currentTime.toString() + "," + id.toString() + "," + request.toString() + ","
        + message.getSignature().toString() + "," + "no," + WITHDRAWAL.getStatus();
    BufferedReader reader = new BufferedReader(new FileReader(validFilePath));
    assertEquals(expectedLine, reader.readLine());
    reader.close();
  }
}