package com.fgarci03.projects.simpleencrypter.constants;

/**
 * Created by fgarcia on 07/02/2016.
 */
public class InputOptionsLongOptions {

  public static final String ENCRYPT = "encrypt";
  public static final String DECRYPT = "decrypt";
  public static final String SIGN = "sign";
  public static final String ENCRYPT_AND_SIGN = "encrypt_sign";
  public static final String CHECK_SIGNATURE = "check";
  public static final String CHECK_SIGNATURE_AND_DECRYPT = "check_decrypt";
  public static final String GENERATE_KEY_PAIR = "generate";
  public static final String PRIVATE_KEY = "private-key";
  public static final String PUBLIC_KEY = "public-key";
  public static final String HELP = "help";

  private InputOptionsLongOptions(){
    throw new AssertionError();
  }
}
