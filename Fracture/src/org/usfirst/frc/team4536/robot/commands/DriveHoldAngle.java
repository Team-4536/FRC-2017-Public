package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;

//TODO THIS DOES NOT WORK. Don't worry i'll fix it. ;)
//Do not try to make anything that builds upon it.

/**
 * @author Noah
 * Class to drive robot-centric while holding an angle
 */
public class DriveHoldAngle extends CommandBase {
	
	private double forwardThrottle, strafeThrottle;
	double desiredAngle;
	
    public DriveHoldAngle(double inputAngle) {
		requires(driveTrain);
		
		desiredAngle = inputAngle;
    }
    
    protected void initialize() {
    	forwardThrottle = 0;
    	strafeThrottle = 0;
    }
    
    protected void execute() {
    	
    	forwardThrottle = -OI.primaryLeftStick.getModY();
		strafeThrottle = OI.primaryLeftStick.getModX();
		
		driveTrain.DriveHoldAngle(forwardThrottle, strafeThrottle, desiredAngle);
    	
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
}
