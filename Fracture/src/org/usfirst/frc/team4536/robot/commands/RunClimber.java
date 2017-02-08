package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;

/**
 * @author Noah
 * Command to run the climber with a joystick
 */
public class RunClimber extends CommandBase {
	double climbSpeed;
    public RunClimber(double speed) {
        requires(climber);
        climbSpeed = speed;
    }
    protected void initialize() {
    }
    
    protected void execute() {
    	climber.setClimber(climbSpeed);
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	climber.setClimber(0);
    }
    
    protected void interrupted() {
    	end();
    }
}
