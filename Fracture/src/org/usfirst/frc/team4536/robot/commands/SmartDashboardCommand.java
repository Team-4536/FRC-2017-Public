package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.MotionProfile;

import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.utilities.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Noah
 * Command for putting things on the smart dash board
 */
public class SmartDashboardCommand extends CommandBase {
	
    public SmartDashboardCommand() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//OI.feederStationAngle = -Constants.FEEDER_STATION_ANGLE;
    }
    
    /**
     * @author Eddie
     * displays values in the Smart Dashboard
     */
    
    protected void execute() {
    	
    	//NavX
    	try {
    		
    		SmartDashboard.putNumber("Adjusted Angle: ", driveTrain.getNavX().getAngle());
        	SmartDashboard.putNumber("Yaw: ", driveTrain.getNavX().getYaw());
        	SmartDashboard.putNumber("Pitch: ", driveTrain.getNavX().getPitch());
        	SmartDashboard.putNumber("Roll: ", driveTrain.getNavX().getRoll());
    		
    	}
    	catch(NavXException e) {
    	}
    	
    	//Joysticks
    	SmartDashboard.putNumber("Right Joystick Y: ", OI.primaryRightStick.getY());		
    	SmartDashboard.putNumber("Right Joystick X: ", OI.primaryRightStick.getX());		
    	SmartDashboard.putNumber("Secondary Joystick Y: ", OI.secondaryStick.getY());		
    	SmartDashboard.putNumber("Secondary Joystick X: ", OI.secondaryStick.getX());		
    	SmartDashboard.putNumber("Left Joystick Y: ", OI.primaryLeftStick.getY());		
    	SmartDashboard.putNumber("Left Joystick X: ", OI.primaryLeftStick.getX());
    	
    	//TESTS
    	SmartDashboard.putNumber("Last Desired Angle", driveTrain.getLastDesiredAngle());
    	SmartDashboard.putNumber("Joystick Angle", OI.primaryRightStick.getDirectionDegrees());
    	try {
			SmartDashboard.putNumber("Derivative", driveTrain.getNavX().getRate());
		} catch (NavXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//Encoders
    	try {
    	SmartDashboard.putNumber("Forward Encoder", driveTrain.getForwardEncoder(0.0));//timer.getTime()));
    	SmartDashboard.putNumber("Forward Encoder Rate", driveTrain.getForwardRate(0.0));//timer.getTime()));
    	SmartDashboard.putNumber("Strafe Encoder", driveTrain.getStrafeEncoder(0.0));//timer.getTime()));
    	SmartDashboard.putNumber("Strafe Encoder Rate", driveTrain.getStrafeRate(0.0));//timer.getTime()));
    	}
    	catch(EncoderException e) {
    	}
    	
    	SmartDashboard.putBoolean("Collision:", driveTrain.checkForCollision());
    	SmartDashboard.putNumber("Jerk", driveTrain.getJerk());
    	
    	SmartDashboard.putData(driveTrain);
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
    	end();
    }
}
