package edu.neu.coe.info6205.compSort.huskySort.sort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.TestUtils;
import edu.neu.coe.info6205.compSort.TimSort;
import edu.neu.coe.info6205.compSort.huskySort.BaseHelper;
import edu.neu.coe.info6205.compSort.huskySort.huskySortUtils.Coding;
import edu.neu.coe.info6205.compSort.huskySort.huskySortUtils.HuskyCoder;
import edu.neu.coe.info6205.compSort.huskySort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.compSort.huskySort.utils.PrivateMethodInvoker;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.text.Collator;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class PureHuskySortTest
{

    private final BaseHelper<String> helper = new BaseHelper<>("dummy helper");

    @Test
    public void testSortString1()
    {
        String[] xs = {"Hello", "Goodbye", "Ciao", "Willkommen"};
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.unicodeCoder, false, false);
        sorter.sort(xs);
        assertTrue(helper.sorted(xs), "sorted");
    }

    @Test
    public void testSortString2()
    {
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        final int N = 1000;
        helper.init(N);
        final String[] xs = helper.random(String.class, r -> r.nextLong() + "");
        sorter.sort(xs);
        assertTrue(helper.sorted(xs), "sorted");
    }

    @Test
    public void testSortString3()
    {
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        final int N = 1000;
        helper.init(N);
        final String[] xs = helper.random(String.class, r ->
        {
            int x = r.nextInt(1000000000);
            final BigInteger b = BigInteger.valueOf(x).multiply(BigInteger.valueOf(1000000));
            return b.toString();
        });
        sorter.sort(xs);
        assertTrue(helper.sorted(xs), "sorted");
    }

    @Test
    public void testSortString4()
    {
        String[] xs = {"Hello", "Goodbye", "Ciao", "Willkommen"};
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        sorter.sort(xs);
        assertTrue(helper.sorted(xs), "sorted");
    }

    @Test
    public void testSortString5()
    {
        String[] xs = {"Hello", "Goodbye", "Ciao", "Welcome"};
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        sorter.sort(xs);
        assertTrue(helper.sorted(xs), "sorted");
    }

    @Test
    public void testFloorLg()
    {
        PrivateMethodInvoker privateMethodInvoker = new PrivateMethodInvoker(PureHuskySort.class);
        assertEquals(1, privateMethodInvoker.invokePrivate("floor_lg", 3));
        assertEquals(2, privateMethodInvoker.invokePrivate("floor_lg", 5));
    }

    @Test
    public void testWithInsertionSort()
    {
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, true);
        PrivateMethodInvoker privateMethodInvoker = new PrivateMethodInvoker(sorter);
        HuskyCoder<String> huskyCoder = (HuskyCoder<String>) privateMethodInvoker.invokePrivate("getHuskyCoder");
        final int N = 100;
        helper.init(N);
        final String[] xs = helper.random(String.class, r -> r.nextLong() + "");
        Coding coding = huskyCoder.huskyEncode(xs);
        PureHuskySort.insertionSort(xs, coding.longs, 0, N);
        assertEquals(0, helper.inversions(xs));
    }

    @Test
    public void testInsertionSort()
    {
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        PrivateMethodInvoker privateMethodInvoker = new PrivateMethodInvoker(sorter);
        HuskyCoder<String> huskyCoder = (HuskyCoder<String>) privateMethodInvoker.invokePrivate("getHuskyCoder");
        final int N = 100;
        helper.init(N);
        final String[] xs = helper.random(String.class, r -> r.nextLong() + "");
        Coding coding = huskyCoder.huskyEncode(xs);
        sorter.insertionSort(xs, coding.longs, 0, N);
        assertEquals(0, helper.inversions(xs));
    }

    @Test
    public void testSortChinese()
    {
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.chineseEncoder, false, false, Collator.getInstance(Locale.CHINA));
        String[][] chineseStrings = TestUtils.getChineseStrings();

        for (String[] strArr : chineseStrings)
        {
            String[] sorted = TestUtils.getSortedArray(strArr);
            sorter.sort(strArr);

            assertArrayEquals(strArr, sorted);
        }
    }
}
