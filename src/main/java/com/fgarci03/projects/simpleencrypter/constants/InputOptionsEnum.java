package com.fgarci03.projects.simpleencrypter.constants;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by fgarcia on 06/02/2016.
 */
public enum InputOptionsEnum {

  ENCRYPT(InputOptionsShortOptions.ENCRYPT, InputOptionsLongOptions.ENCRYPT, true, "Encrypt a file (requires -u [public key])"),
  DECRYPT(InputOptionsShortOptions.DECRYPT, InputOptionsLongOptions.DECRYPT, true, "Decrypt a file (requires -r [private key])"),
  SIGN(InputOptionsShortOptions.SIGN, InputOptionsLongOptions.SIGN, true, "Sign a file (requires -r [private key])"),
  ENCRYPT_AND_SIGN(InputOptionsShortOptions.ENCRYPT_AND_SIGN, InputOptionsLongOptions.ENCRYPT_AND_SIGN, true, "Encrypt and Sign a file (requires -r [private key] & -u [public-key])"),
  CHECK_SIGNATURE(InputOptionsShortOptions.CHECK_SIGNATURE, InputOptionsLongOptions.CHECK_SIGNATURE, true, "Check a file's signature (requires -u [public key])"),
  CHECK_SIGNATURE_AND_DECRYPT(InputOptionsShortOptions.CHECK_SIGNATURE_AND_DECRYPT, InputOptionsLongOptions.CHECK_SIGNATURE_AND_DECRYPT, true, "Check a file's signature and decrypt it (requires -r [private key] & -u [public-key])"),
  GENERATE_KEY_PAIR(InputOptionsShortOptions.GENERATE_KEY_PAIR, InputOptionsLongOptions.GENERATE_KEY_PAIR, false, "Generate private/public key pair"),
  PRIVATE_KEY(InputOptionsShortOptions.PRIVATE_KEY, InputOptionsLongOptions.PRIVATE_KEY, true, "Private key"),
  PUBLIC_KEY(InputOptionsShortOptions.PUBLIC_KEY, InputOptionsLongOptions.PUBLIC_KEY, true, "Public key"),
  HELP(InputOptionsShortOptions.HELP, InputOptionsLongOptions.HELP, false, "Show this message");

  private String opt;
  private String longOpt;
  private boolean hasArg;
  private String description;

  InputOptionsEnum(String opt, String longOpt, boolean hasArg, String description) {
    this.opt = opt;
    this.longOpt = longOpt;
    this.hasArg = hasArg;
    this.description = description;
  }

  InputOptionsEnum(String opt, String longOpt, String description) {
    this.opt = opt;
    this.longOpt = longOpt;
    this.hasArg = false;
    this.description = description;
  }


  public String getOpt() {
    return opt;
  }

  public void setOpt(String opt) {
    this.opt = opt;
  }

  public String getLongOpt() {
    return longOpt;
  }

  public void setLongOpt(String longOpt) {
    this.longOpt = longOpt;
  }

  public boolean isHasArg() {
    return hasArg;
  }

  public void setHasArg(boolean hasArg) {
    this.hasArg = hasArg;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public static InputOptionsEnum getByLongOpt(String longOpt) {

    InputOptionsEnum inputOptionsEnum;
    switch (longOpt) {
      case InputOptionsLongOptions.ENCRYPT:
        inputOptionsEnum = ENCRYPT;
        break;
      case InputOptionsLongOptions.DECRYPT:
        inputOptionsEnum = DECRYPT;
        break;
      case InputOptionsLongOptions.SIGN:
        inputOptionsEnum = SIGN;
        break;
      case InputOptionsLongOptions.ENCRYPT_AND_SIGN:
        inputOptionsEnum = ENCRYPT_AND_SIGN;
        break;
      case InputOptionsLongOptions.CHECK_SIGNATURE:
        inputOptionsEnum = CHECK_SIGNATURE;
        break;
      case InputOptionsLongOptions.CHECK_SIGNATURE_AND_DECRYPT:
        inputOptionsEnum = CHECK_SIGNATURE_AND_DECRYPT;
        break;
      case InputOptionsLongOptions.GENERATE_KEY_PAIR:
        inputOptionsEnum = GENERATE_KEY_PAIR;
        break;
      case InputOptionsLongOptions.PRIVATE_KEY:
        inputOptionsEnum = PRIVATE_KEY;
        break;
      case InputOptionsLongOptions.PUBLIC_KEY:
        inputOptionsEnum = PUBLIC_KEY;
        break;
      case InputOptionsLongOptions.HELP:
        inputOptionsEnum = HELP;
        break;
      default:
        throw new NotImplementedException();
    }
    return inputOptionsEnum;
  }
}
