package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Noah
 * Command for choosing an auto mode
 */
public class AutoChooser extends CommandBase {
	
	SendableChooser<Integer> autoChooser;

    public AutoChooser() {
    	autoChooser = new SendableChooser<Integer>();
    	
    	autoChooser.addDefault(" Do Nothing", 0);
    	autoChooser.addObject(" Cross Baseline", 1);
    	autoChooser.addObject(" Score Gear RedLeft",  2);
    	autoChooser.addObject(" Score Gear RedRight",  3);
    	autoChooser.addObject(" Score Gear Middle", 4);
    	autoChooser.addObject(" Score Gear BlueLeft",  5);
    	autoChooser.addObject(" Score Gear BlueRight", 6);
    	SmartDashboard.putData(" Auto Chooser", autoChooser);
    }
    
    public void setInitialAngle(double input) {
    	try {
    	driveTrain.getNavX().setAngleAdjustment(input);
    	}
    	catch(NavXException e){
    		end();
    	}
    	driveTrain.setLastDesiredAngle(input);
    }
    
    protected void initialize() {
    	switch ((int) autoChooser.getSelected().hashCode()) {
    		case 1:
    			setInitialAngle(0.0);
    			new CrossBaseline().start();
    		break;
    		case 2:
    			setInitialAngle(Constants.GEAR_SCORE_RED_LEFT_START_ANGLE);
    			(new GearScoreRedLeft()).start();
    		break;
    		case 3:
    			setInitialAngle(Constants.GEAR_SCORE_RED_RIGHT_START_ANGLE);
    			(new GearScoreRedRight()).start();
    		break;
    		case 4:
    			setInitialAngle(Constants.GEAR_MIDDLE_START_ANGLE);
    			(new ScoreGear(Constants.PEG_POSITION.MIDDLE_PEG)).start();
    		break;
    		case 5:
    			setInitialAngle(Constants.GEAR_SCORE_BLUE_LEFT_START_ANGLE);
    			(new GearScoreRedLeft()).start();
    		break;
    		case 6:
    			setInitialAngle(Constants.GEAR_SCORE_BLUE_RIGHT_START_ANGLE);
    			(new GearScoreRedRight()).start();
    		break;
    		default:
    			setInitialAngle(0.0);
    			new DoNothing().start();
    		break;
    	}
    }
    
    protected void execute() {
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
}
