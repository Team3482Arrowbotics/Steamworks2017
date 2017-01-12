package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Rangefinder extends Subsystem {

	private final AnalogInput rangeFinder = RobotMap.rangefinder;
	
	public double getDistance() {
		double range = rangeFinder.getVoltage();
		return range;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
