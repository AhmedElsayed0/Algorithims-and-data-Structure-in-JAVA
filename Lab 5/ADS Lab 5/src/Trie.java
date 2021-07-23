import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Trie {
    private Node root;
    private int[] frequencies;
    private PriorityQueue<Node> pq;
    private Scanner scanner;
    private final String input;
    private final HashSet<Character> setOfChars;
    private final char[] charArray;
    private String treeOutput;
    private int depth;
    private int maxTreeDepth;

    private class Node implements Comparable<Node> {
        char ch;
        Node left, right;
        int freq;

        public Node(char letter, int freq, Node left, Node right) {
            ch = letter;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            if(this.freq > o.freq)
                return 1;
            else if(this.freq == o.freq)
                return 0;
            else
                return -1;
        }
        boolean isLeaf() {
            return left == null && right == null;
        }

    }

    /**
     * Constructor to the class that takes a string as its input
     */
    public Trie(String input) {
        this.input = input;
        pq = new PriorityQueue<>();
        charArray = input.toCharArray();
        setOfChars = new HashSet<Character>();
        treeOutput = "";
        depth = 0;

        constructHashSet();
        tabulateFrequencies();
        initializePQ();
        mergeTree();
        maxTreeDepth = maxTreeDepth(root);
    }

    /**
     * Constructs the HashSet used for displaying the characters in the string while printing their frequencies
     */
    private void constructHashSet()
    {
        for(int i = 0; i < charArray.length; i++)
            setOfChars.add(charArray[i]);
    }

    /**
     * The frequencies are stored in the array for each letter to their respective ASCII code
     */
    private void tabulateFrequencies() {
        frequencies = new int[128];
        for (int i = 0; i < input.length(); i++) {
            char newChar = input.charAt(i);
            frequencies[newChar]++;
        }
    }

    /**
     * Initializes the priority queue used to store the nodes
     */
    private void initializePQ() {
        for (int i = 0; i < 128; i++)
            if (frequencies[i] > 0)
                pq.add(new Node((char) i, frequencies[i], null, null));
    }

    /**
     * Connects the nodes of the trie together and then assigns the root node to have access to the whole trie
     */
    private void mergeTree() {
        while (pq.size() > 1) {
            Node x = pq.remove();
            Node y = pq.remove();
            Node parent = new Node('*', x.freq + y.freq, x, y);
            pq.add(parent);
        }
        root = pq.remove();
    }

    /**
     * Prints the frequency of each character that appeared in the input string to the trie
     */
    public void printFrequencies() {

        for (char currentChar : setOfChars) {
            int currentFrequency = 0;
            for (int i = 0; i < input.length(); i++) {
                currentFrequency = frequencies[currentChar];
            }
            System.out.println("Current Character: " + currentChar + ", Frequency: " + currentFrequency + ".");
        }
    }

    private String treeEncoding(Node node) {
        if(node.isLeaf()) {
            treeOutput += node.ch;
        }
        else
            treeOutput += "*";
        if (node.left != null) {
             treeEncoding(node.left);
        }
        if (node.right != null)
        {
            treeEncoding(node.right);
        }
        return treeOutput;
    }

    private int maxTreeDepth(Node rootNode) {
        if (rootNode == null)
            return 0;
        else {

            int leftSubTreeDepth = maxTreeDepth(rootNode.left);
            int rightSubTreeDepth = maxTreeDepth(rootNode.right);

            if (leftSubTreeDepth > rightSubTreeDepth) {
                maxTreeDepth = leftSubTreeDepth + 1;
            } else
                maxTreeDepth = rightSubTreeDepth + 1;
        }
        return maxTreeDepth;

    }

    public int nodeDepth(Node node) {
        int nodeDepth = maxTreeDepth(this.root) - maxTreeDepth(node);
        return nodeDepth;
    }

    public static void main(String[] args) {
        Trie trie1 = new Trie("she_sells_sea_shells_by_the_seashore");
        trie1.printFrequencies();
        System.out.println(trie1.treeEncoding(trie1.root));
        Trie trie2 = new Trie("selly_sells_her_shorts_by_the_seattle_store");
        trie2.printFrequencies();
        System.out.println(trie2.treeEncoding(trie2.root));
    }
}

