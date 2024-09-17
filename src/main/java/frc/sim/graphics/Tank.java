/*
 * IGNORE THIS FILE
 */
package frc.sim.graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.TimerTask;

import javax.swing.*;

import frc.sim.CommandBase;
import frc.sim.TimedRobot;
import frc.sim.util.GVector;

import java.util.ArrayList;
import java.util.Timer;

public class Tank extends JPanel implements KeyListener {
    private final double friction = 0.9;

    private JFrame frame;
    private TimedRobot robot;
    private XboxController xbox;

    public GVector acc_buffer = new GVector(0, 0);
    public double rot_acc_buffer = 0;

    public GVector acc = new GVector(0, 0);
    public GVector vel = new GVector(0, 0);
    public GVector pos = new GVector(400, 300);

    public double rot_acc = 0;
    public double rot_vel = 0;
    public double rot_pos = 45;

    public boolean auton = false;
    public ArrayList<CommandBase> cmds = new ArrayList<CommandBase>();
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Robot body
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GREEN);
        Rectangle rect = new Rectangle((int)(pos.x - 40), (int)(pos.y - 40), 80, 80);

        Path2D.Double path = new Path2D.Double();
        path.append(rect, false);
        AffineTransform t = new AffineTransform();

        // Rotate robot
        t.rotate(Math.toRadians(rot_pos), pos.x, pos.y);
        path.transform(t);

        g2d.draw(path);
        g2d.fill(path);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    private void createAndShowGui() {
        frame = new JFrame("Tank Drive Sim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    private void calcPhysics() {
        acc = acc_buffer.copy();
        rot_acc = rot_acc_buffer;
        acc_buffer.set(0, 0);
        rot_acc_buffer = 0;

        vel.add(acc.timesI(0.02));
        pos.add(vel.rotateI(rot_pos).timesI(0.02));
        acc.times(friction);
        vel.times(friction);

        rot_vel += rot_acc * 0.02;
        rot_pos += rot_vel * 0.02;
        rot_acc *= friction;
        rot_vel *= friction;

        rot_pos = rot_pos % 360;
    }

    public Tank(TimedRobot robot) {
        this.robot = robot;
        this.xbox = robot.xbox;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               createAndShowGui();
            }
        });

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                if (frame != null) {
                    calcPhysics();
                    frame.repaint();

                    if (xbox != null) {
                        if (xbox.releasedX) {
                            xbox.releasedX = false;
                        }

                        robot.robotPeriodic();
                        if (!auton) {
                            robot.teleopPeriodic();
                        } else {
                            robot.autonomousPeriodic();
                        }

                        for (CommandBase cmd : cmds) {
                            if (cmd.firstRun) {
                                cmd.initialize();
                                cmd.firstRun = false;
                            }

                            if (!cmd.isFinished()) {
                                cmd.execute();
                            } else {
                                if (cmd.firstFinish) {
                                    cmd.end(false);
                                    cmd.firstFinish = false;
                                }
                            }
                        }
                    }

                    
                }
            }
        }, 0, 20);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xbox.rightXleft = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xbox.rightXright = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            xbox.rightYleft = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            xbox.rightYright = 1;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            xbox.leftXleft = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            xbox.leftXright = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            xbox.leftYleft = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            xbox.leftYleft = 1;
        }

        if (e.getKeyCode() == KeyEvent.VK_X) {
            xbox.pressedX = true;
            xbox.downX = true;
            xbox.releasedX = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xbox.rightXleft = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xbox.rightXright = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            xbox.rightYleft = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            xbox.rightYright = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            xbox.leftXleft = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            xbox.leftXright = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            xbox.leftYleft = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            xbox.leftYleft = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_X) {
            xbox.pressedX = false;
            xbox.downX = false;
            xbox.releasedX = true;
        }
    }
}
