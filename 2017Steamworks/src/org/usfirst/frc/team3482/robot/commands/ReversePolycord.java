package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ReversePolycord extends Command {
public ReversePolycord() {
		
	}
	
	protected void initialize() {
		Robot.chassis.reversePolycordOne();
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopReversePolyOne();
	}
	protected void interrupted() {
		end();
	}
}
