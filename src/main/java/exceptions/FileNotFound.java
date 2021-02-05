package exceptions;

import java.io.FileNotFoundException;

public class FileNotFound extends FileNotFoundException {
  public FileNotFound(String s, String fileName) {
    super(String.format(s, fileName));
  }
}
