/**
 * Enum InputCase.
 * 
 * <ol> <li> SORTED, i.e. input is an array of random numbers that have been pre-sorted in ascending order
 * </li><li> REVERSE, i.e. input is an array of random numbers that have been pre-sorted in descending order
 * </li><li> RANDOM, i.e. input is an array of random numbers as provided by the random-number generator
 * </li><li> and AVERAGE, i.e. the average for a sample of M RANDOM input cases 
 *             taken subsequently with a starting seed value.
 * </li></ol>
 * 
 * @author Wolfgang Renz, HAW Hamburg 
 * @version Oct. 2020
 */
public enum InputCase
{    
    AVG("Average"), 

    SORTED("Presorted"), 

    REVERSE("Reverse"), 

    RANDOM("Random example");
    
    String name;
    
    InputCase(String name)
    {
        this.name=name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
