package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.ProcessFrameAndRotate;
import org.usfirst.frc.team3482.robot.commands.Rotate;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick xboxController;
//	
	public OI () {
		xboxController = new Joystick ( 0 );
	
		JoystickButton moveme = new JoystickButton(xboxController,3);
		moveme.whenPressed(new Move(10000));
		moveme.whenReleased(new Move(0));
		
		JoystickButton imageProcess = new JoystickButton(xboxController, 1);
		imageProcess.whenPressed(new ProcessFrameAndRotate());
		
		JoystickButton rotateTest = new JoystickButton(xboxController, 1);
		rotateTest.whenPressed(new ProcessFrameAndRotate());
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
}
