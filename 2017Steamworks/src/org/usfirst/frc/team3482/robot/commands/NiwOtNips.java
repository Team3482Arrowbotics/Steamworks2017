package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class NiwOtNips extends TimedCommand{
	double speed;
	public NiwOtNips(double timeout, double speed) {
		super(timeout);
		this.speed = speed;
	}
	
	protected void initialize(){
		Robot.isDrive = false;
		RobotMap.driveRobot.tankDrive(speed, -speed);
	}
	protected void end(){
		Robot.isDrive = true;
	}

	

}
