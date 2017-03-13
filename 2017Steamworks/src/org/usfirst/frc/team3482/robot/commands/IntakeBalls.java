package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeBalls extends Command {
	protected void initialize() {
		Robot.chassis.startIntake();
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopIntake();
	}
	
	protected void interrupted() {
		end();
	}
}