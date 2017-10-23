package com.team2502.robot2017.file;

import com.team2502.robot2017.RobotMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileData
{
    static int value = 0;

    static ArrayList array = new ArrayList();

    static File file;

    public static String FileName = "";

    /**
     * @param fileName add directory plus file name
     */
    public static void newFile(String fileName)
    {
        FileName = fileName + RobotMap.Files.FilesMade + ".txt";
        file = new File(FileName);
        RobotMap.Files.FilesMade++;
    }

    public static void writeTimeAndValuesToFile(String fileName, long time, String arrayname, ArrayList values)
    {
        WriteToFile data = new WriteToFile(fileName, true);
        try
        {
            data.writeToFile("Time: " + time);
            data.writeToFile(arrayname + ": " + String.valueOf(values));
        } catch(IOException e)
        {
            e.printStackTrace();
        }

    }

}
