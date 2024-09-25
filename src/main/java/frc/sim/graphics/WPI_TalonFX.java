/*
 * IGNORE THIS FILE
 */
package frc.sim.graphics;

import frc.sim.TimedRobot;
import frc.sim.util.GVector;

public class WPI_TalonFX {
    private double multiplier = 0.5;

    public int id;

    private ControlMode mode = ControlMode.PercentOutput;
    private double value = 0;

    public static enum ControlMode {
        PercentOutput
    }

    enum NeutralMode {
        Brake,
        Coast
    }

    /*
     * 
     */
    public WPI_TalonFX(int id) {
        this.id = id;

        TimedRobot.window.motors.add(this);
    }

    /*
     * 
     */
    public void set(ControlMode mode, double value) {
        this.mode = mode;
        this.value = value;

        update();
    }

    public void update() {
        double output = 0;
        if (mode == ControlMode.PercentOutput) {
            output = value * 250;
        }

        switch (id) {
            case 0:
                TimedRobot.window.acc_buffer.add(new GVector(0, output * multiplier));
                TimedRobot.window.rot_acc_buffer += output * 0.7 * multiplier;
                // TimedRobot.window.rot_jerk_buffer += output * 0.7 * multiplier;
                break;
            case 1:
                TimedRobot.window.acc_buffer.add(new GVector(0, output * multiplier));
                TimedRobot.window.rot_acc_buffer += output * 0.7 * multiplier;
                // TimedRobot.window.rot_jerk_buffer += output * 0.7 * multiplier;
                break;
            case 2:
                TimedRobot.window.acc_buffer.add(new GVector(0, output * multiplier));
                TimedRobot.window.rot_acc_buffer += -output * 0.7 * multiplier;
                // TimedRobot.window.rot_jerk_buffer += -output * 0.7 * multiplier;
                break;
            case 3:
                TimedRobot.window.acc_buffer.add(new GVector(0, output * multiplier));
                TimedRobot.window.rot_acc_buffer += -output * 0.7 * multiplier;
                // TimedRobot.window.rot_jerk_buffer += -output * 0.7 * multiplier;
                break;
            default:
                break;
        }
    }

    /*
     * 
     */
    public void setNeutralMode(NeutralMode mode) {
        if (mode == NeutralMode.Brake) {

        } else {

        }
    }

    /*
     * 
     */
    public void setInverted(boolean isInverted) {
        if (isInverted) {
            multiplier = -Math.abs(multiplier);
        } else {
            multiplier = Math.abs(multiplier);
        }
    }
}
