package com.fgarci03.projects.simpleencrypter.utils;

import java.io.*;

/**
 * Created by fgarcia on 06/02/2016.
 */
public class Serialization {

  public Serialization() {
  }

  public static byte[] serialize(Object obj) throws IOException {
    byte[] serializedKey = null;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out = null;

    try {
      out = new ObjectOutputStream(bos);
      out.writeObject(obj);
      serializedKey = bos.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return serializedKey;
  }

  public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
    ByteArrayInputStream bis = new ByteArrayInputStream(data);
    ObjectInput in = null;
    Object obj = null;

    try {
      in = new ObjectInputStream(bis);
      obj = in.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        bis.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        if (in != null) {
          in.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return obj;
  }
}
