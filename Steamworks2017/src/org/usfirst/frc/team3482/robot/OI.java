package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.ProtoIntake;
import org.usfirst.frc.team3482.robot.commands.Protoshooter;
import org.usfirst.frc.team3482.robot.commands.Rotate;
import org.usfirst.frc.team3482.robot.commands.RotateManipulator;
import org.usfirst.frc.team3482.robot.commands.niWoTnipS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick xboxController;
	public JoystickButton shootButton;
	public JoystickButton ballIntakeButton;
	public JoystickButton hehehehHeheheheh;
	public JoystickButton manipulatorUpButton;
	public JoystickButton manipulatorDownButton;
	public OI () {
		xboxController = new Joystick ( 0 );
		JoystickButton rotate90 = new JoystickButton(xboxController,3);
		rotate90.whenPressed(new Rotate(90.0f));
		
		shootButton = new JoystickButton(xboxController, 3);
		shootButton.whileHeld(new Protoshooter(1.0));
		
		ballIntakeButton = new JoystickButton(xboxController, 4);
		ballIntakeButton.whileHeld(new ProtoIntake(1.0));
		
		hehehehHeheheheh = new JoystickButton(xboxController, 10);
		hehehehHeheheheh.whenPressed(new niWoTnipS());
		
		//manipulatorUpButton = new JoystickButton(xboxController, 1);
		//manipulatorUpButton.whenPressed(new RotateManipulator(11));
		
		//manipulatorDownButton = new JoystickButton(xboxController, 2);
		//manipulatorDownButton.whenPressed(new RotateManipulator(0));
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
