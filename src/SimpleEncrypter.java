import java.util.*;

public class SimpleEncrypter {

	private static int dicSize = 16; // dicSize...
	private static String result;
	
	/**
	* @author	Filipe Garcia
	* 			http://filipe-garcia.com
	* 			me@filipe-garcia.com
	* 			@fgarci03
	*/
	public static void main(String[] args) {
		// Instanciate all the classes
		IO io = new IO();
		Validator valid = new Validator(dicSize);
		
		// Validate and assign arguments
		valid.NumOfArguments(args);
		String password = args[0];
		String option = args[1];
		String file = args[2];

		// Validate password and option
		valid.Password(password, dicSize);
		valid.Option(option);

		// Validate and read file
		valid.File(file);
		ArrayList<String> fcontents = io.ReadFile(file);		
		
		// Generate the dictionary automatically
		Algorithm cypher = new Algorithm(dicSize, password);
		
		// Preform the (de)encryption
		if(option.equals("decrypt")) {
			// File contents validation is only needed for decryption
			valid.DecFile(password, fcontents);
			result = cypher.Decrypt(fcontents);
		} else {
			result = cypher.Encrypt(fcontents);
		}
		
		// Output the result to file
		String outputName = io.Output(result);
		System.out.println("\nFile " + option.substring(0, 2) + "crypted with password '" + password + "' and saved as '" + outputName + "'!");
	}
}
