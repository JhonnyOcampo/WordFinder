package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

class WordFinderTest {

	private WordFinder wordFinder = new WordFinder();
	

	@Test
	void testFindAllEnglishWords() {
		
		Collection<String> result1 = wordFinder.findAllEnglishWords("WORKING");
		assertTrue(result1.contains("working"));
		assertTrue(result1.contains("work"));
		assertTrue(result1.contains("king"));
		assertTrue(result1.contains("row"));
		assertTrue(result1.contains("ring"));
		assertTrue(result1.contains("know"));	
		assertTrue(result1.contains("wrong"));
		assertTrue(result1.contains("or"));
		assertTrue(result1.contains("go"));
		assertTrue(result1.contains("ok"));
		assertTrue(result1.contains("in"));		
		assertFalse(result1.contains("wrkng"));
		assertFalse(result1.contains("rk"));		
		
		Collection<String> result2 = wordFinder.findAllEnglishWords("working");
		assertTrue(result2.contains("working"));
		assertTrue(result2.contains("work"));
		assertTrue(result2.contains("king"));
		assertTrue(result2.contains("row"));
		assertTrue(result2.contains("ring"));
		assertTrue(result2.contains("know"));	
		assertTrue(result2.contains("wrong"));
		assertTrue(result2.contains("or"));
		assertTrue(result2.contains("go"));
		assertTrue(result2.contains("ok"));
		assertTrue(result2.contains("in"));		
		assertFalse(result2.contains("wrkng"));
		assertFalse(result2.contains("rk"));	
				
		Collection<String> result3 = wordFinder.findAllEnglishWords("eye");	
		int counter = 0;
		for (String r3 : result3) {
			if(r3.equals("eye")) {
				counter++;
			}
		}
		assertTrue(counter == 1);
				
		Collection<String> result4 = wordFinder.findAllEnglishWords("qwrtpsdfgh");	
		assertTrue(result4.isEmpty());	
		
		Collection<String> result5 = wordFinder.findAllEnglishWords(null);	
		assertTrue(result5.isEmpty());	
				
		Collection<String> result6 = wordFinder.findAllEnglishWords("!#$%&/()=?¡");	
		assertTrue(result6.isEmpty());	
		
		Collection<String> result7 = wordFinder.findAllEnglishWords("!W#O$R%K&I/N()G=?¡");	
		assertTrue(result7.contains("working"));
		assertTrue(result7.contains("work"));
		assertTrue(result7.contains("king"));
		assertTrue(result7.contains("row"));
		assertTrue(result7.contains("ring"));
		assertTrue(result7.contains("know"));	
		assertTrue(result7.contains("wrong"));
		assertTrue(result7.contains("or"));
		assertTrue(result7.contains("go"));
		assertTrue(result7.contains("ok"));
		assertTrue(result7.contains("in"));		
		assertFalse(result7.contains("wrkng"));
		assertFalse(result7.contains("rk"));
		
		Collection<String> result8 = wordFinder.findAllEnglishWords("excellent");	
		assertTrue(result8.contains("next"));
		assertTrue(result8.contains("cent"));
		assertTrue(result8.contains("tell"));
		assertTrue(result8.contains("let"));
		assertTrue(result8.contains("net"));
		assertTrue(result8.contains("teen"));
		assertTrue(result8.contains("excel"));
		assertTrue(result8.contains("excellent"));
		assertFalse(result8.contains("xllt"));
		assertFalse(result8.contains("le"));
		
		Collection<String> result9 = wordFinder.findAllEnglishWords("an");	
		assertTrue(result9.contains("an"));
	
	}
	
	
	@Test
	void testPerfomanceFindAllEnglishWords() {
		Collection<String> result10 = wordFinder.findAllEnglishWords("hippopotamus");	
		assertTrue(result10.contains("ship"));
		assertTrue(result10.contains("path"));
		assertTrue(result10.contains("mouth"));
		assertTrue(result10.contains("shampoo"));
		assertTrue(result10.contains("hippopotamus"));
		assertTrue(result10.contains("spam"));
		assertTrue(result10.contains("autism"));
		assertTrue(result10.contains("oath"));
		assertTrue(result10.contains("atom"));
	}

}
