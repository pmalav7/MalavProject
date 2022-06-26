package view;

import milestone.WorldImpl;

/**
 * This interface represents the transmitter which will share file of new specification.
 */

public interface FileTransmitter {

  /**
   * This method refers to registering a listener.
   * @param obs refers to object og WorldImpl.
   */

  void register(WorldImpl obs);

  /**
   * This method would notify observer in case of any activity.
   */

  void notifyObserver();
}
