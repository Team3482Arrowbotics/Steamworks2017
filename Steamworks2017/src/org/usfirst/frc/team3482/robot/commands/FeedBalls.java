package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class FeedBalls extends Command{
	public FeedBalls() {
		
	}
	
	protected void initialize() {
		Robot.chassis.startFeeder();
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopFeeder();
	}
	protected void interrupted() {
		end();
	}
}