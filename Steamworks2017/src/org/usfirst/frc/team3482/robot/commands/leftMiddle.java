package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class leftMiddle extends CommandGroup {
	
	public leftMiddle(){
		//deposit gear
		addSequential (new Move(4000));
		//addSequential (new SetGear(-20));
		addSequential (new Move(695));
		//addSequential (new SetGear(-25));
		
		//move back, get moving to baseline
		addSequential (new Move(-2435));
		addSequential (new Rotate(90));
		addSequential (new Move(2852));
		addSequential (new Rotate(-90));
		addSequential (new Move( 3500));
	}
}
