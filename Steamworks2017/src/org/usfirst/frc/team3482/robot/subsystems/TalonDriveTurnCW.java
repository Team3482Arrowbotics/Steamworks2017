package org.usfirst.frc.team3482.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TalonDriveTurnCW implements PIDOutput {
	public RobotDrive drive;

	public TalonDriveTurnCW (RobotDrive d)
	{
		drive = d;
	}
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		drive.tankDrive(output, -output);
		SmartDashboard.putNumber("TalonDrive output", output);
	}
	

}
