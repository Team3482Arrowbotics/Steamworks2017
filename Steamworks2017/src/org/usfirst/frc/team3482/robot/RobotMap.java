package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.EncoderInput;
import org.usfirst.frc.team3482.robot.subsystems.TalonDrive;
import org.usfirst.frc.team3482.robot.subsystems.TalonDriveTurnCW;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports. Sensors and actuators are wired
 * into to a variable name. This provides flexibility changing wiring, makes
 * checking the wiring easier and significantly reduces the number of magic
 * numbers floating around.
 */
public class RobotMap {
	public static CANTalon frontLeft;
	public static CANTalon shooter;
	public static CANTalon rearRight;
	public static CANTalon rearLeft;
	public static CANTalon frontRight;
	public static CANTalon intake;
	public static CANTalon feeder;
	public static CANTalon gearManip;
	public static CANTalon gearManipWheels;
	public static CANTalon polychord1;
	public static CANTalon polychord2;
	public static CANTalon climber;
	public static CANTalon test;
	public static PIDController turnController;
	public static PIDController moveController;
	public static AnalogInput rangefinder;
	public static AHRS ahrs;
	public static DigitalInput limitSwitch;
	public static Counter counter;
	public static LiveWindow liveWindow;
	public static RobotDrive driveRobot;


	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	public static void init() {
		shooter = new CANTalon(12);
		rearRight = new CANTalon(5);
		frontRight = new CANTalon(15);
		rearLeft = new CANTalon(0);
		intake = new CANTalon(8);
		frontLeft = new CANTalon(13);
		gearManip = new CANTalon(1);
		gearManipWheels = new CANTalon(2);
		polychord1 = new CANTalon(4);
		polychord2 = new CANTalon(14);
		feeder = new CANTalon(3);
		climber = new CANTalon(9);
		//rearLeft, intake, gearManipWheels, feeder
		rangefinder = new AnalogInput(0);
		ahrs = new AHRS(SPI.Port.kMXP);
		
		/*turnController = new PIDController(0.15, 0.0, 0.0, 0.00, ahrs, new TalonDriveTurnCW(driveRobot));
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(0.5f);
		turnController.setContinuous(true);

		moveController = new PIDController(0.0015,0.000001,0,850,new EncoderInput(frontRight),new TalonDrive(driveRobot));
		moveController.setInputRange(-20000, 20000);
		moveController.setOutputRange(-1,1);
		moveController.setAbsoluteTolerance(4);
		moveController.setContinuous(true);*/

		limitSwitch = new DigitalInput(1);
		counter = new Counter(limitSwitch);

		driveRobot = new RobotDrive(rearLeft, frontLeft, rearRight, frontRight);
		driveRobot.setSafetyEnabled(false);
		driveRobot.setMaxOutput(0.5);
		

		liveWindow = new LiveWindow();
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}