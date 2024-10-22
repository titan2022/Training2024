package sim;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sim.util.GVector;

public class SimWindow extends JPanel implements KeyListener {
    private JFrame frame;

    private double currentAngle_c = 0;
    private int[] rawInput = new int[] {0, 0, 0, 0};
    private GVector keyInput = new GVector(0, 0);

    private double gravity = -9.8;
    private double dt = 0.02;
    private ClockHand stick = new ClockHand();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // stick
        g.setColor(Color.BLUE);
        g.drawLine(
            (int)400,
            (int)300,
            (int)(400 + 100 * Math.cos(currentAngle_c)),
            (int)(300 + 100 * Math.sin(currentAngle_c))
        );

        // // Robot body
        // Graphics2D g2d = (Graphics2D)g;
        // g2d.setColor(Color.GREEN);
        // Rectangle rect = new Rectangle((int)(pos.x - 40), (int)(pos.y - 40), 80, 80);

        // Path2D.Double path = new Path2D.Double();
        // path.append(rect, false);
        // AffineTransform t = new AffineTransform();

        // // Rotate robot
        // t.rotate(Math.toRadians(rot_pos), pos.x, pos.y);
        // path.transform(t);

        // g2d.draw(path);
        // g2d.fill(path);
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

    private void calcPhysics() {
        keyInput.setX(-rawInput[0] + rawInput[1]);
        keyInput.setY(-rawInput[2] + rawInput[3]);
        stick.setTargetAngle(Math.atan2(keyInput.y, keyInput.x));

        stick.setAcceleration(0);
        stick.addVelocity(stick.getAcceleration() * dt);
        stick.addCurrentAngle(stick.getVelocity() * dt);
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

    public SimWindow(PeriodicBase periodic) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                if (frame != null) {
                    periodic.run(stick);
                    currentAngle_c = stick.getCurrentAngle();
                    calcPhysics();
                    frame.repaint();
                }
            }
        }, 0, 20);
    }
}
