package org.usfirst.frc.team3482.robot.commands;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class RunMotor extends Command
{
	CANTalon talon;
	public double speed;
	public RunMotor(CANTalon test, double s)
	{
		talon = test;
		speed = s;
	}
	protected void execute()
	{
		talon.set(speed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		talon.set(0.0);
		
	}

	protected void interrupted() {
		end();
	}
	
	
}
