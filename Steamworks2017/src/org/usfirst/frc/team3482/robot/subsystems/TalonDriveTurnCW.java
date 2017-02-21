package org.usfirst.frc.team3482.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

public class TalonDriveTurnCW extends TalonDrive implements PIDOutput{

	public TalonDriveTurnCW(RobotDrive d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public void pidWrite(double output){
		drive.tankDrive(output, -output);
	}
}
