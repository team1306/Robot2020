package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.vision.PositionVisionCommand;
import frc.robot.commands.vision.ShootVisionCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * Used for calling the vision command sequence in the correct order
 */
public class VisionCommand extends SequentialCommandGroup {
    public VisionCommand(DriveTrain driveTrain, Shooter shooter, Intake intake) {
        super();
        PositionVisionCommand position = new PositionVisionCommand(driveTrain);
        ShootVisionCommand shoot = new ShootVisionCommand(shooter, intake);
        //addCommands(shoot,position);// - the test way
        addCommands(position, shoot);// - the right way
    }

    public void execute(){
        super.execute();

        //my code
    }
}