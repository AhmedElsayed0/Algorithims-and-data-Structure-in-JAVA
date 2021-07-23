import java.util.Random;

/**
 * This class UseCase is for demonstration only!
 * It shows how to use enum in switch/case, loop over it's values and it's name printing.
 *
 * @author Wolfgang Renz, HAW Hamburg
 * @version Nov. 14, 2020
 */
public abstract class UseCase {
    public static final int kmax = 15;
    public static final int Nmax = (int) Math.round(Math.pow(2, kmax));
    public static final int M = 20;  // sample size

    // instance variables:
    private final InputCase inputCase;
    protected static Comparable[] testingArray;
    private static int size;
    private int iterations; // for averaging a sample
    protected int comp, copy; // results to be written by sub-class

    abstract void generateExampleTrees();
    abstract void printData();

    protected UseCase(InputCase inputCase, int size) {
        // initialise instance variables
        this.testingArray = new Comparable[size];
        this.size = size;
        this.inputCase = InputCase.RANDOM;
        if (inputCase == InputCase.AVG) {
            iterations = M;
        } else {
            iterations = size;
        }

        switch (inputCase) {

            case RANDOM:
                initRandom();
                break;

            default:
                ;
        }
    }


    @Override
    public String toString() {
        return inputCase + " case for size " + size + ":";
    }

    String getResults(String format) {

        String results = "\n";
        printData();
        return results;
    }

    public void writeResults(String format) {
        System.out.printf("%-6d", size); // first part of suitable format
        format = format.substring(0);   // skip part consumed
        System.out.printf(getResults(format));
    }

    public static void makeTable(String sortCase) {

        int N = 1;
        System.out.println(InputCase.RANDOM + " case:");
        System.out.println("N                                MaxTreeDepth                         MeanTreeDepth                            ");
        String format = "";

        for (int k = 1; k <= kmax; k++) {
            N *= 2;
            UseCase usecase;
            if (sortCase.equals("BSTCase")) {
                for (int i = 1; i <= 20; i++) {
                    UseCase.initRandom();
                    usecase = new BSTCase(InputCase.RANDOM, N);
                    usecase.writeResults(format);
                }
            } else if (sortCase.equals("RBTCase")) {
                for (int i = 1; i <= 20; i++) {
                    UseCase.initRandom();
                    usecase = new RBTCase(InputCase.RANDOM, N);
                    usecase.writeResults(format);
                }
            } else
                usecase = new BSTCase(InputCase.RANDOM, 1); // if executed, something is wrong in the input
        }
    }


    public static void main(String arg[]) {
        makeTable("BSTCase");
        makeTable("RBTCase");
    }

    public static void initRandom() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            testingArray[i] = random.nextInt();
        }
    }


}