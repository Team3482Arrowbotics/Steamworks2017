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
		//if (distance > 0){
			RobotMap.moveController.enable();
			RobotMap.moveController.setSetpoint(distance);
		//} else {
			//RobotMap.moveController.disable();
		//}
		System.out.println("is move enabled: "+RobotMap.moveController.isEnable());
	}
	protected void execute()
	{
		if(RobotMap.moveController.getError()<70)
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