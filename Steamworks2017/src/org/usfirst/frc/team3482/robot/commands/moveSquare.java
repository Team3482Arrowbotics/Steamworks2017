package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class moveSquare extends CommandGroup {

	public moveSquare() {
		addSequential (new Move(1000));
		//addSequential (new Rotate(90));
		addSequential (new Move(-1000));
		//addSequential (new Rotate(90));
		//addSequential (new Move(1000));
		//addSequential (new Rotate(90));
		//addSequential (new Move(1000));
		//addSequential (new Rotate(90));
		//addSequential (new Rotate(-90));
		//addSequential (new Move(-500));
		//addSequential (new Rotate(90));
		//addSequential (new Move(1000));
		//addSequential (new Rotate(90));
		//addSequential (new Move(0));
		//addSequential (new Rotate(90));
		//addSequential (new Move(-1000));
		//addSequential (new Move(1000));
		//addSequential (new Move(1000));
		//addSequential (new Move(-2000));
	}
}
