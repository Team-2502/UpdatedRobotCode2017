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
    // Make sure these match up!
    public static final int WALL_LANE_ID = 2;
    public final static String[] kPathNames = {"InsideLanePathFar",
            "CenterLanePathFar",
            "WallLanePath",
            "InsideLanePathClose",
            "StraightAheadPath",
    };
    public final static String[] kPathDescriptions = {"Inside, Far",
            "Middle Lane",
            "Wall Lane",
            "Inside, Close",
            "Straight ahead",
    };
    static Hashtable paths_ = new Hashtable();

    public static void loadPaths()
    {
        Timer t = new Timer();
        t.start();
        PathFileReader deserializer = new PathFileReader();
        for(int i = 0; i < kPathNames.length; ++i)
        {

			TextFileReader reader = new TextFileReader("/home/lvuser/path/" + kPathNames[i] + ".txt");

			Path path = deserializer.deserialize(reader.readWholeFile());
			paths_.put(kPathNames[i], path);
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

