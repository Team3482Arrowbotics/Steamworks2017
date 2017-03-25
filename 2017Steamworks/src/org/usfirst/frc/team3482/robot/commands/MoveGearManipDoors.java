package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveGearManipDoors extends Command {
	private boolean direction;
	public MoveGearManipDoors(boolean direction) {
		direction = Robot.oi.doorsOpen;
	}
	
	protected void initialize() {
		if(direction) { //true to close; false to open
			Robot.gearManipulator.closeGearManipDoors();
			Robot.oi.doorsOpen = !direction;
		} else {
			Robot.gearManipulator.openGearManipDoors();
			Robot.oi.doorsOpen = !direction;
		}
	}
	
	protected void execute() {
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
