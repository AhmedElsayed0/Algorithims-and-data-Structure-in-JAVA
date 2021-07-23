/**
 * This class is created to implement a Stack<Double> using an array
 */
public class StackOfDoubles {
    private Double[] arrayOfDoubles;
    private int stackItemsNumber;

    public StackOfDoubles() {
        arrayOfDoubles = new Double[1];
        stackItemsNumber = 0;
    }

    public boolean empty()
    {
        if(arrayOfDoubles[0] == null)
            return true;
        return false;
    }

    public void push(double newItem)
    {
        if(stackItemsNumber > 2)
        {
            if (arrayOfDoubles[(stackItemsNumber / 2) - 1] != null)          // In case the stack is half-filled, resizing is done
                dynamicIncreasing();
        }
        arrayOfDoubles[stackItemsNumber] = newItem;
        stackItemsNumber++;
    }

    public double pop()
    {
        double poppedItem = 0.0;
        if(!empty())
        {
            poppedItem = arrayOfDoubles[stackItemsNumber - 1];
            arrayOfDoubles[stackItemsNumber-1] = null;
            stackItemsNumber--;
        }
        else
        {
            System.out.println("There are no items in the stack to be shown");
        }
        if(stackItemsNumber > 4)
        {
            if (arrayOfDoubles[(stackItemsNumber / 4) - 1] == null)
                dynamicDecreasing();            // in case the stack has unused memory, resizing is done

        }
        return poppedItem;
    }

    /**
     * resizing in case too many items have been added to the array and we need more stack memory to be booked
     */
    public void dynamicIncreasing()
    {
        Double[] copyingArray = this.arrayOfDoubles;
        arrayOfDoubles = new Double[(stackItemsNumber*stackItemsNumber)/2];
        for(int i=0; i < stackItemsNumber; i++)
            arrayOfDoubles[i] = copyingArray[i];
    }

    /**
     * resizing in case too many items have been removed from the array and we have
     * useless booked memory that needs to be freed
     */
    public void dynamicDecreasing()
    {
        Double[] copyingArray = this.arrayOfDoubles;
        arrayOfDoubles = new Double[stackItemsNumber/2];
        for(int i=0; i < stackItemsNumber; i++)
            arrayOfDoubles[i] = copyingArray[i];
    }
}
