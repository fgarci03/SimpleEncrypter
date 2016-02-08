package com.fgarci03.projects.simpleencrypter;

import com.fgarci03.projects.simpleencrypter.algorithm.rsa.AsymmetricKey;
import com.fgarci03.projects.simpleencrypter.algorithm.rsa.Algorithm;
import com.fgarci03.projects.simpleencrypter.business.SimpleEncrypterImpl;
import com.fgarci03.projects.simpleencrypter.constants.InputOptionsLongOptions;
import com.fgarci03.projects.simpleencrypter.constants.InputOptionsShortOptions;
import com.fgarci03.projects.simpleencrypter.constants.OutputFileNames;
import com.fgarci03.projects.simpleencrypter.utils.Cli;
import com.fgarci03.projects.simpleencrypter.utils.IO;
import com.fgarci03.projects.simpleencrypter.utils.Serialization;
import com.fgarci03.projects.simpleencrypter.utils.Validator;
import org.apache.commons.cli.CommandLine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

public class SimpleEncrypter {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Cli cli = new Cli(args);
    CommandLine cmd = cli.parse();

    if (cmd.hasOption(InputOptionsLongOptions.ENCRYPT)) {
      applyCryptographyOperation(cmd.getOptionValue(InputOptionsShortOptions.ENCRYPT), cmd.getOptionValue(InputOptionsShortOptions.PUBLIC_KEY), InputOptionsLongOptions.ENCRYPT);

    } else if (cmd.hasOption(InputOptionsLongOptions.DECRYPT)) {
      applyCryptographyOperation(cmd.getOptionValue(InputOptionsShortOptions.DECRYPT), cmd.getOptionValue(InputOptionsShortOptions.PRIVATE_KEY), InputOptionsLongOptions.DECRYPT);

    } else if (cmd.hasOption(InputOptionsLongOptions.SIGN)) {
      applyCryptographyOperation(cmd.getOptionValue(InputOptionsShortOptions.SIGN), cmd.getOptionValue(InputOptionsShortOptions.PRIVATE_KEY), InputOptionsLongOptions.SIGN);

    } else if (cmd.hasOption(InputOptionsLongOptions.ENCRYPT_AND_SIGN)) {
      applyCryptographyOperation(cmd.getOptionValue(InputOptionsShortOptions.ENCRYPT_AND_SIGN), cmd.getOptionValue(InputOptionsShortOptions.PRIVATE_KEY), cmd.getOptionValue(InputOptionsShortOptions.PUBLIC_KEY), InputOptionsLongOptions.ENCRYPT_AND_SIGN);

    } else if (cmd.hasOption(InputOptionsLongOptions.CHECK_SIGNATURE)) {
      applyCryptographyOperation(cmd.getOptionValue(InputOptionsShortOptions.CHECK_SIGNATURE), cmd.getOptionValue(InputOptionsShortOptions.PUBLIC_KEY), InputOptionsLongOptions.CHECK_SIGNATURE);

    } else if (cmd.hasOption(InputOptionsLongOptions.CHECK_SIGNATURE_AND_DECRYPT)) {
      applyCryptographyOperation(cmd.getOptionValue(InputOptionsShortOptions.CHECK_SIGNATURE_AND_DECRYPT), cmd.getOptionValue(InputOptionsShortOptions.PRIVATE_KEY), cmd.getOptionValue(InputOptionsShortOptions.PUBLIC_KEY), InputOptionsLongOptions.CHECK_SIGNATURE_AND_DECRYPT);

    } else if (cmd.hasOption(InputOptionsLongOptions.GENERATE_KEY_PAIR)) {
      generateAsymmetricKeyPair();

    } else if (cmd.hasOption(InputOptionsLongOptions.HELP)) {
      cli.help();

    } else {
      cli.error();
    }

