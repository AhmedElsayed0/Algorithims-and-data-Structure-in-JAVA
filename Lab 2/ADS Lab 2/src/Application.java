public class Application {
    public static void main(String[] args)
    {
        ArithmeticTerm firstTerm = new ArithmeticTerm(
                "( ( ( ( sin ( 4 * ( pi / 3 ) ) ) * ( exp ( -1 * ( ( sqrt 2 ) - 1 ) / 8 ) ) ) ) / ( sqrt ( 6 * pi ) ) )");
        ArithmeticTerm secondTerm = new ArithmeticTerm("( ( ( sqrt 4 ) * ( 2 ^ 3 ) ) / 2 )");
        ArithmeticTerm thirdTerm = new ArithmeticTerm("( ( ( ( ( ( 5 ^ 4 ) ^ 2 ) ^ 4 ) + 9 ) - 8 ) / 2 )");
        ArithmeticTerm fourthTerm = new ArithmeticTerm("exp ( ( ( sin ( 30 ) * 90 ) + ( 100 / 200 ) ) )");
        ArithmeticTerm fifthTerm = new ArithmeticTerm("( 2 ^ exp ( ( ( sin ( 30 ) * 90 ) + ( 100 / 200 ) ) ) )");
        ArithmeticTerm sixthTerm = new ArithmeticTerm("( 9 * ( 3 ^ ( 2 ^ exp ( ( ( sin ( 30 ) * 90 ) + ( 100 / 200 ) ) ) ) ) )");

        firstTerm.convert();
        secondTerm.convert();
        thirdTerm.convert();
        fourthTerm.convert();
        fifthTerm.convert();
        sixthTerm.convert();

        Tree firstTree = new Tree(firstTerm.toString());
        Tree secondTree = new Tree(secondTerm.toString());
        Tree thirdTree = new Tree(thirdTerm.toString());
        Tree fourthTree = new Tree(fourthTerm.toString());
        Tree fifthTree = new Tree(fifthTerm.toString());
        Tree sixthTree = new Tree(sixthTerm.toString());

        firstTree.infixGenerator();
        secondTree.infixGenerator();
        thirdTree.infixGenerator();
        fourthTree.infixGenerator();
        fifthTree.infixGenerator();
        sixthTree.infixGenerator();
    }
}
