package com.team2502.robot2017.file;

import com.team2502.robot2017.RobotMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileData
{
    static File file;

    public static String FileName = "";

    /**
     * @param fileName add directory (Always a .txt file)
     */
    public static void newFile(String fileName)
    {
        FileName = fileName + RobotMap.Files.FilesMade + ".csv";
        file = new File(FileName);
        RobotMap.Files.FilesMade++;
    }

    public static void writeTimeAndValuesToFile(String fileName, long time, String arrayName, ArrayList values)
    {
        WriteToFile data = new WriteToFile(fileName, true);
        try
        {
//            data.writeToFile("Loop Error,");
            data.writeToFile(String.valueOf(time) + ", ");
            data.writeToFile(String.valueOf(values.get(values.size()-1)) + ", ");
        } catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
