
package org.usfirst.frc.team4536.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4536.robot.commands.*;
import org.usfirst.frc.team4536.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.EnhancedTimer;
import org.usfirst.frc.team4536.utilities.Utilities;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static OI oi;
	Command smartDashboardCommand;
	Command autonomousCommand;
	Command runClimber;
	SendableChooser<Command> chooser = new SendableChooser<>();
	Command driveProfile;
	EnhancedTimer cycleTimer;
	Command holdAngle;
	Command crossBaseline;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0);
		camera0.setResolution(Constants.CAMERA_RESOLUTION_WIDTH, Constants.CAMERA_RESOLUTION_HEIGHT);
		// chooser.addDefault("Default Auto", );
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		smartDashboardCommand = new SmartDashboardCommand();
		driveProfile = new DriveMotionProfile(2.0, 15.0, 10.0, 0, -135);
		cycleTimer = new EnhancedTimer();
		holdAngle = new HoldAngle(0);
		crossBaseline = new CrossBaseline();
		OI.ButtonHandling();
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		if (smartDashboardCommand != null) {        	
        	smartDashboardCommand.start();
        }
		cycleTimer.stopTimer();
		cycleTimer.resetTimer();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//autonomousCommand = chooser.getSelected();
		/*if (driveProfile != null)
			driveProfile.start();
		autonomousCommand = chooser.getSelected();*/
		
		if (crossBaseline != null)
			crossBaseline.start();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		//if (autonomousCommand != null)
			//autonomousCommand.start();
		
		cycleTimer.startTimer();

		if (smartDashboardCommand != null) {
			smartDashboardCommand.start();
       }
		
		CommandBase.driveTrain.getNavX().reset();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		

		cycleTimer.updateCycleTime();

	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		holdAngle.start();
		
		if (smartDashboardCommand != null) {        	
        	smartDashboardCommand.start();
        }

		CommandBase.driveTrain.setLastDesiredAngle(60);
		cycleTimer.startTimer();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		cycleTimer.updateCycleTime();
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void testInit(){
		
	}
}
