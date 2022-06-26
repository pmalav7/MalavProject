package milestone;

import java.util.Random;

/**
 * This class creates the random class and initialize the
 * associated method of generating the random number.
 */

public class RandomClass implements RandomInterface {

  @Override
  public int getRandom(int max) {
    if (max < 0) {
      throw new IllegalStateException("Value can't be less than 0");
    }
    Random r = new Random();
    return r.nextInt(max);
  }
}
