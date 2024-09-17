/*
 * IGNORE THIS FILE
 */
package frc.sim.graphics;

public class XboxController {
    public double leftXleft = 0;
    public double leftXright = 0;

    public double leftYleft = 0;
    public double leftYright = 0;
    
    public double rightXleft = 0;
    public double rightXright = 0;
    
    public double rightYleft = 0;
    public double rightYright = 0;

    public boolean downX = false;
    public boolean pressedX = false;
    public boolean releasedX = false;

    public XboxController(int port) {
        if (port != 0) {
            return;
        }
    }

    public double getLeftX() {
        return leftXleft + leftXright;
    }
    
    public double getLeftY() {
        return leftYleft + leftYright;
    }

    public double getRightX() {
        return rightXleft + rightXright;
    }

    public double getRightY() {
        return rightYleft + rightYright;
    }


    public boolean getXButton() {
        return downX;
    }

    public boolean getXButtonPressed() {
        return pressedX;
    }

    public boolean getXButtonReleased() {
        return releasedX;
    }
}
