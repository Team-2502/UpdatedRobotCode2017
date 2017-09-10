package com.team2502.robot2017.trajectory.util;

import java.io.*;


/**
 * stolen from chez pof
 * Read a text file into a string.
 *
 * @author Jared341
 */
public class TextFileReader
{

    private File file_connection_ = null;
    private BufferedReader reader_ = null;

    public TextFileReader(String path)
    {
        try
        {
            // Open the new file
            file_connection_ = new File(path);
            if(!file_connection_.exists())
            {
                System.err.println("Could not find specified file!");
                return;
            }

            // Make an I/O adapter sandwich to actually get some text out
            reader_ = new BufferedReader(new FileReader(file_connection_));

        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println("Could not open file connection!");
            closeFile();
        }
    }

    private void closeFile()
    {
        try
        {
            // If there was a file
            if(file_connection_ != null)
            {
                // If we made a buffered reader, close it
                if(reader_ != null)
                {
                    reader_.close();
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println("Could not close file");
        }
    }

    // Returns null at end of file
    public String readLine()
    {
        String line = null;
        try
        {
            line = reader_.readLine();
        }
        catch(NullPointerException | IOException e)
        {
            e.printStackTrace();
            closeFile();
        }
        return line;
    }

    public String readWholeFile()
    {
        StringBuffer buffer = new StringBuffer();
        String line;
        while((line = readLine()) != null)
        {
            buffer.append(line);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
