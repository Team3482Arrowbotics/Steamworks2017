package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator extends Subsystem {
	private double targetPositionRotations;
	public static double groundPosition = 11;
	public static double pegPosition = 3;
	public static double startPosition;
	private CANTalon manipulatorTalon = RobotMap.gearManipulator;
	private CANTalon manipulatorTalonWheels = RobotMap.gearManipulatorWheels;
	public GearManipulator() {
		int absolutePosition = manipulatorTalon.getPulseWidthPosition() &0xFFF;
		manipulatorTalon.setEncPosition(absolutePosition);
		manipulatorTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		manipulatorTalon.reverseSensor(false);
		manipulatorTalon.configNominalOutputVoltage(+0f, -0f);
		manipulatorTalon.configPeakOutputVoltage(+12f, -12f);
		manipulatorTalon.setAllowableClosedLoopErr(0);
		manipulatorTalon.setProfile(0);
		manipulatorTalon.setF(0.0);
		manipulatorTalon.setP(0.1);
		manipulatorTalon.setI(0.0);
		manipulatorTalon.setD(0.0);
		
		//manipulatorTalon.changeControlMode(TalonControlMode.Position);
		//startPosition = manipulatorTalon.get();
	}
	
	public void moveGearManipGround() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		targetPositionRotations = startPosition - groundPosition;
		manipulatorTalon.set(targetPositionRotations);
	}
	
	public void moveGearManipStartPos() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		targetPositionRotations = startPosition;
		manipulatorTalon.set(targetPositionRotations);
	}
	
	public void moveGearManipPegPos() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		targetPositionRotations = startPosition - pegPosition;
		manipulatorTalon.set(targetPositionRotations);
	}
	
	public void spinGearManipWheels() {
		manipulatorTalonWheels.set(0.4);
	}
	
	public void stopGearManipWheels() {
		manipulatorTalonWheels.set(0.0);
	}
	
	public double getGearManipPosition() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		return manipulatorTalon.get();
	}
	
	public void moveGearManip(Joystick s) {
		double flightStickAxisValue = s.getRawAxis(1);
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		double currentPosition = manipulatorTalon.get();
		manipulatorTalon.set(currentPosition - flightStickAxisValue);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
