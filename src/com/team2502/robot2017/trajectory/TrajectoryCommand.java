package com.team2502.robot2017.trajectory;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A conglomeration of code stolen from Cheesy Poofs adapted from their Controller/Action system to our Command system
 */
public class TrajectoryCommand extends Command
{
    Path path;
    Trajectory leftWheelTraj;
    Trajectory rightWheelTraj;

    TrajectoryFollower leftWheel;
    TrajectoryFollower rightWheel;

    DriveTrainSubsystem dt;
    AHRS navx;

    double direction = -1;
    double orig_offset;
    double heading;
    double kTurn = -3.0 / 80.0;


    /**
     * Private constructor
     * Put subsystem requirements here
     */
    private TrajectoryCommand()
    {
        super(10);
        requires(Robot.DRIVE_TRAIN);
        dt = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        orig_offset = navx.getYaw();
    }

    /**
     * Drive the robot on a trajectory
     *
     * @param pathname The name of the trajectory as in com.team2502.robot2017.trajectory.AutoPaths
     * @param left     Whether to call goLeft or goRight on the Path instance
     */
    public TrajectoryCommand(String pathname, boolean left)
    {
        this();
        path = AutoPaths.get(pathname);
        if(left)
        {
            path.goLeft();
        }
        else
        {
            path.goRight();
        }
        setWheelTraj();
    }


    /**
     * Drive the robot on a trajectory
     *
     * @param path The path for the robot to take
     */
    public TrajectoryCommand(Path path)
    {
        this();
        this.path = path;
        setWheelTraj();
    }

    /**
     * Drive the robot on a trajectory
     *
     * @param leftWheelTraj  The trajectory for the left wheel
     * @param rightWheelTraj The trajectory for the right wheel
     */
    public TrajectoryCommand(Trajectory leftWheelTraj, Trajectory rightWheelTraj)
    {
        this();
        this.leftWheelTraj = leftWheelTraj;
        this.rightWheelTraj = rightWheelTraj;
        this.path = new Path("<Anonymous path at TrajectoryCommand>", new Trajectory.Pair(leftWheelTraj, rightWheelTraj));
    }

    public static double getDifferenceInAngleRadians(double from, double to)
    {
        double angle = to - from;
        // Naive algorithm
        while(angle >= Math.PI)
        {
            angle -= 2.0 * Math.PI;
        }
        while(angle < -Math.PI)
        {
            angle += 2.0 * Math.PI;
        }
        return angle;
    }

    /**
     * Derive the trajectories from the path instance
     */
    private void setWheelTraj()
    {
        leftWheelTraj = path.getLeftWheelTrajectory();
        rightWheelTraj = path.getRightWheelTrajectory();
    }

    /**
     * Derive the TrajectoryFollower instances from the Trajectory instances
     */
    private void setFollower()
    {
        leftWheel = new TrajectoryFollower("left");
        rightWheel = new TrajectoryFollower("right");
        double kp = 1/34.0F;
        double ki = 0;
        double kd = 0;
        double kv = 1/34.0F;
        double ka = 1/34.0F;
        leftWheel.configure(kp, ki, kd, kv, ka);
        rightWheel.configure(kp, ki, kd, kv, ka);
    }

    /**
     * Reset drivetrain encoders
     */
    private void resetEncoders()
    {
        dt.leftTalon0.setEncPosition(0);
        dt.leftTalon1.setEncPosition(0);
        dt.rightTalon0.setEncPosition(0);
        dt.rightTalon1.setEncPosition(0);
    }

    /**
     * Reset Drivetrain encoders, PID Error, and put the trajectory back to the beginning
     */
    private void reset()
    {
        resetEncoders();
        leftWheel.reset();
        rightWheel.reset();
    }

    private void loadProfile(Trajectory leftProfile, Trajectory rightProfile, double direction)
    {
        reset();
        leftWheel.setTrajectory(leftProfile);
        rightWheel.setTrajectory(rightProfile);
        this.direction = direction;

    }

    private void loadProfileNoReset(Trajectory leftProfile, Trajectory rightProfile)
    {
        leftWheel.setTrajectory(leftProfile);
        rightWheel.setTrajectory(rightProfile);
    }

    @Override
    protected void initialize()
    {
        System.out.println("********************TRAJCOMMAND**************");
        setFollower();
        reset();
        navx.reset();
        loadProfile(leftWheelTraj,rightWheelTraj, -1);
    }

    @Override
    protected void execute()
    {
        // in case it flips
        loadProfileNoReset(leftWheelTraj, rightWheelTraj);

        double distanceL = direction * dt.getEncLeftPosition();
        double distanceR = direction * dt.getEncRightPosition();

        double speedLeft = direction * leftWheel.calculate(distanceL);
        double speedRight = direction * rightWheel.calculate(distanceR);

        double goalHeading = leftWheel.getHeading();
        double observedHeading = Math.toRadians(navx.getYaw());

        double angleDiffRads = getDifferenceInAngleRadians(observedHeading, goalHeading);
        double angleDiff = Math.toDegrees(angleDiffRads);

        double turn = kTurn * angleDiff;
        dt.runMotors((speedLeft + turn), (speedRight - turn));

    }

    @Override
    protected boolean isFinished()
    {
        return leftWheel.isFinishedTrajectory() || rightWheel.isFinishedTrajectory();
    }

    @Override
    protected void end()
    {
        // Leave the navx how we found it
        navx.setAngleAdjustment(orig_offset);

        // Stop driving
        dt.runMotors(0, 0);
    }

}
