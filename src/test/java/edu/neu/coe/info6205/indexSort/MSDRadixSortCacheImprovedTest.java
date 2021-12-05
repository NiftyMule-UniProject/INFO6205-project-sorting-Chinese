package edu.neu.coe.info6205.indexSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.TestUtils;
import edu.neu.coe.info6205.utils.FileUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MSDRadixSortCacheImprovedTest
{
    @Test
    public void basicSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortMSD = new MSDRadixSortCacheImproved();
        radixSortMSD.mutatingSort(arr);
        assertArrayEquals(arr, new String[]{"aaa", "abc", "add"});
    }

    @Test
    public void basicPartialSortTest()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortMSD = new MSDRadixSortCacheImproved();
        radixSortMSD.sort(arr, 0, 1);
        assertArrayEquals(arr, new String[]{"abc", "add", "aaa"});
    }

    @Test
    public void basicPartialSortTest2()
    {
        String[] arr = {"abc", "add", "aaa"};
        SortInterface radixSortMSD = new MSDRadixSortCacheImproved();
        radixSortMSD.sort(arr, 1, 2);
        assertArrayEquals(arr, new String[]{"abc", "aaa", "add"});
    }

    @Test
    public void sortChineseWithCutoffTest()
    {
        String[][] chineseStrings = TestUtils.getChineseStrings();
        SortInterface radixSortMSD = new MSDRadixSortCacheImproved();

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
        SortInterface radixSortMSD = new MSDRadixSortCacheImproved();

        for (String[] strArr : chineseStrings)
        {
            String[] sorted = TestUtils.getSortedArray(strArr);
            radixSortMSD.mutatingSort(strArr);

            assertArrayEquals(strArr, sorted);
        }
    }

    @Test
    public void writeSortedChineseToFile()
    {
        // this testcase is purely to generate sorted string and store it into a file
        String[] strArr = FileUtil.readAllLines("src/main/resources/shuffledChinese.txt");
        SortInterface radixSortMSD = new MSDRadixSortCacheImproved();
        radixSortMSD.mutatingSort(strArr);
        assert strArr != null;
        FileUtil.writeToFiles("src/main/resources/sortedChinese.txt", strArr);
    }
}