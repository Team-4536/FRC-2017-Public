package org.usfirst.frc.team4536.utilities;

import java.lang.Math;

public final class Utilities {
	
	private Utilities() {} // prevent object construction which is useless. All variables and methods are static.
	
	/*------------------------------------------------methods---------------------------------------------*/
	
	/**
	 * @author Audrey
	 * @param input value to be limited.
	 * @param lowerBound minimum value input will be no greater than.
	 * @param upperBound maximum value input will be no less than.
	 * @return value after being constrained by upper and lower bounds.
	 */
	public static final double limit(double input, double lowerBound, double upperBound) {
		
		if (input < lowerBound) 
			return lowerBound;
		else if (input > upperBound)
			return upperBound;
		else 
			return input;
	}
	
	/**
	 * @author Olivia & Audrey
	 * @param input value to be limited.
	 * @param bound constrains the input value with a maximum value of bound and a minimum value of negative bound.
	 * @return value after being constrained by upper and lower bounds.
	 */
	public static final double limit(double input, double bound) {
		
		return limit(input, -bound, bound);
	}
	
	/**
	 * @author Audrey
	 * @param input value to be limited
	 * @return value after being constrained with a maximum value of 1 and a minimum value of -1.
	 */
	public static final double limit(double input) {
		
		return limit(input, 1.0);
	}
	
	/**
	 * @author Audrey
	 * @param input value to be raised to a power (or curved)
	 * @param curve exponent that the method uses to curve the input (curves smaller than 0.1 are invalid)
	 * @return input value raised an exponent (curved)
	 */
	public static final double speedCurve(double input, double curve) {
		//negative curves cause asymptotes, leading to overflow errors. Curves smaller than 0.1 aren't very useful.
		double adjustedCurve = limit(curve, 0.10, Double.MAX_VALUE);
		double adjustedInput = limit(input, 1.0);
		//if the input is negative, outputs can be undefined and positive for certain curves
		if(input < 0.0) {
			return -Math.pow(Math.abs(adjustedInput), adjustedCurve);
		}
		
		return Math.pow(adjustedInput, adjustedCurve);
	}

	/**
	 * @author Audrey
	 * @param input value that needs to be of a certain magnitude
	 * @param deadZone magnitude the input should be greater than
	 * @return input if greater than magnitude, 0 if not
	 */
	public static final double deadZone(double input, double deadZone){
	
		if((input > -deadZone) && (input < deadZone)) 
			return 0.0;
		else
			return input;
	}
	
	/**
	 * @author Audrey
	 * @param throttle - the throttle of an object.
	 * @param prevThrottle - Throttle Value from previous cycle of code.
	 * @param fullSpeedTime - The amount of time it will take the throttle to reach full speed. Range: [0, infinity+). It is a double. 
	 * @return finalThrottle - returns the throttle adjusted to be within the bounds of the acceleration limit.
	 */
	public static final double accelLimit(double throttle, double prevThrottle, double fullSpeedTime) {
		
		double finalThrottle = throttle;
		
		double throttleDiff = throttle - prevThrottle;

		double accelerationLimit = EnhancedTimer.cycleTime / fullSpeedTime;

		
		if (throttleDiff > accelerationLimit)
			finalThrottle = prevThrottle + accelerationLimit;
		else if (throttleDiff < -accelerationLimit)
			finalThrottle = prevThrottle - accelerationLimit;
		
		return finalThrottle;
	}
	
	/**
	 *@author Audrey
	 *@param startingAngle The angle the robot is turning from
	 *@param desiredAngle The angle the robot is turning to
	 *@return The difference between those two angles as a number from -180 to 180
	 * Example: If you want to get to 0, and your actual angle is 20, it will return
	 * -20
	 */
	public static final double angleDifference(double startingAngle, double desiredAngle){
		double difference = desiredAngle - startingAngle;
		return angleConverter(difference);
	}
	
	/**
	 * @author Theo
	 * @param ang This can be any angle.
	 * @return the inputted angle once it has been converted to a scale from -180 to 180
	 */
	public static double angleConverter(double ang){
		
		ang = ang % 360.0;
		if(ang > 180.0){
			ang = ang -360.0;
		}
		if(ang < -180.0){
			ang = ang + 360.0;
		}
		return(ang);
	}
	
	/**
	 * @author Theo
	 * @param ang This is the robot's original angle.(Can be between -180 and 180 degrees or 0 and 360).
	 * @param desAng This is the angle we want the robot to be facing towards or directly opposite to.
	 * @return The difference between starting angle and desired angle or the difference between starting angle and the opposite of the desired angle depending on which is smaller.
	 */
	 
	public static double shortestAngle(double ang, double desAng){

		ang = angleConverter(ang);
		desAng = angleConverter(desAng);
		double backAng = angleConverter(desAng + 180.0);
		double faceForward = angleConverter(desAng - ang);
		double faceBackward = angleConverter(backAng - ang);
		if(Math.abs(faceForward) <= Math.abs(faceBackward))
			return(faceForward);
		else{
			return(faceBackward);
		}
	}
	
	/**
	 * @author Audrey
	 * @param velocity The current velocity of the motor system in feet per second
	 * @param stiction The static friction of the motor system in throttle
	 * @param maxVelocity The max velocity of the motor system in feet per second
	 * @return throttle A throttle within the focused range of values which will actuate
	 */
	public static final double adjustForStiction(double velocity, double stiction, double maxVelocity) {
		
		if (velocity > -0.001 && velocity < 0.001) {
			
			return 0.0;
		}
		else {
			
			double velocityToThrottle = velocity/maxVelocity;
			
			double focusedRange = 1.0 - stiction;
			
			if (velocity < 0.0) {
				
				return velocityToThrottle*focusedRange - stiction;
			}
			else {
				
				return velocityToThrottle*focusedRange + stiction;
			}
		}
	}
	
	/**
	 * @author Jasper
	 * @param a : percent of throttle of one degree of freedom of robot drive
	 * @param b : percent of throttle of second degree of freedom
	 * @param scaleParam : max combined percentage of throttle for a and b
	 * @return newA : scaled down A that fits within throttle percentage limit
	 */
	public static double scale(double a, double b, double scaleParam){
		
		double newA;
		
		if ((Math.abs(a) + Math.abs(b)) > scaleParam){
	
			newA = a * scaleParam/(Math.abs(a) + Math.abs(b));		
		}
		else{
		
			newA = a;
		}
		return newA;
	}
	
	
}
