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
		RobotMap.turnController.setSetpoint(angle);
		RobotMap.turnController.enable();
	}
	@Override
	protected void execute()
	{
		if(RobotMap.turnController.getError() < 1){
			RobotMap.turnController.disable();
			finished = true;
		}
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}
	protected void end()
	{
		RobotMap.turnController.disable();
	}
}
