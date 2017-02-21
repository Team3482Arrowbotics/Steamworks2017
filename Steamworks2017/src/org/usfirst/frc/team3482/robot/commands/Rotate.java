package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command 
{
	private double angle;
	private PIDController rotateController = RobotMap.turnController;
	public Rotate(double a)
	{
		angle=a;
	}
	protected void initialize(){
		rotateController.setSetpoint(angle);
		rotateController.enable();
	}
	@Override
	protected void execute()
	{
		if(rotateController.onTarget()) {
			rotateController.disable();
		}
	}
	protected boolean isFinished() {
		return rotateController.onTarget();
	}
}
