package org.usfirst.frc.team3482.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDOutput;

public class TwoTalon implements PIDOutput{
	CANTalon talonRight;
	CANTalon talonLeft;
	public TwoTalon(CANTalon tR, CANTalon tL){
		talonRight = tR;
		talonLeft = tL;
	}
	
	@Override
	public void pidWrite(double output) {
		talonRight.set(output);
		talonLeft.set(output);
		
	}

}
