/*
 * IGNORE THIS FILE
 */
package frc.sim.util;

public class GVector {
    public double x;
    public double y;

    public GVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public GVector add(GVector value) {
        this.x += value.x;
        this.y += value.y;
        return this;
    }

    public GVector subtract(GVector value) {
        this.x -= value.x;
        this.y -= value.y;
        return this;
    }

     public GVector times(double value) {
        this.x *= value;
        this.y *= value;
        return this;
    }

    public GVector timesI(double value) {
        return new GVector(x * value, y* value);
    }

    public double getMag() {
        return Math.sqrt(x * x + y * y);
    }

    public GVector rotateI(double theta) {
        double rad = Math.toRadians(theta);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        double new_x = cos * x - sin * y;
        double new_y = sin * x + cos * y;

        return new GVector(new_x, new_y);
    }

    public GVector copy() {
        return new GVector(x, y);
    }
}
