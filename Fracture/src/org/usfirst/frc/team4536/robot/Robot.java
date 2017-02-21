
package org.usfirst.frc.team4536.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.usfirst.frc.team4536.robot.commands.*;
import org.usfirst.frc.team4536.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4536.utilities.Constants;
import org.usfirst.frc.team4536.utilities.EnhancedTimer;
import org.usfirst.frc.team4536.utilities.NavXException;
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
	//Controls which camera will be used(Camera 1 is default)
	public static boolean allowCam1 = true;
	Command smartDashboardCommand;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	Command driveProfile;
	EnhancedTimer cycleTimer;
	Command rotateHoldAngle;
	Command crossBaseline;
	Command autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		setupOneCamera();
		SmartDashboard.putData("Auto mode", chooser);
		smartDashboardCommand = new SmartDashboardCommand();
		driveProfile = new DriveMotionProfile(1.0, 5.0, 3.0, 0, 0);
		cycleTimer = new EnhancedTimer();
		rotateHoldAngle = new AutoRotateFieldCentric();
		crossBaseline = new CrossBaseline();
		autoChooser = new AutoChooser();
		OI.ButtonHandling();
		
	}
	
	private void setupCameras()
	{
		Thread t = new Thread(() -> {
			
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
			camera1.setResolution(Constants.CAMERA_RESOLUTION_WIDTH, Constants.CAMERA_RESOLUTION_HEIGHT);
			UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
			camera1.setResolution(Constants.CAMERA_RESOLUTION_WIDTH, Constants.CAMERA_RESOLUTION_HEIGHT);
			
			CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
			CvSink cvSink2 = CameraServer.getInstance().getVideo(camera2);
			CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", Constants.CAMERA_RESOLUTION_WIDTH, Constants.CAMERA_RESOLUTION_HEIGHT);
			
			Mat image = new Mat();
			
			while(Thread.interrupted() == false){
				if(allowCam1){
					cvSink2.setEnabled(false);
					cvSink1.setEnabled(true);
					cvSink1.grabFrame(image);
				}
				else{
					cvSink2.setEnabled(true);
					cvSink1.setEnabled(false);
					cvSink2.grabFrame(image);
				}
				
				outputStream.putFrame(image);
			}
		});
		t.start();
			
	}
	
	private void setupOneCamera(){
		
		UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera1.setResolution(Constants.CAMERA_RESOLUTION_WIDTH, Constants.CAMERA_RESOLUTION_HEIGHT);
		
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
		
		cycleTimer.startTimer();

		if (smartDashboardCommand != null) {
			smartDashboardCommand.start();
        }
		
		//OI.setFeederStationAngle();
    
		autoChooser.start();
		
		try {
    		
			CommandBase.driveTrain.getNavX().reset();
    		
    	}
		catch(NavXException e) {
    	}

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
		
		if (rotateHoldAngle != null){
			rotateHoldAngle.start();
		}
		
		if (smartDashboardCommand != null) {        	
        	smartDashboardCommand.start();
        }
		
		//OI.setFeederStationAngle();

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
		
		//OI.setFeederStationAngle();
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		cycleTimer.updateCycleTime();
	}
	
	public void testInit(){
		
	}
}
