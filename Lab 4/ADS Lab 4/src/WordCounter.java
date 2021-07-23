import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Keep a record of how many times each word was
 * entered by users.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    1.0 (2016.02.29)
 */
public class WordCounter
{
    // Associate each word with a count.
    private BST<String, Integer> counts;
    private BST<Integer, HashSet<String> > inverted;
    // will contain the histogram with the integer over the words

    /**
     * Create a WordCounter
     */
    public WordCounter()
    {
        counts = new BST<String, Integer>();
        updateInverted(); //instance variable
    }

    /**
     * Update the usage count of all words in input.
     * @param input A set of words entered by the user.
     */
    public void addWords(HashSet<String> input)
    {
        for(String word : input) {
            addWord(word);
        }
    }

    /**
     * Method addWord and update the counts of all words
     *
     * @param word A word to be added
     */
    public void addWord(String word)
    {
        int counter = (int) counts.getOrDefault(word, 0);
        // get(word) would return null if word is not in the counts HashMap
        // but we want to get returned 0 it word is not ... " ....
        // getOrDefault(word, 999) will return 999 if word is not ... " ....
        counts.put(word, counter + 1);
        updateInverted();
    }
    
    /**
     * get all words
     *
     * @return the set of all words
     */
    public HashSet<String> getWords()
    {
        HashSet<String> set= new HashSet<String>(); // uses HashSet's copy contructor
        return set;
    }

    private void updateInverted() // always to be called after changing counts!!
    // in order to keep inverted consistent!!!
    {
        Iterator<String> iterator = counts.iterator();
        inverted = new BST<Integer, HashSet<String> >();

        while(iterator.hasNext())
        {
            String currentWord = iterator.next(); //iterate through the words
            int counter = counts.get(currentWord); //gets the count of the current word
            HashSet<String> tmp= inverted.get(counts.get(currentWord)); //String HashSet used to update the HashMap inverted
            if(tmp == null)
                tmp= new HashSet<String>();

            tmp.add(currentWord); //add the word to the temporary HashSet
            inverted.put(counter, tmp); //replace the old HashSet with the new one that was just created and added the word to
        }

    }

    /**
     * Method calculateInverted 
     * to demonstrate the implementation of problems 8,9 in Lab 3
     * contains redundant code taken from method private updateInverted
     * Think the HashMap in terms of Histogram and inverted Histogram (sketch in the lecture)
     *
     * @return The return value
     */
    public HashMap<Integer, HashSet<String> > calculateInverted()
    {
        HashMap<Integer, HashSet<String> > inverted 
        = new HashMap<Integer, HashSet<String> >();
        Iterator<String> iterator = counts.iterator();
        // counts.keySet() returns all the words in the HashMap keys
        while(iterator.hasNext())
        {
            String currentKey = iterator.next();
            int counter= (int) counts.get(currentKey);
            HashSet<String> tmp= inverted.get(counts.get(currentKey));
            if(tmp == null)
                tmp= new HashSet<String>();
            tmp.add(currentKey);
            inverted.put(counter, tmp);
        }
        return inverted;
    }

    /**
     * print the HashMap containing the counted words
     *
     */
    public void print()
    {
        Iterator<String> countsIterator = counts.iterator();
        Iterator<Integer> invertedIterator = inverted.iterator();
        while(countsIterator.hasNext())
        {
            String currentWord = countsIterator.next();
            System.out.printf("%-6s:%-3d\n", currentWord, counts.get(currentWord));
        }

        while(invertedIterator.hasNext())
        {
            Integer currentCount = invertedIterator.next();
            HashSet<String> currentSet = inverted.get(currentCount);
            for(String currentWord: currentSet)
            {
                System.out.printf("%-3d:%-6s\n", currentCount, currentWord);
            }
        }
//        double mean = counts.meanTreeDepth();
//        System.out.println("Mean Tree Depth is: " + mean);
//        System.out.println("Node count is: " + counts.getNodeCount());
//        System.out.println("Max Tree Depth is: " + counts.getMaxTreeDepth());
//        System.out.println(counts);
//        System.out.println(inverted);
    }

    public BST<String, Integer> getCounts() {
        return counts;
    }

    public BST<Integer, HashSet<String>> getInverted() {
        return inverted;
    }
}
