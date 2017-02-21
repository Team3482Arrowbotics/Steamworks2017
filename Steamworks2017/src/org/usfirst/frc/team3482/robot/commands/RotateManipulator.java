package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.subsystems.GearManipulator;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class RotateManipulator extends Command
{
	private boolean objectiveRotation;	//true = ground; false = peg
	public RotateManipulator(boolean objectiveRot) {
		objectiveRotation = objectiveRot;
	}
	
	protected void initialize() {
		if(objectiveRotation) {
			Robot.gearManipulator.moveGearManipGround();
		} else {
			Robot.gearManipulator.moveGearManipPegPos();
		}
	}
	
	protected void execute() {
		if(objectiveRotation) {
			Robot.gearManipulator.spinGearManipWheels();
		} else {
			//Robot.gearManip.spinGearManipWheels();  //delete comment if you want wheels to run while at peg position
		}
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.gearManipulator.stopGearManipWheels();
		Robot.gearManipulator.moveGearManipStartPos();
	}
	
	protected void interrupted() {
		end();
	}

}
