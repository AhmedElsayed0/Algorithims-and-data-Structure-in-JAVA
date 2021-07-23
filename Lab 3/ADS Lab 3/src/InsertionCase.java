public class InsertionCase extends UseCase {

    public InsertionCase(InputCase inputCase, int size)
    {
        super(inputCase, size);
    }

    public void sortAndCount()
    {
        Insertion.sort(testingArray);

    }

}
