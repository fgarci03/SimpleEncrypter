package com.fgarci03.projects.simpleencrypter.api;

import com.fgarci03.projects.simpleencrypter.algorithm.rsa.AsymmetricKey;
import com.fgarci03.projects.simpleencrypter.algorithm.rsa.Algorithm;

/**
 * Created by fgarcia on 07/02/2016.
 */
public interface SimpleEncrypter {

  Algorithm generateKeyPair();

  byte[] encryptMessage(AsymmetricKey publicKey, byte[] message);

  byte[] decryptMessage(AsymmetricKey privateKey, byte[] message);

  byte[] signMessage(AsymmetricKey privateKey, byte[] message);

  byte[] checkMessageSignature(AsymmetricKey publicKey, byte[] message);

  byte[] encryptAndSignMessage(AsymmetricKey privateKey, AsymmetricKey publicKey, byte[] message);

  byte[] checkMessageSignatureAndDecrypt(AsymmetricKey privateKey, AsymmetricKey publicKey, byte[] message);
}
