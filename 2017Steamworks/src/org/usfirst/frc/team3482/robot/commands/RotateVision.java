package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class RotateVision extends Command {
	private boolean finished = false;
	private boolean centered;
	public RotateVision() {
		
	}
	
	protected void initialize(){
		Robot.isDrive = false;
		RobotMap.driveRobot.arcadeDrive(0.0, 0.2);
	}
	
	@Override
	protected void execute()
	{
		centered = Robot.isCentered;
		if(centered) {
			RobotMap.driveRobot.stopMotor();
			Robot.isDrive = true;
			finished = true;
		}
	}
	
	protected boolean isFinished() {
		return finished;
	}
}
