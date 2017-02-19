package org.usfirst.frc.team3482.robot;
import org.usfirst.frc.team3482.robot.commands.Drive;
import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.MoveSquare;
//import org.usfirst.frc.team3482.robot.subsystems.Camera;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
//import org.usfirst.frc.team3482.robot.subsystems.NavXChip;
import org.usfirst.frc.team3482.robot.subsystems.Rangefinder;

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
	//Not Resolved apparently (camera, navXChip)
	//public static Camera camera;
	public static Rangefinder rangefinder;
	//public static NavXChip nav;
	public static OI oi;
	double initialPosition;
	public Command teleopCommand;
	Command autonomousCommand;
	SendableChooser<Command> teleopchooser;
	SendableChooser<Command> autoChooser;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		RobotMap.init();
		teleopchooser = new SendableChooser<>();
		autoChooser = new SendableChooser<>();
		rangefinder = new Rangefinder();
		//nav = new NavXChip(RobotMap.ahrs);
		//camera = new Camera();
		chassis = new Chassis();
		oi = new OI();
		teleopchooser.addDefault("Default Auto", new Drive());
		teleopchooser.addObject("move 2000", new Move(2000));
		
		autoChooser.addDefault("Move Square", new MoveSquare());
		
		RobotMap.talon8.setEncPosition(0);
		RobotMap.talon2.setEncPosition(0);
	
		SmartDashboard.putData("Auto mode", teleopchooser);
		SmartDashboard.putData("Auto mode", autoChooser);
		
		//nav.putValuesToDashboard();
		RobotMap.rangefinder.setAverageBits(6);
		RobotMap.rangefinder.setOversampleBits(4);
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
		//RobotMap.ahrs.reset();
		SmartDashboard.putData("Auto mode", autoChooser);
		SmartDashboard.putData("Auto mode", teleopchooser);
		SmartDashboard.putData("Auto mode", autoChooser);
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(RobotMap.turnController.getError());
	}
	@Override

	public void teleopInit() {
		//teleopCommand = new Drive();
		System.out.println("talon 8 position: "+RobotMap.talon8.getEncPosition());
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
		System.out.println("get: "+RobotMap.moveController.get());
		System.out.println("error: "+RobotMap.moveController.getAvgError());
		System.out.println("setpoint: "+RobotMap.moveController.getSetpoint());
		if(RobotMap.moveController.getAvgError()<0)
		{
			RobotMap.moveController.disable();
			teleopCommand.start();
		}
		
		//if(Robot.oi.getxboxController().getRawButton(1)){
			//RobotMap.moveController.setSetpoint(1000);
		//}
		//RobotMap.moveController.enable();
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
