package edu.neu.coe.info6205.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil
{
    public static String[] readAllLines(String filePath)
    {
        String encoding = "UTF-8";
        List<String> list = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding));
            String str;
            while ((str = reader.readLine()) != null) {
                list.add(str);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (list.isEmpty()) return null;
        return list.toArray(String[]::new);
    }

    // Read the first `n` lines from the file
    public static String[] readLines(String filePath, int n)
    {
        String encoding = "UTF-8";
        List<String> list = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding));
            String str;
            int counter = 0;
            while (counter++ < n && (str = reader.readLine()) != null) {
                list.add(str);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (list.isEmpty()) return null;
        return list.toArray(String[]::new);
    }

    public static void writeToFiles(String filePath, String[] strArr)
    {
        String encoding = "UTF-8";
        try
        {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), encoding));
            for (String str : strArr)
            {
                writer.write(str);
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
