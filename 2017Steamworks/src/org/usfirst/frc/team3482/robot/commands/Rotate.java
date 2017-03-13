package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command 
{
	private double angle;
	private boolean finished = false;
	private double presentAngle;
	public double presentPosition;
	private PIDController rotateController = RobotMap.turnController;
	public Rotate(double a)
	{
		angle = a;
	}
	
	protected void initialize(){
		RobotMap.driveRobot.stopMotor();
		Robot.isDrive = false;
		presentAngle = RobotMap.ahrs.getAngle();
		//rotateController.setSetpoint(presentAngle + angle);
		rotateController.setSetpoint(angle);
		rotateController.enable();
	}
	
	@Override
	protected void execute()
	{
		if(rotateController.onTarget()) {
			rotateController.disable();
			Robot.isDrive = true;
			finished = true;
		}
	}
	
	protected boolean isFinished() {
		return finished;
	}
}
