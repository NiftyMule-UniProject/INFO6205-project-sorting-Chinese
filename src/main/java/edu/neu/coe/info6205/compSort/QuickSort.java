package edu.neu.coe.info6205.compSort;

import edu.neu.coe.info6205.SortInterface;
import java.util.List;
import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.UnicodeSortingHelper;

public class QuickSort implements SortInterface {

    private final UnicodeSortingHelper helper;
    public QuickSort()
    {
        this.helper = new UnicodeSortingHelper();
    }

    public QuickSort(UnicodeSortingHelper helper)
    {
        this.helper = helper;
    }

    @Override
    public String [] sort(String [] a )
    {
        sort(a, 0, a.length - 1);
        return a;
    }

    public  void sort(String [] a, int lo, int hi)
    {

        int i = lo;
        int j = hi;


        if (j - i >= 1)
        {
            String pivot = a[i];
            while (j > i)
            {
                while (helper.compare(a[i],pivot) <= 0 && i < hi && j > i){
                    i++;
                }
                while (helper.compare(a[j],pivot) >= 0 && j > lo && j >= i){
                    j--;
                }
                if (j > i)
                    helper.swap(a, i, j);
            }
            helper.swap(a, lo, j);
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }
    }



//    public int partition(String [] a, int lo, int hi)
//    {
//
//        int pivot = lo;
//
//        while (lo < hi){
//            while (lo < hi && helper.compare(a[hi] , pivot) < 0) hi--;
//           // a.set(lo, a.get(hi));
//            a[lo] = a[hi];
//
//            while (lo < hi && helper.compare(a[lo] , pivot) > 0) lo++;
//            //a.set(hi, a.get(lo));
//            a[hi] = a[lo];
//        }
//
//       // a.set(lo, pivot);
//        a[lo] = a[pivot];
//
//        return lo;
//    }


    /*private void swap(List<T> list, int i, int j) {
        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }*/

    /*private  boolean less(T a, T b) {
        if(a.compareTo(b) < 0) {
            return true;
        }
        return false;
    }*/
}
