package edu.neu.coe.info6205.indexSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MSDRadixSortTest
{
    @Test
    public void basicSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortMSD = new MSDRadixSort();
        radixSortMSD.mutatingSort(arr);
        assertArrayEquals(arr, new String[]{"aaa", "abc", "add"});
    }

    @Test
    public void basicPartialSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortMSD = new MSDRadixSort();
        radixSortMSD.sort(arr, 0, 1);
        assertArrayEquals(arr, new String[]{"abc", "add", "aaa"});
    }

    @Test
    public void basicPartialSortTest2()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortMSD = new MSDRadixSort();
        radixSortMSD.sort(arr, 1, 2);
        assertArrayEquals(arr, new String[]{"abc", "aaa", "add"});
    }

    @Test
    public void sortChineseWithCutoffTest()
    {
        String[][] chineseStrings = TestUtils.getChineseStrings();
        SortInterface radixSortMSD = new MSDRadixSort();

        for (String[] strArr : chineseStrings)
        {
            String[] sorted = TestUtils.getSortedArray(strArr);
            radixSortMSD.mutatingSort(strArr);

            assertArrayEquals(strArr, sorted);
        }
    }

    @Test
    public void sortChineseWithoutCutoffTest()
    {
        String[][] chineseStrings = TestUtils.getChineseStrings();
        SortInterface radixSortMSD = new MSDRadixSort(0);

        for (String[] strArr : chineseStrings)
        {
            String[] sorted = TestUtils.getSortedArray(strArr);
            radixSortMSD.mutatingSort(strArr);

            assertArrayEquals(strArr, sorted);
        }
    }
}