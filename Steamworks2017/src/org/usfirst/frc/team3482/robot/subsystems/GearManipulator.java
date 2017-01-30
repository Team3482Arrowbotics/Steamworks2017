package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator extends Subsystem {
	public static CANTalon manipTalon = RobotMap.talon2;
	public static boolean isPID = false;
	public GearManipulator() {
		manipTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	}
	
	public void maintainPosition() {
		if (isPID) {
			manipTalon.changeControlMode(TalonControlMode.Position);
			manipTalon.set(manipTalon.get());
		}
	}

	public void startPID() {
		isPID = true;
	}
	
	public void stopPID() {
		isPID = false;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
