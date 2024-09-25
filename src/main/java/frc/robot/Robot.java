package frc.robot;

import frc.robot.commands.MoveCommand;
import frc.robot.commands.TankControlCommand;
import frc.robot.subsystems.TankDriveSubsystem;
import frc.sim.CommandScheduler;
import frc.sim.SequentialCommandGroup;
import frc.sim.TimedRobot;

public class Robot extends TimedRobot {
    /*
     * The controller object would need to be created, but for this simulator,
     * it already exists and you can access it through the `xbox` variable
     * 
     * Don't uncomment unless you are using it for actual deployment
     */
    //private XboxController xbox = new XboxController(0);
    private TankDriveSubsystem drive = new TankDriveSubsystem();

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
        // Seq command group example
        // runs for 0.5s, stops for 1.5s, runs again for 1.5s
        new SequentialCommandGroup(
            new MoveCommand(drive, 1.0).withTimeout(0.5),
            new MoveCommand(drive, 0.0).withTimeout(1.5),
            new MoveCommand(drive, 0.5).withTimeout(1.5)
        ).schedule();
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
        new TankControlCommand(drive, xbox).schedule();
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
