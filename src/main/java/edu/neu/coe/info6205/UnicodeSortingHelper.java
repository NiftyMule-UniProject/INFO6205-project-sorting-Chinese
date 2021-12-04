package edu.neu.coe.info6205;

import java.text.Collator;
import java.util.Locale;

/**
 * Helper class for sorting unicode strings.<br>
 * Provides basic {@code compare} and {@code swap} methods
 */
public class UnicodeSortingHelper
{
    protected Collator collator = Collator.getInstance(Locale.CHINA);

    public UnicodeSortingHelper()
    {
        collator.setStrength(Collator.PRIMARY);
    }

    /**
     * Set the sorting rules (collator) according to given locale
     *
     * @param locale locale to be used to retrieve sorting rules (collator instance)
     */
    public void setCollator(Locale locale)
    {
        collator = Collator.getInstance(locale);
    }

    /**
     * Compare two strings
     *
     * @param a first string to be compared
     * @param b second string to be compared
     * @return 0 if strings are equal, 1 if String {@code a} is larger or -1 if String {@code b} is larger
     */
    public int compare(String a, String b)
    {
        return collator.compare(a, b);
    }

    public byte[] getRadix(String a)
    {
        return collator.getCollationKey(a).toByteArray();
    }

    /**
     * Swap two strings elements in the array
     *
     * @param arr string array container
     * @param x   first element's array index
     * @param y   second element's array index
     */
    public void swap(String[] arr, int x, int y)
    {
        String tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
