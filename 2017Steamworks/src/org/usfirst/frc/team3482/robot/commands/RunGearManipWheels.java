package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.subsystems.WheelDirection;

import edu.wpi.first.wpilibj.command.Command;

public class RunGearManipWheels extends Command {
	private WheelDirection dir;
	public RunGearManipWheels(WheelDirection dir){
		this.dir = dir;
	}
	protected void initialize() {
		RobotMap.gearManipulatorWheels.set(0.6 * dir.direction());
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void interrupted() {
		RobotMap.gearManipulatorWheels.set(0.0);
	}

}
