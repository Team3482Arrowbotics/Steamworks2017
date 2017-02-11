package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator extends Subsystem {
	public static CANTalon manipTalon = RobotMap.talon2;
	public static boolean isPID = false;
	public static double startPos;
	public GearManipulator() {
		manipTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		int absolutePosition = RobotMap.talon2.getPulseWidthPosition() & 0xFFF;
		RobotMap.talon2.setEncPosition(absolutePosition);
        RobotMap.talon2.reverseSensor(false);
        RobotMap.talon2.setP(0.1);
        RobotMap.talon2.setI(0.0);
        RobotMap.talon2.setD(0.0);
        RobotMap.talon2.setF(0.0);
		RobotMap.talon2.changeControlMode(TalonControlMode.Position);
		RobotMap.talon2.setAllowableClosedLoopErr(0);
		RobotMap.talon2.configNominalOutputVoltage(0, 0);
		RobotMap.talon2.configPeakOutputVoltage(12, -12);
		RobotMap.talon2.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		startPos  = RobotMap.talon2.get();
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
