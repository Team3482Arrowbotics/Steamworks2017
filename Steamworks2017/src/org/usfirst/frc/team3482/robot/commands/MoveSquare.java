package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveSquare extends CommandGroup {
	
	public MoveSquare() {
		
		addSequential (new Move(1500));
		addSequential (new Move(0));
		addSequential (new Move(1500));
		addSequential (new Move (0));
		addSequential (new Rotate(86));
		//addSequential (new Move(1500));
		//addSequential (new Rotate(86));
		//addSequential (new Move(1500));
		//addSequential (new Rotate(86));
		
	}
}