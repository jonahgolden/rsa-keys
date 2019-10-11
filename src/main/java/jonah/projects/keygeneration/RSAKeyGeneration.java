package jonah.projects.keygeneration;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RSAKeyGeneration {

  private static final int MIN_MIN = 1000;
  private static final int MIN_MAX = 3000;
  private static final int MAX_MIN = 3000;
  private static final int MAX_MAX = 10000;
  private Integer p;
  private Integer q;
  private Integer phiN;
  private Integer n;
  private Integer a;

  // QUESTIONS
  // 1) Will max always be greater than min when generating random numbers? Or make helper method
  //    to prevent min being greater? ANSWER: Must use two functions.. sob


  /**
   * Constructs two distinct prime numbers and assigns them to p, q.
   *
   * Phi(n) = (p-1)(q-1) n = p * q
   */
  public RSAKeyGeneration() {
    this.p = getRandomPrimeFromList(
        generatePrimeList(generateRandomIntMin(), generateRandomIntMax()));
    this.q = getRandomPrimeFromList(
        generatePrimeList(generateRandomIntMin(), generateRandomIntMax()));

    this.phiN = computePhiN();
    this.n = computeN();
    /**this.a = TODO: this.a = */

  }

  /**
   * Generates a list of prime numbers using stream. The method takes in a minimum and maximum then
   * creates a list of primes between those. The LongStream.range -> generates the range of numbers
   * our list of Longs will be between. The .filter() -> uses the class and the private method
   * isPrime to filter only prime values. Lastly, the .boxed() and .collect(Collectors.toList()) are
   * intermediate operations that help convert the stream to a list.
   *
   * @param minimum the minimum number of the range
   * @param maximum the maximum number of the range
   * @return a list of long's
   */
  private static List<Integer> generatePrimeList(Integer minimum, Integer maximum) {
    return IntStream.range(minimum, maximum)
        .filter(RSAKeyGeneration::isPrime)   // double colon helps call a class function inline
        .boxed()
        .collect(Collectors.toList());
  }

  // MIGHT NOT NEED THIS AS STATIC

  /**
   * Using streams, determines if the long being passed in is a prime number or not. If it is a
   * prime number it will return True, otherwise false. Method is used in 'generatePrimeList'
   * method.
   *
   * @param num the number to check if its prime or not
   * @return true or false
   */
  private static boolean isPrime(Integer num) {
    return IntStream.rangeClosed(2, (int) Math.sqrt(num)).allMatch(i -> num % i != 0);
  }

  /**
   * Gets a random number from the list of longs created from 'generatePrimeList' method. In
   * addition, it removes the number/index from the list to ensure the next number picked is unique.
   * This method is the final method that is used to get the random number used for instance
   * variables p and q.
   *
   * @return the random number from the list
   */
  private Integer getRandomPrimeFromList(List<Integer> primeList) {
    Random random = new Random();
    Integer randPrime = primeList.get(random.nextInt(primeList.size()));
    primeList.remove(randPrime);
    return randPrime;
  }

  /**
   * Generates a random number between 1000 and 3000 inclusive. This method is utilized in
   * 'generatePrimeList' to help provide a lower bound.
   *
   * @return a random int between 1000 and 3000, inclusive
   */
  private Integer generateRandomIntMin() {
    return (int) (Math.abs(Math.random() * (MIN_MAX - MIN_MIN) + 1) + MIN_MIN);
  }

  /**
   * Generates a random number between 3001 and 10000. This method is utilized in
   * 'generatePrimeList' to help provide an upper bound.
   *
   * @return a random int between 3001 and 10,000, inclusive
   */
  private Integer generateRandomIntMax() {
    return (int) (Math.abs(Math.random() * (MAX_MIN - MAX_MAX) + 1) + MAX_MIN);
  }

  private Integer computePhiN() {
    return (this.getP() - 1) * (this.getQ() - 1);
  }

  private Integer computeN() {
    return (this.getP()) * (this.getQ());
  }

  public Integer getPhiN() {
    return phiN;
  }

  public Integer getN() {
    return n;
  }

  /**
   * Used for testing.
   *
   * @return the prime number P
   */
  public Integer getP() {
    return p;
  }

  /**
   * Used for testing.
   *
   * @return the prime number Q
   */
  public Integer getQ() {
    return q;
  }
}
