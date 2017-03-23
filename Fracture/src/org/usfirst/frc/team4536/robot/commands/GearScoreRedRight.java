package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearScoreRedRight extends CommandGroup {

    public GearScoreRedRight() {

    	addSequential(new DriveMotionProfile(Constants.GEAR_SCORE_RED_RIGHT_DISTANCE, Constants.GEAR_SCORE_RED_RIGHT_GOAL_ANGLE, Constants.GEAR_SCORE_RED_RIGHT_START_ANGLE));
        addSequential(new DriveMotionProfile(2.0, Constants.GEAR_SCORE_RED_RIGHT_START_ANGLE, Constants.GEAR_SCORE_RED_RIGHT_START_ANGLE));

    }
}
