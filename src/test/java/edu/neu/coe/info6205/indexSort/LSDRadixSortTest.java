package edu.neu.coe.info6205.indexSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LSDRadixSortTest
{
    @Test
    public void basicSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortLSD = new LSDRadixSort();
        radixSortLSD.mutatingSort(arr);
        assertArrayEquals(arr, new String[]{"aaa", "abc", "add"});
    }

    @Test
    public void basicPartialSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortLSD = new LSDRadixSort();
        radixSortLSD.sort(arr, 0, 1);
        assertArrayEquals(arr, new String[]{"abc", "add", "aaa"});
    }

    @Test
    public void basicPartialSortTest2()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortLSD = new LSDRadixSort();
        radixSortLSD.sort(arr, 1, 2);
        assertArrayEquals(arr, new String[]{"abc", "aaa", "add"});
    }

    @Test
    public void sortChineseWithCutoffTest()
    {
        String[][] chineseStrings = TestUtils.getChineseStrings();
        SortInterface radixSortLSD = new LSDRadixSort();

        for (String[] strArr : chineseStrings)
        {
            String[] sorted = TestUtils.getSortedArray(strArr);
            radixSortLSD.mutatingSort(strArr);

            assertArrayEquals(strArr, sorted);
        }
    }

    @Test
    public void sortChineseWithoutCutoffTest()
    {
        String[][] chineseStrings = TestUtils.getChineseStrings();
        SortInterface radixSortLSD = new LSDRadixSort(0);

        for (String[] strArr : chineseStrings)
        {
            String[] sorted = TestUtils.getSortedArray(strArr);
            radixSortLSD.mutatingSort(strArr);

            assertArrayEquals(strArr, sorted);
        }
    }
}