package edu.neu.coe.info6205.basicSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.UnicodeSortingHelper;

public class InsertionSort implements SortInterface
{
    private final UnicodeSortingHelper helper;

    public InsertionSort()
    {
        this.helper = new UnicodeSortingHelper();
    }

    public InsertionSort(UnicodeSortingHelper helper)
    {
        this.helper = helper;
    }

    @Override
    public void sort(String[] arr, int from, int to)
    {
        for (int i = from + 1; i <= to; ++i)
        {
            int j = i;
            while (j > from && helper.compare(arr[j - 1], arr[j]) > 0)
            {
                helper.swap(arr, j - 1, j);
                j--;
            }
        }
    }
}
