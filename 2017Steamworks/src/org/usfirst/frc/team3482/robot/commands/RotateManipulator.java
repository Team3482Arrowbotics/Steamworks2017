package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.subsystems.ManipulatorPosition;

import edu.wpi.first.wpilibj.command.Command;

public class RotateManipulator extends Command
{
	ManipulatorPosition angle;
	public RotateManipulator(ManipulatorPosition angle) {
		this.angle = angle;
	}
	
	protected void initialize() {
		Robot.gearManipulator.moveGearManip(angle);
	}
	
	protected void execute() {
		System.out.println(Robot.gearManipulator.getGearManipPosition());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void interrupted() {
		System.out.println("interrupted");
		Robot.gearManipulator.stopGearManipWheels();
		Robot.gearManipulator.moveGearManipStartPos();
		
	}
	
	protected void end() {
		return;
	}
	
	
}