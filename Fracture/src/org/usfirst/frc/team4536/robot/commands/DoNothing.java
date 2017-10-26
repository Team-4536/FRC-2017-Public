package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.NavXException;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Noah
 * This extremely useful auto mode does absolutely nothing
 */
public class DoNothing extends CommandBase {

    public DoNothing() {
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.Drive(0.0, 0.0, 0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
