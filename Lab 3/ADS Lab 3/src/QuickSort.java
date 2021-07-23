import java.util.Arrays;

import static java.util.Collections.shuffle;

public class QuickSort extends Sort {
    public static void sort(Comparable[] a)
    {
        shuffle(Arrays.asList(a));
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int l, int r)
    {
        if (r <= l) return;
        int m = partition(a, l, r);
        sort(a, l, m-1);
        sort(a, m+1, r);
        assert Sort.isSorted(a, l, r);
        assert Sort.isPartitioned(a, l, m, r);
    }

    private static int partition(Comparable[] a, int l, int r)
    {
        int i = l - 1;
        int j = r;
        while(true)
        {
            while (Sort.less(a[++i], a[r]))
                if (i == r) break;
            while (Sort.less(a[r], a[--j]))
                if (j == l) break;
            if (i >= j) break;
            Sort.exch(a, i, j);
        }
        Sort.exch(a, i, r);
        return i;
    }
}

