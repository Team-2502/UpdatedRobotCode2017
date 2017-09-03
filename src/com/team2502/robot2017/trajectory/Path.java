package com.team2502.robot2017.trajectory;

import com.team2502.robot2017.trajectory.Trajectory.Segment;

/**
 * Base class for an autonomous path.
 * stolen from chez pofs
 *
 * @author Jared341
 */
public class Path
{
    protected Trajectory.Pair go_left_pair_;
    protected String name_;
    protected boolean go_left_;

    public Path(String name, Trajectory.Pair go_left_pair)
    {
        name_ = name;
        go_left_pair_ = go_left_pair;
        go_left_ = true;
    }

    /**
     * @return The Name
     */
    public String getName()
    {
        return name_;
    }

    /**
     * Sets up the path to have the robot go left
     *
     * The x-axis runs from the origin to the destination
     * The y axis is perpendicular to the origin-destination, and the positive side is on the left
     * Therefore, if we don't invert the y-axis to go onto the right side instead, it will go left probably
     */
    public void goLeft()
    {
        go_left_ = true;
        go_left_pair_.left.setInvertedY(false);
        go_left_pair_.right.setInvertedY(false);
    }

    /**
     * Sets up the path to have the robot go right
     *
     * The x-axis runs from the origin to the destination
     * The y axis is perpendicular to the origin-destination, and the positive side is on the left
     * Therefore, if we invert the y-axis to go onto the right side instead, it will go right probably
     */
    public void goRight()
    {
        go_left_ = false;
        go_left_pair_.left.setInvertedY(true);
        go_left_pair_.right.setInvertedY(true);
    }

    /**
     * Tells you the trajectory the left wheel is taking
     *
     * @return the trajectory
     */
    public Trajectory getLeftWheelTrajectory()
    {
        return (go_left_ ? go_left_pair_.left : go_left_pair_.right);
    }

    /**
     * Tells you the trajectory the right wheel is taking
     *
     * @return the trajectory
     */
    public Trajectory getRightWheelTrajectory()
    {
        return (go_left_ ? go_left_pair_.right : go_left_pair_.left);
    }


    public boolean canFlip(int segmentNum)
    {
        Segment a = go_left_pair_.right.getSegment(segmentNum);
        Segment b = go_left_pair_.left.getSegment(segmentNum);
        return (a.pos == b.pos) && (a.vel == b.vel);
    }

    public double getEndHeading()
    {
        int numSegments = getLeftWheelTrajectory().getNumSegments();
        Segment lastSegment = getLeftWheelTrajectory().getSegment(numSegments - 1);
        return lastSegment.heading;
    }
}
