package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;

public class Shooter extends RobotMap {
	private final CANTalon objectShooter = RobotMap.actualShooter;
	
	public Shooter() {
		
	}
	public void testShooter()
	{
		objectShooter.set(0.6);
	}
	public void stopShooter()
	{
		objectShooter.set(0.0);
	}
	public double getSpeed()
	{
		double speed = objectShooter.getSpeed();
		return speed;
	}
	
}