package org.usfirst.frc.team4536.utilities;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
  
	//Utilities
	
	public static final double DEAD_ZONE = 0.05;
	public static final double FORWARD_SPEED_CURVE = 0.0;
	public static final double TURN_SPEED_CURVE = 0.0;
	public static final double STRAFE_SPEED_CURVE = 0.0;
	public static final double SCALE_PARAM = 0.8; //TODO this needs fine tuning
	public static final double FORWARD_STICTION = 0.07; //TODO IDK what value this should be
	public static final double DRIVE_TRAIN_MAX_VELOCITY = 13.0; //TODO IDK what value this should be
	
	//Camera

	public static final int CAMERA_RESOLUTION_WIDTH = 1280;
	public static final int CAMERA_RESOLUTION_HEIGHT = 780;
	
	//Drive Train
  
	public static final double HOLD_ANGLE_P_CONSTANT = 0.02; //Throttle per degree
	public static final double FORWARD_STRAFE_RATIO = 3.0; //Ratio of forward velocity to strafing velocity
	public static final double DRIVE_TRAIN_ACCEL_LIMIT = 0.1; //In seconds
	public static final double BACKUP_DRIVE_STRAFE_SPEED_CURVE = 1.0;
	public static final double BACKUP_DRIVE_TURN_SPEED_CURVE = 1.0;
	public static final double BACKUP_DRIVE_FORWARD_SPEED_CURVE = 1.0;
	public static final double CROSS_BASELINE_SPEED = 0.5;
	public static final double CROSS_BASELINE_TIMEOUT = 0.5;
	//OI
	public static final double FEEDER_STATION_ANGLE = 116.6;
	public static final double LEFT_PEG_ANGLE = 60.0;
	public static final double MIDDLE_PEG_ANGLE = 0;
	public static final double RIGHT_PEG_ANGLE = -60.0;
	
	//Profile constants
	//These might need changing, they're mostly just copied from last year.
	
	public static final double FORWARD_NAVX_PROPORTIONALITY = 0.07; //Measured in throttle per inch.

	public static final double DEFAULT_SPEED = 10; //Measured in meters per second.
	public static final double DEFAULT_ACCELERATION = 4; //Measured in meters per second squared.
	public static final double PROFILE_TIMEOUT_OFFSET = 0.5; //Measured in seconds. Gives the motion profile a bit of extra time to do what it needs.

	public static final double DRIVE_ENCODER_PROPORTIONALITY_CONSTANT = 16.; //Ticks per inch

    
	//Angle Adjustments
	
	public static final int TRIM_STEP = 3;
    
	//Sao Drive (These need to be modified to fit needs)
    
	public static final double SAO_FORWARD_CURVE = 1;
	public static final double SAO_STRAFE_CURVE = 1;
	public static final double SAO_FORWARD_MAX_SPEED = 1;
	public static final double SAO_STRAFE_MAX_SPEED = 1;
	
	//Field centric
	
	public static final double FORWARD_SCALE = 0.8;
	public static final double STRAFE_SCALE = 0.8;

}
