package org.usfirst.frc.team3482.robot.commands;
import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command{

	public Shoot() {
		// TODO Auto-generated constructor stub
	}
	protected void initialize() { 
	}
	protected void execute()
	{
		Robot.shooter.testShooter();
	}
	@Override
	protected void end() {
		Robot.shooter.stopShooter();
		//Robot.shooter.stopRumble();
		
}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
