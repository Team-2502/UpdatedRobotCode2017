package com.team2502.robot2017.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteToFile
{
    private String path;
    private boolean append_to_file = false;

    public WriteToFile (String file_path)
    {
        path = file_path;
    }
    public WriteToFile(String file_path, boolean append_value)
    {
        path = file_path;
        append_to_file = append_value;
    }

    public void writeToFile(String text) throws IOException
    {
        FileWriter write = new FileWriter(path, append_to_file);
        PrintWriter print_line = new PrintWriter(write);

        print_line.printf("%s" + "%n", text);

        print_line.close();
    }
}
