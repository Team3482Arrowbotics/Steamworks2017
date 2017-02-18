package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightTop extends CommandGroup{

	public RightTop(){
		//get to position to set gear
		addSequential (new Rotate(-90));
		addSequential (new Move(2852));
		addSequential (new Rotate(90));
		
		//start process for set gear and get to baseline
		addSequential (new rightMiddle());
	}
}
