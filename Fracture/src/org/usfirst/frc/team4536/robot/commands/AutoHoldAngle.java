package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.EnhancedTimer;
import org.usfirst.frc.team4536.utilities.NavXException;
import org.usfirst.frc.team4536.utilities.Utilities;

/**
 *@author Theo
 *Class to drive during auto while holding an angle. Will be used after scoring gears to get in a better position.
 */
public class AutoHoldAngle extends CommandBase {
	
	EnhancedTimer timer;
	private double forwardThrottle, strafeThrottle, turnThrottle, dAng,dDirection, autoThrottle, endTime;

    public AutoHoldAngle(double desiredEndAngle, double desiredDirection, double throttle, double time) {
        requires(driveTrain);
        dAng = desiredEndAngle; //The angle we want the robot to be facing when the method stops running.
        dDirection = desiredDirection; // The direction in which we want the robot to be moving.
        autoThrottle = throttle; 
        endTime = time; //The amount of time this should run for.
        timer = new EnhancedTimer();
    }

    protected void initialize() {
    	forwardThrottle = 0;
    	strafeThrottle = 0;
    	turnThrottle = 0;
    	timer.startTimer();
    	//Keep the robot from spazzing out.
    }

    protected void execute() {
    	if(timer.getTime() > endTime){
    		driveTrain.Drive(0.0, 0.0, 0.0);
    	}
    	else{
    	try {
    		
    		double speedCurveMagnitude = Utilities.speedCurve(autoThrottle, Constants.FIELD_SPEED_CURVE);
    		forwardThrottle = Math.cos(Math.toRadians(driveTrain.getNavX().getAngle() - dDirection)) * speedCurveMagnitude;
        	strafeThrottle = Math.sin(Math.toRadians(driveTrain.getNavX().getAngle() - dDirection)) * Constants.FORWARD_STRAFE_RATIO * -speedCurveMagnitude;

        	double angleDif = Utilities.angleDifference(driveTrain.getNavX().getAngle(), dAng);
        	turnThrottle = angleDif * Constants.HOLD_ANGLE_P_CONSTANT;
        	
        	turnThrottle = Utilities.limit(turnThrottle, 1 - Constants.AUTO_ROTATE_SCALE_PARAM);
        	forwardThrottle = Utilities.scale(forwardThrottle, strafeThrottle, 1 - Math.abs(turnThrottle));
        	strafeThrottle = Utilities.scale(strafeThrottle, forwardThrottle, 1 - Math.abs(turnThrottle));
    		driveTrain.Drive(forwardThrottle, strafeThrottle, turnThrottle);
    		
    	}
    	catch(NavXException e) {
    		end();
    	}
    	
    	driveTrain.setLastDesiredAngle(dAng);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
  }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}