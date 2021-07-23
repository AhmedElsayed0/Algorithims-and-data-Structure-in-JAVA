/**
 * This class is created to be used in creating a stack using a linked list
 */

public class Node {
    private double nodeData;
    private Node nextNode;

    public Node(double nodeData, Node nextNode)
    {
        this.nodeData = nodeData;
        this.nextNode = nextNode;
    }

    public double getNodeData() {
        return nodeData;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNodeData(double nodeData) {
        this.nodeData = nodeData;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}
