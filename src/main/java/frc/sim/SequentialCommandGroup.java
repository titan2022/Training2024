package frc.sim;

import java.util.ArrayList;

public class SequentialCommandGroup {
    private ArrayList<CommandBase> cmds = new ArrayList<>();

    public SequentialCommandGroup(CommandBase... cmds) {
        for (CommandBase cmd : cmds) {
            this.cmds.add(cmd);
        }
    }

    public void schedule() {
        TimedRobot.window.cmdSeqs.add(this.cmds);
    }
}