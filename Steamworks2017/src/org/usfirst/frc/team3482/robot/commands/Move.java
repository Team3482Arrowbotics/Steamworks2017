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
		RobotMap.moveController.reset();
		System.out.println("init error: "+RobotMap.moveController.getError());
		//RobotMap.turnController.setSetpoint(distance);
		RobotMap.moveController.setSetpoint(RobotMap.moveController.getSetpoint() + distance);
		RobotMap.moveController.enable();
	}
	protected void execute()
	{
		System.out.println("error: "+RobotMap.moveController.getError());
		System.out.println(RobotMap.moveController.onTarget());
		
	}
	protected boolean isFinished(){
		return RobotMap.moveController.onTarget();
	}
	@Override
	protected void end()
	{
		System.out.println("Move end");
		RobotMap.moveController.disable();
	}
}