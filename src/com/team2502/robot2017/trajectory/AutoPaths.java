package com.team2502.robot2017.trajectory;

import java.util.Hashtable;

import com.team2502.robot2017.trajectory.util.PathFileReader;
import com.team2502.robot2017.trajectory.Trajectory;
import com.team2502.robot2017.trajectory.util.TextFileReader;
import edu.wpi.first.wpilibj.Timer;

/**
 * Load all autonomous mode paths.
 *
 * @author Jared341
 * @author Stephen Pinkerton
 */
public class AutoPaths
{
    public final static String[] kPathNames = {"HopperTrajectory",
            "Straight",
            "NotStraight",
    };

    static Hashtable paths_ = new Hashtable();

    public static void loadPaths()
    {
        Timer t = new Timer();
        t.start();
        PathFileReader deserializer = new PathFileReader();

        // This is a foreach type of loop
        // Akin to `for kPathName in kPathNames:` in python
        for(String kPathName : kPathNames)
        {
            TextFileReader reader = new TextFileReader("/home/lvuser/path/" + kPathName + ".txt");

            Path path = deserializer.deserialize(reader.readWholeFile());
            paths_.put(kPathName, path);
        }
        System.out.println("Parsing paths took: " + t.get());
    }

    public static Path get(String name)
    {
        return (Path) paths_.get(name);
    }

    public static Path getByIndex(int index)
    {
        return (Path) paths_.get(kPathNames[index]);
    }
}

