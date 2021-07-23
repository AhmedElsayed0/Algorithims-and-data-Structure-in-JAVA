import java.util.PriorityQueue;



    public class HuffmanTreeConstructionTrial implements Comparable<HuffmanTreeConstructionTrial> {
        HuffmanTreeConstructionTrial left;
        HuffmanTreeConstructionTrial right;
        HuffmanTreeConstructionTrial parent;
        char text;
        int frequency;

        public HuffmanTreeConstructionTrial(char textIn, int frequencyIn) {
            text = textIn;
            frequency = frequencyIn;
        }

        public HuffmanTreeConstructionTrial(int frequencyIn) {
            text = ' ';
            frequency = frequencyIn;
        }

        public int compareTo(HuffmanTreeConstructionTrial n) {
            if (frequency < n.frequency) {
                return -1;
            }
            else if(frequency > n.frequency) {
                return 1;
            }
            return 0;
        }

        public static void printFromPreOrder(HuffmanTreeConstructionTrial n, String dashes) {
            // print with colon if leaf node
            if (n.text != ' ') {
                System.out.println(dashes + n.frequency + ":" + n.text);
            }
            else {
                System.out.println(dashes + n.frequency);
            }

            // Start recursive on left child then right
            if (n.left != null) {
                printFromPreOrder(n.left, dashes + "-");
            }
            if (n.right != null) {
                printFromPreOrder(n.right, dashes + "-");
            }
        }

        // Returns root node to pass to printFromPreOrder
        public static HuffmanTreeConstructionTrial makeHuffmanTree(int frequencies[], String text) {
            PriorityQueue<HuffmanTreeConstructionTrial> queue = new PriorityQueue<HuffmanTreeConstructionTrial>();
            for (int i = 0; i < text.length(); i++) {
                HuffmanTreeConstructionTrial n = new HuffmanTreeConstructionTrial(text.charAt(i), frequencies[i]);
                queue.add(n);
            }
            HuffmanTreeConstructionTrial root = null;
            while (queue.size() > 1)  {
                HuffmanTreeConstructionTrial least1 = queue.poll();
                HuffmanTreeConstructionTrial least2 = queue.poll();
                HuffmanTreeConstructionTrial combined = new HuffmanTreeConstructionTrial(least1.frequency + least2.frequency);
                combined.right = least1;
                combined.left = least2;
                least1.parent = combined;
                least2.parent = combined;
                queue.add(combined);
                // Keep track until we actually find the root
                root = combined;
            }
            return root;
        }

        public static void main(String[] args) {
            int frequencies[] = {10, 15, 12, 3, 4, 13, 1};
            String text = "she_sells_sea_shells_by_the_seashore";
            HuffmanTreeConstructionTrial root = HuffmanTreeConstructionTrial.makeHuffmanTree(frequencies, text);
            HuffmanTreeConstructionTrial.printFromPreOrder(root, "");
        }
    }

