package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateManipulator extends Command{
	
	boolean finish;
	int direction;
	public RotateManipulator() {
		finish = false;
		direction = 0;
	}
	
	public RotateManipulator(int direction) {
		this.direction = direction;
	}

	protected void initialize() {
		Robot.manipulator.stopPID();
	}

	protected void execute() {
		System.out.println("Before Position: " + Robot.manipulator.getRestPosition());
		if (direction == 0) {
			Robot.manipulator.raiseIntake();
		} else if (direction == 1)  {
			Robot.manipulator.middleIntake();
		}
		finish = true;
	}

	protected boolean isFinished() {
		return finish;
	}

	protected void end() {
		Robot.manipulator.stopMovingIntake();
		System.out.println("After Position: " + Robot.manipulator.getRestPosition());
		Robot.manipulator.startPID();
	}

	protected void interrupted() {
		end();
	}

}

