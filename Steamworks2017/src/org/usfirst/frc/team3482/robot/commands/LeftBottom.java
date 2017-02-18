package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftBottom extends CommandGroup{

	public LeftBottom()
	{
		//get to position for setting gear
		addSequential (new Rotate(-90));
		addSequential (new Move(2852));
		addSequential (new Rotate(90));
		//start process for set gear and get to baseline
		addSequential (new leftMiddle());
	}

}
