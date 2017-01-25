package org.usfirst.frc.team4536.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static ModifiedJoystick primaryStick;
	public static ModifiedJoystick secondaryStick;
	
	public OI() {
		primaryStick = new ModifiedJoystick(RobotMap.PRIMARY_STICK);
		secondaryStick = new ModifiedJoystick(RobotMap.SECONDARY_STICK);
	}
}
