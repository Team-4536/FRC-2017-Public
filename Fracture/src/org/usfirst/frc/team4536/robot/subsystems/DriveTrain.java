package org.usfirst.frc.team4536.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4536.robot.Constants;
import org.usfirst.frc.team4536.robot.Utilities;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;

/**
 * @author Noah
 * Subsystem for the robot's drivetrain
 */
public class DriveTrain extends Subsystem {

    Spark leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    AHRS navX;
    double leftFrontMotorThrottle, leftBackMotorThrottle, rightFrontMotorThrottle, rightBackMotorThrottle;
    double leftFrontMotorThrottleAccel, leftBackMotorThrottleAccel, rightFrontMotorThrottleAccel, rightBackMotorThrottleAccel;
    double leftFrontMotorThrottleAccelPrev, leftBackMotorThrottleAccelPrev, rightFrontMotorThrottleAccelPrev, rightBackMotorThrottleAccelPrev;
    
    /**
     * @author Noah
     * @param leftFrontMotorChannel
     * @param leftBackMotorChannel
     * @param rightFrontMotorChannel
     * @param rightBackMotorChannel
     * 
     * Motor channels should be set in CommandBase
     */
    public DriveTrain(int leftFrontMotorChannel, int leftBackMotorChannel, int rightFrontMotorChannel, int rightBackMotorChannel) {
    	
    	leftFrontMotor = new Spark(leftFrontMotorChannel);
    	leftBackMotor = new Spark(leftBackMotorChannel);
    	rightFrontMotor = new Spark(rightFrontMotorChannel);
    	rightBackMotor = new Spark(rightBackMotorChannel);
    	
    	leftFrontMotor.set(0);
    	leftBackMotor.set(0);
    	rightFrontMotor.set(0);
    	rightBackMotor.set(0);
    	
    	try {
    		navX = new AHRS(SPI.Port.kMXP);
    	} catch(RuntimeException ex) {
    		DriverStation.reportError("Error instantiating naxV-MXP: "+ex.getMessage(), true);
    	}
    	
    }

    public void initDefaultCommand() {
    }
    
    /**
     * @author Noah
     * @param forwardThrottle
     * @param strafeThrottle
     * @param turnThrottle
     * 
     * Throttle ranges: -1 to 1
     * 1 means forward -1 means backwards
     * Feed values into this method through a command
     */
    public void Drive(double forwardThrottle, double strafeThrottle, double turnThrottle) {
    	//TODO Make sure the negative signs are correct for Sidewinder and Fracture
    	
    	leftFrontMotorThrottle = forwardThrottle + turnThrottle + strafeThrottle;
        leftBackMotorThrottle = forwardThrottle + turnThrottle - strafeThrottle;
        rightFrontMotorThrottle = forwardThrottle - turnThrottle - strafeThrottle;
        rightBackMotorThrottle = forwardThrottle - turnThrottle + strafeThrottle;
    	
    	DriveAccelLimit(leftFrontMotorThrottle, leftBackMotorThrottle, rightFrontMotorThrottle, rightBackMotorThrottle);
    	
    }
    
    /**
     * @author Noah
     * @param leftFrontMotorThrottleInput
     * @param leftBackMotorThrottleInput
     * @param rightFrontMotorThrottleInput
     * @param rightBackMotorThrottleInput
     */
    public void DriveAccelLimit(double leftFrontMotorThrottleInput, double leftBackMotorThrottleInput, double rightFrontMotorThrottleInput, double rightBackMotorThrottleInput) {
    	
    	leftFrontMotorThrottleAccel = Utilities.accelLimit(leftFrontMotorThrottleInput, leftFrontMotorThrottleAccelPrev, Constants.DRIVE_TRAIN_ACCEL_LIMIT);
    	leftBackMotorThrottleAccel = Utilities.accelLimit(leftBackMotorThrottleInput, leftBackMotorThrottleAccelPrev, Constants.DRIVE_TRAIN_ACCEL_LIMIT);
    	rightFrontMotorThrottleAccel = Utilities.accelLimit(rightFrontMotorThrottleInput, rightFrontMotorThrottleAccelPrev, Constants.DRIVE_TRAIN_ACCEL_LIMIT);
    	rightBackMotorThrottleAccel = Utilities.accelLimit(rightBackMotorThrottleInput, rightBackMotorThrottleAccelPrev, Constants.DRIVE_TRAIN_ACCEL_LIMIT);
    	
    	leftFrontMotorThrottleAccelPrev = leftFrontMotorThrottleAccel;
    	leftBackMotorThrottleAccelPrev = leftBackMotorThrottleAccel;
    	rightFrontMotorThrottleAccelPrev = rightFrontMotorThrottleAccel;
    	rightBackMotorThrottleAccelPrev = rightBackMotorThrottleAccel;
    	
    	DriveBasic(leftFrontMotorThrottleAccel, leftBackMotorThrottleAccel, rightFrontMotorThrottleAccel, rightBackMotorThrottleAccel);
    	
    }
    
    /**
     * @author Noah
     * @param leftFrontMotorThrottleBasic
     * @param leftBackMotorThrottleBasic
     * @param rightFrontMotorThrottleBasic
     * @param rightBackMotorThrottleBasic
     * 
     * Motor ranges: -1 to 1
     * 1 means forward -1 means backwards
     * Feed values into this method through a command
     */
    public void DriveBasic(double leftFrontMotorThrottleBasic, double leftBackMotorThrottleBasic, double rightFrontMotorThrottleBasic, double rightBackMotorThrottleBasic) {
    	
    	leftFrontMotor.set(-leftFrontMotorThrottleBasic);
    	leftBackMotor.set(-leftBackMotorThrottleBasic);
    	rightFrontMotor.set(rightFrontMotorThrottleBasic);
    	rightBackMotor.set(rightBackMotorThrottleBasic);
    	
    }
    
    /**
     * @author Noah
     * @param forwardThrottle -1 to 1
     * @param strafeThrottle -1 to 1
     * @param desiredAngle -180 to 180, should use the getYaw method
     * @param pConstant proportionality constant for the angle
     * 
     * Method for driving robot-centric while holding a certain angle
     */
    public void DriveHoldAngle(double forwardThrottle, double strafeThrottle, double desiredAngle) {
    	
    	double angle = getYaw();
    	double angleDifference = desiredAngle - angle;
    	if (angleDifference > 180) {
    		angleDifference = angleDifference - 360;
    	}
    	else if (angleDifference < -180) {
    		angleDifference = angleDifference + 360;
    	}
    	double turnThrottle = angleDifference * Constants.HOLD_ANGLE_P_CONSTANT;
    	
    	Drive(forwardThrottle, strafeThrottle, turnThrottle);
    	
    }
   
    /**
    * @author Audrey
    * @return Yaw value between -180 and 180 degrees
    */
    public double getYaw() {
    	
    	return navX.getYaw();
    	
    }
    
    /**
     * @author Audrey
     * @return Angle between -360 and 360 degrees
     */
    public double getAngle() {
    	
    	return navX.getAngle();
    	
    }
    
}

