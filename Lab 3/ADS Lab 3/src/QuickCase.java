public class QuickCase extends UseCase {
    public QuickCase(InputCase inputCase, int size)
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
        QuickSort.sort(this.testingArray);

    }

    private void changeCounters()
    {
        copy = Sort.copies;
        comp = Sort.comparisons;
    }
}
