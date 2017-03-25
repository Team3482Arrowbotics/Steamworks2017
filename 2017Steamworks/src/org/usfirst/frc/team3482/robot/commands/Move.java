package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command 
{
	private double distance;
	private boolean finished = false;
	public Move(double a)
	{
		distance=a;
	}
	protected void initialize(){
		RobotMap.driveEncoder1.reset();
		RobotMap.driveEncoder2.reset();
		System.out.println("init error: "+RobotMap.moveController.getError());
		RobotMap.moveController.setSetpoint(RobotMap.driveEncoder2.get() - distance);
		System.out.println(RobotMap.moveController.getSetpoint());
		RobotMap.moveController.enable();
	}
	protected void execute()
	{
		System.out.println("Execute error: " + RobotMap.moveController.getError());
		finished = RobotMap.moveController.onTarget();
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