package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.robot.MotionProfile;
import org.usfirst.frc.team4536.utilities.Utilities;
import edu.wpi.first.wpilibj.Timer;

public class DriveMotionProfile extends CommandBase{
	Timer timer = new Timer();
	MotionProfile prof;
	double startingAngle;
	double proportionalityConstant = Constants.FORWARD_NAVX_PROPORTIONALITY;

/**
 * @author Theo
 * @param distance the distance we want the robot to travel. Can be negative or positive. In meters
 * @param goalAngle the angle at which we want the robot to be moving. In degrees.
 * @param startAngle the angle the robot is facing. In degrees.
 * Uses the default values for max speed and max acceleration.
 */
public DriveMotionProfile(double distance, double goalAngle, double startAngle) {
	
	this(distance, Constants.DEFAULT_SPEED, Constants.DEFAULT_ACCELERATION, goalAngle, startAngle);
}

/**
 * @author Theo
 * @param distance the distance we want the robot to travel. Can be negative or positive. In meters.
 * @param maxSpeed the maximum possible speed we want the robot to be traveling at. Always positive. In meters/second.
 * @param maxAcceleration the maximum change in speed we will allow to occur. Always positive. In meters/second squared.
 * @param goalAngle the angle at which we want the robot to be moving. In degrees.
 * @param startAngle the angle the robot is facing. In degrees.
 * similar to the one above but allows overriding the default values of max speed and max acceleration.
 */
public DriveMotionProfile(double distance, double maxSpeed, double maxAcceleration, double goalAngle, double startAngle) {

	requires(driveTrain);
	prof = new MotionProfile(distance, maxSpeed, maxAcceleration, goalAngle, startAngle);
}

/**
 * @author Theo
 * @param distance the distance we want the robot to travel. Can be negative or positive. In meters.
 * @param maxSpeed the maximum possible speed we want the robot to be traveling at. Always positive. In meters/second.
 * @param maxAcceleration the maximum change in speed we will allow to occur. Always positive. In meters/second squared.
 * @param goalAngle the angle at which we want the robot to be moving. In degrees.
 * @param startAngle the angle the robot is facing. In degrees.
 * @param navXProportionality A custom value for NavXProportionality that can be used to override the default. In throttle/inch.
 * similar to the one above but allows overriding the default value of the navXProportionality constant.
 */
public DriveMotionProfile(double distance, double maxSpeed, double maxAcceleration, double goalAngle, double startAngle, double navXProportionality) {
	
	this(distance, maxSpeed, maxAcceleration, goalAngle, startAngle);
	proportionalityConstant = navXProportionality;
}

/**
 * @author Liam
 * @return time in seconds since the command was started.
 */
public double getTime() {
	
	return timer.get();
}

/**
 * @author Audrey
 * @return time needed from the motion profile method.
 */
public double getNeededTime(){
	
	return prof.getTimeNeeded();
}

protected void initialize() {
	timer.reset();
	timer.start();
	
	startingAngle = driveTrain.getNavX().getAngle();
	setTimeout(prof.getTimeNeeded() + Constants.PROFILE_TIMEOUT_OFFSET);
}

protected void execute() {
	driveTrain.DriveHoldAngle(prof.getForwardThrottle(getTime()), prof.getStrafeThrottle(getTime()), prof.getDesiredAngle());
}

protected boolean isFinished() {
	return false;
}

protected void end() {
	driveTrain.DriveHoldAngle(0, 0, 0);
}
		
protected void interrupted() {
	end();
}
}
