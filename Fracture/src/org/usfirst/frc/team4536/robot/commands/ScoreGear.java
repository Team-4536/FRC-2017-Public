package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ScoreGear extends CommandBase {

	Constants.PEG_POSITION desiredPeg;
	
    public ScoreGear(Constants.PEG_POSITION peg) {
       desiredPeg = peg;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (desiredPeg == Constants.PEG_POSITION.LEFT_PEG) {
    		(new GearScoreRedLeft()).start();
    	}
    	else if (desiredPeg == Constants.PEG_POSITION.MIDDLE_PEG) {
    		(new DriveMotionProfile(Constants.GEAR_MIDDLE_DISTANCE, Constants.GEAR_MIDDLE_GOAL_ANGLE, Constants.GEAR_MIDDLE_START_ANGLE)).start();
    	}
    	else if (desiredPeg == Constants.PEG_POSITION.RIGHT_PEG) {
<<<<<<< HEAD
    		(new DriveMotionProfile(Constants.GEAR_SCORE_RED_RIGHT_DISTANCE, Constants.GEAR_SCORE_RED_RIGHT_GOAL_ANGLE, Constants.GEAR_SCORE_RED_RIGHT_START_ANGLE)).start();
=======
    		(new GearScoreRedRight()).start();
>>>>>>> b6914b3825d92f2d3365ba549d98f8a55cd190d1
    	}
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
