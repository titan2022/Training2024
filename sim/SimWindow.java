package sim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SimWindow extends JPanel implements KeyListener {
    private static double TIME_MULT = 1.0;
    private static double MAX_JERK = 5;
    private static double MAX_ACCELERATION = 50;

    private JFrame frame;
    public Elevator elevator = new Elevator();

    private double gravity = -9.8;
    private double dt = 0.02;

    private int[] rawInput = new int[] {0, 0, 0, 0}; // left right down up

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // static elevator part
        g.setColor(Color.BLUE);
        g.drawLine(
            (int)400,
            (int)100,
            (int)400,
            (int)500
        );

        // moving elevator part
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GREEN);
        Rectangle rect = new Rectangle(420, (int)(-elevator.getPosition() * 370.0) + 470, 30, 30);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        // g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    private void createAndShowGui() {
        frame = new JFrame("Control Theory Sim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    // very messy
    private long prevTime = System.nanoTime();
    public void calculatePhysics() {
        dt = TIME_MULT * (double)(System.nanoTime() - prevTime) / 1000000000.0;//0.02;
        PIDController.m_period = dt;
        prevTime = System.nanoTime();

        elevator.setInput(-rawInput[0] + rawInput[1]);

        double boundedTargetAccel = bound(elevator.getMotorAcceleration(), MAX_ACCELERATION);
        double newAccel = elevator.getAcceleration() + bound(boundedTargetAccel - elevator.getAcceleration(), MAX_JERK);
        elevator.private_setAcceleration(newAccel);

        elevator.private_addVelocity(elevator.getAcceleration() * dt);
        elevator.private_addVelocity(gravity * dt);

        double newPos = elevator.getPosition() + elevator.getVelocity() * dt;
        elevator.private_setPosition(Math.max(newPos, 0.0));

        if (newPos <= 0) {
            if (elevator.getAcceleration() <= 0.0) {
                elevator.private_setAcceleration(0);
            }

            if (elevator.getVelocity() <= 0.0) {
                elevator.private_setVelocity(0);
            }
        }








        // elevator.setTargetPosition((-rawInput[2] + rawInput[3]));
        // double newTargetVel = (-rawInput[0] + rawInput[1]) * 2.0;
        // if (Math.abs(newTargetVel) > 0.01) {
        //     elevator.setTargetVelocity(newTargetVel);
        // }

        // elevator.setTargetAcceleration(velocityPID.calculate(elevator.getVelocity(), elevator.getTargetVelocity()));

        // if (Math.abs(elevator.getTargetAcceleration()) < 500.0) {
        //     elevator.private_addAcceleration(10 * (elevator.getTargetAcceleration() - elevator.getAcceleration()) * dt);
        // } else {
        //     elevator.private_setAcceleration(elevator.getTargetAcceleration());
        // }

        // elevator.private_addVelocity(elevator.getAcceleration() * dt);
        // elevator.private_addVelocity(gravity * dt);

        // double newPos = elevator.getPosition() + elevator.getVelocity() * dt;
        // elevator.private_setPosition(Math.max(newPos, 0.0));

        // if (newPos <= 0) {
        //     if (elevator.getAcceleration() <= 0.0) {
        //         elevator.private_setAcceleration(0);
        //     }

        //     if (elevator.getVelocity() <= 0.0) {
        //         elevator.private_setVelocity(0);
        //     }
        // }
    }

    private double noise(double start, double end) {
        return Math.random() * (end - start) + start;
    }

    private double bound(double value, double bound) {
        return Math.max(Math.min(value, bound), -bound);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            rawInput[0] = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rawInput[1] = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rawInput[2] = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rawInput[3] = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            rawInput[0] = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rawInput[1] = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rawInput[2] = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rawInput[3] = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public SimWindow(PlaygroundBase playground, boolean isAutomated) {
        if (isAutomated) {
            playground.start(elevator);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGui();
                }
            });
    
            Timer timer = new Timer();
    
            playground.start(elevator);
            timer.schedule(new TimerTask() {
                public void run() {
                    if (frame != null) {
                        playground.update(elevator);
                        calculatePhysics();
                        frame.repaint();
                    }
                }
            }, 0, 20);
        }
    }
}
