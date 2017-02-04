package org.usfirst.frc.team4536.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
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
     * @author Jasper
     * @param input, throttle of gear slide motor
     */
    
    public void setGearSlide(double input) {
    	
    	gearSlideMotor.set(input);
    }
    
   
}

