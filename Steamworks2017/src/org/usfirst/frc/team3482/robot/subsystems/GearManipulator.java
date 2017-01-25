package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator  extends Subsystem{

	private final CANTalon moveAxel = RobotMap.talon4;
	
	private double startPosition; //.703
	private double lowerPosition; //-34.196
	private double restPosition; //-2.283

	double targetPositionRotations;
	boolean isPID = false;
	
	public GearManipulator() {
		int absolutePosition = moveAxel.getPulseWidthPosition() & 0xFFF;
        moveAxel.setEncPosition(absolutePosition);
        moveAxel.reverseSensor(false);
        moveAxel.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        //motor.configEncoderCodesPerRev(codesPerRev); //only for quad
        moveAxel.configNominalOutputVoltage(+0f, -0f);
        moveAxel.configPeakOutputVoltage(+12f, -12f);
        
        moveAxel.setAllowableClosedLoopErr(0);
		moveAxel.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
        moveAxel.setProfile(0);
        //moveIntake.setPID
        moveAxel.setF(0.0);
        moveAxel.setP(0.04);
        moveAxel.setI(0.0);
        moveAxel.setD(0.0);
        
        startPosition = 0.7;
        
        setRestPosition(getStartPosition());
        lowerPosition = getStartPosition()-32.92;
        targetPositionRotations = getRestPosition();
	}
        
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void maintainPosition() {
		if (isPID) {
	        moveAxel.changeControlMode(TalonControlMode.Position);
	    	moveAxel.set(getRestPosition());
		}
	}
	
	public void raiseIntake() {
		this.stopPID();
		System.out.println("Intake set to upper position.");
//		setRestPosition(getStartPosition());
		moveAxel.changeControlMode(TalonControlMode.Position);
		moveAxel.set(getStartPosition());
		this.startPID();
	}
	
	public void middleIntake() {
		this.stopPID();
		System.out.println("Intake set to middle position.");
		moveAxel.changeControlMode(TalonControlMode.Position);
		setRestPosition(lowerPosition);
//		moveAxel.set(getLowerPosition());
		this.startPID();
	}
	
	public void stopMovingIntake() {
		moveAxel.changeControlMode(TalonControlMode.PercentVbus);
		moveAxel.set(0.0);
	}
	
    public void stopPID() {
    	isPID = false;
    }
    
    public void startPID() {
    	isPID = true;
    }
    
    public boolean isPIDOn() {
    	return isPID;
    }
	
	public CANTalon getMoveIntake() {
		return moveAxel;
	}

	public double getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(double startPosition) {
		this.startPosition = startPosition;
	}

	public double getRestPosition() {
		return restPosition;
	}
	
	public void setRestPosition(double restPosition) {
		this.restPosition = restPosition;
	}

	public double getLowerPosition() {
		// TODO Auto-generated method stub
		return lowerPosition;
	}
}
