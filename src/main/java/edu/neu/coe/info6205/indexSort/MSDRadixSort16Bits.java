package edu.neu.coe.info6205.indexSort;

import edu.neu.coe.info6205.SortInterface;
import edu.neu.coe.info6205.UnicodeSortingHelper;
import edu.neu.coe.info6205.indexSort.cutoff.InsertionCutoff;

public class MSDRadixSort16Bits implements SortInterface
{
    private final UnicodeSortingHelper helper;
    private final int cutoff;

    private int[][] auxArray;
    private int[] resultIndex;
    private int[] auxResultIndex;

    public MSDRadixSort16Bits()
    {
        helper = new UnicodeSortingHelper();
        cutoff = 16;
    }

    public MSDRadixSort16Bits(int cutoff)
    {
        this.helper = new UnicodeSortingHelper();
        this.cutoff = cutoff;
    }

    public MSDRadixSort16Bits(UnicodeSortingHelper helper)
    {
        this.helper = helper;
        cutoff = 16;
    }

    public MSDRadixSort16Bits(UnicodeSortingHelper helper, int cutoff)
    {
        this.helper = helper;
        this.cutoff = cutoff;
    }

    @Override
    public void sort(String[] arr, int from, int to)
    {
        // Construct byte array
        int[][] intsArray = new int[arr.length][];
        for (int i = 0; i < arr.length; i++)
        {
            intsArray[i] = convertBytesToInts(helper.getRadix(arr[i]));
        }

        // Initialize util arrays
        auxArray = new int[intsArray.length][];
        resultIndex = new int[intsArray.length];
        auxResultIndex = new int[intsArray.length];
        for (int i = 0; i < resultIndex.length; i++) resultIndex[i] = i;

        radixSort(intsArray, from, to, 0);

        // rearrange string array based on sorted indices
        String[] tmp = new String[arr.length];
        for (int i = 0; i < resultIndex.length; i++)
        {
            tmp[i] = arr[resultIndex[i]];
        }

        System.arraycopy(tmp, 0, arr, 0, arr.length);
    }

    private void radixSort(int[][] intsArray, int from, int to, int depth)
    {
        if (from >= to) return;

        if (to - from <= cutoff)
        {
            InsertionCutoff.sortMSD(intsArray, from, to, depth, resultIndex);
            return;
        }

        System.arraycopy(resultIndex, from, auxResultIndex, from, to - from + 1);

        // 2 (to loop easier :p) + 1 (if string has no byte at <depth>) + 65536 (range of byte)
        int[] count = new int[65539];

        // calculate byte occurrence
        for (int i = from; i <= to; i++)
        {
            if (intsArray[i].length > depth)
                // we reserve the first 2 elements in the array
                count[intsArray[i][depth] + 2]++;
            else
                count[2]++;
        }

        // calculate accumulative indices (the first 2 elements are reserved)
        for (int i = 3; i < 65539; i++)
        {
            count[i] += count[i - 1];
        }

        // put strings(bytes arrays) into different radix group
        for (int i = from; i <= to; i++)
        {
            if (intsArray[i].length > depth)
            {
                int index = count[intsArray[i][depth] + 1];
                auxArray[index] = intsArray[i];
                resultIndex[from + index] = auxResultIndex[i];
                count[intsArray[i][depth] + 1]++;
            }
            else
            {
                auxArray[count[1]] = intsArray[i];
                resultIndex[from + count[1]] = auxResultIndex[i];
                count[1]++;
            }
        }

        System.arraycopy(auxArray, 0, intsArray, from, to - from + 1);

        // if all strings' length are less than depth
        if (count[1] == intsArray.length) return;

        // sort by less significant byte
        for (int i = 1; i < 65539; i++)
        {
            radixSort(intsArray, from + count[i - 1], from + count[i] - 1, depth + 1);
        }
    }

    private static int[] convertBytesToInts(byte[] bytes)
    {
        /*
         As CollationKey's bytes array is converted from ints,
         it's guaranteed that the bytes array has even number of elements
         CallationKey source code: https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/text/RuleBasedCollationKey.java#L96
        */
        int n = bytes.length / 2;
        int[] result = new int[n];
        for (int i = 0; i < n; ++i)
        {
            result[i] = ((bytes[2 * i] & 0xff) << 8) | (bytes[2 * i + 1] & 0xff);
        }
        return result;
    }
}
