package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.TalonDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports. Sensors and actuators are wired
 * into to a variable name. This provides flexibility changing wiring, makes
 * checking the wiring easier and significantly reduces the number of magic
 * numbers floating around.
 */
public class RobotMap {
	public static CANTalon talon0;
	public static CANTalon talon4;
	public static CANTalon talon3;
	public static CANTalon talon8;
	public static CANTalon talon2;
	public static CANTalon talon5;
	public static CANTalon talon7;
	public static Encoder encoder1;
	public static PIDController turnController;
	public static PIDController moveController;
	public static AHRS ahrs;
	public static RobotDrive driveRobot;


	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	public static void init() {
		talon4 = new CANTalon(4);
		talon3 = new CANTalon(3);
		talon2 = new CANTalon(2);
		talon8 = new CANTalon(8);
		talon5 = new CANTalon(5);
		talon7 = new CANTalon(7);
		talon0 = new CANTalon(0);

		driveRobot = new RobotDrive(talon0, talon8, talon2, talon3);
		driveRobot.setSafetyEnabled(false);
		driveRobot.setMaxOutput(0.5);
		
		ahrs = new AHRS(SPI.Port.kMXP);
		
		//P=0.15,I=0,D=0,F=0
		
		/*****
		 * turnController = new PIDController(0.15, 0.0, 0.0, 0.00, ahrs, new TalonDriveTurnCW(driveRobot));
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(0.5f);
		turnController.setContinuous(true);
		******/
		
		talon8.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		talon8.setEncPosition(10);

		moveController = new PIDController(0.0025,0.00001,0,500,new Encoder(0,1),new TalonDrive(driveRobot));
		moveController.setInputRange(-20000, 20000);
		moveController.setOutputRange(-1,1);
		moveController.setAbsoluteTolerance(4);
		moveController.setContinuous(true);
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}