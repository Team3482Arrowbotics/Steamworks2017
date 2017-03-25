package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.OI;
import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.subsystems.GearManipulator;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class ManualMoveGearManipulator extends Command {
	boolean wheels = false;
	
	public ManualMoveGearManipulator(){
		
		
	};
	
	protected void initialize() {
	}
	
	protected void execute() {
		Robot.gearManipulator.moveGearManip(Robot.oi.getflightStick());
		GearManipulator.toggleWheelsButton.whenPressed(new Command(){
			@Override
			public void initialize(){
				wheels = !wheels;
			}
			@Override
			protected boolean isFinished() {
				return true;
			}
			
		});
//		if(Robot.oi.getflightStick().getRawButton(10)) {
//			wheels = !wheels;
//			System.out.println(wheels);
//		}
		if(wheels) {
			RobotMap.gearManipulatorWheels.set(0.6);
		} else if(!wheels) {
			Robot.gearManipulator.stopGearManipWheels();
		}
	}
	
	protected boolean isFinished() {
		return Robot.oi.getflightStick().getRawButton(4) == false;
	}
	
	protected void end() {
		Robot.gearManipulator.moveGearManipStartPos();
		Robot.gearManipulator.stopGearManipWheels();
	}
	
	protected void interrupted() {
		end();
	}
}
