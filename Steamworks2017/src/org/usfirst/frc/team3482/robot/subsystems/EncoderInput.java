package org.usfirst.frc.team3482.robot.subsystems;


import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class EncoderInput implements PIDSource {
	public CANTalon talon;
	public int initialPosition;

	public EncoderInput(CANTalon s)
	{
		talon = s;
	}
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return talon.getEncPosition();
	}

}