    System.exit(0);
  }


  private static void generateAsymmetricKeyPair() throws IOException {
    com.fgarci03.projects.simpleencrypter.api.SimpleEncrypter simpleEncrypter = new SimpleEncrypterImpl();
    Algorithm algorithm = simpleEncrypter.generateKeyPair();

    IO.writeOutputFile(Serialization.serialize(algorithm.getPrivateKey()), OutputFileNames.PRIVATE_KEY);
    IO.writeOutputFile(Serialization.serialize(algorithm.getPublicKey()), OutputFileNames.PUBLIC_KEY);
    System.out.println("\nKeypair generated!");
  }

  private static void applyCryptographyOperation(String file, String keyFile, String option) throws IOException, ClassNotFoundException {
    com.fgarci03.projects.simpleencrypter.api.SimpleEncrypter simpleEncrypter = new SimpleEncrypterImpl();

    Validator.validateInputFile(file);
    Validator.validateInputFile(keyFile);

    byte[] fileContents = IO.getFileContents(file);
    byte[] keyFileContents = IO.getFileContents(keyFile);
    AsymmetricKey key = (AsymmetricKey) Serialization.deserialize(keyFileContents);

    switch (option) {
      case InputOptionsLongOptions.ENCRYPT:
        byte[] encryptedContent = simpleEncrypter.encryptMessage(key, fileContents);
        cryptographyOutputFile(encryptedContent, "encrypted", OutputFileNames.ENCRYPTED);
        break;

      case InputOptionsLongOptions.DECRYPT:
        byte[] decryptedContent = simpleEncrypter.decryptMessage(key, fileContents);
        cryptographyOutputFile(decryptedContent, "decrypted", OutputFileNames.DECRYPTED);
        break;

      case InputOptionsLongOptions.SIGN:
        byte[] signedContent = simpleEncrypter.signMessage(key, fileContents);
        cryptographyOutputFile(signedContent, "signed", OutputFileNames.SIGNED);
        break;

      case InputOptionsLongOptions.CHECK_SIGNATURE:
        byte[] unsignedContent = simpleEncrypter.checkMessageSignature(key, fileContents);
        cryptographyOutputFile(unsignedContent, "signed", OutputFileNames.SIGNED);
        break;

      default:
        throw new NotImplementedException();
    }
  }

  private static void applyCryptographyOperation(String file, String privateKeyFile, String publicKeyFile, String option) throws IOException, ClassNotFoundException {
    com.fgarci03.projects.simpleencrypter.api.SimpleEncrypter simpleEncrypter = new SimpleEncrypterImpl();

    Validator.validateInputFile(file);
    Validator.validateInputFile(privateKeyFile);
    Validator.validateInputFile(publicKeyFile);

    byte[] fileContents = IO.getFileContents(file);
    byte[] privateKeyFileContents = IO.getFileContents(privateKeyFile);
    byte[] publicKeyFileContents = IO.getFileContents(publicKeyFile);

    AsymmetricKey privateKey = (AsymmetricKey) Serialization.deserialize(privateKeyFileContents);
    AsymmetricKey publicKey = (AsymmetricKey) Serialization.deserialize(publicKeyFileContents);

    switch (option) {
      case InputOptionsLongOptions.ENCRYPT_AND_SIGN:
        byte[] encryptedAndSignedContent = simpleEncrypter.encryptAndSignMessage(privateKey, publicKey, fileContents);
        cryptographyOutputFile(encryptedAndSignedContent, "encrypted and signed,", OutputFileNames.ENCRYPTED_AND_SIGNED);
        break;

      case InputOptionsLongOptions.CHECK_SIGNATURE_AND_DECRYPT:
        byte[] unsignedAndDecryptedContent = simpleEncrypter.checkMessageSignatureAndDecrypt(privateKey, publicKey, fileContents);
        cryptographyOutputFile(unsignedAndDecryptedContent, "unsigned and decrypted,", OutputFileNames.UNSIGNED_AND_DECRYPTED);
        break;

      default:
        throw new NotImplementedException();
    }
  }

  private static void cryptographyOutputFile(byte[] content, String action, String outputFileName) throws IOException {
    IO.writeOutputFile(content, outputFileName);
    System.out.println("\nFile " + action + " and saved as '" + outputFileName + "'!");
  }
}
