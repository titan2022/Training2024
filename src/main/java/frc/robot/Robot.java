package frc.robot;

import frc.sim.CommandScheduler;
import frc.sim.TimedRobot;

public class Robot extends TimedRobot {
    /*
     * The controller object would need to be created, but for this simulator,
     * it already exists and you can access it through the `xbox` variable
     * 
     * Don't uncomment unless you are using it for actual deployment
     */
    //private XboxController xbox = new XboxController(0);

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        
    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Executes every scheduled command, otherwise commands don't work
        // Forgetting this line is the most common reason behind non-functioning code
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once during autonomous.
     * Schedule continous commands here.
     */
    @Override
    public void autonomousInit() {
        
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {

    }

    /**
     * This function is called once during operator contorl.
     * Schedule continous commands here.
     */
    @Override
    public void teleopInit() {
        
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {

    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
        
    }
}
