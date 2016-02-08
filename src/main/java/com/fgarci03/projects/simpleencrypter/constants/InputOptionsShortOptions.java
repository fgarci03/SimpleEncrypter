package com.fgarci03.projects.simpleencrypter.constants;

/**
 * Created by fgarcia on 07/02/2016.
 */
public class InputOptionsShortOptions {

  public static final String ENCRYPT = "e";
  public static final String DECRYPT = "d";
  public static final String SIGN = "s";
  public static final String ENCRYPT_AND_SIGN = "t";
  public static final String CHECK_SIGNATURE = "c";
  public static final String CHECK_SIGNATURE_AND_DECRYPT = "z";
  public static final String GENERATE_KEY_PAIR = "g";
  public static final String PRIVATE_KEY = "r";
  public static final String PUBLIC_KEY = "u";
  public static final String HELP = "h";

  private InputOptionsShortOptions(){
    throw new AssertionError();
  }
}
