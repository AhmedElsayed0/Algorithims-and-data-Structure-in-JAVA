import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * This class is created to tokenize and decompose an arithmetic expression into several parts
 * which we then insert into a stack
 */

public class ArithmeticTerm {
    private String arithmeticExpression;


    public ArithmeticTerm(String arithmeticExpression) {
        this.arithmeticExpression = arithmeticExpression;
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
    public void reverse() {
        StringTokenizer expressionTokenizer = new StringTokenizer(arithmeticExpression);
        arithmeticExpression = "";
        while (expressionTokenizer.hasMoreTokens()) {
            String nextToken = expressionTokenizer.nextToken();
            arithmeticExpression.concat(nextToken);
        }
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
            }
            else if(currentToken.equals("pi"))
            {
                postFixExpression += "pi" + " ";
            }
            else if (currentToken.equals(")")) {
                try {
                    postFixExpression += operatorStack.pop() + " ";
                } catch (EmptyStackException exception) {
                    System.out.println("Please check the syntax of the inserted arithmetic Expression");
                    return null;
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
            else if(element.equals("pi"))
            {
                operandStack.push(Math.PI);
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
            else if(element.equals("exp"))
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
                else if(element.equals("sin"))
                {
                    double sinArgument = operandStack.pop();
                    double valueToPush = Math.sin(sinArgument);
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
                "( ( ( ( sin ( 4 * ( pi / 3 ) ) ) * ( exp ( -1 * ( ( sqrt 2 ) - 1 ) / 8 ) ) ) ) / ( sqrt ( 6 * pi ) ) )");
        System.out.println("The arithmetic Term is " + myTerm.toString());
        System.out.println("The postFix expression of the previous arithmetic expression is " + myTerm.convert());
        System.out.println("The value of the previous arithmetic expression is " + myTerm.evaluate());

        myTerm.reverse();
        System.out.println("The reversed state of the arithmetic expression is" + myTerm.arithmeticExpression);

    }

    /**
     * checks if the sent String to the method is an Binary or Unary Operator
     * @param token String that is sent to the method by the StringTokenizer
     * @return true if the String token is one of the Binary or Unary Operators("+", "-", "*", "/", "sqrt", "e", "^") otherwise false
     */
    public static boolean isOperators(String token)
    {
        if(token.equals("*"))
            return true;
        else if(token.equals("+"))
            return true;
        else if(token.equals("exp"))
            return true;
        else if(token.equals("-"))
            return true;
        else if(token.equals("/"))
            return true;
        else if(token.equals("sqrt"))
            return true;
        else if(token.equals("^"))
            return true;
        else if(token.equals("sin"))
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
