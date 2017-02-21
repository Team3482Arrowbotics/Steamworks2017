package org.usfirst.frc.team3482.robot.commands;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class GearManipulator extends Command {
	private double angle;
	private int absolutePosition;
	
	public GearManipulator(double a){
		angle = a;
	}
	
	protected void initialize() {
		int absolutePosition = RobotMap.gearManip.getPulseWidthPosition() & 0xFFF;
		RobotMap.gearManip.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		RobotMap.gearManip.changeControlMode(TalonControlMode.Position);
		RobotMap.gearManip.set(absolutePosition);
		RobotMap.gearManip.set(absolutePosition - angle);
		System.out.println("initialized");
	}
	
	protected void execute() {
		System.out.println("Current Position: " + RobotMap.gearManip.getPosition());
		RobotMap.gearManipWheels.set(0.4);
	}
	
	protected void interrupted()
	{
		RobotMap.gearManipWheels.set(0.0);
		RobotMap.gearManip.changeControlMode(TalonControlMode.Position);
		RobotMap.gearManip.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		RobotMap.gearManip.set(absolutePosition);
		System.out.println("interrupted");
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub	
		return false;
	}
}