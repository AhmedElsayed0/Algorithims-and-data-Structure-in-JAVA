public class MathSupport {

    /**
     * A method which assists computation methods by checking if a token is an operator.
     *
     * It supports the following binary operators:
     * addition (+),
     * subtraction (-),
     * multiplication (*),
     * division (/),
     * and exponentiation ("^");
     *
     * @parameters token a String holding the token to be checked
     * @return  a boolean, with true meaning that the token is an operator
     *
     * @author Mohamed ElGhamrawy
     * */
    public static boolean isBinaryOperator(String token)
    {
        switch (token) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                return true;
            default:
                return false;
        }
    }

    /**
     * A method which assists computation methods by checking if a token is an operator.
     *
     * It supports the following unary operators:
     * square-rooting (sqrt),
     * factorialization (!),
     * sine (sin),
     * cosine (cos),
     * tangent (tan),
     * natural exponentiation (exp),
     * and multiplication by pi (pi).
     *
     * @parameters token a String holding the token to be checked
     * @return  a boolean, with true meaning that the token is an operator
     *
     * @author Mohamed ElGhamrawy
     * */
    public static boolean isUnaryOperator(String token)
    {
        switch (token) {
            case "sqrt":
            case "!":
            case "sin":
            case "cos":
            case "tan":
            case "exp":
                return true;
            default:
                return false;
        }
    }

    /**
     * A method which assists computation methods by calculating factorials recursively.
     *
     * @parameters n an integer to be factorialized
     * @return  an integer with the value of n!
     *
     * @author Mohamed ElGhamrawy
     * */
    public static int factorial(int n) {
        return (n == 1 || n == 0) ? 1 : n*factorial(n-1);

    }

    /**
     * A method which assists the convert() method by checking if a token is an operand of type double.
     *
     * @parameters token a String holding the token to be checked
     * @return  a boolean, with true meaning that the token is an operand of type double.
     *
     * @author Hussam Kayed
     * */
    public static boolean checkDouble(String token)
    {
        try
        {
            Double.parseDouble(token);
        } catch(NumberFormatException exception)
        {
            return false;
        }
        return true;
    }
}
