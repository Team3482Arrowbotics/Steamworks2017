package org.usfirst.frc.team3482.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

public class TurnPID implements PIDOutput {
	public RobotDrive drive;
	
	public TurnPID(RobotDrive d) {
		drive = d;
	}

	@Override
	public void pidWrite(double output) {
		drive.arcadeDrive(0, output);
	}
}
