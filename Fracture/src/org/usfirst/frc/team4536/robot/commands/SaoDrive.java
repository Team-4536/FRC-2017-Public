package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;
import org.usfirst.frc.team4536.utilities.Utilities;

/**
 * @author Audrey
 * Class to drive robot-centric while holding an angle
 * Also uses constants specific to Sao
 */
public class SaoDrive extends CommandBase {
	
	private double forwardThrottle, strafeThrottle;
	double desiredAngle;
	
    public SaoDrive() {
		requires(driveTrain);
    }
    
    protected void initialize() {
    	forwardThrottle = 0.0;
    	strafeThrottle = 0.0;
    	desiredAngle = driveTrain.getLastDesiredAngle();
    }
    
    protected void execute() {
    	
    	forwardThrottle = -OI.secondaryStick.getModY();
		strafeThrottle = OI.secondaryStick.getModX();
		
		forwardThrottle = Utilities.speedCurve(forwardThrottle, Constants.SAO_FORWARD_CURVE);
		strafeThrottle = Utilities.speedCurve(strafeThrottle, Constants.SAO_STRAFE_CURVE);
		
		forwardThrottle = forwardThrottle*Constants.SAO_FORWARD_MAX_SPEED;
		strafeThrottle = strafeThrottle*Constants.SAO_STRAFE_MAX_SPEED;
		
		try {
			
			double angleDif = Utilities.angleDifference(driveTrain.getNavX().getAngle(), desiredAngle);
        	
        	double turnThrottle = angleDif * Constants.HOLD_ANGLE_P_CONSTANT;
        		
    		driveTrain.Drive(forwardThrottle, strafeThrottle, turnThrottle);
    		
    	}
    	catch(NavXException e) {
    		end();
    	}
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
}
