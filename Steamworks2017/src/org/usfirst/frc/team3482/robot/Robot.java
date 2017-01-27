
package org.usfirst.frc.team3482.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team3482.robot.commands.Drive;
import org.usfirst.frc.team3482.robot.commands.ProtoIntake;
import org.usfirst.frc.team3482.robot.commands.Rotate;
import org.usfirst.frc.team3482.robot.commands.Rotate90Then70;
import org.usfirst.frc.team3482.robot.networks.ImageListener;
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

	double rotateToAngleRate;
	int autoLoop;
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
		//// disabled
		chooser.addDefault("Default Auto", new Drive());
		teleopChooser.addDefault("None", new ProtoIntake(0.0));

		SmartDashboard.putData("Teleop mode", teleopChooser);
		SmartDashboard.putData("Auto mode", chooser);

		SmartDashboard.putNumber("TurnP", .01);
		SmartDashboard.putNumber("TurnI", 0);
		SmartDashboard.putNumber("TurnD", 0);
		SmartDashboard.putNumber("shooter speed", 0.0);
		//SmartDashboard.putNumber("Intake Spin Speed", 0.0);
		
		chooser.addObject("Rotate 90", new Rotate(90));
		chooser.addObject("Rotate 90 then to 70", new Rotate90Then70());

		nav.putValuesToDashboard();

		new Thread(() -> {
			cameraTable = NetworkTable.getTable("camera");
			cameraTable.addTableListener(new ImageListener(), true);
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setExposureManual(20);
			camera.setResolution(640, 480);

			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
			Mat source = new Mat();

			cameraTable.putNumberArray("ImgArray", new double[1]);
			cameraTable.putBoolean("TF", true);

			while (true) {
				// get input image
				cvSink.grabFrame(source);

				double[] imgToDoubles = new double[source.rows() * source.cols() * 3];
				int counter = 0;
				for (int i = 0; i < source.rows(); i++) {
					for (int j = 0; j < source.cols(); j++) {
						for (int k = 0; k < 3; k++) {
							imgToDoubles[counter + k] = source.get(i, j)[k];
						}
						counter += 3;

					}
				}
				Robot.camera.process(source);
				outputStream.putFrame(Robot.camera.maskOutput());

				cameraTable.putNumberArray("ImgArray", source.get(0, (int) (Math.random() * 10)));
			}
		}).start();

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
		autonomousCommand = chooser.getSelected();
		autoLoop = 0;
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
//		autoLoop ++;
//		SmartDashboard.putNumber("AutoLoop: ", autoLoop);
//		//Robot.intake.middleIntake();
//		if (autoLoop < 500) {
//			
//			RobotMap.talon0.set(-0.2); 
//			SmartDashboard.putNumber("Talon 0 spode", RobotMap.talon0.get());
//			RobotMap.talon3.set(0.2);
//			RobotMap.talon2.set(-0.2);
//			RobotMap.talon8.set(0.2);
//		}
//		else if (autoLoop >= 500){
//			RobotMap.talon0.set(0.0);
//			RobotMap.talon3.set(0.0);
//			RobotMap.talon2.set(0.0);
//			RobotMap.talon8.set(0.0);
//		}
	}

	@Override

	public void teleopInit() {
		teleopCommand = new Drive();
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
		
		RobotMap.talon4.set(SmartDashboard.getNumber("shooter speed", 0.0));
		//RobotMap.talon7.set(SmartDashboard.getNumber("Intake Spin Speed", 0.0));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
