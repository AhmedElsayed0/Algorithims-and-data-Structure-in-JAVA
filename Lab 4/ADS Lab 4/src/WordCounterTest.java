import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WordCounterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WordCounterTest
{
    /**
     * Default constructor for test class WordCounterTest
     */
    public WordCounterTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testAddWord()
    {
        WordCounter wordCoun1 = new WordCounter();
        wordCoun1.addWord("a");
        wordCoun1.addWord("a");
        wordCoun1.addWord("b");
        HashSet<String> value= new HashSet<String>();
        value.add("a");
        value.add("b");
        HashSet<String> set = wordCoun1.getWords();
        assertEquals(set, value);
    }

    @Test
    public void testCalculateInverted()
    {
        WordCounter wordCoun1 = new WordCounter();
        wordCoun1.addWord("a");
        wordCoun1.addWord("a");
        wordCoun1.addWord("a");
        wordCoun1.addWord("b");
        wordCoun1.addWord("c");
        HashSet<String> aSet = new HashSet<String>();
        assertEquals(true, aSet.add("a"));
        HashSet<String> bcSet = new HashSet<String>();
        assertEquals(true, bcSet.add("b"));
        assertEquals(true, bcSet.add("c"));
        HashMap map = new HashMap<Integer, HashSet<String>>();
        assertEquals(null, map.put(1, bcSet));
        assertEquals(null, map.put(3, aSet));
        HashMap<Integer, HashSet<String>> hashMap1 = wordCoun1.calculateInverted();
        assertEquals(map, hashMap1);
    }
}

