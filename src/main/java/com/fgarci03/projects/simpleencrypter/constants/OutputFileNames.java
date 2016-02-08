package com.fgarci03.projects.simpleencrypter.constants;

/**
 * Created by fgarcia on 06/02/2016.
 */
public class OutputFileNames {

  public static final String ENCRYPTED = "encrypted.txt";
  public static final String DECRYPTED = "decrypted.txt";
  public static final String SIGNED = "signed.txt";
  public static final String ENCRYPTED_AND_SIGNED = "encrypted_signed.txt";
  public static final String UNSIGNED_AND_DECRYPTED = "unsigned_decrypted.txt";
  public static final String PRIVATE_KEY = "private.key";
  public static final String PUBLIC_KEY = "public.key";

  private OutputFileNames() {
    throw new AssertionError();
  }
}
