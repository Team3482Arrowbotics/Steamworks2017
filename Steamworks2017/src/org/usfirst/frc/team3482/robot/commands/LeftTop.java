package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftTop extends CommandGroup{

	//rotate right, turn to gear
	//set down gear
	public LeftTop()
	{
		//get to position for setting gear
		addSequential (new Rotate(90));
		addSequential (new Move(2852));
		addSequential (new Rotate(-90));
		//start process for set gear and get to baseline
		addSequential (new leftMiddle());
		
	}
	// retreat, then start to baseline
}
