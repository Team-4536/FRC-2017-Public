package org.usfirst.frc.team4536.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Motors
	public static final int LEFT_FRONT_MOTOR = 2;
	public static final int LEFT_BACK_MOTOR = 1;
	public static final int RIGHT_FRONT_MOTOR = 0;
	public static final int RIGHT_BACK_MOTOR = 3;
	
	public static final int GEAR_SLIDE_MOTOR = 5;
	
	public static final int CLIMBER_MOTOR = 4;
	
	//Joysticks
	
	public static final int PRIMARY_LEFT_STICK = 0;
	public static final int PRIMARY_RIGHT_STICK = 1;
	public static final int SECONDARY_STICK = 2;
	
	//Primary Left Buttons
	public static final int BACKUP_SWITCH = 1;
	
	
	//Primary Right Buttons
	public static final int HOLD_FEEDER_BUTTON = 3;
	public static final int HOLD_LEFT_BUTTON = 4;
	public static final int HOLD_MIDDLE_BUTTON = 2;
	public static final int HOLD_RIGHT_BUTTON = 5;
	public static final int HOLD_CENTER_BUTTON = 3;
	public static final int PRIMARY_SWITCH = 1;
	public static final int PLUS_DEGREE_BUTTON = 10;
	public static final int MINUS_DEGREE_BUTTON = 11;
	public static final int ROTATE_RIGHT = 6;
	public static final int ROTATE_LEFT = 7;
	public static final int TANK_FIELD_CENTRIC = 8;

	//Secondary Stick Buttons
	public static final int SAO_SWITCH = 1;
	public static final int CLIMB = 8;
	public static final int FULL_CLIMB = 6;
	public static final int SLOW_CLIMB = 7;
	public static final int AUTO_CLIMB = 2;
	public static final int POSITION_TOP = 4;
	public static final int POSITION_GEAR = 3;
	public static final int POSITION_MIDDLE = 5;
	
	public static final int SWITCH_CAMERA = 7;
	
	//Encoders
	
	public static final int STRAFE_ENCODER_CHANNEL_A = 0; //3 Wires
	public static final int STRAFE_ENCODER_CHANNEL_B = 1; //1 Wire
	public static final int FORWARD_ENCODER_CHANNEL_A = 2; //3 Wires
	public static final int FORWARD_ENCODER_CHANNEL_B = 3; //1 Wire
}
