import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Sha2Hashing {

	
	public ArrayList<String> fileRead(String filePath) throws IOException {
		FileReader in = null;
		BufferedReader read = null;
		ArrayList<String> listOfWords = new ArrayList<String>();
		try {
			in = new FileReader(filePath);
			read = new BufferedReader(in);

			String line;
			while ((line = read.readLine()) != null) {
				String[] wordsInLine = line.split(" ");
				for (String string : wordsInLine) {
					listOfWords.add(string);
				}
			}
		} finally {
			if (in != null) {
				in.close();
			}

		}
		return listOfWords;
	}

	public String sha2Hashing(String text) {
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(text);
		return sha256hex;
	}


	public Boolean writeToFile(String filePath, ArrayList<String> listOfWords) {
		try {
		File file = new File(filePath);

		// creates the file
		file.createNewFile();

		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		Iterator<String> itr = listOfWords.iterator();
		while (itr.hasNext()) {
			String word = itr.next();
			String hashedWord = sha2Hashing(word);
			writer.write(hashedWord);
			writer.write("\n");
		}

		// Writes the content to the file

		writer.flush();
		writer.close();
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return false;
	}

	public static void main(String args[]) throws IOException {
		Sha2Hashing obj = new Sha2Hashing();
		ArrayList<String> listOfWords = obj.fileRead("emails.txt");
		obj.writeToFile("result.txt", listOfWords);
	}
}
