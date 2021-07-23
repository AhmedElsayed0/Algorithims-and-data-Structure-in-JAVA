/**
 * This class is used to create a Stack<Double> using a linkedList
 */
public class StackOfDoublesLL {
    private Node first = null;

    private class Node {
        double operand;
        Node nextNode;
    }

    public StackOfDoublesLL() {
        this.first = new Node();
    }

    public void push(double newOperand) {
        Node second = first;
        first.operand = newOperand;
        first.nextNode = second;
    }
    public double pop() {
        double poppedOperand = first.operand;
        first = first.nextNode;
        return poppedOperand;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
