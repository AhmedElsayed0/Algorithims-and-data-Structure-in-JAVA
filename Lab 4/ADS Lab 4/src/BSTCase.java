import java.util.HashMap;

public class BSTCase extends UseCase {
    private BST<Integer,Integer> testingTree;
    private HashMap<Integer, Integer> iteratingMap;

    public BSTCase(InputCase input, int size)
    {
        super(input, size);
        testingTree = new BST<>();
        iteratingMap = new HashMap<>();
        generateOccurrences();
        generateExampleTrees();
        testingTree.meanTreeDepth();
        testingTree.maxTreeDepth();
    }

    public void generateExampleTrees()
    {
        for(Integer currentKey: iteratingMap.keySet())
        {
            testingTree.put(currentKey, iteratingMap.get(currentKey));
        }
    }

    public void printData()
    {
        System.out.printf("                          ,%-12d                    ,%-12f                       " ,testingTree.getMaxTreeDepth(), testingTree.getMeanDepth());
    }

    public void generateOccurrences()
    {
        for(int i = 0; i < testingArray.length; i++)
        {
            if(iteratingMap.containsKey(testingArray[i]))
            {
                int newNumberOfOccurrences = iteratingMap.get(testingArray[i]);
                iteratingMap.put((Integer) testingArray[i], ++newNumberOfOccurrences);
            }
            else
                iteratingMap.put((Integer) testingArray[i], 1);
        }
    }
}
