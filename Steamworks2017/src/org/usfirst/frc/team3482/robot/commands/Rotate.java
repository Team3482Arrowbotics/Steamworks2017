package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command
{
	private double angle; 
	public Rotate(double a)
	{
		this.angle = a;
	}
	protected void execute()
	{
		RobotMap.turnController.setSetpoint(angle);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
