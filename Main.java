import sim.SimWindow;

public final class Main {
    public static void main(String... args) {
        // Manual mode with visual
        new SimWindow(new ElevatorPlayground(), false);

        // Automated
        // ElevatorPlayground playground = new ElevatorPlayground();
        // SimWindow window = new SimWindow(playground, true);

        // // TODO: Put your `start` code here
        // window.elevator.setMotorAcceleration(0);
        // window.elevator.setTargetVelocity(0);
        // window.elevator.setTargetPosition(0);

        // for (int i = 0; i < 100; i++) {
        //     // TODO: Put your `update` code here
        //     window.calculatePhysics();
        // }
    }
}
