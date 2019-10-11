package jonah.projects.constantUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UniqueIdTest {
  private int NUM_TRIALS = 50000;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void getRandomUniqueID() {
    for (int i = 0; i < NUM_TRIALS; i++) {
      Integer id = UniqueId.getRandomUniqueID();
      assertTrue(id >= UniqueId.ID_NUM_MIN);
      assertTrue(id <= UniqueId.ID_NUM_MAX);
    }
  }

  @Test
  public void resetUniqueIdNumbers() {
    int initialMax = UniqueId.ID_NUM_MAX;
    int initialNumIDs = UniqueId.INITIAL_NUM_IDS;

    for (int i = 0; i <= initialNumIDs + 1; i++) {
      UniqueId.getRandomUniqueID();
    }

    assertEquals(UniqueId.ID_NUM_MIN, initialMax + 1);
    for (int i = 0; i < NUM_TRIALS; i++) {
      assertTrue(initialMax <= UniqueId.getRandomUniqueID());
    }
  }

  @Test
  public void generateNewUniqueIdNumbers() {
  }
}