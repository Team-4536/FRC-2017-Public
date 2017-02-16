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
	public static Button backupDrive;
	public static Button fullSpeedClimb;
	public static Button slowClimb;

	public static Button slidePositionTop;
	public static Button slidePositionGear;
	public static Button slidePositionMiddle;
	
	public static Button rotateRight;
	public static Button rotateLeft;
	
	public static Button switchCamera;
	
	public static void ButtonHandling() {
		setFeederStationAngle();
		holdFeeder = new JoystickButton(primaryRightStick, RobotMap.HOLD_FEEDER_BUTTON);
		holdFeeder.whenPressed(new HoldAngle(feederStationAngle));
		holdFeeder.whenPressed(new DriveSlidePositions(Constants.GEAR_POSITION));
		
		holdLeft = new JoystickButton(primaryRightStick, RobotMap.HOLD_LEFT_BUTTON);
		holdLeft.whenPressed(new HoldAngle(Constants.LEFT_PEG_ANGLE));
		
		holdMiddle = new JoystickButton(primaryRightStick, RobotMap.HOLD_MIDDLE_BUTTON);
		holdMiddle.whenPressed(new HoldAngle(Constants.MIDDLE_PEG_ANGLE));
		
		holdRight = new JoystickButton(primaryRightStick, RobotMap.HOLD_RIGHT_BUTTON);
		holdRight.whenPressed(new HoldAngle(Constants.RIGHT_PEG_ANGLE));
		
		plusDegree = new JoystickButton(primaryRightStick, RobotMap.PLUS_DEGREE_BUTTON);
		plusDegree.whenPressed(new AngleAdjustment(true));
		
		minusDegree = new JoystickButton(primaryRightStick, RobotMap.MINUS_DEGREE_BUTTON);
		minusDegree.whenPressed(new AngleAdjustment(false));
		
		switchSao = new JoystickButton(secondaryStick, RobotMap.SAO_SWITCH);
		switchSao.whenPressed(new SaoDrive());
		
		backupDrive = new JoystickButton(primaryLeftStick, RobotMap.BACKUP_SWITCH);
		backupDrive.whenPressed(new BackupDrive());
        
		fullSpeedClimb = new JoystickButton(secondaryStick, RobotMap.FULL_CLIMB);
		fullSpeedClimb.whenPressed(new RunClimber(1));
		fullSpeedClimb.whenPressed(new DriveSlidePositions(Constants.TOP_POSITION));
		fullSpeedClimb.whenReleased(new RunClimber(0));
        
		slowClimb = new JoystickButton(secondaryStick, RobotMap.SLOW_CLIMB);
		slowClimb.whenPressed(new RunClimber(Constants.SLOW_CLIMB_SPEED));
		slowClimb.whenPressed(new DriveSlidePositions(Constants.TOP_POSITION));
		slowClimb.whenReleased(new RunClimber(0));
		
		switchPrimary = new JoystickButton(primaryRightStick, RobotMap.PRIMARY_SWITCH);
		switchPrimary.whenPressed(new AutoRotateFieldCentric());

		slidePositionTop = new JoystickButton(secondaryStick, RobotMap.POSITION_TOP);
		slidePositionTop.whenPressed(new DriveSlidePositions(Constants.TOP_POSITION));
		
		slidePositionGear = new JoystickButton(secondaryStick, RobotMap.POSITION_GEAR);
		slidePositionGear.whenPressed(new DriveSlidePositions(Constants.GEAR_POSITION));
		
		slidePositionMiddle = new JoystickButton(secondaryStick, RobotMap.POSITION_MIDDLE);
		slidePositionMiddle.whenPressed(new DriveSlidePositions(Constants.MIDDLE_POSITION));
		
		rotateRight = new JoystickButton(primaryRightStick, RobotMap.ROTATE_RIGHT);
		rotateRight.whenPressed(new RotateFieldCentric(Constants.ROTATE_RIGHT_THROTTLE));
		rotateRight.whenReleased(new AutoRotateFieldCentric());
		
		rotateLeft = new JoystickButton(primaryRightStick, RobotMap.ROTATE_LEFT);
		rotateLeft.whenPressed(new RotateFieldCentric(Constants.ROTATE_LEFT_THROTTLE));
		rotateLeft.whenReleased(new AutoRotateFieldCentric());
		
		switchCamera = new JoystickButton(secondaryStick, RobotMap.SWITCH_CAMERA);
		switchCamera.whenPressed(new CameraSwitcher());
	
	}
	
	public static void setFeederStationAngle(){
		if ((DriverStation.getInstance()).getAlliance() == DriverStation.Alliance.Blue) {
			feederStationAngle = Constants.FEEDER_STATION_ANGLE;
		}
		else if ((DriverStation.getInstance()).getAlliance() == DriverStation.Alliance.Red) {
			feederStationAngle = -Constants.FEEDER_STATION_ANGLE;	
		}
		else {
			feederStationAngle = 0.0;
		}
	}
}
