import milestone.RandomInterface;

/**
 * This clas creates a random mock and implements the associated method. This class will be used
 * for testing the computer player.
 */

public class RandomMock implements RandomInterface {

  private int[] arrayOfValues;

  private int index;

  /**
   * Random mock class can be initialized with variable number of parameters.
   * @param arrayOfValues refers to the values that will be used by imitate behaviour of computer
   *                      player.
   */

  public RandomMock(int... arrayOfValues) {
    index = 0;
    this.arrayOfValues = arrayOfValues;
  }

  @Override
  public int getRandom(int max) {

    int val = arrayOfValues[index++];
    index = index >= arrayOfValues.length ? 0 : index;
    return val;
  }
}
