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

	public void drive3 () {
		RobotMap.backRight.set(0.5);
	}
	
	public void stop3() {
		RobotMap.backRight.set(0.0);
	}
	
	public void drive ( Joystick s ) {
		double deadZone = 0.1;
		double leftY = s.getRawAxis ( 1 );
		double rightX = s.getRawAxis ( 4 );
		
		if ( leftY < deadZone && leftY > -deadZone ) {
			leftY = 0;
		}
		
		if ( rightX < deadZone && rightX > -deadZone ) {
			rightX = 0;
		}
		
		if ( leftY == 0 && rightX == 0) {
			return;
		}
		if ( Robot.oi.xboxController.getRawAxis(2) != 0) {
			robotDrive.arcadeDrive ( -(leftY) * 0.5, rightX * turnSpeed * 0.75 );
		} else {
			robotDrive.arcadeDrive ( -(leftY), rightX * turnSpeed );
		}
	}
	
	
	public void stop () {
		robotDrive.stopMotor ();
	}
	
	public boolean getLimitSwitch() {
		boolean isOn = RobotMap.limitSwitch.get();
		return isOn;
	}
	
	public void initializePhotoElectricCounter() {
		
	}
	
	public double getChassisAngle() {
		return RobotMap.gyro.getAngle();
	}
	
	public double getChassisTurnRate(){
		return RobotMap.gyro.getRate();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
