/*
 * IGNORE THIS FILE
 */

package frc.sim;

public abstract class CommandBase {
    public boolean firstRun = true;
    public boolean firstFinish = true;

    /** The initial subroutine of a command. Called once when the command is initially scheduled. */
    public void initialize() {
        
    }

    /** The main body of a command. Called repeatedly while the command is scheduled. */
    public void execute() {

    }

    /**
     * The action to take when the command ends. Called when either the command finishes normally, or
     * when it interrupted/canceled.
     *
     * <p>Do not schedule commands here that share requirements with this command. Use {@link
     * #andThen(Command...)} instead.
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    public void end(boolean interrupted) {

    }

    /**
     * Whether the command has finished. Once a command finishes, the scheduler will call its end()
     * method and un-schedule it.
     *
     * @return whether the command has finished.
     */
    public boolean isFinished() {
        return false;
    }

    public final CommandBase schedule(TimedRobot robot) {
        robot.window.cmds.add(this);
        return this;
    }
}
