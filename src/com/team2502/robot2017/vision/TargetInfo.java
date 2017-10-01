package com.team2502.robot2017.vision;

/**
 * A container class for Targets detected by the vision system, containing the location in three-dimensional space.
 */
public class TargetInfo {
    protected double height;
    protected double offset;

    public TargetInfo(double height, double offset) {
        this.height = height;
        this.offset = offset;
    }

    public double getHeight() {
        return height;
    }

    public double getOffset() {
        return offset;
    }
}