package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private final RobotDrive robotDrive = RobotMap.driveRobot;
	double turnSpeed = -0.75;

	public void drive(Joystick s) {
		double deadZone = 0.1;
		double leftY = s.getRawAxis(1);
		double rightX = s.getRawAxis(4);

		if (leftY < deadZone && leftY > -deadZone) {
			leftY = 0;
		}

		if (rightX < deadZone && rightX > -deadZone) {
			rightX = 0;
		}

		if (Robot.oi.xboxController.getRawAxis(2) != 0) {
			robotDrive.arcadeDrive(leftY * 0.5, rightX * turnSpeed * 0.75);
		} else {
			robotDrive.arcadeDrive(leftY, rightX * turnSpeed);
		}
	}

	public void stopDrive() {
		robotDrive.stopMotor();
	}
	
	public void prepareShoot(Joystick s) {
		RobotMap.polycord1.set(-0.4);
		RobotMap.polycord2.set(-0.25);
		RobotMap.shooter.set((-(s.getRawAxis(3)) + 1)/2);
	}
	
	public void stopPrepareShoot() {
		RobotMap.polycord1.set(0.0);
		RobotMap.polycord2.set(0.0);
		RobotMap.shooter.set(0.0);
	}
	
	public void startFeeder() {
		RobotMap.feeder.set(-0.5);
	}
	
	public void stopFeeder() {
		RobotMap.feeder.set(0.0);
	}

	public void startIntake() {
		RobotMap.intake.set(-0.6);
	}
	
	public void stopIntake() {
		RobotMap.intake.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
