package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.GearManipulator;
import org.usfirst.frc.team3482.robot.subsystems.RangeFinder;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static FeedbackDeviceStatus status;
	public static Chassis chassis;
	public static GearManipulator gearManipulator;
	public static RangeFinder rangeFinder;
	public static OI oi;
	
	 /* This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture("shooter", "/dev/video0");
		cam.setBrightness(30);
		cam.setExposureHoldCurrent();
		cam.setExposureManual(5);
		cam.setResolution(640, 480);
		
		RobotMap.init();
		status = RobotMap.gearManipulator.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		
		gearManipulator = new GearManipulator();
		rangeFinder = new RangeFinder();
		chassis = new Chassis();
		oi = new OI();
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
		//autonomousCommand = chooser.getSelected();

		 /* String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		// schedule the autonomous command (example)
		//if (autonomousCommand != null)
			//autonomousCommand.start();
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
		
		//Encoder health check
		System.out.println("Encoder health check: " + status);
		
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