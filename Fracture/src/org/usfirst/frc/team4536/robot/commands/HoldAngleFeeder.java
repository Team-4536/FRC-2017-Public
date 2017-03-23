package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;
import org.usfirst.frc.team4536.utilities.Utilities;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@author Theo
 *Class to drive while holding an angle, field-centric.
 */
public class HoldAngleFeeder extends CommandBase {
	private double forwardThrottle, strafeThrottle, turnThrottle, rAng;
	
	SendableChooser teamChooser;

    public HoldAngleFeeder() {
        requires(driveTrain);
        
        teamChooser = new SendableChooser();
    	teamChooser.addDefault("Red", 0);
    	teamChooser.addObject("Blue", 1);
    	SmartDashboard.putData("Team Chooser", teamChooser);
    }

    protected void initialize() {
    	forwardThrottle = 0;
    	strafeThrottle = 0;
    	turnThrottle = 0;
    	
    	switch ((int) teamChooser.getSelected().hashCode()) {
    		case 1:
    			rAng = Constants.BLUE_FEEDER_STATION_ANGLE;
    		break;
    		default:
    			rAng = Constants.RED_FEEDER_STATION_ANGLE;
    		break;
    	}
    }

    protected void execute() {
    	
    	try {
    		
    		double speedCurveMagnitude = Utilities.speedCurve(OI.primaryRightStick.getModMagnitude(), Constants.HOLD_ANGLE_SPEED_CURVE);
    		forwardThrottle = Math.cos(Math.toRadians(driveTrain.getNavX().getAngle() - OI.primaryRightStick.getDirectionDegrees())) * speedCurveMagnitude;
        	strafeThrottle = Math.sin(Math.toRadians(driveTrain.getNavX().getAngle() - OI.primaryRightStick.getDirectionDegrees())) * Constants.FORWARD_STRAFE_RATIO * -speedCurveMagnitude;

        	double angleDif = Utilities.angleDifference(driveTrain.getNavX().getAngle(), rAng);
        	turnThrottle = angleDif * Constants.HOLD_ANGLE_P_CONSTANT;
        	
        	turnThrottle = Utilities.limit(turnThrottle, 1 - Constants.HOLD_ANGLE_SCALE_PARAM);
        	forwardThrottle = Utilities.scale(forwardThrottle, strafeThrottle, 1 - Math.abs(turnThrottle));
        	strafeThrottle = Utilities.scale(strafeThrottle, forwardThrottle, 1 - Math.abs(turnThrottle));
        		
    		driveTrain.Drive(forwardThrottle, strafeThrottle, turnThrottle);
    		
    	}
    	catch(NavXException e) {
    		end();
    	}
    	
    	driveTrain.setLastDesiredAngle(rAng);
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
