package org.usfirst.frc.team3482.robot.commands;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

public class ProcessFrameAndRotate extends Command{
	
	protected void initialize(){
		CvSink cvSink = CameraServer.getInstance().getVideo();
		Mat source = new Mat();
    	cvSink.grabFrame(source);
    	Robot.camera.process(source);
    	int nContours = Robot.camera.filterContoursOutput().size();
    	int centerX;
    	if(nContours >= 1){
    		Rect r = Imgproc.boundingRect(Robot.camera.filterContoursOutput().get(0));
    		centerX = r.x + (r.width /2);
    		double turnPixels = centerX - (640 / 2);
    		System.out.println("TURN IS :                                                  " + turnPixels);
    		//System.out.println("TESTVISIONTHREAD IS :                                        " + testVisionThread);
    		//RobotMap.driveRobot.arcadeDrive(0.0, turn * 0.005); 
    		//RobotMap.driveRobot.arcadeDrive(0.0, 0.4);
    		double degrees = turnPixels / 7;
    		int seconds = (int)(1.361 * degrees);
    		System.out.println("NUMBER OF CONTOURS :                                             " + nContours);
    		System.out.println("DEGREES :                      " + degrees);
    		RobotMap.turnController.setSetpoint(RobotMap.turnController.getSetpoint() + degrees);
    		RobotMap.turnController.enable();
    	}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
