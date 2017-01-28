package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Rotate90Then70 extends CommandGroup{
	public Rotate90Then70(){
		addSequential(new Rotate(90));
		addSequential(new ProtoIntake(0.3), 1);
	}
}
