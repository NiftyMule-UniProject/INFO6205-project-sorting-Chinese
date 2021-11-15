package edu.neu.coe.info6205;

/**
 * Class modified from rchillyard/INFO6205 original repo<br>
 * Reference:
 * <a href="https://github.com/rchillyard/INFO6205/blob/Fall2021/src/main/java/edu/neu/coe/info6205/sort/GenericSort.java"> github link </a>
 */
public interface SortInterface
{
    /**
     * Generic sort method
     * @param arr array to be sorted
     * @param makeCopy sort in-place if set to false, otherwise make a copy then sort the new array
     */
    void sort(String[] arr, boolean makeCopy);

    /**
     * Copy & sort the array, return the new sorted array
     * @param arr array to be sorted
     */
    default void sort(String[] arr) {sort(arr, false);}

    /**
     * Sort the array in-place
     * @param arr array to be sorted
     */
    default void mutatingSort(String[] arr) {sort(arr, true);}

    /**
     * Sort the sub-array
     * @param arr array to be sorted
     * @param from the starting index of sub-array
     * @param to the ending index of sub-array
     */
    void sort(String[] arr, int from, int to);
}
