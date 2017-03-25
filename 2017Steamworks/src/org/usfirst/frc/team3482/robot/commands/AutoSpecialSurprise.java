package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSpecialSurprise extends CommandGroup{
	public AutoSpecialSurprise(){
		addSequential(new AutoSanic(2, 0.8));
		addSequential(new NiwOtNips(6, .8));
	}
}
