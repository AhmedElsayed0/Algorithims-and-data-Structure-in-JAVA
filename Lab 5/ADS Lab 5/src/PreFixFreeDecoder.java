import java.util.Stack;

public class PreFixFreeDecoder {


    private Stack<Character> stack;
    private Node root;
    private StringBuffer indent;

    private class Node {
        char ch;
        Node left, right;

        Node(char ch) {
            this.ch = ch;
            if (ch == '*') {
                left = new Node(stack.pop());
                right = new Node(stack.pop());
            }
        }

        boolean isInternal() {
            return root.left != null || root.right != null;
        }
    }

    public PreFixFreeDecoder(String sequence) {
        // reverse string
        indent = new StringBuffer();
        stack = new Stack<Character>();
        StringBuffer reversedSequence = new StringBuffer(sequence);
        reversedSequence.reverse();
        String s = reversedSequence.toString();
        // tokenize the input postfix expression
        char[] tokenizer;
        tokenizer = s.toCharArray();
        // fill stack to temporarily hold nodes
        for (int i = 0; i < tokenizer.length; i++) {
            stack.push(tokenizer[i]);
        }
        root = new Node(stack.pop());
        preorderTraversal(root);
    }

    /**
     * A method which assists preorderTraversal() by controlling indentation and hence the displaying the tree in a
     * visually comprehensible manner.
     *
     * @parameters plus a boolean which increases and decreases the level of indentation
     * @author Mohamed ElGhamrawy
     */
    private void indentation(boolean plus) {
        if (plus) indent.append("    ");
        else indent.setLength(indent.length() - 4);
    }

    /**
     * A method which assists preorderTraversal() by being recursively called to visit each node within the tree and
     * print the tree.
     *
     * @parameters node the current node to be traversed
     * @author Mohamed ElGhamrawy
     */
    private void preorderTraversal(Node node) {
        indentation(true);
        //System.out.println(indent.toString() + "traverse(" + node.element + ")");
        System.out.println(indent.toString() + "visit(" + node.ch + ")");
        if (node.left != null) preorderTraversal(node.left);
        if (node.right != null) preorderTraversal(node.right);
        //System.out.println(indent.toString() + "return");
        indentation(false);
    }

    public static void main(String[] args)
    {
        PreFixFreeDecoder decoder = new PreFixFreeDecoder("***sbl****hea*t*y*ro_");
    }
}
