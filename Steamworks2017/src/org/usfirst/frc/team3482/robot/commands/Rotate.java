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
		//System.out.println("init rotate error: "+RobotMap.turnController.getError());
		//RobotMap.turnController.enable();
		//System.out.println("is rotate enabled: "+RobotMap.turnController.isEnabled());
		RobotMap.turnController.reset();
		RobotMap.turnController.setSetpoint(angle);
		RobotMap.turnController.enable();
	}
	
	@Override
	protected void execute()
	{
		//RobotMap.turnController.setSetpoint(angle);
		System.out.println(RobotMap.turnController.getError());
		System.out.println(finished);
		if(RobotMap.turnController.getError() < 10 && RobotMap.turnController.getError()>-10){
			RobotMap.turnController.disable();
			RobotMap.turnController.reset();
			finished = true;
		}
	}
	protected boolean isFinished(){
		return finished;
	}
	protected void end()
	{
		System.out.println("rotate finished");
		RobotMap.turnController.disable();
	}
}