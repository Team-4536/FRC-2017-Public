package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearScoreBlueRight extends CommandGroup {

    public GearScoreBlueRight() {
    	
    	addSequential(new DriveMotionProfile(Constants.GEAR_SCORE_BLUE_RIGHT_DISTANCE, Constants.DEFAULT_SPEED, Constants.DEFAULT_ACCELERATION, Constants.GEAR_SCORE_BLUE_RIGHT_GOAL_ANGLE, Constants.GEAR_SCORE_BLUE_RIGHT_START_ANGLE, Constants.MOTION_PROFILE_NAVX_PORPORTIONALITY, true));
        addSequential(new DriveMotionProfile(2.0, Constants.GEAR_SCORE_BLUE_RIGHT_START_ANGLE, Constants.GEAR_SCORE_BLUE_RIGHT_START_ANGLE));

    }
}
