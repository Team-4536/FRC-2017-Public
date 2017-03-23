package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;
import org.usfirst.frc.team4536.utilities.Utilities;

/**
 * @author Audrey
 */
public class RotateFieldCentric extends CommandBase {
	private double forwardThrottle, strafeThrottle, turnThrottle;

    public RotateFieldCentric(double turnInput) {
        requires(driveTrain);
    	turnThrottle = turnInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	forwardThrottle = 0.0;
    	strafeThrottle = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	try {
    		
    		double speedCurveMagnitude = Utilities.speedCurve(OI.primaryRightStick.getModMagnitude(), Constants.FIELD_SPEED_CURVE);
    		forwardThrottle = Math.cos(Math.toRadians(driveTrain.getNavX().getAngle() - OI.primaryRightStick.getDirectionDegrees())) * speedCurveMagnitude;
        	strafeThrottle = Math.sin(Math.toRadians(driveTrain.getNavX().getAngle() - OI.primaryRightStick.getDirectionDegrees())) * Constants.FORWARD_STRAFE_RATIO * -speedCurveMagnitude;
        	
        	if(turnThrottle>=0.0)turnThrottle = 1.0 - Constants.ROTATE_SCALE_PARAM;
        	else turnThrottle = -(1.0 - Constants.ROTATE_SCALE_PARAM);
        	forwardThrottle = Utilities.scale(forwardThrottle, strafeThrottle, 1.0 - Math.abs(turnThrottle));
        	strafeThrottle = Utilities.scale(strafeThrottle, forwardThrottle, 1.0 - Math.abs(turnThrottle));
        		
    		driveTrain.Drive(forwardThrottle, strafeThrottle, turnThrottle);
    		
    	}
    	catch(NavXException e) {
    		end();
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
