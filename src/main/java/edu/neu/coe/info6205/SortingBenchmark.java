package edu.neu.coe.info6205;

import edu.neu.coe.info6205.compSort.DualPivotSort;
import edu.neu.coe.info6205.compSort.QuickSort;
import edu.neu.coe.info6205.compSort.TimSort;
import edu.neu.coe.info6205.compSort.huskySort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.compSort.huskySort.sort.PureHuskySort;
import edu.neu.coe.info6205.compSort.huskySort.utils.LazyLogger;
import edu.neu.coe.info6205.indexSort.LSDRadixSort;
import edu.neu.coe.info6205.indexSort.MSDRadixSort;
import edu.neu.coe.info6205.indexSort.MSDRadixSort16Bits;
import edu.neu.coe.info6205.indexSort.MSDRadixSortCacheImproved;
import edu.neu.coe.info6205.utils.Benchmark;
import edu.neu.coe.info6205.utils.Benchmark_Timer;
import edu.neu.coe.info6205.utils.FileUtil;

import java.text.Collator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

public class SortingBenchmark
{
    public static void sortingBenchMark(String sortName, Consumer<String[]> sort, String[] strArr, int arraySize, int runs)
    {
        Benchmark<String[]> bm = new Benchmark_Timer<>("Test " + sortName, String[]::clone, sort);
        double time = bm.run(strArr, runs);
        logger.info(String.format("Array size: %-10d, %-35s, Runs: %2d, benchmark time: %.2f", arraySize, sortName, runs, time));
    }

    public static void main(String[] args)
    {
        String[] strArr;
        int[] Ns = {1000, 500000, 1000000, 2000000, 4000000};
        Map<String, Consumer<String[]>> sorters = new HashMap<>();

        sorters.put("TimSort", new TimSort()::mutatingSort);
        sorters.put("QuickSort", new QuickSort()::mutatingSort);
        sorters.put("Dual-Pivot QuickSort", new DualPivotSort()::mutatingSort);
        sorters.put("LSD radix sort", new LSDRadixSort()::mutatingSort);
        sorters.put("MSD radix sort - 8 bits", new MSDRadixSort()::mutatingSort);
        sorters.put("MSD radix sort - 16 bits", new MSDRadixSort16Bits()::mutatingSort);
        sorters.put("MSD radix sort - better cache", new MSDRadixSortCacheImproved()::mutatingSort);
        sorters.put("Pure HuskySort - system sort",
                new PureHuskySort<>(HuskyCoderFactory.chineseEncoder, false, false, Collator.getInstance(Locale.CHINA))::sort);
        sorters.put("Pure HuskySort - InsertionSort",
                new PureHuskySort<>(HuskyCoderFactory.chineseEncoder, false, true, Collator.getInstance(Locale.CHINA))::sort);

        for (int n : Ns)
        {
            strArr = FileUtil.readLines(CHINESE_FILEPATH, n);
            if (strArr != null)
            {
                if (n > 1000000)
                {
                    strArr = getExpandedStrArr(strArr, n);
                }
                for (String sortName : sorters.keySet())
                {
                    sortingBenchMark(sortName, sorters.get(sortName), strArr, n, BENCHMARK_RUNS);
                }
            }
        }
    }

    public static String[] getExpandedStrArr(String[] strArr, int n)
    {
        String[] ret = new String[n];
        int size = strArr.length;
        int index = 0;
        int t = Math.min(n, size);
        while (t > 0)
        {
            System.arraycopy(strArr,  0, ret,  index, t);
            index += t;
            n -= t;
            t = Math.min(n, size);
        }
        return ret;
    }

    private static final LazyLogger logger = new LazyLogger(SortingBenchmark.class);
    private static final String CHINESE_FILEPATH = "src/main/resources/shuffledChinese.txt";
    private static final int BENCHMARK_RUNS = 50;
}
