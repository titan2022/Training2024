/*
 * IGNORE THIS FILE
 */
package frc.sim.graphics;

import frc.sim.TimedRobot;
import frc.sim.util.GVector;

public class WPI_TalonFX {
    private int multiplier = 1;

    public int id;
    private TimedRobot robot;

    public enum ControlMode {
        PercentOutput
    }

    enum NeutralMode {
        Brake,
        Coast
    }

    /*
     * 
     */
    public WPI_TalonFX(int id, TimedRobot robot) {
        this.id = id;
        this.robot = robot;
    }

    /*
     * 
     */
    public void set(ControlMode mode, double value) {
        double output = 0;
        if (mode == ControlMode.PercentOutput) {
            output = value * 250;
        }

        switch (id) {
            case 0:
                robot.window.acc_buffer.add(new GVector(0, output * multiplier));
                robot.window.rot_acc_buffer += output * 0.7 * multiplier;
                break;
            case 1:
                robot.window.acc_buffer.add(new GVector(0, output * multiplier));
                robot.window.rot_acc_buffer += output * 0.7 * multiplier;
                break;
            case 2:
                robot.window.acc_buffer.add(new GVector(0, output * multiplier));
                robot.window.rot_acc_buffer += -output * 0.7 * multiplier;
                break;
            case 3:
                robot.window.acc_buffer.add(new GVector(0, output * multiplier));
                robot.window.rot_acc_buffer += -output * 0.7 * multiplier;
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
            multiplier = -1;
        } else {
            multiplier = 1;
        }
    }
}
