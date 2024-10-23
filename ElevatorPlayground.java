import sim.Elevator;
import sim.PlaygroundBase;

public class ElevatorPlayground extends PlaygroundBase {
    /**
     * This function runs once on the first frame. (like `-init` in WPILib)
     */
    @Override
    public void start(Elevator elevator) {
        elevator.setMotorAcceleration(0);
        elevator.setTargetVelocity(0);
        elevator.setTargetPosition(0);
    }

    /**
     * This function runs every frame. (like `-periodic` in WPILib)
     */
    @Override
    public void update(Elevator elevator) {
        
    }
}
