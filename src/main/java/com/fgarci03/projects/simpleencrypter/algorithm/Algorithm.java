package com.fgarci03.projects.simpleencrypter.algorithm.rsa;

import com.fgarci03.projects.simpleencrypter.constants.Cryptography;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by fgarcia on 06/02/2016.
 */
public class Algorithm {

  private AsymmetricKey privateKey;
  private AsymmetricKey publicKey;

  public Algorithm() {
    Random r = new Random();

    BigInteger p = BigInteger.probablePrime(Cryptography.BIT_LENGHT, r);
    BigInteger q = BigInteger.probablePrime(Cryptography.BIT_LENGHT, r);
    BigInteger N = p.multiply(q);

    BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    BigInteger e = BigInteger.probablePrime(Cryptography.BIT_LENGHT / 2, r);

    while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
      e.add(BigInteger.ONE);
    }
    BigInteger d = e.modInverse(phi);

    this.privateKey = new AsymmetricKey(d, N);
    this.publicKey = new AsymmetricKey(e, N);
  }

  public Algorithm(BigInteger d, BigInteger e, BigInteger N) {
    this.privateKey = new AsymmetricKey(d, N);
    this.publicKey = new AsymmetricKey(e, N);
  }


  public byte[] encrypt(byte[] message) {
    return encryptOrDecryptMessage(message, this.publicKey);
  }

  public byte[] decrypt(byte[] message) {
    return encryptOrDecryptMessage(message, this.privateKey);
  }

  public byte[] sign(byte[] message) {
    return encryptOrDecryptMessage(message, this.privateKey);
  }

  public byte[] checkMessageSignature(byte[] message) {
    return encryptOrDecryptMessage(message, this.publicKey);
  }

  public byte[] encryptAndSign(byte[] message) {
    return encryptOrDecryptAndSignMessage(message);
  }

  public byte[] checkMessageSignatureAndDecrypt(byte[] message) {
    return encryptOrDecryptAndSignMessage(message);
  }


  public AsymmetricKey getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(AsymmetricKey privateKey) {
    this.privateKey = privateKey;
  }

  public AsymmetricKey getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(AsymmetricKey publicKey) {
    this.publicKey = publicKey;
  }


  private byte[] encryptOrDecryptMessage(byte[] message, AsymmetricKey key) {
    return (new BigInteger(message)).modPow(key.getKey(), key.getN()).toByteArray();
  }

  private byte[] encryptOrDecryptAndSignMessage(byte[] message) {
    byte[] encryptedOrDecryptedMessage = encryptOrDecryptMessage(message, this.publicKey);
    return encryptOrDecryptMessage(encryptedOrDecryptedMessage, this.privateKey);
  }
}
