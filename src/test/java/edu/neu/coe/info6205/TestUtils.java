package edu.neu.coe.info6205;

import java.util.Arrays;

public class TestUtils
{
    public static String[][] getChineseStrings()
    {
        return new String[][]{
                {"少年", "天才", "生活"}, // Correct order: [少年, 生活, 天才]
                {"三", "生活", "任务"}, // Correct order: [任务, 三, 生活]
                {"一", "九九九", "三得利", "田", "似曾相识"} // Correct order: [九九九, 三得利, 似曾相识, 田, 一]
        };
    }

    public static String[] getSortedArray(String[] array)
    {
        UnicodeSortingHelper helper = new UnicodeSortingHelper();

        // Use system sort
        return Arrays.stream(array).sorted(helper::compare).toArray(String[]::new);
    }
}
