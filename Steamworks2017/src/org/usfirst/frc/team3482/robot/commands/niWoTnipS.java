package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class niWoTnipS extends Command {

	int loop = 0;
	boolean finished = false;
	@Override
	protected void initialize() {
		RobotMap.talon8.changeControlMode(TalonControlMode.PercentVbus);
		RobotMap.talon3.changeControlMode(TalonControlMode.PercentVbus);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		while ( loop <= 100 ) {
			RobotMap.talon8.set(1.0);
			RobotMap.talon3.set(1.0);
			loop ++;
		}
		finished = true;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		//RobotMap.talon3.set(0.0);
		//RobotMap.talon8.set(0.0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}