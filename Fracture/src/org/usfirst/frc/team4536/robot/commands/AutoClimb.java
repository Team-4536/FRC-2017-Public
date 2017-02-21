package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Audrey
 */
public class AutoClimb extends CommandGroup {

    public AutoClimb() {
        addSequential(new OffGround());
        addParallel(new DriveSlidePositions());
    }
}
