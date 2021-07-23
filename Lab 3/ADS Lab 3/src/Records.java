import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class Records:
 * Test of stability of divers sorting algorithms.<br>
 *
 * @author Wolfgang Renz
 * @version Nov. 3, 2020
 */
public class Records {
    // size for stable sort tests in main program:
    private final static int N = 8;
    // instance variables - replace the example below with your own
    private final int size;
    private Record[] recs;
    private List<Record> list;

    /**
     * Constructor for objects of class Records
     */
    public Records(int size) {
        // initialise instance variables
        this.size = size;
        recs = new Record[size];
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            recs[i] = new Record(new Integer(i / 4), "o" + i);
        }
        list = Arrays.asList(recs);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public void shuffle() {
        Collections.shuffle(list, new Random(System.currentTimeMillis()));
        recs = list.toArray(recs); // not required, why?
    }

    public void insertionSort() {
        Insertion.sort(recs);
        list = Arrays.asList(recs); // not required, why?
    }

    public void selectionSort() {
        Selection.sort(recs);
        list = Arrays.asList(recs); // not required, why?
    }

    public static void main(String[] a) {
//        Records recs= new Records(N);
//        System.out.println( recs);
//
//        System.out.println("\n Stable Sort Test for Insertion Sort:");
//        recs.shuffle();
//        System.out.println( recs);
//        recs.insertionSort();
//        System.out.println( recs);
//
//        System.out.println("\n Stable Sort Test for Selection Sort:");
//        recs.shuffle();
//        System.out.println( recs);
//        recs.selectionSort();
//        System.out.println( recs);

        Integer[] testArray = new Integer[]{1, 2, 3, 5, 4, 6, 7, 8};

        System.out.println("Problem 6 isSorted() test");
        if (Sort.isSorted(testArray, 2, 7))
        System.out.println("testArray is sorted ");
        else
        System.out.println("testArray isn't sorted");

        System.out.println("Problem 6 isPartitioned() test");
        if(Sort.isPartitioned(testArray, 0, 2, 7))
            System.out.println("testArray is partitioned");
        else
            System.out.println("testArray isn't partitioned");


        Integer[] unsortedArray1 = new Integer[]{20, 19, 68, 45};
        Integer[] unsortedArray2 = new Integer[]{20, 19, 68, 45, 2, 1, 0, 4};
        Integer[] unsortedArray3 = new Integer[]{20, 19, 68, 45, 2, 1, 0, 4, 24, 18, 76, 67, 55, 43, 32, 23};
        Integer[] unsortedArray4 = new Integer[]{20, 19, 68, 45, 2, 1, 0, 4, 24, 18, 76, 67, 55, 43, 32, 23, 14, 7, 3, 90, 27, 13, 12, 11, 5,
        8, 6, 70, 71, 62, 36, 34};


        System.out.println("Problem 8 QuickSort test 1");
        System.out.print("Original Array: ");
        for (Integer integer4 : unsortedArray1) System.out.print(integer4 + " ");

        System.out.println(" ");

        System.out.print("Sorted Array: ");
        QuickSort.sort(unsortedArray1);
        for (Integer integer3 : unsortedArray1) System.out.print(integer3 + " ");
        System.out.println(" ");

        System.out.println("isSorted is " + Sort.isSorted(unsortedArray1, 0, unsortedArray1.length));

        System.out.println("Number of Comparisons is " + QuickSort.comparisons);
        System.out.println("Number of Copies is " + QuickSort.copies);

        //Resetting counters
        QuickSort.comparisons = 0;
        QuickSort.copies = 0;
        System.out.println("\n");

        System.out.println("Problem 8 QuickSort test 2");
        System.out.print("Original Array: ");
        for (Integer integer2 : unsortedArray2) System.out.print(integer2 + " ");

        System.out.println(" ");

        System.out.print("Sorted Array: ");
        QuickSort.sort(unsortedArray2);
        for (Integer integer1 : unsortedArray2) System.out.print(integer1 + " ");
        System.out.println(" ");

        System.out.println("isSorted is " + Sort.isSorted(unsortedArray2, 0, unsortedArray2.length));

        System.out.println("Number of Comparisons is " + QuickSort.comparisons);
        System.out.println("Number of Copies is " + QuickSort.copies);

        //Resetting counters
        QuickSort.comparisons = 0;
        QuickSort.copies = 0;
        System.out.println("\n");

        System.out.println("Problem 8 QuickSort test 3");
        System.out.print("Original Array: ");
        for (Integer element : unsortedArray3) System.out.print(element + " ");

        System.out.println(" ");

        System.out.print("Sorted Array: ");
        QuickSort.sort(unsortedArray3);
        for (Integer item : unsortedArray3) System.out.print(item + " ");
        System.out.println(" ");

        System.out.println("isSorted is " + Sort.isSorted(unsortedArray3, 0, unsortedArray3.length));

        System.out.println("Number of Comparisons is " + QuickSort.comparisons);
        System.out.println("Number of Copies is " + QuickSort.copies);

        //Resetting counters
        QuickSort.comparisons = 0;
        QuickSort.copies = 0;
        System.out.println("\n");

        System.out.println("Problem 8 QuickSort test 4");
        System.out.print("Original Array: ");
        for (Integer value : unsortedArray4) System.out.print(value + " ");

        System.out.println(" ");

        System.out.print("Sorted Array: ");
        QuickSort.sort(unsortedArray4);
        for (Integer integer : unsortedArray4) System.out.print(integer + " ");
        System.out.println(" ");

        System.out.println("isSorted is " + Sort.isSorted(unsortedArray4, 0, unsortedArray4.length));

        System.out.println("Number of Comparisons is " + QuickSort.comparisons);
        System.out.println("Number of Copies is " + QuickSort.copies);

        //Resetting counters
        QuickSort.comparisons = 0;
        QuickSort.copies = 0;
        System.out.println("\n");

        Integer[] unsortedArrayInsertion1 = new Integer[]{20, 19, 68, 45};
        Integer[] unsortedArrayInsertion2 = new Integer[]{20, 19, 68, 45, 2, 1, 0, 4};
        Integer[] unsortedArrayInsertion3 = new Integer[]{20, 19, 68, 45, 2, 1, 0, 4, 24, 18, 76, 67, 55, 43, 32, 23};
        Integer[] unsortedArrayInsertion4 = new Integer[]{20, 19, 68, 45, 2, 1, 0, 4, 24, 18, 76, 67, 55, 43, 32, 23, 14, 7, 3, 90, 27, 13, 12, 11, 5,
                8, 6, 70, 71, 62, 36, 34};

        System.out.println("Problem 8 InsertionSort test 1");
        System.out.print("Original Array: ");
        for (Integer integer : unsortedArrayInsertion1) System.out.print(integer + " ");

        System.out.println(" ");

        System.out.print("Sorted Array: ");
        Insertion.sort(unsortedArrayInsertion1);
        for (Integer integer : unsortedArrayInsertion1) System.out.print(integer + " ");
        System.out.println(" ");

        System.out.println("isSorted is " + Sort.isSorted(unsortedArrayInsertion1, 0, unsortedArrayInsertion1.length));

        System.out.println("Number of Comparisons is " + Insertion.comparisons);
        System.out.println("Number of Copies is " + Insertion.copies);
        System.out.println("\n");
        //Resetting counters
        Insertion.comparisons = 0;
        Insertion.copies = 0;

        System.out.println("Problem 8 InsertionSort test 2");
        System.out.print("Original Array: ");
        for (Integer integer : unsortedArrayInsertion2) System.out.print(integer + " ");

        System.out.println(" ");

        System.out.print("Sorted Array: ");
        Insertion.sort(unsortedArrayInsertion2);
        for (Integer integer : unsortedArrayInsertion2) System.out.print(integer + " ");
        System.out.println(" ");

        System.out.println("isSorted is " + Sort.isSorted(unsortedArrayInsertion2, 0, unsortedArrayInsertion2.length));

        System.out.println("Number of Comparisons is " + Insertion.comparisons);
        System.out.println("Number of Copies is " + Insertion.copies);

        //Resetting counters
        Insertion.comparisons = 0;
        Insertion.copies = 0;

        System.out.println("\n");

        System.out.println("Problem 8 InsertionSort test 3");
        System.out.print("Original Array: ");
        for (Integer integer : unsortedArrayInsertion3) System.out.print(integer + " ");

        System.out.println(" ");

        System.out.print("Sorted Array: ");
        Insertion.sort(unsortedArrayInsertion3);
        for (Integer integer : unsortedArrayInsertion3) System.out.print(integer + " ");
        System.out.println(" ");

        System.out.println("isSorted is " + Sort.isSorted(unsortedArrayInsertion3, 0, unsortedArrayInsertion3.length));

        System.out.println("Number of Comparisons is " + Insertion.comparisons);
        System.out.println("Number of Copies is " + Insertion.copies);

        //Resetting counters
        Insertion.comparisons = 0;
        Insertion.copies = 0;

        System.out.println("\n");

        System.out.println("Problem 8 InsertionSort test 4");
        System.out.print("Original Array: ");
        for (Integer integer : unsortedArrayInsertion4) System.out.print(integer + " ");

        System.out.println(" ");

        System.out.print("Sorted Array: ");
        Insertion.sort(unsortedArrayInsertion4);
        for (Integer integer : unsortedArrayInsertion4) System.out.print(integer + " ");
        System.out.println(" ");

        System.out.println("isSorted is " + Sort.isSorted(unsortedArrayInsertion4, 0, unsortedArrayInsertion4.length));

        System.out.println("Number of Comparisons is " + Insertion.comparisons);
        System.out.println("Number of Copies is " + Insertion.copies);

        //Resetting counters
        Insertion.comparisons = 0;
        Insertion.copies = 0;

        System.out.println("\n");
    }
}
