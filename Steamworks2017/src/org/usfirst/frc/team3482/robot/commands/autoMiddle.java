package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoMiddle extends CommandGroup {
	private double distance; 
	private boolean finished = false;
	
	public autoMiddle(double d){
		distance = d;
	}
	protected void initialize(){
		RobotMap.moveController.setSetpoint(distance);
		RobotMap.moveController.enable();
	}
	
	protected void execute(){
		/*RobotMap.talon8.changeControlMode(TalonControlMode.Position);
		RobotMap.talon3.changeControlMode(TalonControlMode.Position);
		RobotMap.talon8.set(distance);
		RobotMap.talon3.set(500);*/
		//RobotMap.moveController.setSetpoint(distance);
		if(RobotMap.moveController.getError()<20)
		{
			RobotMap.moveController.disable();
		}
		finished = true;
	}
	
	protected boolean isFinished()
	{
		return finished;
	}
	
	protected void end()
	{
		//RobotMap.moveController.disable();
	}
	

}
