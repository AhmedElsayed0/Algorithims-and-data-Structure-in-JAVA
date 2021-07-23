/**
 * Class Record defines simple records consisting of an integer and a string. <br>
 * Sorting shall be done with respect to the integer.<br>
 * Test of stability of divers sorting algorithms.<br>
 *
 * @author Wolfgang Renz
 * @version Nov. 3, 2020
 */
public class Record implements Comparable<Record>
{
    // instance variables - replace the example below with your own
    private Integer n;
    private String s;

    /**
     * Constructor for objects of class Record
     */
    public Record(int i, String str)
    {
        // initialise instance variables
        n= i;
        s= str;
    }

    @Override
    public String toString()
    {
        return n+"-"+s;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  rec  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int compareTo(Record rec)
    {
        //return s.compareTo(rec.s); //alternative sorting w.r.t. the string
        return n.compareTo(rec.n);
    }
}
