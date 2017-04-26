package com.team2502.robot2017.command;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Do you or a loved one suffer from high error rate? Then you may be entitled for financial compensation!
 * <br>
 * Shaky turning disorder is caused by a lack of PID in the Autonomous Command.
 * <br>
 * Exposure to poor programming in FLL, FTC, VRC, or FRC may have put you at risk for a lack of PID.
 * <br>
 * Please don't wait. Call now for free JavaDoc comments . . .
 *
 */
public abstract class PIDCommandBase extends Command
{

	private double output;
	private PIDController controller;
	private PIDOutput pidwrite;

	/**
	 * Make a new Command that uses PID for something with all the bells and whistles
	 * @param kP      P-value in PID
	 * @param kI      I-Value in PID
	 * @param kD      D-value in PID
	 * @param kF      F-value in feed-forward
	 * @param source  An object that implements PIDSource.
	 * @param period  The number of milliseconds to wait between each calculation. Important for calculating error if kD and kI are not 0.
	 */
	public PIDCommandBase(double kP, double kI, double kD, double kF, PIDSource source, double period)
	{

		pidwrite = new PIDOutput() {
			@Override
			public void pidWrite(double result) {
				output = result;
			}
		};

		controller = new PIDController(kP, kI, kD, kF, source, pidwrite, period);
	}

	/**
	 * Make a new Command that uses PID for something that doesn't need feed-forward
	 * @param kP      P-value in PID
	 * @param kI      I-Value in PID
	 * @param kD      D-value in PID
	 * @param source  An object that implements PIDSource.
	 * @param period  The number of milliseconds to wait between each calculation. Important for calculating error if kD and kI are not 0.
	 */
	public PIDCommandBase(double kP, double kI, double kD, PIDSource source, double period)
	{
		this(kP, kI, kD, 0.0, source, period);
	}

	/**
	 * Make a new Command that uses PID for something with the default period
	 * @param kP      P-value in PID
	 * @param kI      I-Value in PID
	 * @param kD      D-value in PID
	 * @param kF      F-value in feed-forward
	 * @param source  An object that implements PIDSource.
	 */
	public PIDCommandBase(double kP, double kI, double kD, double kF, PIDSource source)
	{
		this(kP, kI, kD, kF, source, 50);
	}

	/**
	 * Make a new Command that uses PID for something with no feed-forward and the default period
	 * @param kP      P-value in PID
	 * @param kI      I-Value in PID
	 * @param kD      D-value in PID
	 * @param source  An object that implements PIDSource.
	 */
	public PIDCommandBase(double kP, double kI, double kD, PIDSource source)
	{
		this(kP, kI, kD, 0.0, source, 50);
	}

	/**
	 * @return The instance of the PIDController we made for you and your PID
	 */
	public PIDController getController() { return controller; }

	/**
	 * @return What the PID controller calculated
	 */
	public double getOutput() { return output; }





}
