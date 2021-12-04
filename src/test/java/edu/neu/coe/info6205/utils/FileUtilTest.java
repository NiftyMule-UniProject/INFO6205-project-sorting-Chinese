package edu.neu.coe.info6205.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest
{
    @Test
    public void testReadAllLines()
    {
        String[] strArr = FileUtil.readAllLines("src/test/resources/sampleNames.txt");
        assertArrayEquals(strArr,
                new String[]{"刘持平",
                        "洪文胜",
                        "樊辉辉",
                        "苏会敏",
                        "高民政"});
    }

    @Test
    public void testReadFirstNLines()
    {
        String[] strArr = FileUtil.readLines("src/test/resources/sampleNames.txt", 3);
        assertArrayEquals(strArr,
                new String[]{"刘持平",
                        "洪文胜",
                        "樊辉辉"});
    }

    @Test
    public void testReadNLinesFromSmallFile()
    {
        String[] strArr = FileUtil.readLines("src/test/resources/sampleNames.txt", 7);
        assertArrayEquals(strArr,
                new String[]{"刘持平",
                        "洪文胜",
                        "樊辉辉",
                        "苏会敏",
                        "高民政"});
    }

    @Test
    public void testReadWrongFile()
    {
        // will print out error message
        String[] strArr = FileUtil.readAllLines("wrongFilePath");
        assertNull(strArr);
    }
}