package control;

import java.util.Collection;
import java.util.Scanner;
import model.WordFinder;

public class Main {

	public static void main(String[] args) {
		System.out.println("Type a string to find all the English words it contains:");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		sc.close();		
		WordFinder wordFinder = new WordFinder();
		Collection<String> words = wordFinder.findAllEnglishWords(word);
		for (String englishWord : words) {
			System.out.println(englishWord);
		}
	}
	
}
