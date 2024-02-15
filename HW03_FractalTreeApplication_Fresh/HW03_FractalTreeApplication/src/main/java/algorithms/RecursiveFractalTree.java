package algorithms;

import javax.swing.*;
import java.awt.*;

public class RecursiveFractalTree extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final double LENGTH_FACTOR = 0.75; // Factor to reduce the branch length
    private final double ANGLE = Math.toRadians(25); // The angle of the branch split

    public RecursiveFractalTree() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    // Method to draw the tree recursively
    private void drawTree(Graphics2D g, int x, int y, double angle, int depth, double length) {
        if (depth == 0) return;

        // Calculate the end point of the branch
        int x2 = x + (int) (Math.cos(angle) * length);
        int y2 = y + (int) (Math.sin(angle) * length);

        // Draw the branch
        g.drawLine(x, y, x2, y2);

        // Recursive calls for the two new branches
        drawTree(g, x2, y2, angle - ANGLE, depth - 1, length * LENGTH_FACTOR);
        drawTree(g, x2, y2, angle + ANGLE, depth - 1, length * LENGTH_FACTOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        // Initial values for the root of the tree
        int x = WIDTH / 2;
        int y = HEIGHT;
        double angle = -Math.PI / 2; // Upwards
        //int depth = 5; // Number of recursive branch generations

        int depth = 12; // Number of recursive branch generations

        double length = HEIGHT / 4; // Initial branch length

        // Draw the tree
        drawTree(g2d, x, y, angle, depth, length);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("L-System Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new RecursiveFractalTree());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
