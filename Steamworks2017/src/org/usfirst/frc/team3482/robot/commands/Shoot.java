package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	CANTalon shooterMotor = RobotMap.shooter;
	private double speedThreshold = -72000;
	private double waitLoop = 0;
	protected void initialize() {
		Robot.chassis.prepareShoot(Robot.oi.getflightStick());
	}
	
	protected void execute() {
		waitLoop ++;
		if((shooterMotor.getSpeed() > speedThreshold) && (waitLoop > 1000)) {
			Robot.chassis.startFeeder();
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopPrepareShoot();
		Robot.chassis.stopFeeder();
	}
	
	protected void interrupted() {
		end();
	}
}
