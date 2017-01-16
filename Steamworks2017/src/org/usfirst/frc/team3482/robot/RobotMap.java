package org.usfirst.frc.team3482.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
/**
 * The RobotMap is a mapping from the ports. Sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static DigitalInput limitSwitch;
	public static CANTalon frontLeft;
	public static CANTalon frontRight;
	public static CANTalon backLeft;
	public static CANTalon backRight;
	
	public static AnalogInput rangefinder;
	public static ADXRS450_Gyro gyro;
	
	public static RobotDrive driveRobot;
	
	public static Counter counter;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	public static void init() {
		frontLeft = new CANTalon ( 1 );
		frontRight = new CANTalon ( 4 );
		backLeft = new CANTalon ( 2 );
		backRight = new CANTalon ( 3 );
		
		rangefinder = new AnalogInput ( 0 );
		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		
		limitSwitch = new DigitalInput ( 1 );
		counter = new Counter ( limitSwitch );
		
		driveRobot = new RobotDrive ( frontLeft, frontRight, backLeft, backRight );
		
		
		
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
