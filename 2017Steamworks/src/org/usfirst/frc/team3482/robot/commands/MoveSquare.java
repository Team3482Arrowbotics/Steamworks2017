package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveSquare extends CommandGroup {
	
	public MoveSquare() {
		
		addSequential (new Move(600), 5);
		addSequential (new Move(-600), 5);
		addSequential (new Move(600), 5);
		addSequential (new Move(-600), 5);
	}
}