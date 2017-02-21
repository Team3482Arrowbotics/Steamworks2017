package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualMoveGearManipulator extends Command {
	int counter = 0;
	public ManualMoveGearManipulator() {
		
	}
	
	protected void initialize() {

	}
	
	protected void execute() {
		Robot.gearManipulator.moveGearManip(Robot.oi.getflightStick());
		if((Robot.oi.getflightStick().getRawButton(10)) && ((counter % 2) != 0)) {
			Robot.gearManipulator.spinGearManipWheels();
		} else if((Robot.oi.getflightStick().getRawButton(10)) && ((counter % 2) == 0)) {
			Robot.gearManipulator.stopGearManipWheels();
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.gearManipulator.moveGearManipStartPos();
		Robot.gearManipulator.stopGearManipWheels();
	}
	
	protected void interrupted() {
		end();
	}
}
