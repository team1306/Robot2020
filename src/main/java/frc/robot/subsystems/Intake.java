package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final Spark indexMotor;
    /**
     * For the intake, the following parts are controlled by intakeMain (:) and intakeLeft (-)
     *  
     *   
     *    |#|::::::::::::::::::|#|=|=|
     *    |#|                  |#|  |
     *  ##|#|--------|:::::::::|#|=|=|##
     */
    private final Spark intakeMain;
    private final Spark intakeLeft;

    private final DigitalInput indexSwitch;

    public Intake() {
        indexMotor = new Spark(Constants.K_INTAKE_INDEXER_SPARK);
        intakeMain = new Spark(Constants.K_INTAKE_AXEL_MAIN_SPARK);
        intakeLeft = new Spark(Constants.K_INTAKE_AXEL_LEFT_SPARK);
        indexSwitch = new DigitalInput(Constants.K_INTAKE_INDEX_SWITCH);
    }

    /**
     * Indexes a ball once intaked.
     * 
     * @param speed - double from 1 (in) to -1 (out)
     */
    public void index(double speed) {
        indexMotor.set(speed);
    }

    /**
     * Returns whether the indexing switch is pressed
     */
    public boolean getSwitch() {
        return indexSwitch.get();
    }

    /**
     * runs the intake bar
     * 
     * @param speedRight -The speed of the right half-axle and the roller bar
     * @param speedLeft -The speed of the left half-axle 
     */
    public void intake(double speedRight, double speedLeft) {
        intakeMain.set(speedRight);
        intakeLeft.set(speedLeft);
    }

}