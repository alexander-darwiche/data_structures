package graphics;

import javax.swing.JPanel;
import java.awt.*;

import datastructures.IStack;
import datastructures.LinkedListStack;

public class FractalTreePanel extends JPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final double LENGTH_FACTOR = 0.75;
    private final double ANGLE = Math.toRadians(25.0);

    public FractalTreePanel() {
        this.setPreferredSize(new Dimension(800, 600));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLUE);
        //drawTree(g);
    }

    protected void drawTree(Graphics g, int x, int y, double angle, int depth, double length) {
        Graphics2D g2d = (Graphics2D)g;
        GraphicsEnhancements.drawGrid(g2d, this.getWidth(), this.getHeight());

        IStack<Integer> another_stack = new LinkedListStack<>();



        if (depth != 0) {
            int x2 = x + (int)(Math.cos(angle) * length);
            int y2 = y + (int)(Math.sin(angle) * length);
            g.drawLine(x, y, x2, y2);
            this.drawTree(g, x2, y2, angle - this.ANGLE, depth - 1, length * 0.75);
            this.drawTree(g, x2, y2, angle + this.ANGLE, depth - 1, length * 0.75);
        }

        // TODO: Implement the iterative fractal tree drawing using LinkedListStack
        // 1. Initialize the stack with the base state of the tree (e.g., trunk position, length, and angle)
        // 2. Use a loop to **iteratively process** each element in the stack:
        //    a. Draw the current branch.
        //    b. Calculate and push new branches based on the current state.
        // 3. Continue until the stack is empty or a maximum depth is reached.

    }


    // TODO: circle place holder =
    private void drawCircle(Graphics g, int x, int y, int radius) {
        // Placeholder for drawing a simple circle
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}
