package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

//command works with joystick button 3

public class Move extends Command 
{
	
	private double distance;
	private boolean finished = false;
	//private Timer moveTimer;
	public Move(double a)
	{
		distance=a;
	}
	protected void initialize(){
		//moveTimer.start();
		RobotMap.moveController.reset();
		System.out.println("init error: "+RobotMap.moveController.getError());
		//RobotMap.turnController.setSetpoint(distance);
		RobotMap.moveController.setSetpoint(RobotMap.moveController.getSetpoint() + distance);
		RobotMap.moveController.enable();
	}
	protected void execute()
	{
		//System.out.println("time: "+moveTimer.get());
		System.out.println("error: "+RobotMap.moveController.getError());
		System.out.println(RobotMap.moveController.onTarget());
		
	}
	protected boolean isFinished(){
		//return RobotMap.moveController.onTarget() && moveTimer.get()>1;
		return RobotMap.moveController.onTarget();
	}
	@Override
	protected void end()
	{
		System.out.println("Move end");
		RobotMap.moveController.disable();
	}
}