
package org.usfirst.frc.team3482.robot;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3482.robot.subsystems.Camera;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.Rangefinder;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	NetworkTable cameraTable;
	private int counter;
	private int sum = 0;
	private int loop = 0;
	private boolean testVisionThread = false;
	public static final double RADIUS = 3.66056;
	private VisionThread visionThread;
	public static final double CIR = 23.0;
	public static boolean isPID = true;
	private double centerX = 0.0;
	private final Object imgLock = new Object();
//	wheel radius: 3.66056 inches
//  wheel circumference: 23	inches
	double rotateToAngleRate;
	int autoLoop;
	//private RobotDrive drive;
	public static Chassis chassis;
	public static Camera cameraSubsystem;
	private int nContours = 0;
	public static Rangefinder rangefinder;
	private Rect r = new Rect();
	public static OI oi;
	double initialPosition;
	Command teleopCommand;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		RobotMap.init();
		rangefinder = new Rangefinder();
		cameraSubsystem = new Camera();
		chassis = new Chassis();
		oi = new OI();
		
		SmartDashboard.putNumber("Low Hue Value: ", 0.0);
		SmartDashboard.putNumber("Low Saturation Value: ", 0.0);
		SmartDashboard.putNumber("Low Luminance Value: ", 0.0);
		SmartDashboard.putNumber("High Hue Value: ", 0.0);
		SmartDashboard.putNumber("High Saturation Value: ", 0.0);
		SmartDashboard.putNumber("High Luminance Value: ", 0.0);
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(640, 480);
        
       /*new Thread(() -> {
            //UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            //camera.setResolution(640, 480);
	                        
	    	CvSink cvSink = CameraServer.getInstance().getVideo();
	        CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
	           
	        Mat source = new Mat();
	        Mat output = new Mat();
	
	        int loop = 0;
	        while(loop < 600) {
	         cvSink.grabFrame(source);
	         Robot.cameraSubsystem.process(source);
	         loop ++;
	         outputStream.putFrame(Robot.cameraSubsystem.cvErodeOutput());
	        }
        }).start();*/
        
         /*new Thread(() -> {
        	 while(true) {
        		 outputStream.putFrame(Robot.camera.cvErodeOutput());
        	 }
         }).start();*/
        
        visionThread = new VisionThread(camera, Robot.cameraSubsystem, pipeline -> {
        	CvSink cvSink = CameraServer.getInstance().getVideo();    
        	Mat source = new Mat();
        	cvSink.grabFrame(source);
        	pipeline.process(source);
        	if (!pipeline.filterContoursOutput().isEmpty()) {
        		testVisionThread = true;
        		nContours = pipeline.filterContoursOutput().size();
        		r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
        		synchronized (imgLock) {
        			centerX = r.x + (r.width /2);
        		}
        	} 
        });
        visionThread.start();
        //drive = new RobotDrive(0, 8, 2, 3);
		
		SmartDashboard.putData("Auto mode", chooser);

		SmartDashboard.putNumber("TurnP", .01);
		SmartDashboard.putNumber("TurnI", 0);
		SmartDashboard.putNumber("TurnD", 0);
		SmartDashboard.putNumber("shooter speed", 0.0);
		SmartDashboard.putNumber("left motor speed 1", 0.0);
		SmartDashboard.putNumber("left motor speed 2", 0.0);
		SmartDashboard.putNumber("right motor speed 1", 0.0);
		SmartDashboard.putNumber("right motor speed 2", 0.0);
		SmartDashboard.putNumber("intake speed", 0.0);
		SmartDashboard.putNumber("gear manipulator speed", 0.0);

		RobotMap.rangefinder.setAverageBits(6);
		RobotMap.rangefinder.setOversampleBits(4);

		//RobotMap.ahrs.reset();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//RobotMap.ahrs.reset();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
//		counter ++;
//		int loop = 0;
		Scheduler.getInstance().run();
		//System.out.println(r);
		double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
		}
		double turnPixels = centerX - (640 / 2);
		System.out.println("TURN IS :                                                  " + turnPixels);
		System.out.println("TESTVISIONTHREAD IS :                                        " + testVisionThread);
		//RobotMap.driveRobot.arcadeDrive(0.0, turn * 0.005); 
		//RobotMap.driveRobot.arcadeDrive(0.0, 0.4);
		double degrees = turnPixels / 7;
		int seconds = (int)(1.361 * degrees);
		System.out.println("NUMBER OF CONTOURS :                                             " + nContours);
		//System.out.println("COUNTER :                      " + counter);
		System.out.println("DEGREES :                      " + degrees);
	}

	@Override

	public void teleopInit() {
		//teleopCommand = new Drive();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		//teleopCommand.start();
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
