package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftTop extends CommandGroup{

	//rotate right, turn to gear
	//set down gear
	public LeftTop()
	{
		//get to position for setting gear
		addSequential (new Move (5000));
		addSequential (new Rotate(30));
		addSequential (new Move(2500));
		addSequential (new SetGear(-10));
		addSequential (new Move(690));
		addSequential (new SetGear(-40));
		addSequential (new Move(-3190));
		addSequential (new Rotate(-30));
		addSequential (new Move (7000));
		
		//start process for set gear and get to baselinE
	}
	// retreat, then start to baseline
}
