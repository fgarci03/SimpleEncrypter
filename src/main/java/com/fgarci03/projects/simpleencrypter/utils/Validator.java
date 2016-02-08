package com.fgarci03.projects.simpleencrypter.utils;

import java.io.File;

public class Validator {

  public Validator() {
  }

  public static void validateInputFile(String fileName) {
    File file = new File(fileName);
    Cli cli = new Cli();

    if (!file.exists()) {
      cli.error("File '" + fileName + "' does not exist!");
    } else if (file.isDirectory()) {
      cli.error("'" + fileName + "' is a directory!");
    } else if (!file.canRead()) {
      cli.error("'" + fileName + "' has no read permissions!");
    } else if (!file.canWrite()) {
      cli.error("'" + fileName + "' has no write permissions!");
    }
  }
}
