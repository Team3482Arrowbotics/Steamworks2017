package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.TwoTalon;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports. Sensors and actuators are wired
 * into to a variable name. This provides flexibility changing wiring, makes
 * checking the wiring easier and significantly reduces the number of magic
 * numbers floating around.
 */
public class RobotMap {
	public static DigitalInput limitSwitch;
	public static CANTalon talon0;
	public static CANTalon talon4;
	public static CANTalon talon3;
	public static CANTalon talon8;
	public static CANTalon talon2;
	public static CANTalon talon5;
	public static CANTalon talon7;
	public static PIDController turnController;
	public static PIDController turnController2;

	public static AnalogInput rangefinder;

	public static AHRS ahrs;
	public static RobotDrive driveRobot;

	public static Counter counter;

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

		rangefinder = new AnalogInput(0);

		ahrs = new AHRS(SPI.Port.kMXP);
		turnController = new PIDController(SmartDashboard.getNumber("TurnP", 0.01), SmartDashboard.getNumber("TurnI", 0), SmartDashboard.getNumber("TurnD", 0), 0.00, ahrs, new TwoTalon(talon8, talon3));
		//turnController2 = new PIDController(0.01, 0.01, 0.00, 0.00, ahrs, talon3);
		
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(0.5f);
		turnController.setContinuous(true);

//		turnController2.setInputRange(-180.0f, 180.0f);
//		turnController2.setOutputRange(-1.0, 1.0);
//		turnController2.setAbsoluteTolerance(0.5f);
//		turnController2.setContinuous(true);

		limitSwitch = new DigitalInput(1);
		counter = new Counter(limitSwitch);

		driveRobot = new RobotDrive(talon0, talon8, talon2, talon3);

	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
