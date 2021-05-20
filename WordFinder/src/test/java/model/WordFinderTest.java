package model;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class WordFinderTest {

	private WordFinder wordFinder = new WordFinder();

	@Test
	void nullEntry() {
		assertThat(wordFinder.findAllEnglishWords(null)).hasSize(0);
	}

	@Test
	void doesNotContainEnglishWords() {
		assertThat(wordFinder.findAllEnglishWords("qwrtpsdfgh")).hasSize(0);
	}

	@Test
	void doesNotContainDuplicates() {
		assertThat(wordFinder.findAllEnglishWords("eye")).containsOnlyOnce("eye");
	}

	@Test
	void inputWithSpecialCharacters() {
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(wordFinder.findAllEnglishWords("!#$%&/()=?¡")).hasSize(0);
		softly.assertThat(wordFinder.findAllEnglishWords("W#o$R%k*In(G"))
				.contains("working", "work", "king", "row", "ring", "know", "wrong", "or", "go", "ok", "in")
				.doesNotContain("wrkng", "rk");
		softly.assertAll();
	}

	@Test
	void stringWithSpaces() {
		assertThat(wordFinder.findAllEnglishWords("a nice day"))
				.contains("a", "nice", "day", "ace", "dice", "ice", "dance", "icy", "an", "aid", "idea", "can", "any")
				.doesNotHaveDuplicates();
	}

	@Test
	void oneLetterWord() {
		assertThat(wordFinder.findAllEnglishWords("a")).contains("a");
	}

	@Test
	void twoLetterWord() {
		assertThat(wordFinder.findAllEnglishWords("an")).contains("a", "an");
	}

	@Test
	void sevenLetterWord() {
		Collection<String> expectedWords = Set.of("working", "work", "king", "row", "ring", "know", "wrong", "or", "go",
				"ok", "in");
		Collection<String> unexpectedWords = Set.of("wrkng", "rk");
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(wordFinder.findAllEnglishWords("WORKING")).containsAll(expectedWords)
				.doesNotContainAnyElementsOf(unexpectedWords);
		softly.assertThat(wordFinder.findAllEnglishWords("working")).containsAll(expectedWords)
				.doesNotContainAnyElementsOf(unexpectedWords);
		softly.assertAll();
	}

	@Test
	void nineLetterWord() {
		assertThat(wordFinder.findAllEnglishWords("excellent"))
				.contains("next", "cent", "tell", "let", "net", "teen", "excel", "excellent")
				.doesNotContain("xllt", "le").doesNotHaveDuplicates();
	}

	@Test
	void twelveLetterWord() {
		assertThat(wordFinder.findAllEnglishWords("hippopotamus"))
				.contains("ship", "path", "mouth", "shampoo", "hippopotamus", "spam", "autism", "oath", "atom")
				.doesNotHaveDuplicates();
	}

}
