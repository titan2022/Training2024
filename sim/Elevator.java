package sim;

public class Elevator {
    private double targetPosition = 0;
    private double position = 0;
    private double targetVelocity = 0;
    private double velocity = 0;
    private double targetAcceleration = 0;
    private double acceleration = 0;
    private double input = 0;

    public double getInput() { return input; }
    public void setInput(double value) { input = value; }

    public double getTargetPosition() { return targetPosition; }
    public double getTargetVelocity() { return targetVelocity; }
    public double getMotorAcceleration() { return targetAcceleration; }
    public double getAcceleration() { return acceleration; }
    public double getVelocity() { return velocity; }
    public double getPosition() { return position; }

    public void setTargetPosition(double value) { targetPosition = Math.max(Math.min(value, 1.0), 0.0); }
    public void setTargetVelocity(double value) { targetVelocity = value; }
    public void setMotorAcceleration(double value) { targetAcceleration = value;}
    public void private_setPosition(double value) { position = value; }
    public void private_setVelocity(double value) { velocity = value; }
    public void private_setAcceleration(double value) { acceleration = Math.max(Math.min(value, 200.0), -200.0); }
    
    public void private_addVelocity(double value) { private_setVelocity(getVelocity() + value); }
    public void private_addAcceleration(double value) { private_setAcceleration(getAcceleration() + value); }
}
