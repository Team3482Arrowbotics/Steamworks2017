package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class moveSquare extends CommandGroup {

	public moveSquare() {
		addSequential (new Move(1000));
		addSequential (new Rotate(90));
	}

}
