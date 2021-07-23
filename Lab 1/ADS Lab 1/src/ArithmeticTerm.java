import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;


/*
Problem 1 (Team: Common preparation)
 For both the arithmetic expressions given in die introductions to problems 2 and 3 determine the maximum stack size needed, i.e. the size of the corresponding stack in that problem at it's maximum usage.  For example, the maximum stack size is two for evaluating 5 4 + and it is one for converting ( 5 + 4 ) into the former postfix term.
Implementation Task
Provide a class ArithmeticTerm with a constructor that takes a String with the arithmetic expression as an argument and stores it in a private instance variable, and with a a method String toString(). Test this constructor by instantiating an ArithmeticTerm and let the instance print it in a main program of that class.
Add  a method void reverse() to that class which tokenizes the String with the arithmetic term and uses a stack of Strings (java.util.Stack<String>) to store the tokens of the arithmetic expression and writes it in the opposite order to the string instance variable. Thus, an object of type ArithmeticTerm behaves under reversing like a number that gets multiplied by -1, i.e. repeated reverse calls will produce the original string in even case and the reversed string in odd case. Test this by extending your main program accordingly. As test cases use the two strings given in die introductions to problems 2 and 3.
Provide a class StackOfDoubles with an array implementation of the stack using start capacity 1 using resizing as in the slide set. Optional: Provide a generic class Stack<T> with a linked list implementation.
Calculate explicitely the number of copy operations needed to grow a stack of general size N when starting from size n=8 for the following resizing strategies. Check your formula for N=32 with manual counting.
Increase the size always by the constant number n, i.e. sizes assume numbers N=(k+1)n after resize step k.
Increase the size always by doubling, i.e. sizes assume numbers N=2^(k+3) after resize step k.
Problem 2 (Member 1: Evaluation of Postfix Expressions)
The evaluatioin of a postfix expression is done by an operand stack that can keep doubles. Look at the explanations, pseudocode and programs presented in the lecture. Make your program work with 5 test cases of increasing complexity, e.g. "5.1 9 8.88 + 4 sqrt 6 / ^ 7 - *". You see that a blank is used as separator pairwise between numbers and/or operators so that you can use a Java tokenizer to break it down.

Now provide a method Double evaluate() to the class ArithmeticTerm which uses a stack of doubles to evaluate the postfix expression with an extended version of the algorithm postfix2value that supports division, minus and exponentiation as well as unary operators. Test it by extending your main program accordingly.
Documentation shall include support of all 5 binary arithmetic operators and provide more than 5 test caes with at least 4 operators in different sequences each.
Finally, include your partners method String convert() of problem 2 into your class, make it run. Write a static main2 method that converts a FPAE and calculates its value.
Optional: Your program has to terminate with an appropriate error message if the postfix expression is not valid, i.e. if operands cannot be converted to the required arithmetic data type or if there are not enough operands available to be operated (pop is applied to an empty stack) or if operands are left on the stack at the end of the expression.

Problem 3 (Member 2: Generation of Postfix from Infix)
The conversion of a Fully Parenthesized Arithmetic Infix Expression (FPAE) to Postfix (Reverse Polish) Notation is simply done by using an operator stack that can keep strings. Look at the explanations, pseudocode and programs presented in the lecture. Make your program work with 5 test cases of increasing complexity, e.g. "( 5.1 * ( ( ( 9 + 8.88 ) ^ ( ( sqrt 4 ) / 6 ) ) - 7 ) )". You see that a blank is used as separator pairwise between brackets, numbers and/or operators so that you can use a trivial Java tokenizer to break it down.

Now provide a method String convert() to that class which uses a stack of strings to convert the FPAE into a postfix expression with a modified version of the algorithm infix2postfix that supports unary operators as well. Test it by extending your main program accordingly, e.g.  with ( ( 4.3 * 1e-1 ) - .4 ).
Documentation shall include support of all 5 binary arithmetic operators and provide more than 5 test caes with at least 4 operators in different sequences each.
Finally, include your partners method Double evaluate() of problem 1 into your class, make it run. Write a static main2 method that converts a FPAE and calculates its value.
Optional: Your program has to terminate with an appropriate error message if the FPAE is not syntactically valid, i.e. if there are too many or too few right parenthesis (pop is applied to an empty stack or operators are left on the stack at the end of the expression) and if not exactly three tokens are inside a pair of parenthesis, the second of which is one of the 5 binary operators.
Bonus Task (Calculator with GUI)
Starting from the PostfixCalculatorDummy:

Make it work for the binary operators represented in the GUI by implementing the required CalculatePostfix methods. In case of questions publish them in the user forum, please.
Extend the GUI as well as the required methods of the underlying classes to support all operators you would like to use.
# Oct 25, 18:32: added: Consequence of former definition of the reverse method.
Last modified: Thursday, 5 November 2020, 7:42 PM
*/

