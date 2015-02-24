import java.io.*;
import java.util.*;

public class Validator {
	
	private int size;
	
	public Validator(int size) {
		this.size = size;
	}
	
	/**
	 * Informative Message
	 * 
	 * and error report
	 */
	public void ErrorReport(String msg) {
		System.out.println("\nUsage: java SimpleEncrypter <password> <option> <file>\n");
		System.out.println("<password>\t" + size + " UNIQUE characters");
		System.out.println("\t\tMay contain a space, but the argument must be wrapped in \"\".");
		System.out.println("\t\tMay contain a '\"', but needs to have a \\ before");
		System.out.println("\t\tMay not contain a '!'");
		System.out.println("\t\tex. as\\\"e4P will convert to as\"e4P");
		System.out.println("\n<option>\tencrypt, decrypt");
		System.out.println("<file>\t\tName of the input file");
		System.out.println("\nhelp\t\tDisplay this message");
		System.out.println("\n\n\n***\n*** " + msg + "\n***");
		System.exit(0);
	}
	
	/**
	 * Validate the number of arguments
	 */
	public void NumOfArguments(String[] args) {
		int numArgs = 3;
		
		if(args.length != numArgs) {
			String errMsg = "Invalid number of arguments (should be " + numArgs + ", but is " + args.length + ").";
			ErrorReport(errMsg);
		}
	}

	/**
	 * Password Validator
	 * 
	 * - Validates the length (must be the same as 'dicSize'
	 * - Validates if all the characters are unique(*)
	 * 
	 * 			(*) - Duplicates the String password and
	 * 				  compares it character by character
	 */
	public void Password(String password, int dicSize) {
		// Size of password validation
		if(password.length() != dicSize) {
			String errMsg = "Invalid password size (should be " + dicSize + ",\n*** but is " + password.length() + " characters long).";
			ErrorReport(errMsg);
		} else {
			// Unique characters validation
			int count = 0;

			for(int i = 0; i < password.length(); i++) {
				for(int j = i + 1; j < password.length(); j++) {
					if(password.charAt(i) == password.charAt(j)) {
						count++;
						if(count > 0) {
							String errMsg = "Character '" + password.charAt(j) + "' is repeated in password.";
							ErrorReport(errMsg);
						}
					}
				}
			}
		}
	}

	/**
	 * Validate the chosen option
	 */
	public void Option(String option) {
		if(!option.equals("encrypt") && !option.equals("decrypt")) {
			String errMsg = "Invalid option (only 'encrypt' or 'decrypt' allowed)!";
			ErrorReport(errMsg);
		}
	}
	
	/**
	 * Validate the file to Decrypt
	 * 
	 * by checking if the file is empty, and the number
	 * and type of characters present on the file.
	 */
	public void DecFile(String password, ArrayList<String> fcontents) {
		int count = 0;
		
		/**
		 * Check if the file is empty
		 * 
		 * by checking if there is only 1 line (an empty file
		 * still has 1 'line' of text), and checking if that
		 * line is empty
		 * 
		 * fcontents.size() 			=> number of lines
		 * fcontents.get(0).length()	=> number of characters
		 * 								   on that line
		 */
		if(fcontents.size() == 1 && fcontents.get(0).length() == 0) {
			String errMsg = "Input file is empty!";
			ErrorReport(errMsg);
		}
		
		/**
		 * Check if the number of characters is even (an
		 * encrypted file always have an even number of
		 * chars).
		 * 
		 * This is an unorthodox way to do it, because I
		 * want to ignore the newline chars on the input
		 * (although they are encrypted by default, if they
		 * are present on the decryption process, they will
		 * be ignored).
		 * Coverting the ArrayList into a String and counting
		 * everything will return unexpected results because
		 * of this.
		 * 
		 * The solution is to count the characters on every
		 * element of the ArrayList (every line) and sum them.
		 * Then, check if the result is even. If it is, that
		 * means the total number of chars (excluding newline
		 * chars) is even.
		 */
		int even = 0;
		
		// Count characters on each line
		for(int i = 0; i < fcontents.size(); i++) {
			// Sum the results
			even += fcontents.get(i).length();
		}
		
		// Check if the total is even
		if(!(even % 2 == 0)) {
			String errMsg = "There's an odd number of chars in your file.\n*** Is it the encrypted version?";
			ErrorReport(errMsg);
		}
		
		/**
		 * Check if every character in the file is present
		 * on the password.
		 * 
		 * It goes through every element of 'fcontents' and
		 * checks it against every character of the password.
		 */
		boolean found = false;
		String cont = "";
		
		// Go through every line
		for(int j = 0; j < fcontents.size(); j++) {
			String content = fcontents.get(j);
			
			// Go through every character of the password
			for(int i = 0; i < password.length(); i++) {
				// Go to every character of the line (the one being evaluated)
				for(int z = 0; z < content.length(); z++) {
					
					// If all the chars exist in the
					// password, it evaluates to true
					cont = String.valueOf(content.charAt(z));
					found = password.contains(cont);
					
					if(!found) {
						String errMsg = "Character '" + cont + "' doesn't exist in the password!\n*** Impossible to Decrypt.";
						ErrorReport(errMsg);
					}
				}
			}
		}
	}

	/**
	 * Validate the input file
	 */
	public void File(String file) {
		File f = new File(file);
		if(!f.exists()) {
			String errMsg = "File does not exist!";
			ErrorReport(errMsg);
		} else if(f.isDirectory()) {
			String errMsg = "File is a directory!";
			ErrorReport(errMsg);
		}
	}
}
