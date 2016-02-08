package com.fgarci03.projects.simpleencrypter.utils;

import com.fgarci03.projects.simpleencrypter.constants.InputOptionsEnum;
import org.apache.commons.cli.*;

/**
 * Created by fgarcia on 07/02/2016.
 */
public class Cli {
  private String[] args = null;
  private Options options = new Options();

  public Cli(String[] args) {
    this.args = args;
    generateOptions();
  }

  public Cli() {
    generateOptions();
  }

  public CommandLine parse() {
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = null;

    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      error();
    }

    return cmd;
  }

  public void help() {
    HelpFormatter formmater = new HelpFormatter();

    formmater.printHelp("SimpleEncrypter [[-edstcz] <filename> [-ru] <key-file>] [-gh]", options);
    // todo: example usage
    System.exit(0);
  }

  public void error() {
    System.out.println("No valid options found!\n");
    help();
  }

  public void error(String message) {
    System.out.println(message);
    help();
  }

  private void generateOptions() {
    for (InputOptionsEnum inputOptionsEnum : InputOptionsEnum.values()) {
      options.addOption(buildOption(inputOptionsEnum.getLongOpt()));
    }
  }

  private Option buildOption(String option) {
    String opt = InputOptionsEnum.getByLongOpt(option).getOpt();
    String longOpt = InputOptionsEnum.getByLongOpt(option).getLongOpt();
    boolean hasArg = InputOptionsEnum.getByLongOpt(option).isHasArg();
    String description = InputOptionsEnum.getByLongOpt(option).getDescription();

    return new Option(opt, longOpt, hasArg, description);
  }
}
