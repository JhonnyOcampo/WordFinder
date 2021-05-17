package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Jhonny Ocampo
 *
 */
public class WordFinder {

	private final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u', 'y' };
	private HashSet<String> wordsFound;
	private Dictionary dictionary;

	public WordFinder() {
		wordsFound = new HashSet<String>();
		dictionary = new Dictionary();
	}

	/**
	 * Find all English words in a given string. For example, if the string
	 * "working" is given, it will return the words "work", "king", "row", "ring",
	 * "know", among others.
	 * 
	 * Generates all possible subsets of the given string and these subsets must
	 * have at least one vowel. Then, it permutes each generated subset and
	 * validates if the subset is an English word. A collection of String is
	 * returned with all English words found.
	 * 
	 * @param word string used to find all subsets that are English words. Letters
	 *             can be lowercase or uppercase.
	 * @return a collection of String that contains all English words found without
	 *         duplicates. All generated words are lowercase. An empty collection is
	 *         returned if the parameter word is null or no English words are found.
	 */
	public Collection<String> findAllEnglishWords(String word) {
		wordsFound.clear();

		if (word == null || word.length() == 0) {
			return wordsFound;
		}

		String lowercaseWord = word.toLowerCase();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < lowercaseWord.length(); i++) {
			if (lowercaseWord.charAt(i) >= 'a' && lowercaseWord.charAt(i) <= 'z') {
				sb.append(lowercaseWord.charAt(i));
			}
		}
		String filteredWord = sb.toString();

		char[] arrWord = filteredWord.toCharArray();
		int maskOfVowels = 0;
		boolean found;

		for (int i = 0; i < arrWord.length; i++) {
			found = false;
			for (int j = 0; j < VOWELS.length && !found; j++) {
				if (arrWord[i] == VOWELS[j]) {
					maskOfVowels = maskOfVowels | (1 << i);
					found = true;
				}
			}
		}

		if (maskOfVowels == 0) {
			return wordsFound;
		}

		generateCombinations(arrWord, maskOfVowels);

		return wordsFound;
	}

	/**
	 * Generates all possible subsets of an array of characters and these subsets
	 * must have at least one vowel. For every subset generated, method
	 * generatePermutations(char[] word) is called.
	 * 
	 * @param word         array of characters to be combined.
	 * @param maskOfVowels a bitmask representing the positions of the vowels in the
	 *                     array of characters.
	 */
	private void generateCombinations(char[] word, int maskOfVowels) {
		int maxComb = (1 << word.length);
		for (int mask = 0; mask < maxComb; mask++) {
			if ((mask & maskOfVowels) != 0) {
				char[] subset = new char[word.length];
				int k = 0;

				for (int i = 0; i < word.length; i++) {
					if (((1 << i) & mask) != 0) {
						subset[k] = word[i];
						k++;
					}
				}

				generatePermutations(Arrays.copyOf(subset, k));
			}
		}
	}

	/**
	 * Generates all possible permutations of the characters in the given string.
	 * The Heap's algorithm is implemented. For every permutation generated, method
	 * processWord(String word) is called.
	 * 
	 * @param word array of characters to be permuted.
	 */
	private void generatePermutations(char[] word) {
		int n = word.length;
		int[] c = new int[n];

		processWord(new String(word));

		int i = 1;
		while (i < n) {
			if (c[i] < i) {
				if (i % 2 == 0) {
					swap(word, 0, i);
				} else {
					swap(word, c[i], i);
				}

				processWord(new String(word));

				c[i]++;
				i = 1;
			} else {
				c[i] = 0;
				i++;
			}
		}
	}

	/**
	 * Swap the character of position indexA for the character of position indexB in
	 * the array arrayToSwap.
	 * 
	 * @param arrayToSwap array of char to be swapped.
	 * @param indexA      index of the character to be swapped for the character of
	 *                    the index indexB.
	 * @param indexB      index of the character to be swapped for the character of
	 *                    the index indexA.
	 */
	private void swap(char[] arrayToSwap, int indexA, int indexB) {
		char temp = arrayToSwap[indexA];
		arrayToSwap[indexA] = arrayToSwap[indexB];
		arrayToSwap[indexB] = temp;
	}

	/**
	 * Call the method Dictionary.isEnglishWord(word) to validate if the given
	 * string is an English word. If so, then the word will be added in a HashSet
	 * that contains all the words found.
	 * 
	 * @param word string to be evaluated if it is an English word. If so, it will
	 *             be stored in a HashSet.
	 */
	private void processWord(String word) {
		if (dictionary.isEnglishWord(word)) {
			wordsFound.add(word);
		}
	}

}
