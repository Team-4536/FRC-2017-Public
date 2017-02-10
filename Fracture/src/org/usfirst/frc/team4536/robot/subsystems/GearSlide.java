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
	//TODO will not work until we replace with servo stuff
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * @author Jasper and Eddie
     * @param input, throttle of gear slide motor
     * 1 is all the way up, 0.3 is all the way down.
     * 
     */
    
    public void setGearSlide(double input) {
    	
    	gearSlideMotor.set(input);
    }
    
   
}

