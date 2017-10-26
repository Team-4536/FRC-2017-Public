package org.usfirst.frc.team4536.robot;

public abstract class Profile {

	/**
	 * @author Jasper
	 * @param time The amount of time since the profile has started
	 * @returns The throttle the robot should be at
	 */
	
	/**
	 * @author Jasper
	 * @param time The amount of time since the profile has started
	 * @returns The veloctiy the robot should be at
	 */
	public abstract double idealVelocity(double time);

	/**
	 * @author Jasper
	 * @param time The amount of time since the profile has started
	 * @returns The distance the robot should be at
	 */
	public abstract double idealDistance(double time);
}