/**
 * This class is created to tokenize and decompose an arithmetic expression into several parts
 * which we then insert into a stack
 */

public class ArithmeticTerm {
    private String arithmeticExpression;
    private Stack<String> reversedExpression;


    public ArithmeticTerm(String arithmeticExpression) {
        this.arithmeticExpression = arithmeticExpression;
        reversedExpression = new Stack<String>();
    }

    /**
     * @return arithmetic expression as a string
     */
    public String toString() {
        return this.arithmeticExpression;
    }

    /**
     * decomposes the arithmetic expression into several parts, each character or number is added to the stack
     * as a single element
     */


    /**
     * how to reverse a string of character or an arithmetic expression use it a string of chars
     *
     */
    public void reverse() {
        StringTokenizer expressionTokenizer = new StringTokenizer(arithmeticExpression);
        reversedExpression = new Stack<String>();
        //loop to iterate through the string that was provided by the user.
        while (expressionTokenizer.hasMoreTokens()) {
            String nextToken = expressionTokenizer.nextToken();
            reversedExpression.push(nextToken);
        }
    }

    public Stack<String> getReversedExpression() {
        return reversedExpression;
    }

    /**
     * Converts the FPAE (Fully Parenthesized Arithmetic Expression) from inFix form to postFix form
     * test cases are:
     * ( ( ( ( 6 + 4 ) ^ 2 ) - 5 ) / 2 )
     * ( ( 3 * 8 ) + ( ( sqrt 9 ) / 3 ) )
     * ( ( ( 4 * ( e 4 ) ) - 1 ) + ( sqrt 2 ) )
     * ( ( ( ( ( 3 - 2 ) * 4 ) / 2 ) * ( sqrt 4 ) ) ^ 2 )
     * ( ( ( ( ( ( 5 ^ 4 ) ^ 2 ) ^ 4 ) + 9 ) - 8 ) / 2 )
     * ( ( ( ( ( sqrt 4 ) * ( sqrt 9 ) ) - 6 ) * 6 ) + 4 )
     * @return postFixExpression A string that is converted from InFix form to postFix form
     * @author Hussam Kayed
     */
    public String convert() {
        StringTokenizer token = new StringTokenizer(this.arithmeticExpression);
        Stack<String> operatorStack = new Stack<String>();
        String postFixExpression = "";

        while (token.hasMoreTokens()) {
            String currentToken = token.nextToken();
            if (checkDouble(currentToken)) {
                postFixExpression += currentToken + " ";
            } else if (isOperators(currentToken)) {
                operatorStack.push(currentToken);
            } else if (currentToken.equals(")")) {
                try {
                    postFixExpression += operatorStack.pop() + " ";
                } catch (EmptyStackException exception) {
                    System.out.println("Please check the syntax of the inserted arithmetic Expression");
                }
            }
        }
        this.arithmeticExpression = postFixExpression;

        return postFixExpression;
    }

