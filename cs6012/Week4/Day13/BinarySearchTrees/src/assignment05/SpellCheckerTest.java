package assignment05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpellCheckerTest {
    SpellChecker mySpellCheck;
    List<String> words;
    List<String> misspelledWords;
    ArrayList<String> returnedWords;

    @BeforeEach
    void setUp() {
        words = Arrays.asList("squid", "formidabble", "afraid", "weak", "inelligable", "unsure", "blank");
    }

    @AfterEach
    void tearDown() {
        mySpellCheck = null;
        words = null;
        misspelledWords = null;
        returnedWords = null;
    }

    @Test
    void testStringSpellCheck() {

        mySpellCheck = new SpellChecker(words);
        returnedWords = mySpellCheck.toArray();
        for (int i = 1; i < returnedWords.size(); i++) {
            Assertions.assertTrue(returnedWords.get(i - 1).compareTo(returnedWords.get(i)) < 0);
        }
        //testing add to dictionary
        mySpellCheck.addToDictionary("sam");
        returnedWords = mySpellCheck.toArray();
        for (int i = 1; i < returnedWords.size(); i++) {
            Assertions.assertTrue(returnedWords.get(i - 1).compareTo(returnedWords.get(i)) < 0);
        }
        //testing remove from dictionary
        mySpellCheck.removeFromDictionary("sam");
        returnedWords = mySpellCheck.toArray();
        for (int i = 0; i < returnedWords.size(); i++) {
            assertNotEquals("sam", returnedWords.get(i));
        }


    }

    @Test
    void testFileSpellCheck() {

        mySpellCheck = new SpellChecker(new File("myWords.txt"));
        //SpellChecker mySC = new SpellChecker(new File("dictionary.txt"));
        misspelledWords = mySpellCheck.spellCheck(new File("myWords.txt"));
        if (misspelledWords.size() == 0) {
            System.out.println("There are no misspelled words in file: myWords.");
        } else {
            System.out.println("The misspelled words in file myWords are:");
            for (String w : misspelledWords) {
                System.out.println("\t" + w);
            }
        }
        returnedWords = mySpellCheck.toArray();
        for (int i = 1; i < returnedWords.size(); i++){
            Assertions.assertTrue(returnedWords.get(i-1).compareTo(returnedWords.get(i))< 0);
        }

    }
}