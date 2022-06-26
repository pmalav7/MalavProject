package milestone;

/**
 * This interface specifies how Random number generator works.
 * It has a command which would generate a random number every time it is invoked.
 */

public interface RandomInterface {
  /**
   * This method generates random number lesser than the number passed in as a parameter.
   *
   * @param max refers to the limit under which random number can be generated.
   * @return a random number.
   */

  int getRandom(int max);
}
