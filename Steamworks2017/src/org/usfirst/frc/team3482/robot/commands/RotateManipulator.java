package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class RotateManipulator extends Command
{
	CANTalon manipulatorTalon = RobotMap.talon2;
	int pos;
	
	public RotateManipulator(int pos){
		this.pos = pos;
	}
	
	protected void initialize(){
		manipulatorTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		Robot.gearManipulator.stopPID();
	}
	
	protected void execute(){
		manipulatorTalon.set(pos);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		Robot.gearManipulator.startPID();
	}

}
