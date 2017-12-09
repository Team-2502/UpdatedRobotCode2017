package com.team2502.robot2017.file;

import com.team2502.robot2017.RobotMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time);


        try
        {
            if (time < 1000)
            {
                data.writeToFile(String.valueOf(time) + ", " + "Loop Error:" + String.valueOf(values.get(values.size() - 1)) + ", ");
            }
            else if(seconds < 60)
            {
                data.writeToFile(String.valueOf(seconds) + ": " + String.valueOf(time % 1000) + ", " + "Loop Error:" + String.valueOf(values.get(values.size() - 1)) + ", ");
            }
            else
            {
                data.writeToFile(String.valueOf(minutes) + ": " + String.valueOf(seconds) + ": " + String.valueOf(time % 1000) + ", " + "Loop Error:" + String.valueOf(values.get(values.size() - 1)) + ", ");
            }

        } catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
