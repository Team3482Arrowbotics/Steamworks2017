package org.usfirst.frc.team3482.robot.subsystems;

public enum ManipulatorPosition {
	START (0), PEG (1), GROUND (2);
	private double angle;
	ManipulatorPosition(double delta){
		this.angle = delta;
	}
	public double angle(){
		return angle;
	}
}
