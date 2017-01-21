package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class StopBeforeWall extends Command{

	public StopBeforeWall()
	{
		
	}
	protected void execute()
	{
		RobotMap.stopController.setSetpoint(4.0);	
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
