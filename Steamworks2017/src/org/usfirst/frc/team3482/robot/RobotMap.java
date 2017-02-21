package org.usfirst.frc.team3482.robot;

<<<<<<< HEAD
import org.usfirst.frc.team3482.robot.subsystems.TurnPID;
=======
import org.usfirst.frc.team3482.robot.subsystems.EncoderInput;
import org.usfirst.frc.team3482.robot.subsystems.TalonDrive;
import org.usfirst.frc.team3482.robot.subsystems.TalonDriveTurnCW;

>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
<<<<<<< HEAD
=======
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017

/**
 * The RobotMap is a mapping from the ports. Sensors and actuators are wired
 * into to a variable name. This provides flexibility changing wiring, makes
 * checking the wiring easier and significantly reduces the number of magic
 * numbers floating around.
 */
public class RobotMap {
<<<<<<< HEAD
	//public static DigitalInput limitSwitch;
	public static CANTalon rearLeft;
	public static CANTalon frontLeft;
	public static CANTalon rearRight;
	public static CANTalon frontRight;
=======
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
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
	public static AHRS ahrs;
<<<<<<< HEAD
	public static CANTalon shooter;
	public static PIDController turnController;
=======
	public static DigitalInput limitSwitch;
	public static Counter counter;
	public static LiveWindow liveWindow;
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
	public static RobotDrive driveRobot;
<<<<<<< HEAD
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
	
=======

>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.

	public static void init() {
<<<<<<< HEAD
		rearLeft = new CANTalon(5);
		frontLeft = new CANTalon(6);
		rearRight = new CANTalon(7);
		frontRight = new CANTalon(4);
		driveRobot = new RobotDrive(rearLeft, frontLeft, rearRight, frontRight);
		driveRobot.setSafetyEnabled(true);
=======
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
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
		
<<<<<<< HEAD
		ahrs = new AHRS(SPI.Port.kMXP);
		turnController = new PIDController(0.3, 0.0, 0.0, 0.0, ahrs, new TurnPID(driveRobot));
		turnController.setInputRange(-180f, 180f);
		turnController.setOutputRange(-1, 1);
=======
		/*turnController = new PIDController(0.15, 0.0, 0.0, 0.00, ahrs, new TalonDriveTurnCW(driveRobot));
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
		turnController.setAbsoluteTolerance(0.5f);
		turnController.setContinuous(true);
<<<<<<< HEAD
		
		intake = new CANTalon(8);
		
		climber = new CANTalon(9);
		
		feeder = new CANTalon(3);
		shooter = new CANTalon(12);
		polycord1 = new CANTalon(1);
		polycord2 = new CANTalon(10);
		
		gearManipulator = new CANTalon(11);
		gearManipulatorWheels = new CANTalon(2);
		
		rangeFinderFront = new AnalogInput(4);
		rangeFinderBack = new AnalogInput(5);
		
		//limitSwitch = new DigitalInput(1);
		//counter = new Counter(limitSwitch);
=======

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
		
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017

		liveWindow = new LiveWindow();
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}