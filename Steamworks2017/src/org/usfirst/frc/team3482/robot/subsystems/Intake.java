package org.usfirst.frc.team3482.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Intake extends PIDSubsystem {
	private final CANTalon testMotor;
	private String name;
	//just have CANTalon so only declared one port
	public Intake(int port, String name)
	{
		super (name,0.0,0.0,0.0);
		this.name = name;
		testMotor = new CANTalon(port);
		setAbsoluteTolerance(0.2);
		getPIDController().setContinuous(false);
		getPIDController().setSetpoint(0);
		getPIDController().enable();
		
		//we do not have both actuator and sensor, so try to declare both as CANTalon
		LiveWindow.addActuator(this.name,"testMotor", testMotor);
		LiveWindow.addSensor(this.name, "testMotor", testMotor);
		LiveWindow.addActuator(this.name, "PIDSubsystem Controller", getPIDController());
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean onTarget()
	{
		double e = Math.abs(getPIDController().getError());
		System.out.println(name + ", " + e);
		return e < .15;
	}

	protected double returnPIDInput()
	{
		//Trust in the CANTalon
		return testMotor.get();
	}
	
	protected void usePIDOutput(double output){
		if(!Double.isNaN(output))
		{
			testMotor.pidWrite(output);
		}
	}
}
