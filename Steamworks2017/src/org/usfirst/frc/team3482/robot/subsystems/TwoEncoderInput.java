package org.usfirst.frc.team3482.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class TwoEncoderInput implements PIDSource {
	private Encoder encoder1, encoder2;
	
	public TwoEncoderInput(Encoder encoder1, Encoder encoder2) {
		this.encoder1 = encoder1;
		this.encoder2 = encoder2;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return (encoder1.getDistance() + encoder2.getDistance())/2.0;
	}


	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		
		
	}

}
