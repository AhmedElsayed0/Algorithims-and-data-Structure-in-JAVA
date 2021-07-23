import java.util.Random;

/**
 *  This class UseCase is for demonstration only!
 *  It shows how to use enum in switch/case, loop over it's values and it's name printing.
 * 
 * @author Wolfgang Renz, HAW Hamburg 
 * @version Nov. 14, 2020
 */
public abstract class UseCase
{
    public static final int kmax= 15;
    public static final int Nmax= (int) Math.round(Math.pow(2, kmax));
    public static final int M= 20;  // sample size

    // instance variables:
    private final InputCase inputCase;
    protected Comparable[] testingArray;
    private int size;
    private int iterations; // for averaging a sample
    protected int comp, copy; // results to be written by sub-class

    abstract void sortAndCount();

    protected UseCase(InputCase inputCase, int size)
    {
        // initialise instance variables
        this.testingArray = new Comparable[size];
        this.size = size;
        this.inputCase = inputCase;
        if (inputCase == InputCase.AVG){
            iterations = M;
        } else{
            iterations = size;
        }

        switch(inputCase){
            case SORTED:
             initAscending();
            break;
            case REVERSE:
             initDescending();
            break;
            case RANDOM:
            case AVG:
             initRandom();
            break;

            default:
            ;
        }
    }


    @Override
    public String toString()
    {
        return inputCase + " case for size " + size + ":";
    }

    String getResults(String format)
    {
        sortAndCount();
        String results = "\n";
        System.out.printf("                          |%-12d                    |%-12d                       " ,Sort.comparisons, Sort.copies);
        Sort.resetCounters();
        return results; 
    }

    public void writeResults(String format)
    {
        System.out.printf("%-6d", size); // first part of suitable format
        format= format.substring(0);   // skip part consumed
        System.out.printf(getResults(format));
    }

    public static void makeTable(String sortCase)
    {
        for (InputCase tableCase: InputCase.values()){
            int N = 1;
            System.out.println(tableCase + " case:");
            System.out.println("N                                #(cmp)                         #(copies)                            ");
            String format= "";

            for(int k = 1; k <= kmax; k++){
                N*= 2;
                UseCase usecase;
                if (sortCase.equals("QuickCase")){
                    usecase= new QuickCase(tableCase, N);
                }
                else if (sortCase.equals("InsertionCase")){
                    usecase= new InsertionCase(tableCase, N);
                }
                else
                    usecase = new SortCase(tableCase, N); // if executed, something is wrong in the input
                
                if(usecase != null){
                    usecase.writeResults(format);
                }
            }
        }
    }

    public static void main(String arg [] )
    {
        makeTable("QuickCase");
        makeTable("InsertionCase");

    }

    public void initRandom()
    {
        Random random = new Random();
        for(int i = 0; i < size; i++)
        {
            testingArray[i] = random.nextInt();
        }
    }

    public void initDescending()
    {
        for(int i = size; i > 0; i--)
        {
            testingArray[size-i] = i;
        }
    }

    public void initAscending()
    {
        for(int i = 0; i < size; i++)
        {
            testingArray[i] = i;
        }
    }

}