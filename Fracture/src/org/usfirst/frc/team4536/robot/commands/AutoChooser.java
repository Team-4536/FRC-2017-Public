package org.usfirst.frc.team4536.robot.commands;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.NavXException;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Noah
 * Command for choosing an auto mode
 */
public class AutoChooser extends CommandBase {
	
	SendableChooser autoChooser;

    public AutoChooser() {
    	autoChooser = new SendableChooser();
    	
    	autoChooser.addDefault(" Do Nothing", 0);
    	autoChooser.addObject(" Cross Baseline", 1);
    	autoChooser.addObject(" Score Gear Left",  2);
    	autoChooser.addObject(" Score Gear Middle", 3);
    	autoChooser.addObject(" Score Gear Right", 4);
    	autoChooser.addObject(" Test ", 5);
    	autoChooser.addObject("AutoHoldAngle", 6);
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
    			setInitialAngle(Constants.GEAR_LEFT_START_ANGLE);
    			(new ScoreGear(Constants.PEG_POSITION.LEFT_PEG)).start();
    		break;
    		case 3:
    			setInitialAngle(Constants.GEAR_MIDDLE_START_ANGLE);
    			(new ScoreGear(Constants.PEG_POSITION.MIDDLE_PEG)).start();
    		break;
    		case 4:
    			setInitialAngle(Constants.GEAR_RIGHT_START_ANGLE);
    			(new ScoreGear(Constants.PEG_POSITION.RIGHT_PEG)).start();
    		break;
    		case 5:
    			setInitialAngle(0.0);
    			new DriveMotionProfile(2.0, 5.0, 5.0).start();
    		break;	
    		case 6:
    			setInitialAngle(0.0);
    			new AutoHoldAngle(-60, 120, 0.2, 2.0).start();
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
