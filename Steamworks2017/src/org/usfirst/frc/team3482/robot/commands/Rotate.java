package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command
{
	private double angle; 
	private boolean rotateToAngle;
	public Rotate() 
	{
		
	}
	//input in degrees 0-360
	public Rotate(double a)
	{
		this.angle = a;
	}
	protected void init()
	{
		rotateToAngle = true;
	}
	protected void execute()
	{
		RobotMap.turnController.setSetpoint(angle);
        //if ( Robot.oi.getxboxController().getRawButton(1)) {
            //RobotMap.ahrs.reset();
        //}      
        if ( rotateToAngle ) {
            RobotMap.turnController.enable();
        }
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end()
	{
		rotateToAngle = false;
		RobotMap.ahrs.reset();
	}
}
