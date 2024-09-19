package frc.robot.subsystems;

import frc.sim.SubsystemBase;
import frc.sim.graphics.WPI_TalonFX;
import frc.sim.graphics.WPI_TalonFX.ControlMode;

public class TankDriveSubsystem extends SubsystemBase {
    WPI_TalonFX topLeftMotor = new WPI_TalonFX(0);
    WPI_TalonFX bottomLeftMotor = new WPI_TalonFX(1);
    WPI_TalonFX topRightMotor = new WPI_TalonFX(2);
    WPI_TalonFX bottomRightMotor = new WPI_TalonFX(3);

    public TankDriveSubsystem() {
        topLeftMotor.setInverted(false);
        bottomLeftMotor.setInverted(false);
    }

    public void driveLeft(double percentPower) {
        topLeftMotor.set(ControlMode.PercentOutput, percentPower);
        bottomLeftMotor.set(ControlMode.PercentOutput, percentPower);
    }

    public void driveRight(double percentPower) {
        topRightMotor.set(ControlMode.PercentOutput, percentPower);
        bottomRightMotor.set(ControlMode.PercentOutput, percentPower);
    }

    public void stop() {
        driveLeft(0);
        driveRight(0);
    }

    /**
     * Periodic function that runs every 20ms.
     * Most subsystems don't need one
     */
    @Override
    public void periodic() {

    }
}