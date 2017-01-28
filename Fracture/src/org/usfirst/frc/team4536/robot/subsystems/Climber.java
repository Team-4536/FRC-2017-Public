package org.usfirst.frc.team4536.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;

import org.usfirst.frc.team4536.robot.Utilities;

/**
 * @author Noah
 * Subsystem for the climber
 */
public class Climber extends Subsystem {
	
	Spark motor;
	/**
	 * @author Noah
	 * @param motorPort
	 */
	public Climber(int motorPort) {
		motor = new Spark(motorPort);
	}
	
    public void initDefaultCommand() {
    }
    
    /**
     * @author Noah
     * @param input
     */
    public void setClimber(double input) {
    	Utilities.limit(input, 0, 1);
    	motor.set(input);
    }
}

