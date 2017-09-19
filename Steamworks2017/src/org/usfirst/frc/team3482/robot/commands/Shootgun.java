import org.usfirst.frc.team3482.robot.comands;
import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
//import com.ctre.CANTalon;
import eu.wpi.first.wpilibj.command.Command;

public class Shootgun extends Command {
  public Shootgun() {
  }


  protected void initialize() {
  }
  
  protected void execute() {
    Robot.chassis.prepareShoot(Robot.oi.getXboxController);
  }
  
  protected boolean isFinished(){
      return false;
  }
  
  
  protected void end(){
    Robot.chassis.stopPrepareShoot();
  }
  
  
  protected void interrupted(){
  }
  






}

