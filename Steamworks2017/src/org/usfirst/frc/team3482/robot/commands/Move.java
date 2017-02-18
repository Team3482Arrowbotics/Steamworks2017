package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

//command works with joystick button 3

public class Move extends Command 
{
	private double distance;
	private boolean finished = false;
	public Move(double a)
	{
		distance=a;
	}
	protected void initialize(){
		System.out.println("init error: "+RobotMap.moveController.getError());
		//RobotMap.turnController.setSetpoint(distance);
		RobotMap.moveController.setSetpoint(distance);
		RobotMap.moveController.enable();
	}
	protected void execute()
	{

		if(RobotMap.moveController.getError()<50)
		{
			RobotMap.moveController.disable();
			finished = true;
		}
	}
	protected boolean isFinished(){
		return finished;
	}
	@Override
	protected void end()
	{
		System.out.println("Move end");
		RobotMap.moveController.disable();
	}
}