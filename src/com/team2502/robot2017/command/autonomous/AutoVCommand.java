package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class AutoVCommand extends Command
{
    private static DriveTrainSubsystem dt;
    private static double offset;
    private static VisionSubsystem vision;
    private double startTime = System.currentTimeMillis();
    private double targetElapsed = 15;
    private static boolean alignOnly = false;
    private double highSpeed = 0.3;
    private double lowSpeed = 0.3;

    /**
     * Automatic vision-based alignment with shiny objects
     * <br>
     * Runs for 2 seconds
     */
    public AutoVCommand()
    {
        this(2);
    }

    /**
     * Automatic vision-based alignment with shiny objects
     *
     * @param runTime How long vision should run for
     */
    public AutoVCommand(double runTime)
    {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.VISION);

        vision = Robot.VISION;
        dt = Robot.DRIVE_TRAIN;
        targetElapsed = runTime * 1000;
    }

    /**
     * Automatic vision-based alignment with shiny objects
     *
     * @param runTime How long vision should run for
     * @param align   if it should be in alignWidth-only mode
     */
    public AutoVCommand(double runTime, boolean align)
    {
        this(runTime);
        alignOnly = align;
    }

    /**
     * Automatic vision-based alignment with shiny objects
     * <br>
     * This one is special because it uses a math function to smooth out the turning
     *
     * @param runTime    How long vision should run for
     * @param slowFactor How much slower the slow side should go.
     */
    public AutoVCommand(double runTime, double slowFactor)
    {
        this(runTime);


        if(slowFactor == 1) { alignOnly = true; }
    }

    /**
     * Automatic vision-based alignment with shiny objects.
     * <br>
     * Wiggly Butt - the closer lowSpeed approaches highSpeed the more of a wiggle.
     *
     * @param runTime   How long vision should run for
     * @param align     if it should be in alignWidth-only mode
     * @param lowSpeed  The lower speed for turning
     * @param highSpeed The higher speed for turning
     */
    public AutoVCommand(double runTime, boolean align, double lowSpeed, double highSpeed)
    {
        this(runTime, align);
        this.lowSpeed = lowSpeed;
        this.highSpeed = highSpeed;
    }

    @Override
    protected void initialize()
    {
        vision.turnOnVisionLight();
//        dt.setAutonSettings();

//        dt.setAutonSettingsVolts(dt.leftTalon0, true, 6);
//        dt.setAutonSettingsVolts(dt.rightTalon1, false, 6);

        startTime = System.currentTimeMillis();
    }

    @Override
    protected void execute()
    {
        vision.alignWidth(dt, lowSpeed, highSpeed, alignOnly, true);
    }

    @Override
    protected boolean isFinished()
    {
        return System.currentTimeMillis() - startTime > targetElapsed && (!alignOnly || Math.abs(offset) < 0.1);
    }

    protected void end()
    {
        dt.runMotors(0, 0);
        dt.setTeleopSettings();
        vision.turnOffVisionLight();
        System.out.println("Did a align");
    }

    @Override
    protected void interrupted() { end(); }
}