package org.usfirst.frc.team4536.robot.subsystems;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.Utilities;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Jasper
 * Command to run the gear slide servo.
 */
public class GearSlide extends Subsystem {

	Servo gearSlideMotor;
	
	/**
	 * @author Jasper
	 * @param motorPort
	 */
	public GearSlide(int motorPort) {
		gearSlideMotor = new Servo(motorPort);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
    	
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * @author Jasper and Eddie
     * @param input Throttle of gear slide motor
     * 1 is all the way up, 0.3 is all the way down.
     */
    public void setGearSlide(double input) {
    	double slideLimit = Utilities.limit(input, Constants.LOWER_LIMIT, Constants.UPPER_LIMIT);
    	gearSlideMotor.set(slideLimit);
    	
    }
    
   
}

