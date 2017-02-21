package org.usfirst.frc.team3482.robot;
<<<<<<< HEAD

import org.usfirst.frc.team3482.robot.commands.FeedBalls;
import org.usfirst.frc.team3482.robot.commands.IntakeBalls;
import org.usfirst.frc.team3482.robot.commands.ManualMoveGearManipulator;
import org.usfirst.frc.team3482.robot.commands.Rotate;
import org.usfirst.frc.team3482.robot.commands.RotateManipulator;
import org.usfirst.frc.team3482.robot.commands.Shoot;
=======
import org.usfirst.frc.team3482.robot.commands.GearManipulator;
import org.usfirst.frc.team3482.robot.commands.RunMotor;
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick xboxController;
<<<<<<< HEAD
	private static Joystick flightStick;
	private static JoystickButton intakeGearButton;
	private static JoystickButton rotateBotButton;
	private static JoystickButton shootSequenceButton;
	private static JoystickButton feedButton;
	private static JoystickButton intakeBallsButton;
	private static JoystickButton placeGearButton;
	private static JoystickButton manualGearManipMoveButton;

=======
	public Joystick joystick;
	public JoystickButton shootButton;//xbox 5 joy 1 
	public JoystickButton intakeButton;//xbox 6 JOY 4
	public JoystickButton feederButton;//xbox 3 JOY 2
	public JoystickButton gearManipButton;//xbox2 GEARMANIP 6
	public JoystickButton polychordMotor;//xbox3 JOY 2 
	public JoystickButton polychordMotor2;//xbox3 JOY 2
	public JoystickButton climberMotor;
	public JoystickButton driveTest;
	
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
	public OI () {
<<<<<<< HEAD
		xboxController = new Joystick(0);
		flightStick = new Joystick(1);
=======
		xboxController = new Joystick ( 0 );
		joystick = new Joystick(1);
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
		
<<<<<<< HEAD
		intakeGearButton = new JoystickButton(flightStick, 2);
		intakeGearButton.whileHeld(new RotateManipulator(true)); //true = move intake to ground; false = move to peg position
	
		placeGearButton = new JoystickButton(flightStick, 1);
		placeGearButton.whileHeld(new RotateManipulator(false));
=======
		shootButton = new JoystickButton(xboxController,5);
		shootButton.whileHeld(new RunMotor(RobotMap.shooter,-0.6));
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
		
<<<<<<< HEAD
		rotateBotButton = new JoystickButton(xboxController, 4); 
		rotateBotButton.whenPressed(new Rotate(90)); //parameter is the degrees to turn
=======
		intakeButton = new JoystickButton(xboxController,6);
		intakeButton.whileHeld(new RunMotor(RobotMap.intake, -0.5));
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
		
<<<<<<< HEAD
		shootSequenceButton = new JoystickButton(xboxController, 6);
		shootSequenceButton.whileHeld(new Shoot());
=======
		feederButton = new JoystickButton(xboxController,3);
		feederButton.whileHeld(new RunMotor(RobotMap.feeder,-0.5));
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
		
<<<<<<< HEAD
		feedButton = new JoystickButton(xboxController, 1);
		feedButton.whileHeld(new FeedBalls());
=======
		gearManipButton = new JoystickButton(xboxController,2);
		gearManipButton.whileHeld(new GearManipulator(5));
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
		
<<<<<<< HEAD
		intakeBallsButton = new JoystickButton(xboxController, 5);
		intakeBallsButton.whileHeld(new IntakeBalls());
		
		manualGearManipMoveButton = new JoystickButton(flightStick, 4);
		manualGearManipMoveButton.whileHeld(new ManualMoveGearManipulator());
	}
=======
		polychordMotor = new JoystickButton(xboxController,3);
		polychordMotor.whileHeld(new RunMotor(RobotMap.polychord1,-0.3));
		
		polychordMotor2 = new JoystickButton(xboxController,3);
		polychordMotor2.whileHeld(new RunMotor(RobotMap.polychord2,-0.25));
		
		climberMotor = new JoystickButton(xboxController, 4);
		climberMotor.whenPressed(new RunMotor(RobotMap.climber, -0.5));		
	}	
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
	
<<<<<<< HEAD
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public Joystick getxboxController() {
=======
	public Joystick getxboxController () {
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
		return xboxController;
	}
<<<<<<< HEAD
	
	public Joystick getflightStick() {
		return flightStick;
	}
}
=======
}
>>>>>>> branch 'master' of https://github.com/Team3482Arrowbotics/Steamworks2017
