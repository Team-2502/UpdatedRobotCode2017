package com.team2502.robot2017.trajectory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Stolen from CheesyPoofs
 * PID + Feedforward controller for following a Trajectory.
 *
 * @author Jared341
 */
public class TrajectoryFollower {

    private double kp_;
    private double ki_;  // Not currently used, but might be in the future.
    private double kd_;
    private double kv_;
    private double ka_;
    private double last_error_;
    private double current_heading = 0;
    private int current_segment;
    private Trajectory profile_;
    public String name;

    /**
     * Make a new Trajectory Follower
     * @param name Name of trajectory
     */
    public TrajectoryFollower(String name) {
        this.name = name;
    }

    /**
     * Set PID constants
     * @param kp Proportional constant
     * @param ki Integral constant
     * @param kd Derivative constant
     * @param kv Velocity constant
     * @param ka Acceleration constant
     */
    public void configure(double kp, double ki, double kd, double kv, double ka) {
        kp_ = kp;
        ki_ = ki;
        kd_ = kd;
        kv_ = kv;
        ka_ = ka;
    }

    /**
     * Reset error, go back to first segment
     */
    public void reset() {
        last_error_ = 0.0;
        current_segment = 0;
    }

    /**
     * Set which trajectory to follow
     * @param profile the trajectory
     */
    public void setTrajectory(Trajectory profile) {
        profile_ = profile;
    }

    /**
     * Calculate motor output for the trajectory
     * @param distance_so_far How far you've gone
     * @return Your motor output to get to the trajectory
     */
    public double calculate(double distance_so_far) {

        if (current_segment < profile_.getNumSegments()) {
            Trajectory.Segment segment = profile_.getSegment(current_segment);
            double error = segment.pos - distance_so_far;
            double output = kp_ * error + kd_ * ((error - last_error_)
                    / segment.dt - segment.vel) + (kv_ * segment.vel
                    + ka_ * segment.acc);

            last_error_ = error;
            current_heading = segment.heading;
            current_segment++;
            SmartDashboard.putNumber(name + "Traj: FollowerSensor", distance_so_far);
            SmartDashboard.putNumber(name + "Traj: FollowerGoal", segment.pos);
            SmartDashboard.putNumber(name + "Traj: FollowerError", error);
            return output;
        } else {
            return 0;
        }
    }

    public double getHeading() {
        return current_heading;
    }

    public boolean isFinishedTrajectory() {
        return current_segment >= profile_.getNumSegments();
    }

    public int getCurrentSegment() {
        return current_segment;
    }

    public int getNumSegments() {
        return profile_.getNumSegments();
    }
}