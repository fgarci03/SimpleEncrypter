package com.fgarci03.projects.simpleencrypter.algorithm.rsa;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by fgarcia on 06/02/2016.
 */
public class AsymmetricKey implements Serializable {

  private static final long serialVersionUID = 730851257729056108L;
  private BigInteger key;
  private BigInteger N;

  public AsymmetricKey(BigInteger key, BigInteger N) {
    this.key = key;
    this.N = N;
  }

  public BigInteger getKey() {
    return key;
  }

  public void setKey(BigInteger key) {
    this.key = key;
  }

  public BigInteger getN() {
    return N;
  }

  public void setN(BigInteger n) {
    this.N = n;
  }
}
