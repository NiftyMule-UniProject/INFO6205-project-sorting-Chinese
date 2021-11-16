package edu.neu.coe.info6205.basicSort;

import edu.neu.coe.info6205.SortInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class InsertionSortTest
{

    @Test
    public void basicSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface insertionSort = new InsertionSort();
        insertionSort.mutatingSort(arr);
        assertArrayEquals(arr, new String[]{"aaa", "abc", "add"});
    }

    @Test
    public void basicPartialSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface insertionSort = new InsertionSort();
        insertionSort.sort(arr, 0, 1);
        assertArrayEquals(arr, new String[]{"abc", "add", "aaa"});
    }

    @Test
    public void basicPartialSortTest2()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface insertionSort = new InsertionSort();
        insertionSort.sort(arr, 1, 2);
        assertArrayEquals(arr, new String[]{"abc", "aaa", "add"});
    }
}