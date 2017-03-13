package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

public class Climb {
	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.chassis.startClimb(Robot.oi.getxboxController());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopClimb();
	}
	
	protected void interrupted() {
		end();
	}
}
