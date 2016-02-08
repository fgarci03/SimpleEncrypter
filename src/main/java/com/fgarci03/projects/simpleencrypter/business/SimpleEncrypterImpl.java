package com.fgarci03.projects.simpleencrypter.business;

import com.fgarci03.projects.simpleencrypter.algorithm.rsa.AsymmetricKey;
import com.fgarci03.projects.simpleencrypter.algorithm.rsa.Algorithm;
import com.fgarci03.projects.simpleencrypter.api.SimpleEncrypter;

/**
 * Created by fgarcia on 07/02/2016.
 */
public class SimpleEncrypterImpl implements SimpleEncrypter {

  @Override
  public Algorithm generateKeyPair() {
    return new Algorithm();
  }

  @Override
  public byte[] encryptMessage(AsymmetricKey publicKey, byte[] message) {
    Algorithm algorithm = new Algorithm(null, publicKey.getKey(), publicKey.getN());
    return algorithm.encrypt(message);
  }

  @Override
  public byte[] decryptMessage(AsymmetricKey privateKey, byte[] message) {
    Algorithm algorithm = new Algorithm(privateKey.getKey(), null, privateKey.getN());
    return algorithm.decrypt(message);
  }

  @Override
  public byte[] signMessage(AsymmetricKey privateKey, byte[] message) {
    Algorithm algorithm = new Algorithm(privateKey.getKey(), null, privateKey.getN());
    return algorithm.sign(message);
  }

  @Override
  public byte[] checkMessageSignature(AsymmetricKey publicKey, byte[] message) {
    Algorithm algorithm = new Algorithm(null, publicKey.getKey(), publicKey.getN());
    return algorithm.checkMessageSignature(message);
  }

  @Override
  public byte[] encryptAndSignMessage(AsymmetricKey privateKey, AsymmetricKey publicKey, byte[] message) {
    Algorithm algorithm = new Algorithm(privateKey.getKey(), publicKey.getKey(), privateKey.getN());
    return algorithm.encryptAndSign(message);
  }

  @Override
  public byte[] checkMessageSignatureAndDecrypt(AsymmetricKey privateKey, AsymmetricKey publicKey, byte[] message) {
    Algorithm algorithm = new Algorithm(privateKey.getKey(), publicKey.getKey(), privateKey.getN());
    return algorithm.checkMessageSignatureAndDecrypt(message);
  }
}
