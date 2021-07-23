import java.util.HashSet;

public class TrialTrie {

    String treeOutput;


        // Inner class Node. Source: Sedgewick Slidesets
        private class Node implements Comparable<Node>
        {
            private final char ch; // used only for leaf nodes
            private final int freq; // used only for compress
            private final Node left, right;

            public Node(char ch, int freq, Node left, Node right)
            {
                this.ch = ch;
                this.freq = freq;
                this.left = left;
                this.right = right;
            }

            public boolean isLeaf()
            { return left == null && right == null; }

            public int compareTo(Node that)
            { return this.freq - that.freq; }
        }

        // fields
        private int [] freq;
        private MinPQ<Node> pq;
        private HashSet<Character> charSet;
        private Node root;

        public  TrialTrie(String input) {
            charSet = new HashSet<Character>();
            tabulateFrequencies(input);
            initializePQ();
            mergeTrees();
            fillCharSet(input);
            printFrequencies(input);

        }

        //Tabulate the frequencies in the array
        private void tabulateFrequencies(String input) {
            freq = new int[128];
            for (int i = 0; i < input.length(); i++)
            { freq[input.charAt(i)]++; }
        }

        private void fillCharSet(String input) {
            char[] charArray =  input.toCharArray();
            for(char ch: charArray) {
                charSet.add(ch);
            }
        }
        //initialize the priority Queue
        private void initializePQ(){
            pq = new MinPQ<Node>();
            for (int i = 0; i < 128; i++)
                if (freq[i] > 0)
                    pq.insert(new Node((char) i, freq[i], null, null));
        }

        // merge the trees
        private void mergeTrees() {
            while (pq.size() > 1)
            {
                Node x = pq.delMin();
                Node y = pq.delMin();
                Node parent = new Node('*', x.freq + y.freq, x, y);
                pq.insert(parent);
            }
            root = pq.delMin();
        }
        //Print the frequencies
        private void printFrequencies(String input) {
            //get the characters from the character hashset
            for(char ch : charSet) {
                System.out.println("The character is " +  ch + " The frequency is " + freq[ch]);
            }
        }

    private String preorderTraversal(Node node) {
        if(node.isLeaf())
            treeOutput += node.ch;
        else
            treeOutput += "*";
        if (node.left != null) {
            preorderTraversal(node.left);
        }
        if (node.right != null)
        {
            preorderTraversal(node.right);
        }
        return treeOutput;
    }
        public static void main(String[] args)
        {
            String s = "she_sells_sea_shells_by_the_seashore";
            TrialTrie trie = new TrialTrie(s);
            System.out.println(trie.preorderTraversal(trie.root));
        }
    }


