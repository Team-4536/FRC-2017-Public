package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.MotionProfile;

import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.utilities.Utilities;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class SmartDashboardCommand extends CommandBase {

    public SmartDashboardCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    
    /**
     * @author Eddie
     * displays values in the Smart Dashboard
     */
    
    protected void execute() {
    	
    	//NavX
    	SmartDashboard.putNumber("Adjusted Angle: ", driveTrain.getNavX().getAngle());
    	SmartDashboard.putNumber("Yaw: ", driveTrain.getNavX().getYaw());
    	SmartDashboard.putNumber("Pitch: ", driveTrain.getNavX().getPitch());
    	SmartDashboard.putNumber("Roll: ", driveTrain.getNavX().getRoll());
    	
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
