/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Command autoCommand;
  public static Command testCommand = null;

  public static DriveTrain driveTrain = null;
  public static Shooter shooter = null;
  public static Intake intake = null;

  private static RobotContainer m_robotContainer;

  private static final boolean debug=true;
  private static final int debugFrequency = 40;
  private int debugCounter=0;
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    debugCounter=debugCounter+1;
    if(debug&&debugCounter%debugFrequency==0){
    debugCounter=0;
    //Provide output to the console (26 char width)
    String message ="\n\n######## ROBOT INFO ########\n\n";
    if(!(driveTrain==null)){
    message+=String.format("Drive: L %0$7f R %0$7f\n", driveTrain.getLeftPercentOut(),driveTrain.getRightPercentOut());
    message+=String.format("Heading:%11s% 7f\n",".",driveTrain.getHeadingDegrees());
    }
    message+="\n###########################\n\n";
    System.out.println(message);
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    autoCommand = m_robotContainer.getAutonomousCommand();
    // schedule the autonomous command only if set by the RobotContainer
    if (autoCommand != null) {
      autoCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //command scheduler already ran in RobotPeriodic
  }

  @Override
  public void teleopInit() {
    // If auto is continuing, cancel it. 
    if (autoCommand != null) {
      autoCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
        //command scheduler already ran in RobotPeriodic
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    //If the robotContainer has set up a command to test, run that. (Usually tuner command)
    try {
      CommandScheduler.getInstance().schedule(testCommand);
      System.out.println("Executing test command");
    } catch (NullPointerException e) {
      //no command found.
      System.out.println("No test command, doing normal routine.");
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
      //command scheduler already ran in RobotPeriodic
  }
}
