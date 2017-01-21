package org.usfirst.frc.team3482.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator  extends Subsystem{
	
	CANTalon motor;
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public GearManipulator(CANTalon m){
		motor = m;
	}
	
	public void init(){
		motor.changeControlMode(TalonControlMode.Position);
	}
	
	public void moveToAngle(int p){
		motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		motor.set(p);
	}
}
