package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command 
{
	private double angle;
	public Rotate(double a)
	{
		angle=a;
	}

	@Override
	protected void execute()
	{
		RobotMap.turnController.setSetpoint(angle);
		RobotMap.turnController.enable();
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	protected void end()
	{
		
	}
}
