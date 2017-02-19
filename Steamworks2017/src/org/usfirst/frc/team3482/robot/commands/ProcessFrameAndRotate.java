package org.usfirst.frc.team3482.robot.commands;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ProcessFrameAndRotate extends Command {
	private boolean finished = false;
	protected void initialize(){
    	System.out.println(Robot.nContours);
    	if((Robot.nContours >= 1)){
    		Rect r = Imgproc.boundingRect(Robot.cameraSubsystem.filterContoursOutput().get(0));
    		Robot.centerX = r.x + (r.width /2);
    		double turnPixels = Robot.centerX - (640 / 2);
    		System.out.println("TURN IS :                                                  " + turnPixels);
    		//System.out.println("TESTVISIONTHREAD IS :                                        " + testVisionThread);
    		//RobotMap.driveRobot.arcadeDrive(0.0, turn * 0.005); 
    		//RobotMap.driveRobot.arcadeDrive(0.0, 0.4);
    		Robot.degrees = (turnPixels * 60) / 640;
    		System.out.println(Robot.degrees);
    		Robot.degreesToTurn = Robot.degrees;
    		System.out.println("NUMBER OF CONTOURS :                                             " + Robot.nContours);
    		System.out.println("DEGREES :                      " + Robot.degrees);
    	}
    	//RobotMap.turnController.setP
		RobotMap.turnController.setSetpoint(Robot.degrees + 10);
		RobotMap.turnController.enable();
	}
	@Override
	protected void execute() {
		
		//System.out.println("Current Setpoint: " + RobotMap.turnController.getSetpoint());
		//RobotMap.turnController.setSetpoint(RobotMap.turnController.getSetpoint() + degrees);
		/*if(((Robot.degrees > -3) && (Robot.degrees < 3))) {
			RobotMap.turnController.disable();
			finished = true;
		}*/
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		RobotMap.turnController.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
