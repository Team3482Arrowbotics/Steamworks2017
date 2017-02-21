package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RangeFinder extends Subsystem {
	AnalogInput frontRangeFinder = RobotMap.rangeFinderFront;
	AnalogInput backRangeFinder = RobotMap.rangeFinderBack;
	
	public double getRange(AnalogInput rangeFinder) {
		return rangeFinder.getAverageVoltage();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
