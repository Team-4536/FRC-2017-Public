package org.usfirst.frc.team4536.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
		//Motors
	
	public static final int LEFT_FRONT_MOTOR = 0;
	public static final int LEFT_BACK_MOTOR = 1;
	public static final int RIGHT_FRONT_MOTOR = 2;
	public static final int RIGHT_BACK_MOTOR = 3;
	public static final int GEAR_SLIDE_MOTOR = 5;
	
	public static final int CLIMBER_MOTOR = 4;
	
	//Joysticks
	
	public static final int PRIMARY_LEFT_STICK = 0;
	public static final int PRIMARY_RIGHT_STICK = 1;
	public static final int SECONDARY_STICK = 2;
	
	//Buttons
	
	public static final int HOLD_FEEDER_BUTTON = 9;
	public static final int HOLD_LEFT_BUTTON = 10;
	public static final int HOLD_MIDDLE_BUTTON = 11;
	public static final int HOLD_RIGHT_BUTTON = 12;
	
	//Encoders These values are copied from last year and almost definitely incorrect.
	
	public static final int STRAFE_ENCODER_CHANNEL_A = 8;
	public static final int STRAFE_ENCODER_CHANNEL_B = 9;
	public static final int FORWARD_ENCODER_CHANNEL_A = 6;
	public static final int FORWARD_ENCODER_CHANNEL_B = 7;
}
