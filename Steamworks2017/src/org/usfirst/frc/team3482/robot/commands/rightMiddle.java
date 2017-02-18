package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class rightMiddle extends CommandGroup {

	public rightMiddle()
	{
		//deposit gear
		addSequential (new Move(4000));
		addSequential (new SetGear(-20));
		addSequential (new Move(695));
		addSequential (new SetGear(-25));
		
		//move back, get moving to baseline
		addSequential (new Move(-2435)); //number is halfway
		addSequential (new Rotate(-90));
		addSequential (new Move(2852));
		addSequential (new Rotate(90));
		addSequential (new Move( 3500));
	}
		
}
