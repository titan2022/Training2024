package frc.robot.commands;

import frc.robot.subsystems.TankDriveSubsystem;
import frc.sim.CommandBase;
import frc.sim.graphics.XboxController;

public class TankControlCommand extends CommandBase {
    private TankDriveSubsystem drive;
    private XboxController xbox;

    public TankControlCommand(TankDriveSubsystem drive, XboxController xbox) {
        this.drive = drive;
        this.xbox = xbox;
        addRequirements(drive);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        drive.driveLeft(xbox.getLeftY());
        drive.driveRight(xbox.getRightY());
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
