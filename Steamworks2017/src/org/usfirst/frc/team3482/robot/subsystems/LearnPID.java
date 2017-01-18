
package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class LearnPID extends PIDSubsystem
{
	CANTalon testMotor = RobotMap.actualShooter;
	public LearnPID()
	{
		super("Test",2.0,0.0,0.0);
	}
	public void initDefaultCommand()
	{
		
	}
	protected double returnPIDInput()
	{
		return testMotor.getSpeed();
	}
	protected void usePIDOutput(double output)
	{
		testMotor.pidWrite(output);
	}
	
}
