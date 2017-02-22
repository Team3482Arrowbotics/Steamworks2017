package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.EncoderInput;
import org.usfirst.frc.team3482.robot.subsystems.TalonDrive;
import org.usfirst.frc.team3482.robot.subsystems.TalonDriveTurnCW;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDController.PercentageTolerance;
import edu.wpi.first.wpilibj.PIDController.Tolerance;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports. Sensors and actuators are wired
 * into to a variable name. This provides flexibility changing wiring, makes
 * checking the wiring easier and significantly reduces the number of magic
 * numbers floating around.
 */
public class RobotMap {
	public static DigitalInput limitSwitch;
	public static CANTalon talon6;
	public static CANTalon talon1;
	public static CANTalon talon4;
	public static CANTalon talon3;
	public static CANTalon talon8;
	public static CANTalon talon2;
	public static CANTalon talon5;
	public static CANTalon talon7;
	public static PIDController turnController;
	public static PIDController moveController;

	public static AnalogInput rangefinder;
	public static AnalogInput test;

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
		talon6 = new CANTalon(6);
		talon1 = new CANTalon(1);
		rangefinder = new AnalogInput(0);
		driveRobot = new RobotDrive(talon4, talon5, talon6, talon7);
		driveRobot.setSafetyEnabled(false);
		driveRobot.setMaxOutput(0.5);
		
		ahrs = new AHRS(SPI.Port.kMXP);
		
		//P=0.15,I=0,D=0,F=0
		//turn:  P = 0.025, I=0.002, D=0.01
		turnController = new PIDController(0.045, 0.00022, 0.052, 0, ahrs, new TalonDriveTurnCW(driveRobot));
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		//turnController.setAbsoluteTolerance(0.5f);
		turnController.setContinuous(true);
		turnController.setAbsoluteTolerance(1.0);
		
		talon8.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		talon8.setEncPosition(10);
		//cant change the PID value from smartdashboard because the object is created in robot map
		//moveController = new PIDController(SmartDashboard.getNumber("MoveP", 0.0), SmartDashboard.getNumber("MoveI", 0), SmartDashboard.getNumber("MoveD", 0), SmartDashboard.getNumber("MoveF",0), new EncoderInput(talon8), new TalonDrive(driveRobot));
		moveController = new PIDController(0.004,0.0001,0,0,new EncoderInput(talon5),new TalonDrive(driveRobot));
		moveController.setInputRange(-20000, 20000);
		moveController.setOutputRange(-1,1);
		//moveController.setAbsoluteTolerance(4);
		moveController.setContinuous(true);
		moveController.setAbsoluteTolerance(50);

		LiveWindow.addActuator("Move Controller", "Hello", moveController);
		LiveWindow.addActuator("Turn Controller", "Test", turnController);
		LiveWindow.addSensor("Turn Controller", "Gyro", ahrs);
		LiveWindow.addSensor("Range Finder", "sensor", rangefinder);
		limitSwitch = new DigitalInput(1);
		counter = new Counter(limitSwitch);
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}