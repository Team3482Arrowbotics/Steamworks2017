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
		RobotMap.encoder1.reset();
		RobotMap.encoder2.reset();
		System.out.println("init error: "+RobotMap.moveController.getError());
		//RobotMap.turnController.setSetpoint(distance);
		RobotMap.moveController.setSetpoint(distance);
		RobotMap.moveController.enable();
	}
	protected void execute()
	{
		System.out.println("execute error: "+RobotMap.moveController.getError());
		if(RobotMap.moveController.getError()<30&& RobotMap.moveController.getError()>-30)
		{
			RobotMap.moveController.disable();
			RobotMap.moveController.reset();
			finished = true;
		}
		System.out.println(finished);
	}
	protected boolean isFinished(){
		return finished;
	}
	@Override
	protected void end()
	{
		System.out.println("Move end");
		RobotMap.moveController.disable();
		System.out.println("Current Setpoint: " + RobotMap.moveController.getSetpoint());
	}
}