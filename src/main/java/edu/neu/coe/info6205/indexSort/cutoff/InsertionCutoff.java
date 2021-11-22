package edu.neu.coe.info6205.indexSort.cutoff;

public class InsertionCutoff
{
    /**
     * MSD radix sort's cutoff sorting method using insertion sort
     *
     * @param bytesArr    All strings' collationKey in byte array form
     * @param from        start index
     * @param to          end index
     * @param depth       MSD radix sort's current depth
     * @param resultIndex array to store sorted strings by indices
     */
    public static void sortMSD(byte[][] bytesArr, int from, int to, int depth, int[] resultIndex)
    {
        for (int i = from + 1; i <= to; i++)
        {
            int j = i;
            while (j > from && !lessMSD(bytesArr[j - 1], bytesArr[j], depth))
            {
                swap(bytesArr, resultIndex, j - 1, j);
                j--;
            }
        }
    }

    public static void sortLSD(byte[][] bytesArr, int from, int to, int depth, int[] resultIndex)
    {
        // TODO - to be implemented
    }

    private static boolean lessMSD(byte[] a, byte[] b, int depth)
    {
        int loop = Math.min(a.length, b.length);
        loop -= depth;
        while (depth < loop)
        {
            int numA = toUnsigned(a[depth]);
            int numB = toUnsigned(b[depth]);
            if (numA < numB)
                return true;
            else if (numA > numB)
                return false;
            depth++;
        }

        // reach one array's end
        // if array <a> is shorter, return true
        // if array <b> is shorter, return false
        // if <a> and <b> are the same, return true
        return depth == a.length;
    }

    private static boolean lessLSD(byte[] a, byte[] b, int depth)
    {
        // TODO - to be implemented
        return false;
    }

    private static void swap(byte[][] bytesArr, int[] resultIndex, int a, int b)
    {
        byte[] tmp = bytesArr[a];
        bytesArr[a] = bytesArr[b];
        bytesArr[b] = tmp;

        int tmpInt = resultIndex[a];
        resultIndex[a] = resultIndex[b];
        resultIndex[b] = tmpInt;
    }

    // convert 2's complement number to unsigned number
    private static int toUnsigned(byte num)
    {
        return num & 0xff;
    }
}
