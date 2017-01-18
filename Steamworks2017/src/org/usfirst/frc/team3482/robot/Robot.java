
package org.usfirst.frc.team3482.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team3482.robot.commands.Drive;
import org.usfirst.frc.team3482.robot.commands.Protoshooter;
import org.usfirst.frc.team3482.robot.subsystems.Camera;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.NavXChip;
import org.usfirst.frc.team3482.robot.subsystems.Rangefinder;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	NetworkTable cameraTable;
	
	public static Chassis chassis;
	public static Camera camera;
	public static Rangefinder rangefinder;
	public static NavXChip nav;
	SendableChooser<Command> teleopChooser = new SendableChooser<>();;
	public static OI oi;
	
	Command teleopCommand;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		
		
		RobotMap.init();
	
		rangefinder = new Rangefinder();
		nav = new NavXChip(RobotMap.ahrs);
		camera = new Camera();
		chassis = new Chassis();
		
		oi = new OI();
		////disabled
		chooser.addDefault("Default Auto", new Drive());
		teleopChooser.addDefault("Protoshooter 0.5 speed", new Protoshooter(0.5));
		teleopChooser.addObject("Protoshooter 0.75 speed", new Protoshooter(0.75));
		teleopChooser.addObject("Protoshooter 0.9 speed", new Protoshooter(0.9));
		teleopChooser.addObject("Protoshooter 0.6 speed", new Protoshooter(0.6));
		teleopChooser.addObject("Protoshooter 0.7 speed", new Protoshooter(0.7));
		teleopChooser.addObject("Protoshooter 0.8 speed", new Protoshooter(0.8));
		
		SmartDashboard.putData("Teleop mode", teleopChooser);
		SmartDashboard.putData("Auto mode", chooser);
		//nav.putValuesToDashboard();
	
		new Thread(() -> {
			cameraTable = NetworkTable.getTable("camera");
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setExposureManual(20);
			camera.setResolution(640, 480);
			
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo ( "Blur", 640, 480 );
			Mat source = new Mat();
			Mat hsv = new Mat();
			Mat output = new Mat();
			
			System.out.println(cameraTable.getKeys(0));
			
			while(true) {
				//get input image
				cvSink.grabFrame(source);
				
				
				
				Boolean imageFound = cameraTable.getBoolean("found", false);
				Double imageOffset = cameraTable.getDouble("offset", -1.0);
				Double imageDistance = cameraTable.getDouble("distance", -1.0);
				
				//System.out.println("found = " + imageFound);
				
				Robot.camera.process(source);
				outputStream.putFrame(Robot.camera.maskOutput());
				cameraTable.putValue("Image", Robot.camera.maskOutput());

			}
		}).start();
		
		
		
		
		//CameraServer.getInstance().startAutomaticCapture();
		RobotMap.rangefinder.setAverageBits (6);
		RobotMap.rangefinder.setOversampleBits (4);
		
		//Gyro calibration
		
		//RobotMap.gyro.reset();
		//RobotMap.gyro.calibrate();
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

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
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		teleopCommand = (Command) teleopChooser.getSelected();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
			teleopCommand.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	int loop = 0;
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
