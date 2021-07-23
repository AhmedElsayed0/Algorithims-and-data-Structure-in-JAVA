import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class RBT<Key  extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node rootNode;
    private int nodeCount;
    public int maxTreeDepth;
    private double meanDepth;
    private HashMap<Integer, Integer> nodesAtEachDepth = new HashMap<>();
    private int currentDepth;

    private class Node
    {
        Key key;
        Value value;
        Node left, right;
        boolean color;
        Node(Key key, Value value, boolean color)
        {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private class RBTIterator implements Iterator<Key>
    {
        private Stack<Node> stackOfNodes = new Stack<Node>();

        public RBTIterator()
        {
            pushLeft(rootNode);
        }

        @Override
        public boolean hasNext()
        {
            return !stackOfNodes.isEmpty();
        }

        private void pushLeft(Node currentNode)
        {
            while (currentNode != null)
            {
                stackOfNodes.push(currentNode);
                currentNode = currentNode.left;
            }
        }

        @Override
        public Key next()
        {
            Node currentNode = stackOfNodes.pop();
            pushLeft(currentNode.right);
            return currentNode.key;
        }
    }
    public void put(Key key, Value val)
    {
        rootNode = put(rootNode, key, val);
        rootNode.color = BLACK;
    }

    private boolean isRed(Node x)
    {
        if (x == null) return false;
        return (x.color == RED);
    }

    public Value get(Key key)
    {
        Node x = rootNode;
        while (x != null)
        {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.value;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }


    private Node put(Node h, Key key, Value val)
    {
        if (h == null)
        {
            nodeCount++;
            if (nodesAtEachDepth.get(currentDepth + 1) == null)
                nodesAtEachDepth.put(currentDepth + 1, 1);
            else {
                int currentNodesAtThisDepth = nodesAtEachDepth.get(currentDepth + 1);
                nodesAtEachDepth.put(currentDepth + 1, ++currentNodesAtThisDepth);
            }
            h = new Node(key, val, RED);
            return h;
        }
        if (isRed(h.left))
            if (isRed(h.left.left))
                h = splitFourNode(h);
        int cmp = key.compareTo(h.key);
        if (cmp == 0) h.value = val;
        else if (cmp < 0) {
            currentDepth++;
            h.left = put(h.left, key, val);
        }
            else
            h.right = put(h.right, key, val);
        if (isRed(h.right)) {
            currentDepth++;
            h = leanLeft(h);
        }
            return h;
    }

    private Node leanLeft(Node h)
    {
        Node x = rotL(h);
        x.color = x.left.color;
        x.left.color = RED;
        return x;
    }

    private Node rotL(Node h)
    {
        Node v = h.right;
        h.right = v.left;
        v.left = h;
        return v;
    }

    private Node rotR(Node h)
    {
        Node u = h.left;
        h.left = u.right;
        u.right = h;
        return u;
    }

    private Node splitFourNode(Node h)
    {
        Node x = rotR(h);
        x.left.color = BLACK;
        return x;
    }

    public Value getOrDefault(Key key, Value defaultValue)
    {
        Node currentRoot = this.rootNode;

        while(currentRoot != null)
        {
            int comparison = key.compareTo(currentRoot.key);
            if(comparison == 0)
                return currentRoot.value;
            else if(comparison > 0)
                currentRoot = currentRoot.right;
            else if(comparison < 0)
                currentRoot = currentRoot.left;
        }
        return defaultValue;
    }

    public double meanTreeDepth(Node node) {
        for(Integer currentDepth: nodesAtEachDepth.keySet())
        {
            meanDepth += currentDepth * nodesAtEachDepth.get(currentDepth);
        }
        meanDepth *=  1.0/nodeCount;
        return meanDepth;
    }

    public Iterator<Key> iterator()
    {
        return new RBTIterator();
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

    public void meanTreeDepth() {
        meanTreeDepth(this.rootNode);
    }

    public void maxTreeDepth() {
        maxTreeDepth(rootNode);
    }

    public int getMaxTreeDepth() {
        return maxTreeDepth;
    }

    public double getMeanDepth() {
        return meanDepth;
    }
}
