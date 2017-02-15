package org.usfirst.frc.team4536.robot;

import java.lang.Math;

import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.Utilities;

/**
 * 
 * @author Liam
 *
 */
public class MotionProfile extends Profile{

	private double distance; // distance The distance the profile should travel in feet. Negative distances move backwards, positive forwards.
	private double timeNeeded; // The time needed to execute the profile In seconds
	private double desiredMaxSpeed; // maxSpeed The maximum speed the profile may achieve in feet per second. Speed is a scalar so it's always positive.
	private double desiredMaxAcceleration; // The maximum acceleration the speed can change by in feet per second squared. We treat acceleration as the raw change in speed and thus as a scalar so it is always positive.
	private double criticalDistance; // This determines whether the profile is a triangle or a trapezoid.
	private double criticalTime; // This is the time it takes to reach maxSpeed if it is reached
	private boolean triangle; // Whether the profile develops a triangle or trapezoid profile
	private double desiredAngle; //The angle we want the robot to be traveling at in relation to the field.
	private double robotAngle; //The angle the robot is actually facing in relation to the field.
	
	/**
	 * @author Liam & Theo
	 * @param dst The distance the profile should travel in feet. Negative distances move backwards, positive forwards.
	 * @param mS The maximum speed the profile may achieve in feet per second. Speed is a scalar so it's always positive.
	 * @param mA The maximum acceleration the speed can change by in feet per second squared. We treat acceleration as the raw change in speed and thus as a scalar so it is always positive.
	 * @param dAng the direction we want the robot to be moving in. In degrees. Relative to the field.
	 * @param rAng the direction the robot is facing. In degrees. Relative to the field.
	 */
	public MotionProfile (double dst, double mS, double mA, double dAng, double rAng) {
		
		distance = dst;
		desiredMaxSpeed = Math.abs(mS);
		desiredMaxAcceleration = Math.abs(mA);
		desiredAngle = Utilities.angleConverter(dAng);
		robotAngle = Utilities.angleConverter(rAng);

		
		criticalTime = desiredMaxSpeed/desiredMaxAcceleration; //This is in seconds
		criticalDistance = criticalTime * desiredMaxSpeed/2;
		
 		if (Math.abs(distance) > criticalDistance) {
			
			triangle = false;
		}
		else {
			
			triangle = true;
		}
		
		if (triangle) {
			
			timeNeeded = 2*Math.sqrt(Math.abs(distance/desiredMaxAcceleration));
		}
		else {
			
			timeNeeded = (2*criticalTime) + ((Math.abs(distance) - 2*criticalDistance)/desiredMaxSpeed); //Still in seconds
		}
	}
		

		
		/**
		 * @author Theo
		 * @param time the amount of time since the profile has started. In seconds.
		 * @return the throttle the robot should be using to move forwards or backwards.
		 */
		public double getForwardThrottle(double time){
			
			return (Math.cos(Math.toRadians(desiredAngle-robotAngle)) * 
					Utilities.adjustForStiction(idealVelocity(time), Constants.FORWARD_STICTION, Constants.DRIVE_TRAIN_MAX_VELOCITY));
		}
		
		/**
		 * @author Theo
		 * @param time how long its been since the profile started. In seconds.
		 * @return the throttle the robot should be using to move sideways.
		 */
		public double getStrafeThrottle(double time){
			
			return (Constants.FORWARD_STRAFE_RATIO * Math.sin(Math.toRadians(desiredAngle - robotAngle)) * 
					Utilities.adjustForStiction(idealVelocity(time), Constants.STRAFE_STICTION, Constants.DRIVE_TRAIN_MAX_VELOCITY));
				}
		
		/**
		 * @author Liam
		 * @return timeNeeded the theoretical time need for the profile to execute without correction
		 */
		public double getTimeNeeded() {
			
			return this.timeNeeded;
		}
		
