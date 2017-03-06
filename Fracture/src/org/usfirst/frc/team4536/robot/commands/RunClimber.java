package org.usfirst.frc.team4536.robot.commands;

/**
 * @author Noah
 * Command to run the climber with a specified value
 */
public class RunClimber extends CommandBase {
	
	double climbSpeed;
    
	/**
	 * @author Noah
	 * @param speed Only accepts positive values.
	 */
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
    	climber.setClimber(0.0);
    }
    
    protected void interrupted() {
    	end();
    }
}
