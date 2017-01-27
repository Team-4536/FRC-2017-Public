package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.Constants;
import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.robot.Utilities;

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
    	
    	forwardThrottle = Utilities.deadZone(-OI.primaryStick.getY(), Constants.DEAD_ZONE);
		strafeThrottle = Utilities.deadZone(OI.primaryStick.getX(), Constants.DEAD_ZONE);
    	turnThrottle = Utilities.deadZone(OI.secondaryStick.getX(), Constants.DEAD_ZONE);
    	forwardThrottle = Utilities.scale(forwardThrottle, strafeThrottle, Constants.SCALE_PARAM);
    	strafeThrottle = Utilities.scale(strafeThrottle, forwardThrottle, Constants.SCALE_PARAM);
    	System.out.println("forward " + Utilities.scale(0.3, 0.9, 0.8));
    	System.out.println("strafe " + Utilities.scale(0.9, 0.3, 0.8));
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
