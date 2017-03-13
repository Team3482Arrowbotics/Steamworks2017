package org.usfirst.frc.team3482.robot.subsystems;

public enum WheelDirection {
	kForwards (1), kBackwards (-1), kStop (0);
	private int direction;
	WheelDirection(int direction){
		this.direction = direction;
	}
	public int direction() {return direction;};
}
