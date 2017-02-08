package org.usfirst.frc.team4536.robot;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team4536.robot.commands.*;
import org.usfirst.frc.team4536.utilities.Constants;

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
	public static Button fieldCentric;
	public static Button autoRotate;
	
	public static Button plusDegree;
	public static Button minusDegree;

	public static double feederStationAngle;
	public static Button switchSao;
	public static Button switchPrimary;
	public static Button climb;

	
	public static void ButtonHandling() {
		holdFeeder = new JoystickButton(primaryRightStick, RobotMap.HOLD_FEEDER_BUTTON);
		holdLeft = new JoystickButton(primaryRightStick, RobotMap.HOLD_LEFT_BUTTON);
		holdMiddle = new JoystickButton(primaryRightStick, RobotMap.HOLD_MIDDLE_BUTTON);
		holdRight = new JoystickButton(primaryRightStick, RobotMap.HOLD_RIGHT_BUTTON);
		fieldCentric = new JoystickButton(primaryRightStick, RobotMap.HOLD_CENTER_BUTTON);
		autoRotate = new JoystickButton(primaryRightStick, RobotMap.HOLD_SPECIAL_BUTTON);
		

		plusDegree = new JoystickButton(primaryLeftStick, RobotMap.PLUS_DEGREE_BUTTON);
		minusDegree = new JoystickButton(primaryLeftStick, RobotMap.MINUS_DEGREE_BUTTON);

		holdFeeder.whenPressed(new DriveHoldAngle(feederStationAngle));
		
    switchSao = new JoystickButton(secondaryStick, RobotMap.SAO_SWITCH);
		switchPrimary = new JoystickButton(primaryRightStick, RobotMap.PRIMARY_SWITCH);
		climb = new JoystickButton(secondaryStick, RobotMap.CLIMB);
		
		holdLeft.whenPressed(new DriveHoldAngle(Constants.LEFT_PEG_ANGLE));
		holdMiddle.whenPressed(new DriveHoldAngle(Constants.MIDDLE_PEG_ANGLE));
		holdRight.whenPressed(new DriveHoldAngle(Constants.RIGHT_PEG_ANGLE));
		fieldCentric.whenPressed(new HoldAngle(Constants.RIGHT_PEG_ANGLE));
		autoRotate.whenPressed(new AutoRotateDriveHoldAngle(Constants.RIGHT_PEG_ANGLE));

		switchSao = new JoystickButton(secondaryStick, RobotMap.SAO_SWITCH);
		switchPrimary = new JoystickButton(primaryRightStick, RobotMap.PRIMARY_SWITCH);
		climb = new JoystickButton(secondaryStick, RobotMap.CLIMB);
		
		plusDegree.whenPressed(new AngleAdjustment(true));
		minusDegree.whenPressed(new AngleAdjustment(false));
		
		holdFeeder.whenReleased(new Drive());
		holdLeft.whenReleased(new Drive());
		holdMiddle.whenReleased(new Drive());
		holdRight.whenReleased(new Drive());
		fieldCentric.whenReleased(new Drive());
		autoRotate.whenReleased(new Drive());

		switchSao.whenPressed(new SaoDrive());
		switchPrimary.whenPressed(new Drive());
		climb.whenPressed(new RunClimber());
		
	}
	
	public void setFeederStationAngle(){
		if ((DriverStation.getInstance()).getAlliance() == DriverStation.Alliance.Blue) {
			feederStationAngle = 116.6;
		}
		else if ((DriverStation.getInstance()).getAlliance() == DriverStation.Alliance.Red) {
			feederStationAngle = -116.6;	
		}
		else {
			feederStationAngle = 0.0;
		}
	}
}
