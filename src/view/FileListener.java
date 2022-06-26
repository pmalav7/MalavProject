package view;

/**
 * This interface represents File Listener which is used to send new specification file.
 */

public interface FileListener {
  /**
   * This method updates the file recieved from transmitter.
   * @param file refers to new file .
   */

  void update(String file);
}
