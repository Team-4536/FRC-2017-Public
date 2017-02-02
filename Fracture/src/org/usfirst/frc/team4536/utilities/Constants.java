package org.usfirst.frc.team4536.utilities;

public class Constants {
  
/*-----------------------------------------------------------Utilities----------------------------------------------------------*/
	
	public static final double DEAD_ZONE = 0.05;
	public static final double FORWARD_SPEED_CURVE = 0.0;
	public static final double TURN_SPEED_CURVE = 0.0;
	public static final double STRAFE_SPEED_CURVE = 0.0;
	public static final double SCALE_PARAM = 0.8; //TODO this needs fine tuning
	public static final double FORWARD_STICTION = 0.07; //TODO IDK what value this should be
	public static final double DRIVE_TRAIN_MAX_VELOCITY = 13; //TODO IDK what value this should be
	
	//Camera

	public static final int CAMERA_RESOLUTION_WIDTH = 1280;
	public static final int CAMERA_RESOLUTION_HEIGHT = 780;
	
	//Drive Train
  
	public static final double HOLD_ANGLE_P_CONSTANT = 0.02; //Throttle per degree
	public static final double FORWARD_STRAFE_RATIO = 3.0; //Ratio of forward velocity to strafing velocity
	public static final double DRIVE_TRAIN_ACCEL_LIMIT = 0.1; //In seconds
	
	//OI
	
	public static final double HOLD_FEEDER_ANGLE = 90.0;
	public static final double HOLD_LEFT_ANGLE = 180.0;
	public static final double HOLD_MIDDLE_ANGLE = -90.0;
	public static final double HOLD_RIGHT_ANGLE = 0.0;
	
	//Profile constants
	//These might need changing, they're mostly just copied from last year.
	
	public static final double FORWARD_NAVX_PROPORTIONALITY = 0.07; //Measured in throttle per inch.
	public static final double DEFAULT_SPEED = 10; //Measured in meters per second.
	public static final double DEFAULT_ACCELERATION = 4; //Measured in meters per second squared.
    public static final double PROFILE_TIMEOUT_OFFSET = .5; //Measured in seconds. Gives the motion profile a bit of extra time to do what it needs.
	
    public static final double DRIVE_ENCODER_PROPORTIONALITY_CONSTANT = 16.; //Ticks per inch
}
