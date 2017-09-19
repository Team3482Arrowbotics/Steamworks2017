public class Shootgun extends Command {
  public Shootgun() {
  }


  protected void initialize() {
  }
  protected void execute() {
    Robot.chassis.prepareShoot(shootButton);
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

