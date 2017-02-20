package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveSquare extends CommandGroup {
	
	public MoveSquare() {
		
		addSequential (new Move(1500));
		addSequential (new Move(0));
		addSequential (new Move(1500));
		addSequential (new Move (0));
		System.out.println("CurrentSetpoint" + RobotMap.moveController.getSetpoint());
		
		addSequential (new Rotate(86));
		//addSequential (new Move(1500));
		//addSequential (new Rotate(86));
		//addSequential (new Move(1500));
		//addSequential (new Rotate(86));
		
	}
}