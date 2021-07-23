import java.util.EmptyStackException;
import java.util.Stack;

public class Tree {
    private BiNode root;
    private StringBuffer indent= new StringBuffer();
    private String expression;
    private static class BiNode
    {
        public String item;
        public BiNode left;
        public BiNode right;

        public BiNode(String item)
        {
            this(item,null,null);
        }

        public BiNode(String item, BiNode left, BiNode right)
        {
            this.item = item;
            this.left = left;
            this.right = right;
        }
        
    }

    public Tree(BiNode root)
    {
        this.root = root;
    }

    public Tree(String postfix) {
        root = construct(postfix);
    }

    /**
     * constructs a tree with a give postfix expression
     * @param postfix arithmetic expression that is needed for the construction of the tree
     * @return root node of the tree
     */
    public BiNode construct(String postfix)
    {
        // tokenize the input postfix expression
        String[] tokenizer;
        tokenizer = postfix.split(" ");

        // stack to temporarily hold nodes
        Stack<BiNode> stack;
        stack = new Stack<>();

        // Traverse through every element of the input postfix expression
        for (String currentElement : tokenizer) {
            BiNode tree;
            // If binary operator, construct new parent node and add children from stack
            if (MathSupport.isBinaryOperator(currentElement)) {
                tree = new BiNode(currentElement);

                try {
                    // Pop two top nodes
                    tree.right = stack.pop();
                    tree.left = stack.pop();
                } catch (EmptyStackException exception) {
                    System.out.println("Please check the syntax of the inserted Arithmetic Expression");
                }
                // Add this element to stack
                stack.push(tree);
            }
            // If unary operator, construct new parent node and add child from stack
            else if (MathSupport.isUnaryOperator(currentElement)) {
                tree = new BiNode(currentElement);

                try {
                    tree.right = stack.pop();
                } catch (EmptyStackException exception) {
                    System.out.println("Please check the syntax of the inserted Arithmetic Expression");
                }
                // Add this element to stack
                stack.push(tree);
            } else { // If operand, simply push into stack
                stack.push(new BiNode(currentElement));
            }
        }
            return stack.pop();
    }

    /**
     * A method which tokenizes the String with the postfix arithmetic term,
     * and uses a stack of Strings (java.util.Stack<String>) to store the tokens of the arithmetic expression.
     *
     * @author Mohamed ElGhamrawy
     * */
    public Stack<String> tokenize(String postfix) {
        // initialize return variable
        Stack<String> tokenizedExpression;
        tokenizedExpression = new Stack<>();

        // step 1: tokenize postfix expression
        String[] tokenizer;
        tokenizer = postfix.split(" ");

        // step 2: form stack
        for (int i = 0; i <= tokenizer.length - 1; i++) {
            tokenizedExpression.push(tokenizer[i]);
        }
        return tokenizedExpression;
    }

    public static void main(String[] args)
    {
        BiNode twoOfPower = new BiNode("2");
        BiNode twoOfDivision = new BiNode("2");
        BiNode threeOfPower = new BiNode("3");
        BiNode power = new BiNode("^", twoOfPower, threeOfPower);
        BiNode four = new BiNode("4");
        BiNode squareRoot = new BiNode("sqrt", null, four);
        BiNode multiplication = new BiNode("*", squareRoot, power);
        BiNode division = new BiNode("/", multiplication, twoOfDivision);
        Tree tree = new Tree(division);
        tree.inOrderTraversal();
        tree.infixGenerator();

    }

    /**
     * traverse through the tree in an in-order manner which means that the left child is visited first, then the parent node
     * then the right node
     * @param node the root of the tree
     */
    private void inOrderTraversal(BiNode node)
    {
        indentation( true);
        System.out.println(indent.toString() + "traverse(" + node.item + ")");
        if(node.left!=null) inOrderTraversal(node.left);
        visit(node);
        if(node.right!=null) inOrderTraversal(node.right);
        System.out.println(indent.toString() + "return");
        indentation( false);
    }

    public void inOrderTraversal()
    {
        inOrderTraversal(this.root);
    }

    /**
     * indentation to show the nodes of the tree until the leave is reached
     * @param plus true then indentation is increased, otherwise decreases
     */
    private void indentation(boolean plus)
    {
        if(plus) indent.append("    ");
        else indent.setLength(indent.length()-4);
    }

    /**
     * shows that the node is visited and won't be outputted again
     * @param node the node that needs to go to the output console
     */
    private void visit(BiNode node)
    {
        System.out.println(indent.toString() + "visit(" + node.item + ")");
    }

    /**
     * generates the FPAE of the tree created for the current instance
     * @param node the root node of the tree that gives us the ability to go through the whole tree
     */
    private void infixGenerator(BiNode node)
    {
        if(node == null)
        {
            System.out.println("Please check the syntax of the inserted Arithmetic Expression");
            return;
        }
        try {
            expression = expression + "(" + " ";
            if (node.left != null) {
                infixGenerator(node.left);
            }
            expression += node.item + " ";
            if (node.right != null) {
                infixGenerator(node.right);
                expression += ")" + " ";
            } else
                expression += ")" + " ";

        }
        catch(NullPointerException exception)
        {
            System.out.println("Please check the syntax of the inserted Arithmetic Expression");
        }
        }

    public void infixGenerator()
    {
        infixGenerator(root);
        //fixExpression();
        System.out.println(expression);
    }

}
