package org.usfirst.frc.team4536.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.Utilities;
import org.usfirst.frc.team4536.utilities.NavXException;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;

import java.lang.Math;

/**
 * @author Noah
 * Subsystem for the robot's drivetrain
 */
public class DriveTrain extends Subsystem {

	//The encoders are untested until we can get Whiplash working.
	Encoder strafeEncoder;
	Encoder forwardEncoder;
	
    Spark leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    AHRS navX;
    double leftFrontMotorThrottle, leftBackMotorThrottle, rightFrontMotorThrottle, rightBackMotorThrottle;
    double leftFrontMotorThrottleAccel, leftBackMotorThrottleAccel, rightFrontMotorThrottleAccel, rightBackMotorThrottleAccel;
    double leftFrontMotorThrottleAccelPrev, leftBackMotorThrottleAccelPrev, rightFrontMotorThrottleAccelPrev, rightBackMotorThrottleAccelPrev;
    private double lastDesiredAngle;
    
    
	/**
     * @author Noah
     * @param leftFrontMotorChannel
     * @param leftBackMotorChannel
     * @param rightFrontMotorChannel
     * @param rightBackMotorChannel
     * 
     * Motor channels should be set in CommandBase
     */
    public DriveTrain(int leftFrontMotorChannel, int leftBackMotorChannel, int rightFrontMotorChannel, 
    		int rightBackMotorChannel, int strafeEncoderChannelA, int strafeEncoderChannelB, 
    		int forwardEncoderChannelA, int forwardEncoderChannelB) {
    	
    	leftFrontMotor = new Spark(leftFrontMotorChannel);
    	leftBackMotor = new Spark(leftBackMotorChannel);
    	rightFrontMotor = new Spark(rightFrontMotorChannel);
    	rightBackMotor = new Spark(rightBackMotorChannel);
    	strafeEncoder = new Encoder(strafeEncoderChannelA, strafeEncoderChannelB);
    	forwardEncoder = new Encoder(forwardEncoderChannelA, forwardEncoderChannelB);
    	
    	leftFrontMotor.set(0.0);
    	leftBackMotor.set(0.0);
    	rightFrontMotor.set(0.0);
    	rightBackMotor.set(0.0);
    	
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
     * Feed values into this method through a command or method
     */
    public void DriveBasic(double leftFrontMotorThrottleBasic, double leftBackMotorThrottleBasic, double rightFrontMotorThrottleBasic, double rightBackMotorThrottleBasic) {
    	
    	leftFrontMotor.set(leftFrontMotorThrottleBasic);
    	leftBackMotor.set(leftBackMotorThrottleBasic);
    	rightFrontMotor.set(-rightFrontMotorThrottleBasic);
    	rightBackMotor.set(-rightBackMotorThrottleBasic);
    	
    }

    /**
     * @author Theo
     * @return strafe encoder distance in inches.
     */
    public double getStrafeEncoder(){
    	return (strafeEncoder.get()/Constants.DRIVE_ENCODER_PROPORTIONALITY_CONSTANT);
    }
    
    /**
     * @author Theo
     * @return forward encoder distance in inches.
     */
    public double getForwardEncoder(){
    	return (forwardEncoder.get()/Constants.DRIVE_ENCODER_PROPORTIONALITY_CONSTANT);
    }
    
   /**
    * @author Theo
    * @return forward encoder rate(velocity) in inches/second.
    */
    public double getForwardRate(){
    	return forwardEncoder.getRate()/Constants.DRIVE_ENCODER_PROPORTIONALITY_CONSTANT;
    }
    
    /**
     * @author Theo
     * @return strafe encoder rate(velocity) in inches/second.
     */
    public double getStrafeRate(){
    	return strafeEncoder.getRate()/Constants.DRIVE_ENCODER_PROPORTIONALITY_CONSTANT;
    }
    
    /**
     * @author Theo
     * resets the strafe encoder.
     */
    public void resetStrafeEncoder(){
    	strafeEncoder.reset();
    }
    
    /**
     * @author Theo
     * resets the forward encoder.
     */
    public void resetForwardEncoder(){
    	forwardEncoder.reset();
    }
    
    /**
     * @author Theo
     * resets both the strafe and forward encoders.
     */
    public void resetEncoders(){
    	resetStrafeEncoder();
    	resetForwardEncoder();
    }
    

    public AHRS getNavX() throws NavXException
    {

    	if (Math.abs(navX.getAngle()) < 0.001 && Math.abs(navX.getPitch()) < 0.001 && Math.abs(navX.getRoll()) < 0.001){
    		throw new NavXException();
    	}
    	return navX;
    }

    /**
     * @author Theo
     * @return the value of lastDesiredAngle
     */
    public double getLastDesiredAngle() {
		return lastDesiredAngle;
	}
    
    /**
     * @author Theo
     * @param desiredAngle the value we want lastDesiredAngle to possess.
     * sets the value of lastDesiredAngle.
     */
	public void setLastDesiredAngle(double desiredAngle) {
		lastDesiredAngle = desiredAngle;
	}

}

