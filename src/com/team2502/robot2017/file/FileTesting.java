package com.team2502.robot2017.file;

import java.util.ArrayList;

public class FileTesting
{
    static int value = 0;

    static ArrayList array = new ArrayList();

    public static void main(String args[]) throws InterruptedException
    {
        for(int q=0; q<2; q++)
        {
            FileData.newFile("/Users/90308982/Documents/Text");

            for(int i = 1; i <= 10; i++)
            {
                Thread.sleep(20);

                if(i % 2 == 0)
                {
                    value = i;
                    array.add(value);
                }

                FileData.writeTimeAndValuesToFile(FileData.FileName, i, "Random value", array);
            }
        }
    }
}
