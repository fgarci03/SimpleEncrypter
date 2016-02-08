package com.fgarci03.projects.simpleencrypter.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class IO {

  public IO() {
  }

  public static byte[] getFileContents(String fileName) throws IOException {
    byte[] data = null;

    try {
      data = FileUtils.readFileToByteArray(new File(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return data;
  }

  public static void writeOutputFile(byte[] content, String outputFileName) throws IOException {
    try {
      FileUtils.writeByteArrayToFile(new File(outputFileName), content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