		/**
		 * @author Liam
		 * @param time The amount of time since the profile has started
		 * @returns The velocity the robot should be at
		 */
		public double idealVelocity(double time) {
			
			double velocity;
			
			if (triangle) {
				
				if(time <= timeNeeded/2 && time > 0) { // first leg of triangle
					
					velocity =  this.desiredMaxAcceleration*time;
				}
				else if (time > timeNeeded/2 && time <= timeNeeded){ // second leg of triangle
					
					double maxTriangleVelocity = this.desiredMaxAcceleration*timeNeeded/2;
					
					velocity = -this.desiredMaxAcceleration*(time - timeNeeded/2) + maxTriangleVelocity;
				}
				else { // garbage
					
					velocity = 0.0;
				}
			}
			else {//trapezoid
				
				if(time <= criticalTime && time >= 0) {//0 to max velocity
					
					velocity = this.desiredMaxAcceleration*time;
				}
				else if (time > criticalTime && time < (timeNeeded - criticalTime)) {//max velocity
					
					velocity = this.desiredMaxSpeed;
				}
				else if (time >= (timeNeeded - criticalTime) && time <= timeNeeded) {//max velocity to 0
					
					velocity = this.desiredMaxAcceleration*(timeNeeded - time);
				}
				else {//Garbage
					
					velocity = 0;
				}
			}
			
			if (distance < 0) {
				
				return -velocity;
			}
			else {
				
				return velocity;
			}
		}
		
		/**
		 * @author Liam
		 * @return distance the robot should be at by that time
		 */
		public double idealDistance(double time) {
			
			double distance;
			
			if (triangle) {
				
				if (time >= 0 && time <= timeNeeded/2) { // First Half, before timeNeeded divided by 2
					
					distance = idealVelocity(time) * time / 2;
				}
				else if (time > timeNeeded/2 && time <= timeNeeded) { // Second Half, after timeNeeded divided by 2
						
					distance = this.distance - (idealVelocity(timeNeeded-time)* (timeNeeded-time))/2;
				}
				else if (time > timeNeeded) { // TimeNeeded or greater
					
					distance = this.distance;
				}
				else { // Negative Time
					
					distance = 0;
				}
			}
			else { // Trapezoid
				
				if (time >= 0 && time <= criticalTime) { // The first leg of the trapezoid
					
					distance = idealVelocity(time)*time/2;
				}
				else if (time > criticalTime && time <= (timeNeeded - criticalTime)) { // The body of the trapezoid
					
					if (this.distance > 0) {
						
						distance = this.desiredMaxSpeed * (time - criticalTime) + criticalDistance;
					}
					else {
						
						distance = -this.desiredMaxSpeed * (time - criticalTime) - criticalDistance;
					}
				}
				else if (time > (timeNeeded - criticalTime) && time <= timeNeeded) { // The last leg of the trapezoid
					
					distance = this.distance - (idealVelocity(timeNeeded - time) * (timeNeeded - time))/2;
				}
				else if (time > timeNeeded) { // After timeNeeded when the distance should have been covered
					
					distance = this.distance;
				}
				else { // Garbage negative values
					
					distance = 0;
				}
			}
			
			if (this.distance < 0) {
				
				if (distance < 0) {
					
					return distance;
				}
				
				return -distance;
			}
			else {
				
				if (distance < 0) {
					
					return -distance;
				}
				
				return distance;
			}
		}
		
		/**
		 * @author Liam
		 * @return triangle whether the profile has developed a triangle or trapezoid profile
		 */
		public boolean isTriangle() {
			
			return triangle;
		}
		
		/**
		 * @author Liam
		 * @return the distance the profile will travel
		 */
		public double getDistance() {
			
			return distance;
		}
		
		/**
		 * @author Theo
		 * @return the angle we want the robot to be moving in. In degrees, relative to the field.
		 */
		public double getDesiredAngle(){
			return desiredAngle;
		}
		
		/**
		 * @author Theo
		 * @param time a certain period of time since the motionProfile began.
		 * @return The distance the robot should have traveled by the end of that time.
		 */
		public double newIdealDistance(double time){
			 double idealDistanceTravelled = 0;
			  for(double i = 0.0; i < time; i += .02){
				  idealDistanceTravelled += (0.02 * idealVelocity(i) + (idealVelocity(i + 0.02) - idealVelocity(i)) *  0.01);
			  }
			  return(idealDistanceTravelled);
		}		
	}