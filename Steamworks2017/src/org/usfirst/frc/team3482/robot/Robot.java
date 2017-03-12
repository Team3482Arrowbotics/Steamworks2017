package org.usfirst.frc.team3482.robot;
import org.usfirst.frc.team3482.robot.commands.Drive;
import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.MoveSquare;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.NavXChip;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
	public static final double RADIUS = 3.66056;
	public static final double CIR = 23.0;
	public static boolean isPID = true;
//	wheel radius: 3.66056 inches
//  wheel circumference: 23	inches
	double rotateToAngleRate;
	int autoLoop;
	public static Chassis chassis;
	public static NavXChip nav;
	public static OI oi;
	double initialPosition;
	public Command teleopCommand;
	public Command autonomousCommand;
	SendableChooser<Command> teleopchooser;
	SendableChooser<Command> autoChooser;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		RobotMap.init();
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
		cam.setBrightness(30);
		cam.setExposureHoldCurrent();
		cam.setExposureManual(5);
		cam.setResolution(640, 480);
		teleopchooser = new SendableChooser<>();
		autoChooser = new SendableChooser<>();
	
		nav = new NavXChip(RobotMap.ahrs);
		chassis = new Chassis();
		oi = new OI();
		teleopchooser.addDefault("Default Auto", new Drive());
		teleopchooser.addObject("move 2000", new Move(2000));
		
		autoChooser.addDefault("Default Auto", null);
		autoChooser.addObject("move test", new Move(100));
		autoChooser.addObject("move square test", new MoveSquare());
		
		RobotMap.talon8.setEncPosition(0);
		RobotMap.talon2.setEncPosition(0);
		
		LiveWindow.addSensor("encoder", "1", RobotMap.encoder1);
	
		RobotMap.ahrs.reset();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
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
	@Override

	//PID actuator not working smoothly in autonomous mode, smooth in test mode
	
	public void autonomousInit() {
		RobotMap.ahrs.reset();
		SmartDashboard.putData("Auto mode", autoChooser);
		autonomousCommand = (Command) autoChooser.getSelected();
		//autonomousCommand = new MoveSquare();
		if (autonomousCommand != null){
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override

	public void teleopInit() {
		teleopCommand = new Drive();
		if (autonomousCommand != null){
			autonomousCommand.cancel();
		}
		teleopCommand.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	int loop = 0;

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// test tank drive
		//RobotMap.talon2.set(0.5);
		//RobotMap.talon5.set(-0.5);
		SmartDashboard.putNumber("analog encoder value", RobotMap.encoder1.getDistance());
		
		System.out.println("get: "+RobotMap.moveController.get());
		System.out.println("error: "+RobotMap.moveController.getAvgError());
		System.out.println("setpoint: "+RobotMap.moveController.getSetpoint());
		/*SmartDashboard.putNumber("teleop encoder position", RobotMap.talon8.getEncPosition()); 
		SmartDashboard.putNumber("move controller P", RobotMap.moveController.getP());
		SmartDashboard.putNumber("Range", Robot.rangefinder.getDistance());
		RobotMap.talon4.set(SmartDashboard.getNumber("shooter speed", 0.0));
		RobotMap.talon7.set(SmartDashboard.getNumber("intake speed", 0.0));*/
		//Robot.chassis.drive(Robot.oi.getxboxController());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}