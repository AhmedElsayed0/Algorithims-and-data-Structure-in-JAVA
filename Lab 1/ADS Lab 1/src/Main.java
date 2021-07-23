/**
 * class is just created for final testing
 */
public class Main {
    /**
     * Main function that is created solely for testing (main2)
     * @param args
     */
    public static void main(String [] args)
    {
        ArithmeticTerm myTerm = new ArithmeticTerm(
                "( ( ( ( ( sqrt 4 ) * ( sqrt 9 ) ) - 6 ) * 6 ) + 4 )");
        //ArithmeticTerm myTerm2 = new ArithmeticTerm("( ( 4.3 * 1e-1 ) - .4 )");
        System.out.println(myTerm.getReversedExpression());
        System.out.println(myTerm.toString());
        System.out.println(myTerm.convert());
        System.out.println(myTerm.evaluate());
    }
}
