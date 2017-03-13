package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.subsystems.WheelDirection;

import edu.wpi.first.wpilibj.command.Command;

public class SpinManipulatorWheels extends Command {
	private WheelDirection dir;
	private double speed;
	public SpinManipulatorWheels(WheelDirection dir, double speed) {
		this.dir = dir;
		this.speed = speed;
	}
	protected void initialize(){
		Robot.gearManipulator.spinGearManipWheels();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end(){
		Robot.gearManipulator.stopGearManipWheels();
	}

}
