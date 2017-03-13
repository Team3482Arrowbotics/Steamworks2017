package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator extends Subsystem {
	
	public static double startPosition;
	public CANTalon manipulatorTalon = RobotMap.gearManipulator;
	public CANTalon manipulatorTalonWheels = RobotMap.gearManipulatorWheels;
	public static Button toggleWheelsButton;

	public GearManipulator() {
//		int absolutePosition = manipulatorTalon.getPulseWidthPosition() & 0xFFF;
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
//		manipulatorTalon.setEncPosition(absolutePosition);
		manipulatorTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		manipulatorTalon.reverseSensor(false);
		manipulatorTalon.configNominalOutputVoltage(+0f, -0f);
		manipulatorTalon.configPeakOutputVoltage(+12f, -12f);
		manipulatorTalon.setAllowableClosedLoopErr(0);
		manipulatorTalon.setProfile(0);
		manipulatorTalon.setF(0.0);
		manipulatorTalon.setP(0.3);
		manipulatorTalon.setI(0.0);
		manipulatorTalon.setD(0.0);
		startPosition = manipulatorTalon.getPosition();
		
		
	}

	public void moveGearManipStartPos() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		manipulatorTalon.set(startPosition);
	}

	public void moveGearManip(ManipulatorPosition pos) {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		manipulatorTalon.set(startPosition - pos.angle());
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
		manipulatorTalon.set(startPosition - (flightStickAxisValue / 4));
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
