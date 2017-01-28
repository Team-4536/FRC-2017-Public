package org.usfirst.frc.team4536.robot;

import org.usfirst.frc.team4536.robot.commands.*;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static ModifiedJoystick primaryLeftStick = new ModifiedJoystick(RobotMap.PRIMARY_LEFT_STICK);
	public static ModifiedJoystick primaryRightStick = new ModifiedJoystick(RobotMap.PRIMARY_RIGHT_STICK);
	public static ModifiedJoystick secondaryStick = new ModifiedJoystick(RobotMap.SECONDARY_STICK);
	
	public static Button holdFeeder;
	public static Button holdLeft;
	public static Button holdMiddle;
	public static Button holdRight;
	
	public static void ButtonHandling() {
		holdFeeder = new JoystickButton(primaryLeftStick, RobotMap.HOLD_FEEDER_BUTTON);
		holdLeft = new JoystickButton(primaryLeftStick, RobotMap.HOLD_LEFT_BUTTON);
		holdMiddle = new JoystickButton(primaryLeftStick, RobotMap.HOLD_MIDDLE_BUTTON);
		holdRight = new JoystickButton(primaryLeftStick, RobotMap.HOLD_RIGHT_BUTTON);
		
		holdFeeder.whenPressed(new DriveHoldAngle(Constants.HOLD_FEEDER_ANGLE));
		holdLeft.whenPressed(new DriveHoldAngle(Constants.HOLD_LEFT_ANGLE));
		holdMiddle.whenPressed(new DriveHoldAngle(Constants.HOLD_MIDDLE_ANGLE));
		holdRight.whenPressed(new DriveHoldAngle(Constants.HOLD_RIGHT_ANGLE));
		
		holdFeeder.whenReleased(new Drive());
		holdLeft.whenReleased(new Drive());
		holdMiddle.whenReleased(new Drive());
		holdRight.whenReleased(new Drive());
	}
}
