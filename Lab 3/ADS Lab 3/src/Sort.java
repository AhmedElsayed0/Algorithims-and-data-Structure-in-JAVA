/**
 * Abstract class Sort is responsible for operations and their counting.
 *
 * @author Wolfgang Renz
 * @version Nov. 3, 2020
 */
public abstract class Sort
{
    protected static int comparisons = 0;
    protected static int copies = 0;

    protected static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
        copies += 3;
    }

    protected static boolean isPartitioned(Comparable a[], int left, int border, int right)
    {
        Comparable[] firstPartArray = new Comparable[border-left+1];
        Comparable[] secondPartArray = new Comparable[right-border+1];
        Comparable maxOfFirstPartArray = 0;

        System.arraycopy(a, left, firstPartArray, 0, border-left);
        System.arraycopy(a, border, secondPartArray, 0, right-border);

        for(int i = 0; i < border-left; i++)
        {
            if(less(maxOfFirstPartArray, firstPartArray[i]))
            {
                maxOfFirstPartArray =  firstPartArray[i];
            }
            else
                continue;

        }

        for(int i = 0; i < right - border; i++)
        {
            if(less(maxOfFirstPartArray,secondPartArray[i]))
                continue;
            else
                return false;
        }
        return true;

    }

    protected static boolean isSorted(Comparable[] a, int left, int right) {
        boolean answer = true;
        Comparable[] subArray = new Comparable[right-left+1]; // declare a new array to hold subarray to be tested
        System.arraycopy(a, left, subArray, 0, right-left); // copy elements from array into subarray
        // Test if subArray is sorted
        for(int i = 0; i < right-left; i++) {
            Comparable element;
            Comparable nextElement;
            if (!(i == right - left - 1)) {
                element = subArray[i];
                nextElement = subArray[i + 1];
            } else
                break;
            if (less(element, nextElement)) {
                continue;
            } else
                answer = false;
        }
        return answer;
    }

    protected static boolean less(Comparable v, Comparable w)
    {
        comparisons++;
        return (v.compareTo(w) < 0);
    }

    public static int getComparisons() {
        return comparisons;
    }

    public static int getCopies() {
        return copies;
    }

    public static void resetCounters()
    {
        copies = 0;
        comparisons = 0;
    }
}
