package com.team254.lib.trajectory;

import com.team254.lib.trajectory.io.JavaSerializer;
import com.team254.lib.trajectory.io.JavaStringSerializer;
import com.team254.lib.trajectory.io.TextFileSerializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Jared341
 */
public class Main
{
    public static double CONV_FACTOR = 0.1/(7*2.75);

    public static String joinPath(String path1, String path2)
    {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }

    private static boolean writeFile(String path, String data)
    {
        try
        {
            File file = new File(path);

            // if file doesnt exists, then create it
            if(!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        }
        catch(IOException e)
        {
            return false;
        }

        return true;
    }

    public static void main(String[] args)
    {

    	System.out.println("Current Directory: " + System.getProperty("user.dir"));
        String directory = "./paths";
        if(args.length >= 1)
        {
            directory = args[0];
        }

        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.dt = .01;
        config.max_acc = 10.0;
        config.max_jerk = 40.0;
        config.max_vel = 9.0;

        final double kWheelbaseWidth = (23.4975 / 12);
        {
            // Path name must be a valid Java class name.
            config.dt = .01;
            config.max_acc = 10.0;
            config.max_jerk = 40.0;
            config.max_vel = 9.0;
            final String path_name = "HopperTrajectory";

            //TODO: Make correct path
            WaypointSequence p = new WaypointSequence(10);
            p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint((74/24.0F), ((30/24.0F)), Math.PI/4.0F));
            p.addWaypoint(new WaypointSequence.Waypoint((74/12.0F), ((30/12.0F)), 0));
//            p.addWaypoint(new WaypointSequence.Waypoint(10./*/5, 8, Math.PI / 12.0));
//            p.addWaypoint(new WaypointSequence.Waypoint(13.75, 9.5, 0.0/* * Math.PI/18.0*/));


            Path path = PathGenerator.makePath(p, config,
                    kWheelbaseWidth, path_name);

            // Outputs to the directory supplied as the first argument.
            TextFileSerializer js = new TextFileSerializer();
            String serialized = js.serialize(path);
            //System.out.print(serialized);
            String fullpath = joinPath(directory, path_name + ".txt");
            if(!writeFile(fullpath, serialized))
            {
                System.err.println(fullpath + " could not be written!!!!1");
                System.exit(1);
            }
            else
            {
                System.out.println("Wrote " + fullpath);
            }
        }

        {
            // Path name must be a valid Java class name.
            config.dt = .01;
            config.max_acc = 10.0;
            config.max_jerk = 40.0;
            config.max_vel = 9.0;
            final String path_name = "Straight";

            // 0.1/7 x ~= 2.75 ft
            // 0.1/7/2.75 ~= 1 ft
            //

            WaypointSequence p = new WaypointSequence(10);
            p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
//            p.addWaypoint(new WaypointSequence.Waypoint(1, 4, Math.PI/4));
            p.addWaypoint(new WaypointSequence.Waypoint(27, 27, Math.PI/4));


            Path path = PathGenerator.makePath(p, config,
                    kWheelbaseWidth, path_name);

            // Outputs to the directory supplied as the first argument.
            TextFileSerializer js = new TextFileSerializer();
            String serialized = js.serialize(path);
            //System.out.print(serialized);
            String fullpath = joinPath(directory, path_name + ".txt");
            if(!writeFile(fullpath, serialized))
            {
                System.err.println(fullpath + " could not be written!!!!1");
                System.exit(1);
            }
            else
            {
                System.out.println("Wrote " + fullpath);
            }
        }

        {
            // Path name must be a valid Java class name.
            config.dt = .01;
            config.max_acc = 10.0;
            config.max_jerk = 40.0;
            config.max_vel = 9.0;
            final String path_name = "NotStraight";

            WaypointSequence p = new WaypointSequence(10);
            p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            p.addWaypoint(new WaypointSequence.Waypoint(5, 0, Math.PI/15));
            p.addWaypoint(new WaypointSequence.Waypoint(10, 0, Math.PI/5));

            Path path = PathGenerator.makePath(p, config,
                    kWheelbaseWidth, path_name);

            // Outputs to the directory supplied as the first argument.
            TextFileSerializer js = new TextFileSerializer();
            String serialized = js.serialize(path);
            //System.out.print(serialized);
            String fullpath = joinPath(directory, path_name + ".txt");
            if(!writeFile(fullpath, serialized))
            {
                System.err.println(fullpath + " could not be written!!!!1");
                System.exit(1);
            }
            else
            {
                System.out.println("Wrote " + fullpath);
            }
        }
    }
}
