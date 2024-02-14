package graphics;

import javax.swing.JPanel;
import java.awt.*;

import datastructures.IStack;
import datastructures.LinkedListStack;

public class FractalTreePanel extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final double LENGTH_FACTOR = 0.75; // Factor to reduce the branch length
    private final double ANGLE = Math.toRadians(25); // The angle of the branch split

    public FractalTreePanel() {
        // Set the preferred size of the panel (for example, 800x600)
        setPreferredSize(new Dimension(800, 600));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
    }

    protected void drawTree(Graphics g,int x, int y, double angle, int depth, double length) {
        Graphics2D g2d = (Graphics2D) g;

        // Draw grid
        GraphicsEnhancements.drawGrid(g2d, getWidth(), getHeight());

        // TODO: Implement the iterative fractal tree drawing using LinkedListStack
        // 1. Initialize the stack with the base state of the tree (e.g., trunk position, length, and angle)
        // 2. Use a loop to **iteratively process** each element in the stack:
        //    a. Draw the current branch.
        //    b. Calculate and push new branches based on the current state.
        // 3. Continue until the stack is empty or a maximum depth is reached.

        if (depth == 0) return;

        // Calculate the end point of the branch
        int x2 = x + (int) (Math.cos(angle) * length);
        int y2 = y + (int) (Math.sin(angle) * length);

        // Draw the branch
        g.drawLine(x, y, x2, y2);

        // Recursive calls for the two new branches
        drawTree(g, x2, y2, angle - ANGLE, depth - 1, length * LENGTH_FACTOR);
        drawTree(g, x2, y2, angle + ANGLE, depth - 1, length * LENGTH_FACTOR);


//        // TODO: placeholder - comment this out later.
//        // For now, as a placeholder, we draw a circle at the center of the panel.
//        // This is to demonstrate a simple drawing operation. Replace this with the actual tree drawing logic.
//        drawCircle(g, getWidth() / 2, getHeight() / 2, 50);


    }


    // TODO: circle place holder =
    private void drawCircle(Graphics g, int x, int y, int radius) {
        // Placeholder for drawing a simple circle
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}
