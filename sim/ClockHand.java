package sim;

public class ClockHand {
    private double targetAngle = 0; // In radians
    private double currentAngle = 0; // In radians
    private double velocity = 0; // In radians/s
    private double acceleration = 0; // In radians/s^2

    public double getTargetAngle() { return targetAngle; }
    public double getCurrentAngle() { return currentAngle; }
    public double getVelocity() { return velocity; }
    public double getAcceleration() { return acceleration; }

    public void setTargetAngle(double value) { targetAngle = value; }
    public void setCurrentAngle(double value) { currentAngle = value; }
    public void setVelocity(double value) { velocity = value; }
    public void setAcceleration(double value) { acceleration = value; }
    
    public void addCurrentAngle(double value) { currentAngle += value; }
    public void addVelocity(double value) { velocity += value; }
    public void addAcceleration(double value) { acceleration += value; }
}
