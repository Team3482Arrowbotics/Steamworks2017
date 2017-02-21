package org.usfirst.frc.team3482.robot;
import org.usfirst.frc.team3482.robot.commands.GearManipulator;
import org.usfirst.frc.team3482.robot.commands.RunMotor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick xboxController;
	public Joystick joystick;
	public JoystickButton shootButton;//xbox 5 joy 1 
	public JoystickButton intakeButton;//xbox 6 JOY 4
	public JoystickButton feederButton;//xbox 3 JOY 2
	public JoystickButton gearManipButton;//xbox2 GEARMANIP 6
	public JoystickButton polychordMotor;//xbox3 JOY 2 
	public JoystickButton polychordMotor2;//xbox3 JOY 2
	public JoystickButton climberMotor;
	public JoystickButton driveTest;
	
	public OI () {
		xboxController = new Joystick ( 0 );
		joystick = new Joystick(1);
		
		shootButton = new JoystickButton(xboxController,5);
		shootButton.whileHeld(new RunMotor(RobotMap.shooter,-0.6));
		
		intakeButton = new JoystickButton(xboxController,6);
		intakeButton.whileHeld(new RunMotor(RobotMap.intake, -0.5));
		
		feederButton = new JoystickButton(xboxController,3);
		feederButton.whileHeld(new RunMotor(RobotMap.feeder,-0.5));
		
		gearManipButton = new JoystickButton(xboxController,2);
		gearManipButton.whileHeld(new GearManipulator(5));
		
		polychordMotor = new JoystickButton(xboxController,3);
		polychordMotor.whileHeld(new RunMotor(RobotMap.polychord1,-0.3));
		
		polychordMotor2 = new JoystickButton(xboxController,3);
		polychordMotor2.whileHeld(new RunMotor(RobotMap.polychord2,-0.25));
		
		climberMotor = new JoystickButton(xboxController, 4);
		climberMotor.whenPressed(new RunMotor(RobotMap.climber, -0.5));		
	}	
	
	public Joystick getxboxController () {
		return xboxController;
	}
}