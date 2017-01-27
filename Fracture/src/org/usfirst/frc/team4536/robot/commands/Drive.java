package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;

/**
 * @author Noah
 * Command for making the robot drive
 */
public class Drive extends CommandBase {
	
	private double forwardThrottle, strafeThrottle, turnThrottle;
	
    public Drive() {
    	requires(driveTrain);
    }
    
    protected void initialize() {
    	
    	forwardThrottle = 0;
    	strafeThrottle = 0;
    	turnThrottle = 0;
    	
    }
    
    protected void execute() {
    	
    	forwardThrottle = -OI.primaryStick.getY();
    	strafeThrottle = OI.primaryStick.getX();
    	turnThrottle = OI.secondaryStick.getX();
    	driveTrain.Drive(forwardThrottle, strafeThrottle, turnThrottle);
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
}
