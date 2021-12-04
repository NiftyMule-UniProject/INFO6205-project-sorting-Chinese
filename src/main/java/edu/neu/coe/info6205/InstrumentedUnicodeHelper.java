package edu.neu.coe.info6205;

import java.util.Locale;

/**
 * Instrumented unicode sorting helper, will count compare & swap operations
 */
public class InstrumentedUnicodeHelper extends UnicodeSortingHelper
{
    private int compareCount;
    private int swapCount;

    public InstrumentedUnicodeHelper(Locale locale)
    {
        super();
        compareCount = 0;
        swapCount = 0;
        setCollator(locale);
    }

    @Override
    public int compare(String a, String b)
    {
        compareCount++;
        return collator.compare(a, b);
    }

    @Override
    public void swap(String[] arr, int x, int y)
    {
        swapCount++;
        String tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public int getCompareCount()
    {
        return compareCount;
    }

    public int getSwapCount()
    {
        return swapCount;
    }
}
