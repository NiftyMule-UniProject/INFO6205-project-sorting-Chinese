package edu.neu.coe.info6205.compSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.UnicodeSortingHelper;

public class DualPivotSort implements SortInterface {

    private final UnicodeSortingHelper helper;
    public DualPivotSort()
    {
        this.helper = new UnicodeSortingHelper();
    }

    public DualPivotSort(UnicodeSortingHelper helper)
    {
        this.helper = helper;
    }

    @Override
    public void sort(String[] arr, int from, int to){
        if (from < to)
        {

            // piv[] stores left pivot and right pivot.
            // piv[0] means left pivot and
            // piv[1] means right pivot
            int[] piv;
            piv = partition(arr, from, to);

            sort(arr, from, piv[0] - 1);
            sort(arr, piv[0] + 1, piv[1] - 1);
            sort(arr, piv[1] + 1, to);
        }
    }
    int[] partition(String[] arr, int low, int high)
    {
        if (helper.compare(arr[low] , arr[high]) > 0)
            helper.swap(arr, low, high);

        // p is the left pivot, and q
        // is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1;
                String p = arr[low], q = arr[high];

        while (k <= g)
        {

            // If elements are less than the left pivot
            if (helper.compare(arr[k] , p) < 0)
            {
                helper.swap(arr, k, j);
                j++;
            }

            // If elements are greater than or equal
            // to the right pivot
            else if (helper.compare(arr[k],q)>=0)
            {
                while (helper.compare(arr[g], q) > 0 && k < g)
                    g--;

                helper.swap(arr, k, g);
                g--;

                if (helper.compare(arr[k] , p)<0)
                {
                    helper.swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // Bring pivots to their appropriate positions.
        helper.swap(arr, low, j);
        helper.swap(arr, high, g);

        // Returning the indices of the pivots
        // because we cannot return two elements
        // from a function, we do that using an array.
        return new int[] { j, g };
    }
}
