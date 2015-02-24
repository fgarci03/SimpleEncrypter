import java.util.*;

public class Algorithm {
	
	private char[][] dic;
	private char[] pwd;
	private int size;
	private String password;
	
	public Algorithm(int size, String password) {
		this.size = size;
		this.password = password;
		pwd = password.toCharArray();
				
		// Create the dictionary automatically
		dic = Dictionary();
	}
	
	/**
	 * Encrypt
	 */
	public String Encrypt(ArrayList<String> fcontents) {
		String content = convertToString(fcontents);
		String encrypted = "";
		String coord;
		int mid;
		
		// 'content.length() - 1' because the last
		// character is ALWAYS '\n' so we ignore it
		for(int i = 0; i < content.length() - 1; i++) {
			mid = encrypted.length() / 2;
			// Get character coordinates
			coord = findCoord(content.charAt(i));
			if(coord == null) {
				// Exceptionally, if some character is unreadable (or some
				// coordinate fails to be created, this warns the user so 
				// no 'undecryptable' messages are generated
				Validator valid = new Validator(size);
				String errMsg = "There is an unknown character in your text file.\n*** It stoped execution at his point:\n" + content.substring(0, i + 1) + "\n\n*** Please use another character.";
				valid.ErrorReport(errMsg);
			}
			// Insert 'coord' in the middle of 'encrypted'
			if(mid > 0)
				encrypted = encrypted.substring(0, mid) + "" + coord + "" + encrypted.substring(mid);
			else encrypted = coord;
		}
		
		return encrypted;
	}
	
	/**
	 * Decrypt
	 */
	public String Decrypt(ArrayList<String> fcontents) {
		String decrypted = "";
		char firstChar, lastChar;
		// Read each line of fcontents
		for (String s: fcontents) {
			// Get the first and last chars, until the
			// 'first and last positions meet'
			for(int i = 0; i < (s.length() / 2); i++) {
				// First char at 'i'
				firstChar = s.charAt(i);
				// Last char at 'length - i - 1'
				lastChar = s.charAt(s.length() - i - 1);
				
				// Append the characters to the end of 'decrypted'
				decrypted += findChar(firstChar, lastChar);
			}
		}
		
		return decrypted;
	}
	
	/**
	 * Generate Dictionary
	 * 
	 * 16x16 array of the ASCII table
	 */
	private char[][] Dictionary() {
		// All 255 ASCII characters are
		// used if the dicSize is 16
		dic = new char[size][size];
		int count = 0;
		
		for(int i = 0; i < size; i++) {
			// From 0 to 255: (char)ASCI_NUM prints
			// the corresponding character
			for(int j = 0; j < size; j++) {
				dic[i][j] = (char)count;
				count++;
			}
		}
		return dic;
	}
	
	/**
	 * Print the Dictionary
	 * 
	 * (not used in the program, but is available
	 * for who wants to hack away =)
	 */
	public void printDictionary() {
		for(int i = 0; i < size; i++) {
			System.out.println();
			for(int j = 0; j < size; j++) {
				System.out.print(dic[i][j] + ", ");
			}
		}
	}
	
	/**
	 * Convert ArrayList<String> to String
	 */
	private String convertToString(ArrayList<String> arr) {
		// Convert the ArrayList into a String
		String contentString = "";
		for (String s: arr) {
			contentString += s + "\n";
		}
		
		return contentString;
	}
	
	/**
	 * Get Coordinates of the chars on the Dictionary
	 */
	private String findCoord(char ch) {
		int count = 0;
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				// Test each char with the dictionary
				if(dic[i][j] == ch) {
					// When they match, return the coordinates as a String
					String coord = String.valueOf(pwd[j]) + String.valueOf(pwd[i]);
					return coord;
				}
				count++;
			}
		}
		return null;
	}
	
	/**
	 * Get character in Dictionary from coordinates
	 * 
	 * The array is read 'columns first' instead of
	 * lines first, that's why you need to switch 'i'
	 * with 'j', getting 'pwdI = j' and 'pwdJ = i'.
	 */
	private char findChar(char first, char last) {
		int pwdI = 0;
		int pwdJ = 0;
		
		// Get numeric position of the characters in the password
		for(int i = 0; i < pwd.length; i++) {
			// When you find the first wanted character...
			if(pwd[i] == first) {
				for(int j = 0; j < pwd.length; j++) {
					// When you find the second wanted character...
					if(pwd[j] == last) {
						pwdI = j;
						break;
					}
				}
				pwdJ = i;
			}
		}
		
		return dic[pwdI][pwdJ];
	}
}
