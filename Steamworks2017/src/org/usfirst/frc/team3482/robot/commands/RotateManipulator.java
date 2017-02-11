package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.subsystems.GearManipulator;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class RotateManipulator extends InstantCommand
{
	CANTalon manipulatorTalon = RobotMap.talon2;
	int pos;
	
	public RotateManipulator(int pos){
		super("Rotate");
		this.pos = pos;
	}
	
	protected void initialize(){
		manipulatorTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
	}
	
	protected void execute(){
		manipulatorTalon.set(GearManipulator.startPos - pos);
	}
	
	protected void end() {
		
	}

}
