package edu.neu.coe.info6205.compSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.UnicodeSortingHelper;

public class TimSort implements SortInterface
{

    private final int MIN_MERGE;
    private final UnicodeSortingHelper helper;

    public TimSort()
    {
        this.MIN_MERGE = 32;
        this.helper = new UnicodeSortingHelper();
    }

    public TimSort(UnicodeSortingHelper helper)
    {
        this.MIN_MERGE = 32;
        this.helper = helper;
    }

    public TimSort(int minMerge)
    {
        this.MIN_MERGE = minMerge;
        this.helper = new UnicodeSortingHelper();
    }

    public TimSort(int minMerge, UnicodeSortingHelper helper)
    {
        this.MIN_MERGE = minMerge;
        this.helper = helper;
    }

    public int minRunLength(int n)
    {
        assert n >= 0;

        int r = 0;
        while (n >= MIN_MERGE)
        {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    void insertionSort(String[] arr, int from, int to)
    {
        for (int i = from + 1; i <= to; i++)
        {
            int j = i;
            while (j > from && helper.compare(arr[j - 1], arr[j]) > 0)
            {
                helper.swap(arr, j - 1, j);
                j--;
            }
        }
    }

    // Merge function merges the sorted runs
    void merge(String[] arr, int l, int m, int r)
    {
        if (m >= r) return;
        int len1 = m - l + 1, len2 = r - m;
        String[] left = new String[len1];
        String[] right = new String[len2];
        System.arraycopy(arr, l, left, 0, len1);
        System.arraycopy(arr, m + 1, right, 0, len2);

        int i = 0;
        int j = 0;
        int k = l;

        while (i < len1 && j < len2)
        {
            if (helper.compare(left[i], right[j]) < 0)
            {
                arr[k] = left[i];
                i++;
            }
            else
            {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < len1)
        {
            arr[k] = left[i];
            k++;
            i++;
        }

        while (j < len2)
        {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    //TimSort implementation
    @Override
    public void sort(String[] arr, int from, int to)
    {
        int n = to - from + 1;
        int minRun = minRunLength(MIN_MERGE);

        for (int i = from; i < to + 1; i += minRun)
        {
            insertionSort(arr, i, Math.min(i + MIN_MERGE - 1, to));
        }

        for (int size = minRun; size < n; size = 2 * size)
        {
            for (int left = from; left < to + 1; left += 2 * size)
            {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), to);
                merge(arr, left, mid, right);
            }
        }
    }
}



