package org.usfirst.frc.team4536.robot;

import org.usfirst.frc.team4536.robot.commands.*;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static ModifiedJoystick primaryStick = new ModifiedJoystick(RobotMap.PRIMARY_STICK);
	public static ModifiedJoystick secondaryStick = new ModifiedJoystick(RobotMap.SECONDARY_STICK);
	
	public static Button holdFeeder;
	public static Button holdLeft;
	public static Button holdMiddle;
	public static Button holdRight;
	
	public static void ButtonHandling() {
		holdFeeder = new JoystickButton(primaryStick, 9);
		holdLeft = new JoystickButton(primaryStick, 10);
		holdMiddle = new JoystickButton(primaryStick, 11);
		holdRight = new JoystickButton(primaryStick, 12);
		
		holdFeeder.whenPressed(new DriveHoldAngle(90));
		holdLeft.whenPressed(new DriveHoldAngle(180));
		holdMiddle.whenPressed(new DriveHoldAngle(-90));
		holdRight.whenPressed(new DriveHoldAngle(0));
		
		holdFeeder.whenReleased(new Drive());
		holdLeft.whenReleased(new Drive());
		holdMiddle.whenReleased(new Drive());
		holdRight.whenReleased(new Drive());
	}
}
