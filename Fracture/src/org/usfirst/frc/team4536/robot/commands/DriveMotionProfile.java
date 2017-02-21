package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;
import org.usfirst.frc.team4536.robot.MotionProfile;
import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4536.utilities.Utilities;
import edu.wpi.first.wpilibj.Timer;

public class DriveMotionProfile extends CommandBase{
	Timer timer = new Timer();
	MotionProfile prof;
	double startingAngle;
	double proportionalityConstant = Constants.AUTO_HOLD_ANGLE_P_CONSTANT;

/**
 * @author Theo
 * @param distance the distance we want the robot to travel. Can be negative or positive. In feet.
 * @param goalAngle the angle at which we want the robot to be moving. In degrees.
 * @param startAngle the angle the robot is facing. In degrees.
 * Uses the default values for max speed and max acceleration.
 */
public DriveMotionProfile(double distance, double goalAngle, double startAngle) {
	
	this(distance, Constants.DEFAULT_SPEED, Constants.DEFAULT_ACCELERATION, goalAngle, startAngle);
}

/**
 * @author Theo
 * @param distance the distance we want the robot to travel. Can be negative or positive. In feet.
 * @param maxSpeed the maximum possible speed we want the robot to be traveling at. Always positive. In feet/second.
 * @param maxAcceleration the maximum change in speed we will allow to occur. Always positive. In feet/second squared.
 * @param goalAngle the angle at which we want the robot to be moving. In degrees.
 * @param startAngle the angle the robot is facing. In degrees.
 * similar to the one above but allows overriding the default values of max speed and max acceleration.
 */
public DriveMotionProfile(double distance, double maxSpeed, double maxAcceleration, double goalAngle, double startAngle) {

	//startingAngle = startAngle;
	requires(driveTrain);
	prof = new MotionProfile(distance, maxSpeed, maxAcceleration, goalAngle, startAngle);
}

/**
 * @author Theo
 * @param distance the distance we want the robot to travel. Can be negative or positive. In feet.
 * @param maxSpeed the maximum possible speed we want the robot to be traveling at. Always positive. In feet/second.
 * @param maxAcceleration the maximum change in speed we will allow to occur. Always positive. In feet/second squared.
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
	
	driveTrain.resetCollision();
	driveTrain.resetEncoders();
	timer.reset();
	timer.start();
	
	try {
		
		//driveTrain.getNavX().getAngle();
		startingAngle = driveTrain.getNavX().getAngle();
		setTimeout(prof.getTimeNeeded() + Constants.PROFILE_TIMEOUT_OFFSET);
		
	}
	catch(NavXException e) {
	}
	
}

protected void execute() {
	
	try {
		
		double angleDif = Utilities.angleDifference(driveTrain.getNavX().getAngle(), startingAngle);
    	
    	double turnThrottle = angleDif * Constants.AUTO_HOLD_ANGLE_P_CONSTANT;
    		
		driveTrain.Drive(prof.getForwardThrottle(getTime()), prof.getStrafeThrottle(getTime()), turnThrottle);

	}
	catch(NavXException e) {
	}
	
	if (driveTrain.checkForCollision()) {
		end();
	}
	
}

protected boolean isFinished() {
	try {
		if (getTime() > getNeededTime() + Constants.PROFILE_TIMEOUT_OFFSET) {
			return true;
		}
		double t = driveTrain.getNavX().getAngle();
		return false;

	}
	catch(NavXException e) {
		return true;
	}
}

protected void end() {
	driveTrain.Drive(0, 0, 0);
}
		
protected void interrupted() {
	end();
}
}
