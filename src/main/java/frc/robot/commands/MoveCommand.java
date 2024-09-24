package frc.robot.commands;

import frc.robot.subsystems.TankDriveSubsystem;
import frc.sim.CommandBase;

public class MoveCommand extends CommandBase {
    private TankDriveSubsystem drive;
    private double percentOutput;

    public MoveCommand(TankDriveSubsystem drive, double percentOutput) {
        this.drive = drive;
        this.percentOutput = percentOutput;
        addRequirements(drive);
    } 

    @Override
    public void initialize() {
        drive.driveLeft(percentOutput);
        drive.driveRight(percentOutput);
    }

    @Override
    public void execute() {
        
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
