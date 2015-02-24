import java.io.*;
import java.util.*;

public class IO {
	
	public IO() {}

	/**
	 * Read the input file
	 * 
	 * and store it's contents to the ArrayList 'fcontents'
	 */
	public static ArrayList<String> ReadFile(String file) {
		ArrayList<String> fcontents = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
 			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				fcontents.add(sCurrentLine);
			}
 		} catch (IOException e) {
			e.printStackTrace();
		}
		return fcontents;
	}
	
	/**
	 * Write the output to file "output.txt"
	 */
	public static String Output(String result) {
		String outputName = "output.txt";
		FileOutputStream fop = null;
		File file;
 
		try {
 			file = new File(outputName);
			fop = new FileOutputStream(file);
 
			// If file doesn't exist, create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			// Get the content in bytes
			byte[] contentInBytes = result.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return outputName;
	}
}
