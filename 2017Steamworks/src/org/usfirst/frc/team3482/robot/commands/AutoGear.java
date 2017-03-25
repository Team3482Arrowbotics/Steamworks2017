package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGear extends CommandGroup{
	public AutoGear() {
		addSequential(new AutoSanic(1.1, 0.8));
	}
}
