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
		RobotMap.turnController.setSetpoint(angle);
		//RobotMap.turnController.enable();
		System.out.println("is rotate enabled: "+RobotMap.turnController.isEnabled());
	}
	@Override
	protected void execute()
	{
		if(RobotMap.turnController.getError() < 1){
			RobotMap.turnController.disable();
			finished = true;
		}
	}
	protected boolean isFinished(){
		return finished;
	}
	protected void end()
	{
		System.out.println("rotate finsihed");
		RobotMap.turnController.disable();
	}
}
