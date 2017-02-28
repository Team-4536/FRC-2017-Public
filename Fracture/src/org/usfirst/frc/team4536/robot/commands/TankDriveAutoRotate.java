package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;
import org.usfirst.frc.team4536.utilities.Utilities;

/**
 *
 */
public class TankDriveAutoRotate extends CommandBase {
	
	private double forwardThrottle, strafeThrottle, turnThrottle, lastAngle;

    public TankDriveAutoRotate() {
    	requires(driveTrain);
    }
    
    protected void initialize() {
    	forwardThrottle = 0;
    	strafeThrottle = 0;
    	turnThrottle = 0;
    	lastAngle = driveTrain.getLastDesiredAngle(); 
    	//Keep the robot from spazzing out.
    }
    
    protected void execute() {
    	try {
    		
    		double speedCurveMagnitude = Utilities.speedCurve(OI.primaryRightStick.getModMagnitude(), Constants.TANK_ROTATE_SPEED_CURVE);
        	forwardThrottle = Math.cos(Math.toRadians(driveTrain.getNavX().getAngle() - OI.primaryRightStick.getDirectionDegrees())) * speedCurveMagnitude;
        	 
        	if(OI.primaryRightStick.getMagnitude() < Constants.DEAD_ZONE){		
        		 turnThrottle = 0; //Constants.AUTO_ROTATE_P_CONSTANT * Utilities.shortestAngle(driveTrain.getNavX().getAngle(), lastAngle);
        	 }
        	 else{
        		turnThrottle = Constants.TANK_ROTATE_P_CONSTANT * Utilities.shortestAngle(driveTrain.getNavX().getAngle(), OI.primaryRightStick.getDirectionDegrees());
        		 lastAngle = OI.primaryRightStick.getDirectionDegrees();
        	 }
    		
    	}
    	catch(NavXException e) {
    		end();
    	}
    	
    	turnThrottle = Utilities.limit(turnThrottle, 1 - Constants.TANK_ROTATE_SCALE_PARAM);
    	forwardThrottle = Utilities.scale(forwardThrottle, strafeThrottle, 1 - Math.abs(turnThrottle));
    	strafeThrottle = 0;
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
