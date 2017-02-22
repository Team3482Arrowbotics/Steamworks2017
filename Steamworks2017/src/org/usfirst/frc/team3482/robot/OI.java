package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.FeedBalls;
import org.usfirst.frc.team3482.robot.commands.GearManipulator;
import org.usfirst.frc.team3482.robot.commands.IntakeBalls;
import org.usfirst.frc.team3482.robot.commands.ManualMoveGearManipulator;
import org.usfirst.frc.team3482.robot.commands.Rotate;
import org.usfirst.frc.team3482.robot.commands.RotateManipulator;
import org.usfirst.frc.team3482.robot.commands.Shoot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick xboxController;
	private static Joystick flightStick;
	private static JoystickButton intakeGearButton;
	private static JoystickButton rotateBotButton;
	private static JoystickButton shootSequenceButton;
	private static JoystickButton feedButton;
	private static JoystickButton intakeBallsButton;
	private static JoystickButton placeGearButton;
	private static JoystickButton manualGearManipMoveButton;
	public Joystick joystick;
	public JoystickButton shootButton;
	public JoystickButton intakeButton;
	public JoystickButton feederButton;
	public JoystickButton gearManipButton;
	public JoystickButton polychordMotor;
	public JoystickButton polychordMotor2;
	public JoystickButton climberMotor;
	public JoystickButton driveTest;
	
	public OI () {
		xboxController = new Joystick(0);
		flightStick = new Joystick(1);
		
		intakeGearButton = new JoystickButton(flightStick, 2);
		intakeGearButton.whileHeld(new RotateManipulator(true)); //true = move intake to ground; false = move to peg position
	
		placeGearButton = new JoystickButton(flightStick, 1);
		placeGearButton.whileHeld(new RotateManipulator(false));
		
		rotateBotButton = new JoystickButton(xboxController, 4); 
		rotateBotButton.whenPressed(new Rotate(90)); //parameter is the degrees to turn
		
		shootSequenceButton = new JoystickButton(xboxController, 6);
		shootSequenceButton.whileHeld(new Shoot());
		
		feedButton = new JoystickButton(xboxController, 1);
		feedButton.whileHeld(new FeedBalls());
		
		gearManipButton = new JoystickButton(xboxController,2);
		gearManipButton.whileHeld(new GearManipulator(5));
		
		intakeBallsButton = new JoystickButton(xboxController, 5);
		intakeBallsButton.whileHeld(new IntakeBalls());
		
		manualGearManipMoveButton = new JoystickButton(flightStick, 4);
		manualGearManipMoveButton.whileHeld(new ManualMoveGearManipulator());		
	}
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
	
	public Joystick getxboxController () {
		return xboxController;
	}

	public Joystick getflightStick() {
		return flightStick;
	}
}
