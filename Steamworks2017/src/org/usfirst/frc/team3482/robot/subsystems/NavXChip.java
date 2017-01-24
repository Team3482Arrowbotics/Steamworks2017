package org.usfirst.frc.team3482.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavXChip extends Subsystem{
	
	AHRS ahrs;
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public NavXChip(AHRS ahrs){
		this.ahrs = ahrs;
		
	}
	public void putValuesToDashboard(){
		
	}
	
	public void calibrate(){
		
	}

}
