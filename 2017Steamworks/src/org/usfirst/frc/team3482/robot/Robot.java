package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Drive;
import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.MoveSquare;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.GearManipulator;
import org.usfirst.frc.team3482.robot.subsystems.RangeFinder;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
	public static boolean isDrive = true;
	public static double turnDegrees;
	public static boolean isCentered;
	public static NetworkTable vision;
	public static FeedbackDeviceStatus status;
	public static Chassis chassis;
	public static GearManipulator gearManipulator;
	public static RangeFinder rangeFinder;
	public static OI oi;
	SendableChooser<Command> teleopchooser;
	SendableChooser<Command> autoChooser;
	public Command autonomousCommand;
	
	 /* This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		vision = NetworkTable.getTable("Vision");
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture("shooter", "/dev/video0");
		cam.setBrightness(30);
		cam.setExposureHoldCurrent();
		cam.setExposureManual(5);
		cam.setResolution(640, 480);
		
		UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture("peg", "/dev/video1");
		cam2.setBrightness(30);
		cam2.setExposureHoldCurrent();
		cam2.setExposureManual(5);
		cam2.setResolution(640, 480);
		
		RobotMap.init();
		status = RobotMap.gearManipulator.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		
		
		gearManipulator = new GearManipulator();
		rangeFinder = new RangeFinder();
		chassis = new Chassis();
		
		oi = new OI();
		
		GearManipulator.toggleWheelsButton = new JoystickButton(Robot.oi.getflightStick(), 10);
		
		teleopchooser = new SendableChooser<>();
		autoChooser = new SendableChooser<>();
		
		teleopchooser.addDefault("Default Auto", new Drive());
		teleopchooser.addObject("move 2000", new Move(2000));
		
		autoChooser.addDefault("Default Auto", null);
		autoChooser.addObject("move test", new Move(600));
		autoChooser.addObject("move square test", new MoveSquare());
		
		RobotMap.ahrs.reset();
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}
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
	public void autonomousInit() {
		RobotMap.ahrs.reset();
		SmartDashboard.putData("Auto mode", autoChooser);
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null){
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		//if (autonomousCommand != null)
			//autonomousCommand.cancel();
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		Robot.chassis.drive(Robot.oi.getxboxController());
		Robot.chassis.startClimb(Robot.oi.getxboxController());
		
		
		//Get degrees from networktables
		turnDegrees = vision.getNumber("degrees", 0.0);
		isCentered = vision.getBoolean("centered", false);
		
		//Encoder health check
		//System.out.println("Encoder health check: " + status);
		
		//SmartDasboard declarations
		SmartDashboard.putNumber("Shooter Speed: ", RobotMap.shooter.getSpeed());
		SmartDashboard.putNumber("Front RangeFinder value: ", Robot.rangeFinder.getRange(RobotMap.rangeFinderFront));
		SmartDashboard.putNumber("Back RangeFinder value: ", Robot.rangeFinder.getRange(RobotMap.rangeFinderBack));
		SmartDashboard.putNumber("Gear Manipulator Position: ", Robot.gearManipulator.getGearManipPosition());
		//SmartDashboard.putNumber("Gear Manipulator peg position: ", GearManipulator.pegPosition);
		//SmartDashboard.putNumber("Gear Manipulator ground position: ", GearManipulator.groundPosition);
		//SmartDashboard.putNumber("Gear Manipulator start position: ", GearManipulator.startPosition);
		SmartDashboard.putNumber("Feeder Speed: ", RobotMap.feeder.get());
	}
    
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}