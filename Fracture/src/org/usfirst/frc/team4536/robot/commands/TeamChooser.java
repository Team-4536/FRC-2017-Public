package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.robot.OI;
import org.usfirst.frc.team4536.utilities.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Noah
 * Sets up the team chooser on the smartdashboard
 */
public class TeamChooser extends CommandBase {
	
	SendableChooser team;
	
    public TeamChooser() {
    	team = new SendableChooser();
    	team.addDefault("Red", 0);
    	team.addObject("Blue", 1);
    	SmartDashboard.putData("Team Chooser", team);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch ((int) team.getSelected().hashCode()) {
		case 1:
			OI.feederStationAngle = Constants.BLUE_FEEDER_STATION_ANGLE;
		break;
		default:
			OI.feederStationAngle = Constants.RED_FEEDER_STATION_ANGLE;
		break;
    	}
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
