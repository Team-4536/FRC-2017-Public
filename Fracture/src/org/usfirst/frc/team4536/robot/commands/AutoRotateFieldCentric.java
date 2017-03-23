package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;
import org.usfirst.frc.team4536.utilities.Utilities;

/**
 * @author Theo
 * Class to drive field-centric while turning slowly to whatever angle the robot is moving in.
 */
public class AutoRotateFieldCentric extends CommandBase {
	
	private double forwardThrottle, strafeThrottle, turnThrottle, lastAngle;
	
    public AutoRotateFieldCentric() {
        requires(driveTrain);
    }
    
    protected void initialize() {
    	forwardThrottle = 0.0;
    	strafeThrottle = 0.0;
    	turnThrottle = 0.0;
    	lastAngle = driveTrain.getLastDesiredAngle(); 
    	//Keep the robot from spazzing out.
    }
    
    protected void execute() {
    	
    	try {
    		
    		double speedCurveMagnitude = Utilities.speedCurve(OI.primaryRightStick.getModMagnitude(), Constants.AUTO_ROTATE_SPEED_CURVE);
        	forwardThrottle = Math.cos(Math.toRadians(driveTrain.getNavX().getAngle() - OI.primaryRightStick.getDirectionDegrees())) * speedCurveMagnitude;
        	strafeThrottle = Math.sin(Math.toRadians(driveTrain.getNavX().getAngle() - OI.primaryRightStick.getDirectionDegrees())) * Constants.FORWARD_STRAFE_RATIO * -speedCurveMagnitude;
        	 
        	if(OI.primaryRightStick.getMagnitude() < Constants.DEAD_ZONE){		
        		 turnThrottle = 0.0; //Constants.AUTO_ROTATE_P_CONSTANT * Utilities.shortestAngle(driveTrain.getNavX().getAngle(), lastAngle);
        	 }
        	 else{
        		turnThrottle = Constants.AUTO_ROTATE_P_CONSTANT * Utilities.shortestAngle(driveTrain.getNavX().getAngle(), OI.primaryRightStick.getDirectionDegrees());
        		 lastAngle = OI.primaryRightStick.getDirectionDegrees();
        	 }
    		
    	}
    	catch(NavXException e) {
    		end();
    	}
    	
    	turnThrottle = Utilities.limit(turnThrottle, 1.0 - Constants.AUTO_ROTATE_SCALE_PARAM);
    	forwardThrottle = Utilities.scale(forwardThrottle, strafeThrottle, 1.0 - Math.abs(turnThrottle));
    	strafeThrottle = Utilities.scale(strafeThrottle, forwardThrottle, 1.0 - Math.abs(turnThrottle));
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