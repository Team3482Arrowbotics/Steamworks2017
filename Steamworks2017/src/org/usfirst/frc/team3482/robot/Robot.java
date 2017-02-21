package org.usfirst.frc.team3482.robot;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.NavXChip;
import org.usfirst.frc.team3482.robot.subsystems.Rangefinder;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
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
	boolean PIDOne = false;
	boolean PIDTwo = false;
	public static Chassis chassis;
	public static Rangefinder rangefinder;
	public static NavXChip nav;
	public static OI oi;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	double startPos;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture("shooter", "/dev/video0");
		cam.setBrightness(30);
		cam.setExposureHoldCurrent();
		cam.setExposureManual(5);
		cam.setResolution(640, 480);
		RobotMap.init();

		rangefinder = new Rangefinder();
		nav = new NavXChip(RobotMap.ahrs);
		chassis = new Chassis();
		oi = new OI();
		
		SmartDashboard.putData("Auto mode", chooser);
		
		RobotMap.rangefinder.setAverageBits(6);
		RobotMap.rangefinder.setOversampleBits(4);		
		RobotMap.ahrs.reset();
		
        RobotMap.gearManip.reverseSensor(false);
        RobotMap.gearManip.setP(0.005);
        RobotMap.gearManip.setI(0.0);
        RobotMap.gearManip.setD(0.01);
        RobotMap.gearManip.setF(0.0);
		RobotMap.gearManip.setAllowableClosedLoopErr(0);
		RobotMap.gearManip.configNominalOutputVoltage(0, 0);
		RobotMap.gearManip.configPeakOutputVoltage(12, -12);
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
		autonomousCommand = chooser.getSelected();
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
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if(RobotMap.shooter.getEncVelocity() > 65000) {
			Robot.oi.xboxController.setRumble(RumbleType.kLeftRumble, 1.0);
		}
		RobotMap.gearManipWheels.set(SmartDashboard.getNumber("gear wheels speed", 0.0));
		Robot.chassis.drive(Robot.oi.getxboxController());	
    }
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}