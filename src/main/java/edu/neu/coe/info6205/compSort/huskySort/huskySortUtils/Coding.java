package edu.neu.coe.info6205.compSort.huskySort.huskySortUtils;

/**
 * Class to combine the long codes for an array of objects with a determination of coding perfection.
 */
public class Coding
{
    public final long[] longs;
    public final boolean perfect;
    public Coding(long[] longs, boolean perfect)
    {
        this.longs = longs;
        this.perfect = perfect;
    }
}
