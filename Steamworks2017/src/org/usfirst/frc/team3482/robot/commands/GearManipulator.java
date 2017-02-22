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
		int absolutePosition = RobotMap.gearManipulator.getPulseWidthPosition() & 0xFFF;
		RobotMap.gearManipulator.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		RobotMap.gearManipulator.changeControlMode(TalonControlMode.Position);
		RobotMap.gearManipulator.set(absolutePosition);
		RobotMap.gearManipulator.set(absolutePosition - angle);
		System.out.println("initialized");
	}
	
	protected void execute() {
		System.out.println("Current Position: " + RobotMap.gearManipulator.getPosition());
		RobotMap.gearManipulatorWheels.set(0.4);
	}
	
	protected void interrupted()
	{
		RobotMap.gearManipulatorWheels.set(0.0);
		RobotMap.gearManipulator.changeControlMode(TalonControlMode.Position);
		RobotMap.gearManipulator.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		RobotMap.gearManipulator.set(absolutePosition);
		System.out.println("interrupted");
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub	
		return false;
	}
}