package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.TurnPID;
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
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
	//public static DigitalInput limitSwitch;
	public static CANTalon rearLeft;
	public static CANTalon frontLeft;
	public static CANTalon rearRight;
	public static CANTalon frontRight;
	public static AHRS ahrs;
	public static CANTalon shooter;
	public static PIDController turnController;
	public static RobotDrive driveRobot;
	public static Counter counter;
	public static CANTalon gearManipulator;
	public static CANTalon gearManipulatorWheels;
	public static AnalogInput rangeFinderFront;
	public static AnalogInput rangeFinderBack;
	public static CANTalon feeder;
	public static CANTalon intake;
	public static CANTalon climber;
	public static CANTalon polycord1; //only polycord that goes outside bot
	public static CANTalon polycord2; 
	

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.

	public static void init() {
		rearLeft = new CANTalon(5);
		frontLeft = new CANTalon(6);
		rearRight = new CANTalon(7);
		frontRight = new CANTalon(4);
		driveRobot = new RobotDrive(rearLeft, frontLeft, rearRight, frontRight);
		driveRobot.setSafetyEnabled(true);
		
		ahrs = new AHRS(SPI.Port.kMXP);
		turnController = new PIDController(0.3, 0.0, 0.0, 0.0, ahrs, new TurnPID(driveRobot));
		turnController.setInputRange(-180f, 180f);
		turnController.setOutputRange(-1, 1);
		turnController.setAbsoluteTolerance(5f);
		turnController.setContinuous(true);
		
		intake = new CANTalon(8);
		
		climber = new CANTalon(9);
		climber.enableBrakeMode(true);
		
		feeder = new CANTalon(3);
		shooter = new CANTalon(12);
		polycord1 = new CANTalon(1);
		polycord2 = new CANTalon(10);
		
		gearManipulator = new CANTalon(0);//11  //0 on test board
		gearManipulatorWheels = new CANTalon(2);//2
				
		rangeFinderFront = new AnalogInput(6);
		rangeFinderBack = new AnalogInput(7);
		//LiveWindow rangefinder test
		LiveWindow.addSensor("Robot", "RangeFinderFront", rangeFinderFront);
		LiveWindow.addSensor("Robot", "RangeFinderBack", rangeFinderBack);
		
		//PID tuning
		LiveWindow.addActuator("Test rotate", "1", RobotMap.turnController);
		LiveWindow.addActuator("Gear manipulator", "1", RobotMap.gearManipulator);
		
		//limitSwitch = new DigitalInput(1);
		//counter = new Counter(limitSwitch);

	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
