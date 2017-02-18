package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.EnhancedTimer;
import org.usfirst.frc.team4536.utilities.NavXException;


/**
 *
 */
public class OffGround extends CommandBase {

	EnhancedTimer timer;
	
    public OffGround() {
        
    	requires(climber);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new EnhancedTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try{
    		
    		if(driveTrain.getNavX().getPitch()>Constants.OFF_GROUND_ANGLE){
            	
            	timer.startTimer();
            	climber.setClimber(0);
            	
            }else climber.setClimber(1);
            
    	}catch(NavXException e) {
    		
    		;
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if (timer.getTime()>Constants.OFF_GROUND_TIME){
    		
    		return true;
    		
    	}else return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
