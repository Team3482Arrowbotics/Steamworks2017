package org.usfirst.frc.team3482.robot.commands;
import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;
public class AutoSanic extends TimedCommand{

	private double speed = 0.0;
	public AutoSanic(double timeout, double s) {
		super(timeout);
		speed = s;
		// TODO Auto-generated constructor stub
	}
	protected void initialize(){
		Robot.isDrive = false;
		RobotMap.driveRobot.arcadeDrive(speed, 0);
	}
	protected void interrupted(){
		end();
	}
	protected void end(){
		Robot.isDrive = true;
		Robot.chassis.stopDrive();
	}	
}