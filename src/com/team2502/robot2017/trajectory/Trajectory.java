package com.team2502.robot2017.trajectory;

//import com.team254.lib.util.ChezyMath;

/**
 * Stolen from Cheezy Poof 2014 code
 *
 * Implementation of a Trajectory using arrays as the underlying storage
 * mechanism.
 *
 * @author Jared341
 */
public class Trajectory {

    /**
     * A pair of trajectories
     */
    public static class Pair {
        public Pair(Trajectory left, Trajectory right) {
            this.left = left;
            this.right = right;
        }

        public Trajectory left;
        public Trajectory right;
    }

    /**
     * A segment inside a trajectory (between waypoints)
     *
     * Tells you how to get to the next waypoint from the one you just passed
     */
    public static class Segment {

        public double pos, vel, acc, jerk, heading, dt, x, y;

        public Segment() {}

        /**
         * Make a segment the hard way
         *
         * The cooordinate system goes on inches
         *
         * @param pos How far you have to travel (inches)
         * @param vel Unknown (inches/sec)
         * @param acc Unknown (inches/sec^2)
         * @param jerk Unknown (inches/sec^3)
         * @param heading Your angle when you get there
         * @param dt How long it should take to get there
         * @param x X-coordinate
         * @param y Y-coordinage
         */
        public Segment(double pos, double vel, double acc, double jerk,
                       double heading, double dt, double x, double y) {
            this.pos = pos;
            this.vel = vel;
            this.acc = acc;
            this.jerk = jerk;
            this.heading = heading;
            this.dt = dt;
            this.x = x;
            this.y = y;
        }

        /**
         * Make a segment the easy way
         * @param to_copy The segment to duplicate
         */
        public Segment(Segment to_copy) {
            pos = to_copy.pos;
            vel = to_copy.vel;
            acc = to_copy.acc;
            jerk = to_copy.jerk;
            heading = to_copy.heading;
            dt = to_copy.dt;
            x = to_copy.x;
            y = to_copy.y;
        }

        /**
         * @return String representation of the segment
         */
        public String toString() {
            return "pos: " + pos + "; vel: " + vel + "; acc: " + acc + "; jerk: "
                    + jerk + "; heading: " + heading;
        }
    }

    Segment[] segments_ = null;
    boolean inverted_y_ = false;

    /**
     * Make a new trajectory
     * @param length How many segments will be in the trajectory
     */
    public Trajectory(int length) {
        segments_ = new Segment[length];
        for (int i = 0; i < length; ++i) {
            segments_[i] = new Segment();
        }
    }

    /**
     * Make a new trajectory
     * @param segments The list of segments that you are going to use
     */
    public Trajectory(Segment[] segments) {
        segments_ = segments;
    }

    /**
     * Drives backwards (probably)
     * @param inverted Do you want to drive backwards?
     */
    public void setInvertedY(boolean inverted) {
        inverted_y_ = inverted;
    }

    /**
     * @return the number of segments in the trajectory #whatdidyouexpect
     */
    public int getNumSegments() {
        return segments_.length;
    }

    /**
     * Get a segment, and <b>adjust it if you are driving backwards</b>
     * @param index The index of the segment in the segment list
     * @return THe segment in question
     */
    public Segment getSegment(int index) {
        if (index < getNumSegments()) {
            if (!inverted_y_) {
                return segments_[index];
            } else {
                Segment segment = new Segment(segments_[index]);
                segment.y *= -1.0;

                // Find the "opposite" angle
                segment.heading = 2*Math.PI - segment.heading;

                // Confine it to be 0 < angle < 2pi
                while (segment.heading >= 2.0 * Math.PI) {
                    segment.heading -= 2.0 * Math.PI;
                }
                while (segment.heading < 0.0) {
                    segment.heading += 2.0 * Math.PI;
                }

                return segment;
            }
        } else {
            return new Segment();
        }
    }

    /**
     * Change a segment
     * @param index The index of the segment to replace
     * @param segment THe new segment
     */
    public void setSegment(int index, Segment segment) {
        if (index < getNumSegments()) {
            segments_[index] = segment;
        }
    }

    /**
     * Scale a segment
     * @param scaling_factor scale it by this much
     */
    public void scale(double scaling_factor) {
        for (int i = 0; i < getNumSegments(); ++i) {
            segments_[i].pos *= scaling_factor;
            segments_[i].vel *= scaling_factor;
            segments_[i].acc *= scaling_factor;
            segments_[i].jerk *= scaling_factor;
        }
    }

    /**
     * Add on another trajectory to the end of this one
     * @param to_append The one to append
     */
    public void append(Trajectory to_append) {
        Segment[] temp = new Segment[getNumSegments()
                + to_append.getNumSegments()];

        for (int i = 0; i < getNumSegments(); ++i) {
            temp[i] = new Segment(segments_[i]);
        }
        for (int i = 0; i < to_append.getNumSegments(); ++i) {
            temp[i + getNumSegments()] = new Segment(to_append.getSegment(i));
        }

        this.segments_ = temp;
    }

    /**
     * Copy a trajectory
     * @return A copy of the trajectory
     */
    public Trajectory copy() {
        Trajectory cloned
                = new Trajectory(getNumSegments());
        cloned.segments_ = copySegments(this.segments_);
        return cloned;
    }

    /**
     * @param tocopy A list of segments to copy
     * @return The list of segments (but copied)
     */
    private Segment[] copySegments(Segment[] tocopy) {
        Segment[] copied = new Segment[tocopy.length];
        for (int i = 0; i < tocopy.length; ++i) {
            copied[i] = new Segment(tocopy[i]);
        }
        return copied;
    }

    /**
     *
     * @return A string representation of the trajectory  #whatdidyouexpect
     */
    public String toString() {
        String str = "Segment\tPos\tVel\tAcc\tJerk\tHeading\n";
        for (int i = 0; i < getNumSegments(); ++i) {
            Trajectory.Segment segment = getSegment(i);
            str += i + "\t";
            str += segment.pos + "\t";
            str += segment.vel + "\t";
            str += segment.acc + "\t";
            str += segment.jerk + "\t";
            str += segment.heading + "\t";
            str += "\n";
        }

        return str;
    }


    /**
     * Literally toString()
     */
    @Deprecated
    public String toStringProfile() {
        return toString();
    }

    /**
     * Gives you x and y instead of pos, vel, acc, jerk, heading
     * @return A different string representation than toString()
     */
    public String toStringEuclidean() {
        String str = "Segment\tx\ty\tHeading\n";
        for (int i = 0; i < getNumSegments(); ++i) {
            Trajectory.Segment segment = getSegment(i);
            str += i + "\t";
            str += segment.x + "\t";
            str += segment.y + "\t";
            str += segment.heading + "\t";
            str += "\n";
        }

        return str;
    }
}