    /**
     * A method which uses a stack of doubles to evaluate the postfix expression with
     * an extended version of the algorithm postfix2value that supports the following binary operators:
     * addition (+),
     * subtraction (-),
     * multiplication (*),
     * division (/),
     * and exponentiation ();
     * and the following unary operators:
     * square-rooting (sqrt), and factorial (!).
     *
     * @return bottom a double which exists at the bottom of the stack at the end of execution, i.e. the answer
     *
     * @author Mohamed ElGhamrawy
     * */
    public double evaluate() {
        Stack<Double> operandStack = new Stack<Double>();
        StringTokenizer token = new StringTokenizer(arithmeticExpression);
        while(token.hasMoreTokens())
        {
            String element = token.nextToken();

            if(checkDouble(element))
            {
                operandStack.push(Double.parseDouble(element));
            }
            else if(isOperators(element))
            {
                if(element.equals("*")) {
                    double first=operandStack.pop();
                    double second=operandStack.pop();
                    double valueToPush = first * second;

                    operandStack.push(valueToPush);
                }
                else if(element.equals("+")) {
                    double first = operandStack.pop();
                    double second = operandStack.pop();
                    double valueToPush = first + second;

                    operandStack.push(valueToPush);
                }
            else if(element.equals("e"))
                {
                    double first = operandStack.pop();
                    double valueToPush = Math.exp(first);

                    operandStack.push(valueToPush);
                }
            else if(element.equals("-"))
                {
                    double first = -operandStack.pop();
                    double second = operandStack.pop();
                    double valueToPush = first + second;

                    operandStack.push(valueToPush);
                }
            else if(element.equals("/"))
                {
                    double first = ( 1 / operandStack.pop());
                    double second = operandStack.pop();
                    double valueToPush = first * second;

                    operandStack.push(valueToPush);
                }
            else if(element.equals("sqrt"))
                {
                    double first = operandStack.pop();
                    double valueToPush = Math.sqrt(first);
                    operandStack.push(valueToPush);
                }
            else if(element.equals("^"))
                {
                    double power = operandStack.pop();
                    double base = operandStack.pop();
                    double valueToPush = Math.pow(base, power);
                    operandStack.push(valueToPush);
                }
            else if(element.equals("!"))
                {
                    int factorialNumber = Integer.parseInt(element);
                    int valueToPush = factorial(factorialNumber);
                    operandStack.push((double) valueToPush);
                }
            else
                {
                    System.out.println("Please check the syntax of your expression");
                }
            }
        }
        return operandStack.pop();

    }

        private int factorial ( int n)
        {
            return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);

        }

    /**
     * Main function that was used for testing one of the test cases
     * @param args
     */
    public static void main(String[] args)
    {
        ArithmeticTerm myTerm = new ArithmeticTerm(
                "( ( ( ( ( sqrt 4 ) * ( sqrt 9 ) ) - 6 ) * 6 ) + 4 )");
        //ArithmeticTerm myTerm2 = new ArithmeticTerm("( ( 4.3 * 1e-1 ) - .4 )");
        System.out.println(myTerm.toString());
        System.out.println(myTerm.convert());
        System.out.println(myTerm.evaluate());

        //System.out.println(myTerm2.convert());
       // System.out.println(myTerm.evaluate());
        myTerm.reverse();
        while(!myTerm.reversedExpression.empty())
        {
            System.out.print(myTerm.getReversedExpression().pop() + " ");
        }

    }

    /**
     * checks if the sent String to the method is an Binary or Unary Operator
     * @param token String that is sent to the method by the StringTokenizer
     * @return true if the String token is one of the Binary or Unary Operators("+", "-", "*", "/", "sqrt", "e", "^") otherwise false
     */
    private boolean isOperators(String token)
    {
        if(token.equals("*"))
            return true;
        else if(token.equals("+"))
            return true;
        else if(token.equals("e"))
            return true;
        else if(token.equals("-"))
            return true;
        else if(token.equals("/"))
            return true;
        else if(token.equals("sqrt"))
            return true;
        else if(token.equals("^"))
            return true;
        else
            return false;
    }

    /**
     * checks if the sent String to the method is a double value
     * @param token String sent by StringTokenizer
     * @return true if the number is a double value otherwise false
     */
    private boolean checkDouble(String token)
    {
        try
        {
            double number = Double.parseDouble(token);
        } catch(NumberFormatException exception)
        {
            return false;
        }
        return true;
    }

}
