
package org.usfirst.frc.team3482.robot;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
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
	public static final double RADIUS = 3.66056;
	public static final double CIR = 23.0;
	public static boolean isPID = true;
//	wheel radius: 3.66056 inches
//  wheel circumference: 23	inches
	double rotateToAngleRate;
	int autoLoop;
	public static Chassis chassis;
	public static Camera camera;
	public static Rangefinder rangefinder;
	public static NavXChip nav;
	public static OI oi;
	double initialPosition;
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
		
		SmartDashboard.putNumber("Low Hue Value: ", 0.0);
		SmartDashboard.putNumber("Low Saturation Value: ", 0.0);
		SmartDashboard.putNumber("Low Luminance Value: ", 0.0);
		SmartDashboard.putNumber("High Hue Value: ", 0.0);
		SmartDashboard.putNumber("High Saturation Value: ", 0.0);
		SmartDashboard.putNumber("High Luminance Value: ", 0.0);
		
		new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
                        
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
            
            Mat source = new Mat();
            Mat output = new Mat();
           
            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                Robot.camera.process(source);
            }
        }).start();
		
//		SmartDashboard.putNumber("key", value);
//		RobotMap.talon2.set(SmartDasboard.getNumber("key", 0.0);
//		chooser.addDefault("Default Auto", new Drive());
//		chooser.addObject("Rotate 90", new Rotate(90));
//		chooser.addObject("Rotate 90 then to 70", new Rotate90Then70());
		

		SmartDashboard.putData("Auto mode", chooser);

		SmartDashboard.putNumber("TurnP", .01);
		SmartDashboard.putNumber("TurnI", 0);
		SmartDashboard.putNumber("TurnD", 0);
		SmartDashboard.putNumber("shooter speed", 0.0);
		SmartDashboard.putNumber("left motor speed 1", 0.0);
		SmartDashboard.putNumber("left motor speed 2", 0.0);
		SmartDashboard.putNumber("right motor speed 1", 0.0);
		SmartDashboard.putNumber("right motor speed 2", 0.0);
		SmartDashboard.putNumber("intake speed", 0.0);
		SmartDashboard.putNumber("gear manipulator speed", 0.0);
		
		

		nav.putValuesToDashboard();

	

		RobotMap.rangefinder.setAverageBits(6);
		RobotMap.rangefinder.setOversampleBits(4);

		RobotMap.ahrs.reset();
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
		RobotMap.ahrs.reset();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("encoder position", RobotMap.talon8.getEncPosition()); 
		SmartDashboard.putNumber("delta encoder position", RobotMap.talon8.getEncPosition()-initialPosition); 
		
		
	}

	@Override

	public void teleopInit() {
		//teleopCommand = new Drive();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		//teleopCommand.start();
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
