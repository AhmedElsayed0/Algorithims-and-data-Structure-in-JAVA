
/**
 * This class is for demonstration purposes only!
 *
 * @author Wolfgang Renz, HAW Hamburg 
 * @version Nov. 14, 2020
 */
public class SortCase extends UseCase
{

    /**
     * Constructor for objects of class SortCase
     */
    public SortCase(InputCase inputCase, int size)
    {
        super(inputCase, size);
    }
    
    @Override
    public String toString()
    {
        return "Sorting method, " + super.toString();
    }
    
    public void sortAndCount()
    {
        // call suitable sort method
        comp= 0; // Sort.getComp();
        copy= 0; // Sort.getCopy();
    }
}
