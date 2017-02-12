package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

//command works with joystick button 3

public class Move extends Command 
{
	private double distance;
	public Move(double a)
	{
		distance=a;
	}
	protected void initialize(){
		System.out.println("Move init");
		if (distance == 0) {
			RobotMap.moveController.disable();
		} else {
			RobotMap.moveController.setSetpoint(distance);
			RobotMap.moveController.enable();
		}
	}
	protected boolean isFinished(){
		return RobotMap.moveController.getAvgError() == 100;
	}
	@Override
	protected void end()
	{
		System.out.println("Move end");
		RobotMap.moveController.disable();
	}
}
