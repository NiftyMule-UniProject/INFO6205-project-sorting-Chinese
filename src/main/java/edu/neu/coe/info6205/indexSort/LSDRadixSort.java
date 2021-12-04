package edu.neu.coe.info6205.indexSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.UnicodeSortingHelper;
import edu.neu.coe.info6205.indexSort.cutoff.InsertionCutoff;

public class LSDRadixSort implements SortInterface
{
    private final int cutoff;
    private final UnicodeSortingHelper helper;

    private byte[][] auxArray;
    private int[] resultIndex;
    private int[] auxResultIndex;

    public LSDRadixSort()
    {
        cutoff = 16;
        helper = new UnicodeSortingHelper();
    }

    public LSDRadixSort(int cutoff)
    {
        this.cutoff = cutoff;
        helper = new UnicodeSortingHelper();
    }

    public LSDRadixSort(UnicodeSortingHelper helper)
    {
        cutoff = 16;
        this.helper = helper;
    }

    public LSDRadixSort(int cutoff, UnicodeSortingHelper helper)
    {
        this.cutoff = cutoff;
        this.helper = helper;
    }

    @Override
    public void sort(String[] arr, int from, int to)
    {
        // Construct byte array
        int maxDepth = 0;
        byte[][] bytesArray = new byte[arr.length][];
        for (int i = 0; i < arr.length; i++)
        {
            bytesArray[i] = helper.getRadix(arr[i]);
            maxDepth = Math.max(bytesArray[i].length, maxDepth);
        }

        // Initialize util arrays
        auxArray = new byte[bytesArray.length][];
        resultIndex = new int[bytesArray.length];
        auxResultIndex = new int[bytesArray.length];
        for (int i = 0; i < resultIndex.length; i++) resultIndex[i] = i;

        radixSort(bytesArray, from, to, maxDepth);

        // rearrange string array based on sorted indices
        String[] tmp = new String[arr.length];
        for (int i = 0; i < resultIndex.length; i++)
        {
            tmp[i] = arr[resultIndex[i]];
        }

        System.arraycopy(tmp, 0, arr, 0, arr.length);
    }

    private void radixSort(byte[][] bytesArr, int from, int to, int depth)
    {
        if (from >= to || depth < 0) return;

        if (to - from <= cutoff)
        {
            InsertionCutoff.sortLSD(bytesArr, from, to, depth, resultIndex);
            return;
        }

        System.arraycopy(resultIndex, from, auxResultIndex, from, to - from + 1);

        // 2 (to loop easier :p) + 1 (if string has no byte at <depth>) + 256 (range of byte)
        int[] count = new int[259];

        // calculate byte occurrence
        for (int i = from; i <= to; i++)
        {
            if (bytesArr[i].length > depth)
                // we reserve the first 2 elements in the array
                count[toUnsigned(bytesArr[i][depth]) + 2]++;
            else
                count[2]++;
        }

        // calculate accumulative indices (the first 2 elements are reserved)
        for (int i = 3; i < 259; i++)
        {
            count[i] += count[i - 1];
        }

        // put strings(bytes arrays) into different radix group
        for (int i = from; i <= to; i++)
        {
            if (bytesArr[i].length > depth)
            {
                int index = count[toUnsigned(bytesArr[i][depth]) + 1];
                auxArray[index] = bytesArr[i];
                resultIndex[from + index] = auxResultIndex[i];
                count[toUnsigned(bytesArr[i][depth]) + 1]++;
            }
            else
            {
                auxArray[count[1]] = bytesArr[i];
                resultIndex[from + count[1]] = auxResultIndex[i];
                count[1]++;
            }
        }

        System.arraycopy(auxArray, 0, bytesArr, from, to - from + 1);

        radixSort(bytesArr, from, to, depth - 1);
    }

    // convert 2's complement number to unsigned number
    private static int toUnsigned(byte num)
    {
        return num & 0xff;
    }
}
