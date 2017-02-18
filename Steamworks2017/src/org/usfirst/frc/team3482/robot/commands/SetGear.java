package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class SetGear extends Command{
	private double angle;
	public SetGear(double a)
	{
		angle = a;
	}
	protected void initialize()
	{
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
		System.out.println(RobotMap.talon2.get());
	}

	protected void execute()
	{
		RobotMap.talon2.set(angle);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}