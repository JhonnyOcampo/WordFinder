package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * It is a mock class that represents an English dictionary
 * 
 * @author jocampo
 *
 */
public class Dictionary {

	/**
	 * A HashSet that contains a collection of English words.
	 */
	private HashSet<String> dictionary;

	/**
	 * Constructs a dictionary 
	 */
	public Dictionary() {
		loadDictionary();
	}

	/**
	 * A mock method which returns if a string is an English word.
	 * 
	 * @param word string to be validated.
	 * @return true if it is an English word, return false otherwise.
	 */
	public boolean isEnglishWord(String word) {
		if (word == null || dictionary == null) {
			return false;
		}

		return dictionary.contains(word);
	}

	/**
	 * Reads a file that contains a list of English words and loads these words in
	 * the HashSet dictionary.
	 * 
	 */
	private void loadDictionary() {
		try {
			dictionary = new HashSet<String>();
			File file = new File("english_words.txt");

			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null) {
				dictionary.add(st);
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
