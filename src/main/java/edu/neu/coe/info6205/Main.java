package edu.neu.coe.info6205;

import edu.neu.coe.info6205.compSort.DualPivotSort;
import edu.neu.coe.info6205.compSort.QuickSort;
import edu.neu.coe.info6205.compSort.TimSort;
import edu.neu.coe.info6205.compSort.huskySort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.compSort.huskySort.sort.PureHuskySort;
import edu.neu.coe.info6205.indexSort.LSDRadixSort;
import edu.neu.coe.info6205.indexSort.MSDRadixSort;
import edu.neu.coe.info6205.indexSort.MSDRadixSort16Bits;
import edu.neu.coe.info6205.indexSort.MSDRadixSortCacheImproved;
import edu.neu.coe.info6205.utils.FileUtil;

import java.text.Collator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

public class Main
{
    // This function is to generate sorted output for each sorting algorithms
    public static void main(String[] args)
    {
        String[] input = FileUtil.readAllLines("src/main/resources/shuffledChinese.txt");
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

        for (String sortName : sorters.keySet())
        {
            assert input != null;
            String[] inputCopy = input.clone();
            sorters.get(sortName).accept(inputCopy);
            String fileOutputName = "src/main/resources/output/" + sortName + ".txt";
            FileUtil.writeToFiles(fileOutputName, inputCopy);
        }
    }
}
