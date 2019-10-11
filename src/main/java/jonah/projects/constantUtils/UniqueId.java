package jonah.projects.constantUtils;


import static jonah.projects.enums.ValueOption.UNIQUE_BANK_CLIENTS;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class for generating unique Ids. NOTE: Simulation performance depends in large part on the
 * constant {@code NUM_IDS_MULTIPLIER}. Suggested value is between 1.0 and 2.0. Setting this less
 * than 1.0 will require some simulations to spend extra time generating more unique ID numbers
 * during simulation. Setting it higher will require all simulations to spend extra time generating
 * unique ID numbers at the beginning of simulation.
 */
public abstract class UniqueId {

  private static final int THRESHOLD_TO_ADD_MORE_IDS = 1;
  private static final double NUM_IDS_MULTIPLIER = 1.0;
  static final int INITIAL_NUM_IDS = (int) (UNIQUE_BANK_CLIENTS.getMaxValue()
      * NUM_IDS_MULTIPLIER);
  static int ID_NUM_MIN = 1000000;
  static int ID_NUM_MAX = ID_NUM_MIN + INITIAL_NUM_IDS;
  private static List<Integer> UNIQUE_ID_NUMBERS = populateRandomIdList();

  /**
   * Return a random ID Number and remove it from {@code RANDOM_ID_NUMBERS}, ensuring uniqueness.
   *
   * @return Integer representing the ID Number.
   */
  public static Integer getRandomUniqueID() {
    if (UNIQUE_ID_NUMBERS.size() < THRESHOLD_TO_ADD_MORE_IDS) {
      generateNewUniqueIdNumbers();
    }
    return UNIQUE_ID_NUMBERS.remove(0);
  }

  /**
   * Resets {@code UNIQUE_ID_NUMBERS} to a new selection of {@code INITIAL_NUM_IDS} unique numbers.
   */
  private static void generateNewUniqueIdNumbers() {
    System.out.printf(
        "Number of remaining unique IDs is below threshold of %d.\nAdding %d more unique IDs.\n",
        THRESHOLD_TO_ADD_MORE_IDS, INITIAL_NUM_IDS);

    ID_NUM_MIN = ID_NUM_MAX + 1;
    ID_NUM_MAX = ID_NUM_MAX + INITIAL_NUM_IDS;
    UNIQUE_ID_NUMBERS = populateRandomIdList();
  }

  /**
   * Returns a random list of Integers between {@code ID_NUM_MIN} and {@code ID_NUM_MAX}.
   *
   * @return random list of Integers.
   */
  private static List<Integer> populateRandomIdList() {
    List<Integer> idNums = IntStream.rangeClosed(ID_NUM_MIN, ID_NUM_MAX)
        .boxed().collect(Collectors.toList());
    Collections.shuffle(idNums);
    return idNums;
  }
}
