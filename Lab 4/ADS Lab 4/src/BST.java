import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class BST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private Node rootNode;
    private int nodeCount;
    public int maxTreeDepth;
    private double meanDepth;
    private HashMap<Integer, Integer> nodesAtEachDepth;
    private int currentDepth;

    public BST() {
        maxTreeDepth = 0;
        nodeCount = 0;
        meanDepth = 0;
        currentDepth = 0;
        nodesAtEachDepth = new HashMap<>();
        nodesAtEachDepth.put(0, 1);
    }

    public int getCurrentDepth() {
        return currentDepth;
    }

    private class BSTIterator implements Iterator<Key> {
        private Stack<Node> stackOfNodes = new Stack<Node>();

        public BSTIterator() {
            pushLeft(rootNode);
        }

        @Override
        public boolean hasNext() {
            return !stackOfNodes.isEmpty();
        }

        private void pushLeft(Node currentNode) {
            while (currentNode != null) {
                stackOfNodes.push(currentNode);
                currentNode = currentNode.left;
            }
        }

        @Override
        public Key next() {
            Node currentNode = stackOfNodes.pop();
            pushLeft(currentNode.right);
            return currentNode.key;
        }
    }

    private class Node {
        Key key;
        Value value;
        Node right;
        Node left;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.right = null;
            this.left = null;
        }

    }

    private Node put(Node currentNode, Key key, Value value) {
        if (currentNode == null) {
            nodeCount++;
            if (nodesAtEachDepth.get(currentDepth + 1) == null)
                nodesAtEachDepth.put(currentDepth + 1, 1);
            else {
                int currentNodesAtThisDepth = nodesAtEachDepth.get(currentDepth + 1);
                nodesAtEachDepth.put(currentDepth + 1, ++currentNodesAtThisDepth);
            }
            currentNode = new Node(key, value);
            return currentNode;
        }
        int comparison = key.compareTo(currentNode.key);

        if (comparison == 0) {
            currentNode.value = value;
        } else if (comparison > 0) {
            currentDepth++;
            currentNode.right = put(currentNode.right, key, value);

        } else if (comparison < 0) {
            currentDepth++;
            currentNode.left = put(currentNode.left, key, value);
        }
        return currentNode;
    }

    public void put(Key key, Value value) {
        currentDepth=0;
        this.rootNode = put(rootNode, key, value);
    }

    public Value get(Comparable key) {
        Node currentRoot = this.rootNode;

        while (currentRoot != null) {
            int comparison = key.compareTo(currentRoot.key);
            if (comparison == 0)
                return currentRoot.value;
            else if (comparison > 0)
                currentRoot = currentRoot.right;
            else if (comparison < 0)
                currentRoot = currentRoot.left;
        }
        return null;
    }

    @Override
    public Value getOrDefault(Key key, Value defaultValue) {
        Node currentRoot = this.rootNode;

        while (currentRoot != null) {
            int comparison = key.compareTo(currentRoot.key);
            if (comparison == 0)
                return currentRoot.value;
            else if (comparison > 0)
                currentRoot = currentRoot.right;
            else if (comparison < 0)
                currentRoot = currentRoot.left;
        }
        return defaultValue;
    }


    public void maxTreeDepth() {
        maxTreeDepth(rootNode);
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

    public double meanTreeDepth(Node node) {
        for(Integer currentDepth: nodesAtEachDepth.keySet())
        {
            meanDepth += currentDepth * nodesAtEachDepth.get(currentDepth);
        }
        meanDepth *=  1.0/nodeCount;
        return meanDepth;
    }

    public int nodeDepth(Node node) {
        if (node.left == null && node.right == null)
            return maxTreeDepth;
        int nodeDepth = maxTreeDepth(this.rootNode) - maxTreeDepth(node);
        return nodeDepth;
    }

    public Iterator<Key> iterator() {
        return new BSTIterator();
    }

    public int getMaxTreeDepth() {
        return maxTreeDepth;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void meanTreeDepth() {
        meanTreeDepth(this.rootNode);
    }

    public double getMeanDepth() {
        return meanDepth;
    }

    public HashMap<Integer, Integer> getNodesAtEachDepth() {
        return nodesAtEachDepth;
    }
//    public int getCurrentNodeDepth(Node node)
//    {
//        int currentDepth = 0;
//        while()
//    }
}


