package edu.neu.coe.info6205.compSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TimSortTest {


    @Test
    public void basicSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface timSort = new TimSort();
        timSort.mutatingSort(arr);
        assertArrayEquals(arr, new String[]{"aaa", "abc", "add"});
    }

    @Test
    public void basicPartialSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface timSort = new TimSort();
        timSort.sort(arr, 0, 1);
        assertArrayEquals(arr, new String[]{"abc", "add", "aaa"});
    }

    @Test
    public void basicPartialSortTest2()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface timSort = new TimSort();
        timSort.sort(arr, 1, 2);
        assertArrayEquals(arr, new String[]{"abc", "aaa", "add"});
    }

    @Test
    public void sortChineseTest()
    {
        String[][] chineseStrings = TestUtils.getChineseStrings();
        SortInterface quickSort = new QuickSort();

        for (String[] strArr : chineseStrings)
        {
            String[] sorted = TestUtils.getSortedArray(strArr);
            quickSort.mutatingSort(strArr);

            assertArrayEquals(strArr, sorted);
        }
    }
}
