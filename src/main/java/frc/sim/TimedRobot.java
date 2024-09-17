/*
 * IGNORE THIS FILE
 */

package frc.sim;

import frc.sim.graphics.Tank;
import frc.sim.graphics.XboxController;

public class TimedRobot {
    public XboxController xbox = new XboxController(0);
    public Tank window;

    public TimedRobot() {
        this.window = new Tank(this);
    }

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    public void robotInit() {

    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    public void robotPeriodic() {
        
    }

    /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
    public void autonomousInit() {
        
    }

    /** This function is called periodically during autonomous. */
    public void autonomousPeriodic() {

    }

    /** This function is called once during operator control. */
    public void teleopInit() {

    }

    /** This function is called periodically during operator control. */
    public void teleopPeriodic() {

    }

    /** This function is called once each time the robot enters Disabled mode. */
    public void disabledInit() {
        
    }

    public final void start(boolean isTeleop) {
        this.robotInit();

        if (isTeleop) {
            this.teleopInit();
        } else {
            this.autonomousInit();
        }
    }
}
