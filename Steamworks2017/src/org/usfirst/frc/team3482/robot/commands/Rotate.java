package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command 
{
	private boolean finished = false;
	private double angle;
	public Rotate(double a)
	{
		angle=a;
	}
	protected void initialize(){
		RobotMap.turnController.setSetpoint(angle);
		RobotMap.turnController.enable();
	}
	@Override
	protected boolean isFinished(){
		return RobotMap.turnController.get() <= 0.01;
	}
	protected void end()
	{
		System.out.println("rotate finsihed");
		RobotMap.turnController.disable();
	}
}
