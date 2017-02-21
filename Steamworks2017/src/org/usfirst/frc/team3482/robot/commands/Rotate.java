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
		System.out.println("init rotate error: "+RobotMap.turnController.getError());
		RobotMap.turnController.enable();
		System.out.println("is rotate enabled: "+RobotMap.turnController.isEnabled());
		RobotMap.turnController.setSetpoint(RobotMap.turnController.getSetpoint() + angle);
	}
	@Override
	protected void execute()
	{
		
	}
	protected boolean isFinished(){
		return RobotMap.turnController.onTarget();
	}
	protected void end()
	{
		System.out.println("rotate finsihed");
		RobotMap.turnController.disable();
	}
}